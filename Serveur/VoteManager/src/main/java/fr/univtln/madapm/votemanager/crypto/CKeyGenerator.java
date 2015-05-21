package fr.univtln.madapm.votemanager.crypto;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Created by civars169 on 20/05/15.
 * copyright Christian
 */

/**
 * Génère une clef AES 128 bits
 */
public class CKeyGenerator {

    private static final String TRANSFORMATION_STRING = "AES";

    private SecretKey mClef;

    /**
     * Génère la clef automatiquement à sa construction
     */
    public CKeyGenerator() {
        this.mClef = this.keyGen();
    }

    public SecretKey getClef() {
        return mClef;
    }

    /**
     * Méthode de génération de clef AES 128 bits
     * @return clef
     */
    SecretKey keyGen(){
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

    @Override
    public String toString() {
        return "CKeyGenerator{" +
                "mclef=" + mClef +
                '}';
    }
}

//http://www.java2s.com/Tutorial/Java/0490__Security/ImplementingtheDiffieHellmankeyexchange.htm
