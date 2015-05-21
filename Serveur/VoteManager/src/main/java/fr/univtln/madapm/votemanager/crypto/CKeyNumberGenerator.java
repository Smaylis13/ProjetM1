package fr.univtln.madapm.votemanager.crypto;

/**
 * Created by civars169 on 21/05/15.
 * copyright Christian
 */
public class CKeyNumberGenerator {

    private int mP;
    private int mG;
    private int mA;
    private int mB;

    public CKeyNumberGenerator() {
    }

    public CKeyNumberGenerator(int pP, int pG, int pA, int pB) {
        this.mP = pP;
        this.mG = pG;
        this.mA = pA;
        this.mB = pB;
    }

    public int getP() {
        return mP;
    }

    public int getG() {
        return mG;
    }

    public int getA() {
        return mA;
    }

    public int getB() {
        return mB;
    }

    @Override
    public String toString() {
        return "CKeyNumberGenerator{" +
                "mP=" + mP +
                ", mG=" + mG +
                ", mA=" + mA +
                ", mB=" + mB +
                '}';
    }
}
