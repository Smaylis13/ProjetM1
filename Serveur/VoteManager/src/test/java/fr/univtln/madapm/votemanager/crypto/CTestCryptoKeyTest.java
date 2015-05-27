package fr.univtln.madapm.votemanager.crypto;

import fr.univtln.madapm.votemanager.crypto.aes.CAESCrypt;
import fr.univtln.madapm.votemanager.crypto.keygen.CKeyGenerator;
import junit.framework.TestCase;

import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by civars169 on 21/05/15.
 * copyright Christian
 */

/**
 * Classe de test sur la génération de clef de cryptage privé et public avec paramètres
 */
public class CTestCryptoKeyTest extends TestCase {

    public void testMain() throws Exception {
        System.out.println("Test de clef à échanger avec l'appli");


        String str = "eafusfgigfyugfrdi+-*/)àé'(çà-èçà&²<!:;,*ùù$";

        CKeyGenerator keyGenerator = new CKeyGenerator();
        System.out.println(keyGenerator);
        SecretKeySpec clef = keyGenerator.specificKeyKeyGen(BigInteger.probablePrime(128, new SecureRandom()));

        CAESCrypt aesCrypt = new CAESCrypt();
        byte[] cyphertext = aesCrypt.encrypt(clef, str);

        System.out.println(aesCrypt.decrypt(clef, cyphertext));

        System.out.println(keyGenerator.mClefInt);


        System.out.println("Fin du test");
    }
}