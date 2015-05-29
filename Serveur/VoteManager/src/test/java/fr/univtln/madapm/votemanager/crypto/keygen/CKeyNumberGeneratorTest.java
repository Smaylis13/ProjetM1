package fr.univtln.madapm.votemanager.crypto.keygen;

import fr.univtln.madapm.votemanager.crypto.CCrypto;
import junit.framework.TestCase;

import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Classe de test final de la crypto
 */
public class CKeyNumberGeneratorTest extends TestCase {

    public void testCreateSpecificKey() throws Exception {

        String str = "fhfhdijdrijiodyniognhtihvri,hieoeomhgvdril,hvioh";

        System.out.println(str);

        //crypto
        CCrypto crypto = new CCrypto();


        //Privé
        byte[] lv = crypto.encrypt(str);
        System.out.println(Arrays.toString(lv));
        str = crypto.decrypt(lv);

        System.out.println(str);

        crypto.fileEncrypt("/src/json/CVote.json", "/src/json/CVoteEncrypt.txt");
        crypto.fileDecrypt("/src/json/CVoteEncrypt.txt", "/src/json/CVoteDecrypt.json");

        //Public
        BigInteger lb = BigInteger.valueOf(1);

        crypto.reciveA(lb, 1);
        byte[] lvp = crypto.publicEncrypt(str, (SecretKeySpec) crypto.getKeyMap().get(1));
        System.out.println(Arrays.toString(lvp));
        //str = crypto.publicDecrypt(crypto.)

        System.out.println();
    }
}