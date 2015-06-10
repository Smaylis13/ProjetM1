package fr.univtln.m1dapm.g3.g3vote.Entite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ludo on 06/05/15.
 */
public class CCandidate implements Serializable{

    @JsonIgnore
    private int mIdCandidat;
    private String mNom;
    private String mDescription;
    private  int mIdVote;

    @Override
    public String toString() {
        return "CCandidat{" +
                "mId=" + mIdCandidat +
                ", mNom='" + mNom + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }


    public CCandidate(int mId, String mNom, String mDescription) {
        this.mIdCandidat = mId;
        this.mNom = mNom;
        this.mDescription = mDescription;
    }

    public CCandidate(int mId, String mNom) {
        this.mIdCandidat = mId;
        this.mNom = mNom;
    }

    public CCandidate(String mNom) {
        this.mNom = mNom;
    }

    public CCandidate() {
    }

    public static ArrayList<CCandidate> getAListOfCandidate(){

        ArrayList<CCandidate> listCandidate = new ArrayList<>();

        listCandidate.add(new CCandidate(0, "Banane", "Fruit 0"));
        listCandidate.add(new CCandidate(1, "Orange", "Fruit 1"));
        //listCandidate.add(new CCandidate(2, "Pomme", "Fruit 2"));
        //listCandidate.add(new CCandidate(3, "Poire", "Fruit 3"));
        //listCandidate.add(new CCandidate(4, "Abricot", "Fruit 4"));

        return listCandidate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CCandidate cCandidate = (CCandidate) o;

        if (mIdCandidat != cCandidate.mIdCandidat) return false;
        if (mDescription != null ? !mDescription.equals(cCandidate.mDescription) : cCandidate.mDescription != null)
            return false;
        if (!mNom.equals(cCandidate.mNom)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mIdCandidat;
        result = 31 * result + mNom.hashCode();
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        return result;
    }

    @JsonSetter("nomCandidat")
    public void setNomCandidat(String pName){
        this.mNom=pName;
    }


    public String getNomCandidat(){return this.mNom;}

    @JsonSetter("descriptionCandidat")
    public void setDescriptionCandidat(String pDescript){
        this.mDescription=pDescript;
    }

    public String getDescriptionCandidat(){return this.mDescription;}

    @JsonSetter("id")
    public void setId(int pId){
        this.mIdCandidat=pId;
    }

    @JsonIgnore
    public int getIdCandidat() {
        return mIdCandidat;
    }

    public void setVote(int pIdVote){
        this.mIdVote=pIdVote;
    }

}
