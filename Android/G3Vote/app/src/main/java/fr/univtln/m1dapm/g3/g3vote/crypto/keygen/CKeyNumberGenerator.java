package fr.univtln.m1dapm.g3.g3vote.crypto.keygen;

import android.util.Log;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by civars169 on 01/06/15.
 * copyright Christian
 */

/**
 * Gènère des paramètres et clef spécifiques
 */
public class CKeyNumberGenerator extends Random {

    private static final int SIZE = 128;

    private static final String KEYHEXA = "FFFFFFFF FFFFFFFF C90FDAA2 2168C234 C4C6628B 80DC1CD1" +
                                          " 29024E08 8A67CC74 020BBEA6 3B139B22 514A0879 8E3404DD" +
                                          " EF9519B3 CD3A431B 302B0A6D F25F1437 4FE1356D 6D51C245" +
                                          " E485B576 625E7EC6 F44C42E9 A637ED6B 0BFF5CB6 F406B7ED" +
                                          " EE386BFB 5A899FA5 AE9F2411 7C4B1FE6 49286651 ECE65381" +
                                          " FFFFFFFF FFFFFFFF";

    private final static Random mRandomParam = new Random();

    private final static BigInteger sKEY = new BigInteger(KEYHEXA.replace(" ",""), 16);
    private final static int sPrimeVALUE = (int)(Math.pow(2, 1024) - Math.pow(2, 960) - 1 +
                                           Math.pow(2, 64) * (  Math.PI * Math.pow(2, 894) + 129093 ));
    private final static int sGeneratorVALUE = 2;
    private final static int sPetitParam = (int) (1000 + mRandomParam.nextInt(10000 - 1000));

    private final BigInteger mPrime = BigInteger.probablePrime(SIZE, mRandomParam);
    private final BigInteger mGenerator = BigInteger.probablePrime(SIZE, mRandomParam);
    private final BigInteger mKeyParam=BigInteger.valueOf(sGeneratorVALUE).pow(sPetitParam).mod(BigInteger.valueOf(sPrimeVALUE));

    //private final BigInteger mKeyParam = BigInteger.valueOf((long) Math.pow(sGeneratorVALUE, sPetitParam) % sPrimeVALUE);

    public int getPValue() {
        return sPrimeVALUE;
    }

    public BigInteger getPublicKey() {
        return mKeyParam;
    }

    public int getab() {
        return sPetitParam;
    }

    @Override
    public String toString() {
        return "CKeyNumberGenerator{" +
                "hexa=" + sKEY +
                "mRandomPram=" + mRandomParam +
                ", mPrime=" + mPrime +
                ", mGenerator=" + mGenerator +
                ", mKeyParam=" + mKeyParam +
                '}';
    }
}
