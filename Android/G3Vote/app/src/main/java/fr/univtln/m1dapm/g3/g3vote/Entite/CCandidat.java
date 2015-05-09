package fr.univtln.m1dapm.g3.g3vote.Entite;

/**
 * Created by ludo on 06/05/15.
 */
public class CCandidat {

    private int mId;
    private String mNom;
    private String mDescription;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmNom() {
        return mNom;
    }

    public void setmNom(String mNom) {
        this.mNom = mNom;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @Override
    public String toString() {
        return "CCandidat{" +
                "mId=" + mId +
                ", mNom='" + mNom + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }


    public CCandidat(int mId, String mNom, String mDescription) {
        this.mId = mId;
        this.mNom = mNom;
        this.mDescription = mDescription;
    }

    public CCandidat(String mNom) {
        this.mNom = mNom;
    }

    public CCandidat() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CCandidat cCandidat = (CCandidat) o;

        if (mId != cCandidat.mId) return false;
        if (mDescription != null ? !mDescription.equals(cCandidat.mDescription) : cCandidat.mDescription != null)
            return false;
        if (!mNom.equals(cCandidat.mNom)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + mNom.hashCode();
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        return result;
    }
}
