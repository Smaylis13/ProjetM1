package fr.univtln.m1dapm.g3.g3vote.crypto.keygen;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by civars169 on 01/06/15.
 * copyright Christian
 */

/**
 * Génèration de clef AES 128 bits
 */
public class CKeyGenerator extends Random {

    private static final String TRANSFORMATION_STRING = "AES";
    private static final int SIZE = 128;
    private static final int SIZES = 16;

    private final SecretKey mPrivateKey;

    private final CKeyNumberGenerator mKeyNumberGenerator = new CKeyNumberGenerator();
    private final BigInteger mPublicKey = mKeyNumberGenerator.getPublicKey();
    //private final byte[] mPublicKey = randByte(new byte[SIZES]); // 16 bytes = 128 bits

    //partie spécifique au serveur
    private SecretKey mClef;
    //fin de la partie spécifique au serveur

    /**
     * Génère la clef privé automatiquement à sa construction
     */
    public CKeyGenerator() {
        this.mPrivateKey = keyGen();
    }

    public CKeyNumberGenerator getKeyNumberGenerator() {
        return mKeyNumberGenerator;
    }

    public SecretKey getPrivateKey() {
        return mPrivateKey;
    }

    public BigInteger getPublicKey() {
        return mPublicKey;
    }

    /**
     * Méthode de génération pour la clef privé AES 128 bits
     * @return Clef
     */
    private static SecretKey keyGen(){
        KeyGenerator lKeyGen;
        try {
            lKeyGen = KeyGenerator.getInstance(TRANSFORMATION_STRING);
            lKeyGen.init(SIZE);
            //System.out.println("clef (" + lClef.getAlgorithm() /*+ "," + lClef.getFormat()*/
            //        + ") : " + new String(lClef.getEncoded()));
            return lKeyGen.generateKey();
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
        //System.out.println(Arrays.toString(pG));
        byte[] lKey = pG;
        //System.out.println(Arrays.toString(lKey));
        MessageDigest lSha;
        try {
            lSha = MessageDigest.getInstance("SHA-1");
            lKey = lSha.digest(lKey);
            lKey = Arrays.copyOf(lKey, SIZES); // utilise les 128 premier bit
            return new SecretKeySpec(lKey, TRANSFORMATION_STRING);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    public byte[] randByte(byte[] bytes) {
        for (int i = 0, len = bytes.length; i < len; )
            for (int rnd = nextInt(),
                         n = Math.min(len - i, Integer.SIZE/Byte.SIZE);
                 n-- > 0; rnd >>= Byte.SIZE)
                bytes[i++] = (byte)rnd;
        return bytes;
    }
    */

    public SecretKey getClef() {
        return mClef;
    }

    public void setClef(SecretKeySpec pK){
        this.mClef = pK;
    }

    @Override
    public String toString() {
        return "CKeyGenerator{" +
                "mPrivateKey=" + mPrivateKey +
                ", mPublicKey=" + mPublicKey +
                ", mClef=" + mClef +
                '}';
    }
}
