package fr.univtln.madapm.votemanager.crypto;

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

    private static final CKeyNumberGenerator mKeyNumberGenerator = new CKeyNumberGenerator();
    static SecureRandom mRmd = new SecureRandom();

    private static final BigInteger mP = BigInteger.probablePrime(SIZE, mRmd);
    private static final BigInteger mG = BigInteger.probablePrime(SIZE, mRmd);
    private static final BigInteger mA = new BigInteger(Integer.toString(mKeyNumberGenerator.getA()));
    private static final BigInteger mB = new BigInteger(Integer.toString(mKeyNumberGenerator.getB()));

    /**private
     * Génère la clef automatiquement à sa construction
     */
    public CKeyGenerator() {
        this.mClef = keyGen();
    }

    public SecretKey getClef() {
        return mClef;
    }

    public static CKeyNumberGenerator getKeyNumberGenerator() {
        return mKeyNumberGenerator;
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
            System.out.println("clef (" + lClef.getAlgorithm() + "," + lClef.getFormat()
                    + ") : " + new String(lClef.getEncoded()));
            return lClef;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fonction qui calcule une clef en fonction de paramètre spécifique
     * @param pP Premier paramètre
     * @param pG Deuxième paramètre
     * @return La clef calculé avec les deux paramètres
     */
    public SecretKeySpec specificKeyKeyGen(int pP, int pG) {
        byte[] lKey = bigIntToByteArray(pP+pG);
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

    /**
     * Fonction qui converti un int en byte[]
     * @param pi Tnteger
     * @return Tableau de byte
     */
    private byte[] bigIntToByteArray(final int pi ) {
        BigInteger lBigInt = BigInteger.valueOf(pi);
        return lBigInt.toByteArray();
    }

    @Override
    public String toString() {
        return "CKeyGenerator{" +
                "mclef=" + mClef +
                '}';
    }
}
