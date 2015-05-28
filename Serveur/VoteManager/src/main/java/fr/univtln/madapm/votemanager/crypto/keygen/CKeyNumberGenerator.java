package fr.univtln.madapm.votemanager.crypto.keygen;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by civars169 on 28/05/15.
 * copyright Christian
 */

/**
 * Gènère des clef spécifique via la librairy DH de crypto.spec
 */
public class CKeyNumberGenerator extends Random {

    private static final int SIZE = 128;

    private static final String mKeyHexa = "FFFFFFFF FFFFFFFF C90FDAA2 2168C234 C4C6628B 80DC1CD1" +
                                          " 29024E08 8A67CC74 020BBEA6 3B139B22 514A0879 8E3404DD" +
                                          " EF9519B3 CD3A431B 302B0A6D F25F1437 4FE1356D 6D51C245" +
                                          " E485B576 625E7EC6 F44C42E9 A637ED6B 0BFF5CB6 F406B7ED" +
                                          " EE386BFB 5A899FA5 AE9F2411 7C4B1FE6 49286651 ECE65381" +
                                          " FFFFFFFF FFFFFFFF";

    private static final BigInteger mKey = new BigInteger(mKeyHexa.replace(" ",""), 16);
    private final static int mPValue = (int)Math.pow(2, (Math.pow(2, 1024) - Math.pow(2, 960) - 1 +
                                           Math.pow(2, 64) * (  Math.PI * Math.pow(2, 894) + 129093 )));
    private final static int mGValue = 2;
    private final static int mNumber = (int) (Math.random() * (0 - 100));

    private Random mrnd = new Random();

    private BigInteger mP = BigInteger.probablePrime(SIZE, mrnd);
    private BigInteger mG = BigInteger.probablePrime(SIZE, mrnd);

    public final byte[] mPublicKey = BigInteger.valueOf((long) Math.pow(mGValue, mNumber) % mPValue).toByteArray();

}
