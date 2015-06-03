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

        String lData = "fhfhdijdrijiodyniognhtihvri,hieoeomhgvdril,hvioh";

        System.out.println(lData + "\n" + Arrays.toString(lData.getBytes()));

        //Crypto
        CCrypto lCryptoServeur = new CCrypto();
        CCrypto lCryptoAppli = new CCrypto();


        //Privé
        byte[] lCryptData = lCryptoServeur.encrypt(lData);
        System.out.println(Arrays.toString(lCryptData));
        System.out.println(lCryptoServeur.decrypt(lCryptData));

        lCryptoServeur.fileEncrypt("/src/json/CVote.json", "/src/json/CVoteEncrypt.txt");
        lCryptoServeur.fileDecrypt("/src/json/CVoteEncrypt.txt", "/src/json/CVoteDecrypt.json");

        //Public
        lCryptoServeur.receiveKeyParam(lCryptoAppli.sendKeyParam(), 1);//A
        lCryptoAppli.receiveKeyParam(lCryptoServeur.sendKeyParam(), 1);//B
        byte[] lCryptData2 = lCryptoServeur.publicEncrypt(lData, (SecretKeySpec) lCryptoServeur.getKeyMap().get(1));
        System.out.println(Arrays.toString(lCryptData2) + "\n" + lCryptoServeur.getKeyMap().toString() +
                lCryptoServeur.getKeyMap().get(1).equals(lCryptoAppli.getKeyMap().get(1)) + "\n" +
                Arrays.toString(((SecretKeySpec) lCryptoServeur.getKeyMap().get(1)).getEncoded()) +
                "\n" + Arrays.toString(((SecretKeySpec) lCryptoAppli.getKeyMap().get(1)).getEncoded()));
        lData = lCryptoServeur.publicDecrypt((SecretKeySpec) lCryptoAppli.getKeyMap().get(1), lCryptData2);
        System.out.println(lData);

        CKeyNumberGenerator cKeyNumberGenerator = new CKeyNumberGenerator();

        System.out.println(cKeyNumberGenerator.getPValue());

        System.out.println("Test terminé.");
    }
}