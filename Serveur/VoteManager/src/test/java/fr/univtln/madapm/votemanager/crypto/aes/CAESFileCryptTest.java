package fr.univtln.madapm.votemanager.crypto.aes;

import fr.univtln.madapm.votemanager.crypto.keygen.CKeyGenerator;
import junit.framework.TestCase;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
 */

/**
 * Classe de test sur la génération de clef de cryptage privé et public
 */
public class CAESFileCryptTest extends TestCase {

    public void testEncrypterDecrypterFichier() throws Exception {
        String str = "Capitaine Kurk à Enterprise, vous me recevez?";
        System.out.println(str);

        CKeyGenerator keyGenerator = new CKeyGenerator();
        System.out.println(keyGenerator.getClef());

        CAESCrypt aesCrypt = new CAESCrypt();
        byte[] strc = aesCrypt.encrypt(keyGenerator.getPrivateKey(), str);
        System.out.println(aesCrypt.decrypt(keyGenerator.getPrivateKey(), strc));

        System.out.println("Test fichier");

        CAESFileCrypt aesFileCrypt = new CAESFileCrypt();
        try {
            aesFileCrypt.encrypterFichier(keyGenerator.getPrivateKey(), "/src/json/CVote.json", "/src/json/CVoteEncrypt.txt");
            aesFileCrypt.decrypterFichier(keyGenerator.getPrivateKey(), "/src/json/CVoteEncrypt.txt", "/src/json/CVoteDecrypt.json");
            System.out.println("test");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }

        System.out.println("fin du test");
    }
}