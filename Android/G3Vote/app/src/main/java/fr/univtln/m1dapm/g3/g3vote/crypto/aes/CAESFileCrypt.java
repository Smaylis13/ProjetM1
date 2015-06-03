package fr.univtln.m1dapm.g3.g3vote.crypto.aes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by civars169 on 01/06/15.
 * copyright Christian
 */

/**
 * Classe contenant les méthodes de cryptage et décryptage pour les fichiers
 */
public class CAESFileCrypt {

    private final String mPathFile=new File("").getAbsolutePath();

    private static final String TRANSFORMATION_STRING = "AES";

    /**
     * Fonction d'encryptage des données d'un fichier vers un autre
     * @param pClef Clef secrète générer via CKeyGenerator
     * @param pSource Chemin du fichier à crypter
     * @param pCible Chemin du fichier qui contiendra les données cryptées
     * @return La fonction retour 1 si l'opération a réussit, 0 si elle a échoué
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public int encrypterFichier(SecretKey pClef, String pSource, String pCible)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException {
        return encrypterDecrypterFichier(Cipher.ENCRYPT_MODE, pClef, pSource, pCible);
    }

    /**
     * Fonction de décryptage des données d'un fichier vers un autre
     * @param pClef Clef secrète utilisé lors du cryptage
     * @param pSource Chemin du fichier à décrypter
     * @param pCible Chemin du fichier qui contiendra les données décryptées
     * @return La fonction retour 1 si l'opération a réussit, 0 si elle a échoué
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public int decrypterFichier(SecretKey pClef, String pSource, String pCible)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException {
        return encrypterDecrypterFichier(Cipher.DECRYPT_MODE, pClef, pSource, pCible);
    }

    /**
     * Fonction interne de cryptage/décryptage
     * @param pMode Défini si le mode est le cryptage ou décryptage
     * @param pClef Clef secrète de cryptage
     * @param pSource Chemin du fichier à traiter
     * @param pCible Chemin du fichier qui contiendra les données traitées
     * @return La fonction retour 1 si l'opération a réussit, 0 si elle a échoué
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    private int encrypterDecrypterFichier(int pMode, SecretKey pClef, String pSource, String pCible)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Cipher lCipher = Cipher.getInstance(TRANSFORMATION_STRING);
        lCipher.init(pMode, pClef);

        FileInputStream lFileInput = null;
        FileOutputStream lFileOutput = null;
        CipherInputStream lCipherInput;

        try {
            //System.out.println("Fichier "+mPathFile+pSource);
            //System.out.println("Résultat "+mPathFile+pCible);
            lFileInput = new FileInputStream(mPathFile+pSource);
            lCipherInput = new CipherInputStream(lFileInput, lCipher);
            lFileOutput = new FileOutputStream(mPathFile+pCible);
            byte[] lByte = new byte[8];
            int i = lCipherInput.read(lByte);
            while (i != -1) {
                lFileOutput.write(lByte, 0, i);
                i = lCipherInput.read(lByte);
            }
            return 1;

        } catch (IOException ioe) {
            if (lFileInput != null) {
                try {
                    lFileInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (lFileOutput != null) {
                try {
                    lFileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    return 0;
    }
}
