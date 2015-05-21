package fr.univtln.madapm.votemanager.crypto;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
 */

/**
 * Classe de test pour la cryptographie
 */
public class CTestCrypto {
    public static void main(String[] args) {

        String str = "Capitaine Kurk à Enterprise, vous me recevez?";
        System.out.println(str);

        CKeyGenerator keyGenerator = new CKeyGenerator();
        System.out.println(keyGenerator.getClef());

        CAESCrypt aesCrypt = new CAESCrypt();
        byte[] strc = aesCrypt.encrypt(keyGenerator.getClef(), str);
        System.out.println(aesCrypt.decrypt(keyGenerator.getClef(), strc));

        System.out.println("Test fichier");

        CAESFileCrypt aesFileCrypt = new CAESFileCrypt();
        try {
            aesFileCrypt.encrypterFichier(keyGenerator.getClef(), "/src/json/CVote.json", "/src/json/CVoteEncrypt.txt");
            aesFileCrypt.decrypterFichier(keyGenerator.getClef(), "/src/json/CVoteEncrypt.txt", "/src/json/CVoteDecrypt.json");
            System.out.println("test");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }

        System.out.println("fin du test");

    }
}
