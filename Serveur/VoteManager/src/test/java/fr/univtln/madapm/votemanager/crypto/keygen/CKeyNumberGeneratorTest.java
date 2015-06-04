package fr.univtln.madapm.votemanager.crypto.keygen;

import junit.framework.TestCase;

import java.util.UUID;

/**
 * Classe de test final de la crypto
 */
public class CKeyNumberGeneratorTest extends TestCase {

    private static final UUID UNIQUE_KEY = UUID.randomUUID();

    public void testCreateSpecificKey() throws Exception {

        /*String lData = "fhfhdijdrijiodyniognhtihvri,hieoeomhgvdril,hvioh";

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
        lCryptoServeur.receiveKeyParam(lCryptoAppli.sendKeyParam(), UNIQUE_KEY);//A
        lCryptoAppli.receiveKeyParam(lCryptoServeur.sendKeyParam(), UNIQUE_KEY);//B
        byte[] lCryptData2 = lCryptoServeur.publicEncrypt(lData,
                (SecretKeySpec) lCryptoServeur.getKeyMap().get(UNIQUE_KEY));
        System.out.println(Arrays.toString(lCryptData2) + "\n" + lCryptoServeur.getKeyMap().toString() +
                lCryptoServeur.getKeyMap().get(UNIQUE_KEY).equals(lCryptoAppli.getKeyMap().get(UNIQUE_KEY)) + "\n" +
                Arrays.toString(((SecretKeySpec) lCryptoServeur.getKeyMap().get(UNIQUE_KEY)).getEncoded()) +
                "\n" + Arrays.toString(((SecretKeySpec) lCryptoAppli.getKeyMap().get(UNIQUE_KEY)).getEncoded()));
        lData = lCryptoServeur.publicDecrypt((SecretKeySpec) lCryptoAppli.getKeyMap().get(UNIQUE_KEY), lCryptData2);
        System.out.println(lData);

        CKeyNumberGenerator cKeyNumberGenerator = new CKeyNumberGenerator();

        System.out.println(cKeyNumberGenerator.getPValue());

        System.out.println("Test terminé.");*/
    }
}