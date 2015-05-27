package fr.univtln.madapm.votemanager.crypto.keygen;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
 */

/**
 * Génère une clef AES 128 bits
 */
public class CKeyGenerator extends Random {

    private static final String TRANSFORMATION_STRING = "AES";
    private static final int SIZE = 128;

    private final SecretKey mClef;

    private final byte[] mPublicKey = randByte(new byte[16]); // 16 bytes = 128 bits

    private final double lval = Math.PI * Math.pow(2, 894);
    public BigInteger mClefInt = BigInteger.valueOf((int)Math.pow(2, (Math.pow(2, 1024) - Math.pow(2, 960) - 1 +
            Math.pow(2, 64) * (  lval + 129093 ))));

    //public byte[] mClefByte = DatatypeConverter.parseHexBinary(mClefString);
    //public long mClefInt = Long.parseLong(mClefHexa, 16);

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
    private static SecretKey keyGen(){
        KeyGenerator lKeyGen;
        try {
            lKeyGen = KeyGenerator.getInstance(TRANSFORMATION_STRING);
            lKeyGen.init(128);
            SecretKey lClef = lKeyGen.generateKey();
            System.out.println("clef (" + lClef.getAlgorithm() /*+ "," + lClef.getFormat()*/
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
    public SecretKeySpec specificKeyKeyGen(byte[] pG) {
        System.out.println(Arrays.toString(mPublicKey));
        System.out.println(Arrays.toString(pG));
        byte[] lBig = mPublicKey;
        //byte[] lKey = lBig.xor pG;
        byte[] lKey = mClef.getEncoded();
        System.out.println(Arrays.toString(lKey));
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

    public byte[] randByte(byte[] bytes) {
        for (int i = 0, len = bytes.length; i < len; )
            for (int rnd = nextInt(),
                         n = Math.min(len - i, Integer.SIZE/Byte.SIZE);
                 n-- > 0; rnd >>= Byte.SIZE)
                bytes[i++] = (byte)rnd;
        return bytes;
    }

    @Override
    public String toString() {
        return "CKeyGenerator{" +
                "mclef=" + mClef +
                '}';
    }
}
