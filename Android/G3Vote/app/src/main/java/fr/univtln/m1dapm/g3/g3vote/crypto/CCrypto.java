package fr.univtln.m1dapm.g3.g3vote.crypto;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import fr.univtln.m1dapm.g3.g3vote.crypto.aes.CAESCrypt;
import fr.univtln.m1dapm.g3.g3vote.crypto.aes.CAESFileCrypt;
import fr.univtln.m1dapm.g3.g3vote.crypto.keygen.CKeyGenerator;

/**
 * Created by civars169 on 01/06/15.
 * copyright Christian
 */

/**
 * Classe regroupant toute les opérations de cryptage.
 *
 * Côté appli.
 * Envoyer le paramètre de l'appli (sendKeyParam) au serveur et attendre la valeur de retour de serveur (receiveKeyParam).
 * Crypter et décrypter pour les messages avec le téléphone via la SecretKey qui
 * aura été placée dans la variable et récupérable via la méthode getKey du téléphone.
 * /!/ Faire attention que la SecretKey avec le téléphone n'ait pas déjà été établi.
 *
 * Les cryptage privé (juste pour l'appli comme pour les données personnels) se fait automatiquement avec
 * les méthodes encrypt, decrypt, encryptFile et decryptFile.
 */
public class CCrypto {

    private static final CAESCrypt AESCRYPT = new CAESCrypt();
    private static final CAESFileCrypt AESFILECRYPT = new CAESFileCrypt();

    private static final CKeyGenerator KEYGENERATOR = new CKeyGenerator();


    /**
     * Cryptage de données via la clef privé
     * @param pData Données à crypter
     */
    public byte[] encrypt(String pData){
        return AESCRYPT.encrypt(KEYGENERATOR.getPrivateKey(), pData);
    }

    /**
     * Décryptage de données via la clef privé
     * @param pCryptData Données à décrypter
     */
    public String decrypt(byte[] pCryptData){
        return AESCRYPT.decrypt(KEYGENERATOR.getPrivateKey(), pCryptData);
    }

    /**
     * Cryptage de donnée via la clef public générer avec le paramètre recu
     * @param pData Données à crypter
     * @param pSecretKeySpec clef public commune avec le téléphone
     */
    public byte[] publicEncrypt(String pData, SecretKeySpec pSecretKeySpec){
        return AESCRYPT.encrypt(pSecretKeySpec, pData);
    }

    /**
     * Décryptage de donnée via la clef public générer avec le paramètre recu
     * @param pSecretKeySpec clef public commune avec le téléphone
     * @param pCryptData Données à décrypter
     */
    public String publicDecrypt(SecretKeySpec pSecretKeySpec, byte[] pCryptData){
        return AESCRYPT.decrypt(pSecretKeySpec, pCryptData);
    }

    /**
     * Cryptage d'un fichier via clef privé avec le paramètre de l'autre machine
     * @param pPath Chemin interne au projet du fichier à crypter
     * @param pCible Chemin interne du nouveau fichier crypté
     */
    public void fileEncrypt(String pPath, String pCible){
        try {
            AESFILECRYPT.encrypterFichier(KEYGENERATOR.getPrivateKey(), pPath, pCible);
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
            AESFILECRYPT.decrypterFichier(KEYGENERATOR.getPrivateKey(), pPath, pCible);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui clacule la clef de cryptage spécifique après réception du paramètre du serveur.
     * Elle sera stocké dans la Map avec l'identifiant reçu.
     * @param pParam Paramètre reçu pour générer la clef de chiffrement commune
     */
    public void receiveKeyParam(BigInteger pParam){
        SecretKeySpec lK = KEYGENERATOR.specificKeyKeyGen(BigInteger.valueOf((long)
                (Math.pow(pParam.doubleValue(), KEYGENERATOR.getKeyNumberGenerator().getab())
                        % KEYGENERATOR.getKeyNumberGenerator().getPValue())).toByteArray());
        KEYGENERATOR.setClef(lK); //La conserve en mémoire
    }

    /**
     * Méthode qui retourne le paramètre à envoyer à l'autre machine
     * @return Paramètre à envoyer
     */
    public static BigInteger sendKeyParam(){
        return KEYGENERATOR.getPublicKey();
    }

    /**
     * Méthode qui retourne la Map dans laquelle sont stockées les clef public
     * @return Map des clef public correspondant aux identifiants
     */
    public SecretKey getKey(){
        return KEYGENERATOR.getClef();
    }
}
