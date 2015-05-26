package fr.univtln.madapm.votemanager.crypto.keygen;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
 */

/**
 * Génère une clef AES 128 bits
 */
public class CKeyGenerator {

    private static final String TRANSFORMATION_STRING = "AES";
    private static final int SIZE = 128;

    private SecretKey mClef;

    static SecureRandom mRmd = new SecureRandom();

    private static final BigInteger mP = BigInteger.probablePrime(SIZE, mRmd);
    private static final BigInteger mG = BigInteger.probablePrime(SIZE, mRmd);

    /**
     * Génère la clef automatiquement à sa construction
     */
    public CKeyGenerator() {
        this.mClef = keyGen();
    }

    public SecretKey getClef() {
        return mClef;
    }

    /**
     * Méthode de génération de clef AES 128 bits
     * @return Clef
     */
    static SecretKey keyGen(){
        KeyGenerator lKeyGen;
        try {
            lKeyGen = KeyGenerator.getInstance(TRANSFORMATION_STRING);
            lKeyGen.init(128);
            SecretKey lClef = lKeyGen.generateKey();
            System.out.println("clef (" + lClef.getAlgorithm() + "," /*+ lClef.getFormat()*/
                    + ") : " + new String(lClef.getEncoded()));
            return lClef;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fonction qui calcule une clef en fonction de paramètre spécifique
     * @param pG paramètre reçu
     * @return La clef calculé avec les deux paramètres
     */
    public SecretKeySpec specificKeyKeyGen(BigInteger pG) {
        System.out.println(mG);
        System.out.println(pG);
        BigInteger lBig = mG;
        lBig = lBig.and(pG);
        System.out.println(lBig);
        byte[] lKey = lBig.toByteArray();
        MessageDigest lSha;
        try {
            lSha = MessageDigest.getInstance("SHA-1");
            lKey = lSha.digest(lKey);
            lKey = Arrays.copyOf(lKey, 16); // utilise les 128 premier bit
            return new SecretKeySpec(lKey, TRANSFORMATION_STRING);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "CKeyGenerator{" +
                "mclef=" + mClef +
                '}';
    }
}
