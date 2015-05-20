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

    private String mSlach = "\\";

    private String mPathFile=new File("").getAbsolutePath(); //TODO corriger le bug ici
    private String mPathFileLinux=mPathFile.replace(mSlach, "/");

    private static final String TRANSFORMATION_STRING = "AES";

    public void encrypterFichier(SecretKey pClef, String pSource, String pCible)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException {
        encrypterDecrypterFichier(Cipher.ENCRYPT_MODE, pClef, pSource, pCible);
    }

    public void decrypterFichier(SecretKey pClef, String pSource, String pCible)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException {
        encrypterDecrypterFichier(Cipher.DECRYPT_MODE, pClef, pSource, pCible);
    }

    private void encrypterDecrypterFichier(int pMode, SecretKey pClef, String pSource, String pCible)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Cipher lCipher = Cipher.getInstance(TRANSFORMATION_STRING);
        lCipher.init(pMode, pClef);

        FileInputStream lFilleInput = null;
        FileOutputStream lFilleOutput = null;
        CipherInputStream lCipherInput = null;

        try {
            System.out.println("oietnfrehvj");
            System.out.println(mPathFileLinux+pSource);
            System.out.println(mPathFileLinux+pCible);
            lFilleInput = new FileInputStream(mPathFileLinux+pSource);
            System.out.println(mPathFileLinux+pSource);
            lCipherInput = new CipherInputStream(lFilleInput, lCipher);
            lFilleOutput = new FileOutputStream(mPathFileLinux+pCible);
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