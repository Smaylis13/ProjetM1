package fr.univtln.madapm.votemanager.crypto;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
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
            aesFileCrypt.encrypterFichier(keyGenerator.getClef(), "/json/CVote.json", "/json/CVoteEncrypt.txt");
            aesFileCrypt.decrypterFichier(keyGenerator.getClef(), "/json/CVoteEncrypt.txt", "/json/CVoteDecrypt.json");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        System.out.println("http://www.jmdoudoux.fr/java/dej/chap-jce.htm");
    }
}
