package fr.univtln.madapm.votemanager.crypto.keygen;

import fr.univtln.madapm.votemanager.crypto.CCrypto;
import junit.framework.TestCase;

import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

/**
 * Classe de test final de la crypto
 */
public class CKeyNumberGeneratorTest extends TestCase {

    public void testCreateSpecificKey() throws Exception {

        String str = "fhfhdijdrijiodyniognhtihvri,hieoeomhgvdril,hvioh";

        System.out.println(str + "\n" + Arrays.toString(str.getBytes()));

        //crypto
        CCrypto crypto = new CCrypto();
        CCrypto cryptoAppli = new CCrypto();


        //Privé
        byte[] lv = crypto.encrypt(str);
        System.out.println(Arrays.toString(lv));
        System.out.println(crypto.decrypt(lv));

        crypto.fileEncrypt("/src/json/CVote.json", "/src/json/CVoteEncrypt.txt");
        crypto.fileDecrypt("/src/json/CVoteEncrypt.txt", "/src/json/CVoteDecrypt.json");

        //Public
        crypto.receiveA(cryptoAppli.sendA(), 1);//A
        cryptoAppli.receiveA(crypto.sendA(), 1);//B
        byte[] lvp1 = crypto.publicEncrypt(str, (SecretKeySpec) crypto.getKeyMap().get(1));
        System.out.println(Arrays.toString(lvp1) + "\n" + crypto.getKeyMap().toString() +
                crypto.getKeyMap().get(1).equals(cryptoAppli.getKeyMap().get(1)) + "\n" +
                Arrays.toString(((SecretKeySpec) crypto.getKeyMap().get(1)).getEncoded()) +
                "\n" + Arrays.toString(((SecretKeySpec) cryptoAppli.getKeyMap().get(1)).getEncoded()));
        str = crypto.publicDecrypt((SecretKeySpec) cryptoAppli.getKeyMap().get(1), lvp1);
        System.out.println(str);

        System.out.println("Test terminé.");
    }
}