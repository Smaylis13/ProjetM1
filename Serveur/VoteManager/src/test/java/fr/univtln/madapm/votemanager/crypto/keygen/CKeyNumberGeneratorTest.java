package fr.univtln.madapm.votemanager.crypto.keygen;

import fr.univtln.madapm.votemanager.crypto.aes.CAESCrypt;
import junit.framework.TestCase;

public class CKeyNumberGeneratorTest extends TestCase {

    public void testCreateSpecificKey() throws Exception {

        String str = "fhfhdijdrijiodyniognhtihvri,hieoeomhgvdril,hvioh";

        System.out.println(str);

        CAESCrypt aesCrypt = new CAESCrypt();
        CKeyGenerator keyGenerator = new CKeyGenerator();
        CKeyNumberGenerator numberGenerator = new CKeyNumberGenerator();

        byte[] cipher = aesCrypt.encrypt(keyGenerator.specificKeyKeyGen(numberGenerator.mPublicKey), str);
        str = aesCrypt.decrypt(keyGenerator.specificKeyKeyGen(numberGenerator.mPublicKey), cipher);

        System.out.println(str);

        System.out.println();
    }
}