package fr.univtln.madapm.votemanager.crypto;

import fr.univtln.madapm.votemanager.crypto.aes.CAESCrypt;
import fr.univtln.madapm.votemanager.crypto.aes.CAESFileCrypt;
import fr.univtln.madapm.votemanager.crypto.keygen.CKeyGenerator;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
 */
public class CCrypto {

    private static final CAESCrypt mAesCrypt = new CAESCrypt();
    private static final CAESFileCrypt mAesFileCrypt = new CAESFileCrypt();

    private static final CKeyGenerator mKeyGenerator = new CKeyGenerator();

    public static CAESCrypt getAesCrypt() {
        return mAesCrypt;
    }

    public static CAESFileCrypt getAesFileCrypt() {
        return mAesFileCrypt;
    }

    public static CKeyGenerator getKeyGenerator() {
        return mKeyGenerator;
    }


    /**
     * Cryptage de données via la clef privé
     * @param pstr Données à crypter
     */
    public void encrypt(String pstr){
        mAesCrypt.encrypt(mKeyGenerator.getClef(), pstr);
    }

    /**
     * Décryptage de données via la clef privé
     * @param pBytes Données à décrypter
     */
    public void decrypt(byte[] pBytes){
        mAesCrypt.decrypt(mKeyGenerator.getClef(), pBytes);
    }

    /**
     * Cryptage de donnée via la clef public générer avec le paramètre recu
     * @param pstr Données à crypter
     * @param pBigInteger Paramètre reçu par l'autre machine
     */
    public void publicEncrypt(String pstr, byte[] pBigInteger){
        mAesCrypt.encrypt(mKeyGenerator.specificKeyKeyGen(pBigInteger), pstr);
    }

    /**
     * Décryptage de donnée via la clef public générer avec le paramètre recu
     * @param pBytes Données à décrypter
     * @param pBigInteger Paramètre reçu par l'autre machine
     */
    public void publicDecrypt(byte[] pBytes, byte[] pBigInteger){
        mAesCrypt.decrypt(mKeyGenerator.specificKeyKeyGen(pBigInteger), pBytes);
    }

    /**
     * Cryptage d'un fichier via clef public avec le paramètre de l'autre machine
     * @param pPath Chemin interne au projet du fichier à crypter
     * @param pCible Chemin interne du nouveau fichier crypté
     * @param pPublicParamKey Paramètre de l'autre machine pour générer la clef public commune
     */
    public void fileEncrypt(String pPath, String pCible, byte[] pPublicParamKey){
        try {
            mAesFileCrypt.encrypterFichier(mKeyGenerator.specificKeyKeyGen(pPublicParamKey), pPath, pCible);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Décryptage d'un fichier via clef public avec le paramètre de l'autre machine
     * @param pPath Chemin interne au projet du fichier à crypter
     * @param pCible Chemin interne du nouveau fichier crypté
     * @param pPublicParamKey Paramètre de l'autre machine pour générer la clef public commune
     */
    public void fileDecrypt(String pPath, String pCible, byte[] pPublicParamKey){
        try {
            mAesFileCrypt.decrypterFichier(mKeyGenerator.specificKeyKeyGen(pPublicParamKey), pPath, pCible);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
