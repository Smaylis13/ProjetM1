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

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
 */

/**
 * Classe regroupant toute les opérations de cryptage.
 *
 * Côté serveur.
 * Attendre le paramètre envoyé par l'appli (receiveA) et renvoyer la valeur de retour à l'appli.
 * Crypter et décrypter pour les messages avec le téléphone via la SecretKey qui
 * aura été placée dans la Map via l'identifiant du téléphone reçu avec le paramètre.
 * /!/ Faire attention que la SecretKey avec le téléphone n'ait pas déjà été établi.
 *
 * Les cryptage privé (juste pour le serveur comme pour les password) se fait automatiquement avec
 * les méthodes encrypt, decrypt, encryptFile et decryptFile.
 */
public class CCrypto {

    private static final CAESCrypt mAesCrypt = new CAESCrypt();
    private static final CAESFileCrypt mAesFileCrypt = new CAESFileCrypt();

    private static final CKeyGenerator mKeyGenerator = new CKeyGenerator();


    /**
     * Cryptage de données via la clef privé
     * @param pstr Données à crypter
     */
    public byte[] encrypt(String pstr){
        return mAesCrypt.encrypt(mKeyGenerator.getPrivateKey(), pstr);
    }

    /**
     * Décryptage de données via la clef privé
     * @param pBytes Données à décrypter
     */
    public String decrypt(byte[] pBytes){
        return mAesCrypt.decrypt(mKeyGenerator.getPrivateKey(), pBytes);
    }

    /**
     * Cryptage de donnée via la clef public générer avec le paramètre recu
     * @param pstr Données à crypter
     * @param pSecretKeySpec clef public commune avec le téléphone
     */
    public byte[] publicEncrypt(String pstr, SecretKeySpec pSecretKeySpec){
        return mAesCrypt.encrypt(pSecretKeySpec, pstr);
    }

    /**
     * Décryptage de donnée via la clef public générer avec le paramètre recu
     * @param pSecretKeySpec clef public commune avec le téléphone
     * @param pBytes Données à décrypter
     */
    public String publicDecrypt(SecretKeySpec pSecretKeySpec, byte[] pBytes){
        return mAesCrypt.decrypt(pSecretKeySpec, pBytes);
    }

    /**
     * Cryptage d'un fichier via clef privé avec le paramètre de l'autre machine
     * @param pPath Chemin interne au projet du fichier à crypter
     * @param pCible Chemin interne du nouveau fichier crypté
     */
    public void fileEncrypt(String pPath, String pCible){
        try {
            mAesFileCrypt.encrypterFichier(mKeyGenerator.getPrivateKey(), pPath, pCible);
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
            mAesFileCrypt.decrypterFichier(mKeyGenerator.getPrivateKey(), pPath, pCible);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui clacule la clef de cryptage spécifique après réception du paramètre de l'apppli
     * en renvoyant son paramètre pour que l'aplli puisse elle aussi optenir la même.
     * Elle sera stocké dans la Map avec l'identifiant reçu.
     * @param pA Paramètre reçu pour générer la clef de chiffrement commune
     * @param pId Identifiant de l'appli
     * @return Paramètre à envoyer à l'appli
     */
    public BigInteger receiveA(BigInteger pA, int pId){
        SecretKeySpec lK = mKeyGenerator.specificKeyKeyGen(BigInteger.valueOf((long)
                (Math.pow(pA.doubleValue(), mKeyGenerator.getKeyNumberGenerator().getab())
                        %mKeyGenerator.getKeyNumberGenerator().getPValue())).toByteArray());
        mKeyGenerator.getClef().put(pId, lK); //La conserve en mémoire dans une Map
        return mKeyGenerator.getPublicKey();
    }

    /**
     * Méthode qui retourne le paramètre à envoyer à l'autre machine
     * @return Paramètre à envoyer
     */
    public BigInteger sendA(){
        return mKeyGenerator.getPublicKey();
    }

    /**
     * Méthode qui retourne la Map dans laquelle sont stockées les clef public
     * @return Map des clef public correspondant aux identifiants
     */
    public Map getKeyMap(){
        return mKeyGenerator.getClef();
    }
}
