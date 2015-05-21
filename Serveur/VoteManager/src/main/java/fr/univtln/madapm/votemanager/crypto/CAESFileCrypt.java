package fr.univtln.madapm.votemanager.crypto;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
 */

/**
 * Classe contenant les méthodes de cryptage et décryptage pour les fichiers
 */
public class CAESFileCrypt {

    private final String mSlach = "\\";

    private final String mPathFile=new File("").getAbsolutePath();
    private final String mPathFileLinux=mPathFile.replace(mSlach, "/");

    private static final String TRANSFORMATION_STRING = "AES";

    /**
     * Fonction d'encryptage des données d'un fichier vers un autre
     * @param pClef Clef secrète générer via CKeyGenerator
     * @param pSource Chemin du fichier à crypter
     * @param pCible Chemin du fichier qui contiendra les données cryptées
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public void encrypterFichier(SecretKey pClef, String pSource, String pCible)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException {
        encrypterDecrypterFichier(Cipher.ENCRYPT_MODE, pClef, pSource, pCible);
    }

    /**
     * Fonction de décryptage des données d'un fichier vers un autre
     * @param pClef Clef secrète utilisé lors du cryptage
     * @param pSource Chemin du fichier à décrypter
     * @param pCible Chemin du fichier qui contiendra les données décryptées
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public void decrypterFichier(SecretKey pClef, String pSource, String pCible)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException {
        encrypterDecrypterFichier(Cipher.DECRYPT_MODE, pClef, pSource, pCible);
    }

    /**
     * Fonction interne de cryptage/décryptage
     * @param pMode Défini si le mode est le cryptage ou décryptage
     * @param pClef Clef secrète de cryptage
     * @param pSource Chemin du fichier à traiter
     * @param pCible Chemin du fichier qui contiendra les données traitées
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    private void encrypterDecrypterFichier(int pMode, SecretKey pClef, String pSource, String pCible)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Cipher lCipher = Cipher.getInstance(TRANSFORMATION_STRING);
        lCipher.init(pMode, pClef);

        FileInputStream lFilleInput = null;
        FileOutputStream lFilleOutput = null;
        CipherInputStream lCipherInput;

        try {
            System.out.println("oietnfrehvj");
            System.out.println(mPathFile+pSource);
            System.out.println(mPathFile+pCible);
            lFilleInput = new FileInputStream(mPathFile+pSource);
            System.out.println(mPathFileLinux+pSource);
            System.out.println("sieovyndghud");
            lCipherInput = new CipherInputStream(lFilleInput, lCipher);
            lFilleOutput = new FileOutputStream(mPathFile+pCible);
            System.out.println(mPathFileLinux+pCible);
            byte[] lb = new byte[8];
            int li = lCipherInput.read(lb);
            while (li != -1) {
                lFilleOutput.write(lb, 0, li);
                li = lCipherInput.read(lb);
            }
        } catch (IOException ioe) {
            if (lFilleInput != null) {
                try {
                    lFilleInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (lFilleOutput != null) {
                try {
                    lFilleOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
