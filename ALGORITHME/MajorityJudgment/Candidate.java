package fr.univtln.bruno.exemple.bibliotheque.TestU1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lyamsi on 05/05/15.
*/
public class Candidate{
    private String mNom;
    private List<Integer> mNote = new ArrayList<Integer>();

    public Candidate(String pNom){
        mNom = pNom;
    }
    public void addNote(int note){
        mNote.add(note);
    }

    public void trieNote() {
        Collections.sort(this.mNote);
    }

    public List<Integer> getNote() {
        return mNote;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "mNom='" + mNom + '\'' +
                ", mNote=" + mNote +
                '}';
    }
}