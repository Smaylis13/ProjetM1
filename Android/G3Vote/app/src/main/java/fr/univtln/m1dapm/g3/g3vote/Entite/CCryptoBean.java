package fr.univtln.m1dapm.g3.g3vote.Entite;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

/**
 * Created by Ookami on 03/06/2015.
 */
public class CCryptoBean implements Serializable {
    private UUID mUniqueKey;
    private BigInteger mPublicKey;

    public CCryptoBean() {
    }

    public CCryptoBean(BigInteger pPublicKey, UUID pUniqueKey) {
        this.mPublicKey = pPublicKey;
        this.mUniqueKey = pUniqueKey;
    }

    public UUID getmUniqueKey() {
        return mUniqueKey;
    }

    @JsonSetter("mUniqueKey")
    public void setmUniqueKey(UUID mUniqueKey) {
        this.mUniqueKey = mUniqueKey;
    }

    public BigInteger getmPublicKey() {
        return mPublicKey;
    }

    @JsonSetter("mPublicKey")
    public void setmPublicKey(BigInteger mPublicKey) {
        this.mPublicKey = mPublicKey;
    }

    @Override
    public String toString() {
        return "CCryptoBean{" +
                "mUniqueKey=" + mUniqueKey +
                ", mPublicKey=" + mPublicKey +
                '}';
    }
}
