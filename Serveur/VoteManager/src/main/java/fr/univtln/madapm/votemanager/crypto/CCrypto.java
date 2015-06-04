package fr.univtln.madapm.votemanager.crypto;

import fr.univtln.madapm.votemanager.crypto.aes.CAESCrypt;
import fr.univtln.madapm.votemanager.crypto.aes.CAESFileCrypt;
import fr.univtln.madapm.votemanager.crypto.keygen.CKeyGenerator;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
 */

/**
 * Classe regroupant toute les opérations de cryptage.
 *
 * Côté serveur.
 * Attendre le paramètre envoyé par l'appli (receiveKeyParam) et renvoyer la valeur de retour à l'appli.
 * Crypter et décrypter pour les messages avec le téléphone via la SecretKey qui
 * aura été placée dans la Map via l'identifiant du téléphone reçu avec le paramètre.
 * /!/ Faire attention que la SecretKey avec le téléphone n'ait pas déjà été établi.
 *
 * Les cryptage privé (juste pour le serveur comme pour les password) se fait automatiquement avec
 * les méthodes encrypt, decrypt, encryptFile et decryptFile.
 */
public class CCrypto {

    private static final CAESCrypt sAesCrypt = new CAESCrypt();
    private static final CAESFileCrypt sAesFileCrypt = new CAESFileCrypt();

    private static final CKeyGenerator sKeyGenerator = new CKeyGenerator();


    /**
     * Cryptage de données via la clef privé
     * @param pData Données à crypter
     */
    public byte[] encrypt(String pData){
        return sAesCrypt.encrypt(sKeyGenerator.getPrivateKey(), pData);
    }

    /**
     * Décryptage de données via la clef privé
     * @param pCryptData Données à décrypter
     */
    public String decrypt(byte[] pCryptData){
        return sAesCrypt.decrypt(sKeyGenerator.getPrivateKey(), pCryptData);
    }

    /**
     * Cryptage de donnée via la clef public générer avec le paramètre recu
     * @param pData Données à crypter
     * @param pSecretKeySpec clef public commune avec le téléphone
     */
    public byte[] publicEncrypt(String pData, SecretKeySpec pSecretKeySpec){
        return sAesCrypt.encrypt(pSecretKeySpec, pData);
    }

    /**
     * Décryptage de donnée via la clef public générer avec le paramètre recu
     * @param pSecretKeySpec clef public commune avec le téléphone
     * @param pCryptData Données à décrypter
     */
    public String publicDecrypt(SecretKeySpec pSecretKeySpec, byte[] pCryptData){
        return sAesCrypt.decrypt(pSecretKeySpec, pCryptData);
    }

    /**
     * Cryptage d'un fichier via clef privé avec le paramètre de l'autre machine
     * @param pPath Chemin interne au projet du fichier à crypter
     * @param pCible Chemin interne du nouveau fichier crypté
     */
    public void fileEncrypt(String pPath, String pCible){
        try {
            sAesFileCrypt.encrypterFichier(sKeyGenerator.getPrivateKey(), pPath, pCible);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Décryptage d'un fichier via clef public avec le paramètre de l'autre machine
     * @param pPath Chemin interne au projet du fichier à crypter
     * @param pCible Chemin interne du nouveau fichier crypté
     */
    public void fileDecrypt(String pPath, String pCible){
        try {
            sAesFileCrypt.decrypterFichier(sKeyGenerator.getPrivateKey(), pPath, pCible);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui clacule la clef de cryptage spécifique après réception du paramètre de l'apppli
     * en renvoyant son paramètre pour que l'aplli puisse elle aussi optenir la même.
     * Elle sera stocké dans la Map avec l'identifiant reçu.
     * @param pParam Paramètre reçu pour générer la clef de chiffrement commune
     * @param pId Identifiant de l'appli
     * @return Paramètre à envoyer à l'appli
     */
    public BigInteger receiveKeyParam(BigInteger pParam, UUID pId){
        SecretKeySpec lK = sKeyGenerator.specificKeyKeyGen(BigInteger.valueOf((long)
                (Math.pow(pParam.doubleValue(), sKeyGenerator.getKeyNumberGenerator().getab())
                        % sKeyGenerator.getKeyNumberGenerator().getPValue())).toByteArray());
        sKeyGenerator.getClef().put(pId, lK); //La conserve en mémoire dans une Map
        return sKeyGenerator.getPublicKey();
    }

    /**
     * Méthode qui retourne le paramètre à envoyer à l'autre machine
     * @return Paramètre à envoyer
     */
    public BigInteger sendKeyParam(){
        return sKeyGenerator.getPublicKey();
    }

    /**
     * Méthode qui retourne la Map dans laquelle sont stockées les clef public
     * @return Map des clef public correspondant aux identifiants
     */
    public Map getKeyMap(){
        return sKeyGenerator.getClef();
    }
}
