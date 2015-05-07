package fr.univtln.bruno.exemple.bibliotheque.TestU1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyamsi on 05/05/15.
 */
public class Votant{
    private Map<Candidate,Integer> mListCandidateNote = new HashMap<Candidate, Integer>();
    public Votant(){}

    public Map<Candidate, Integer> getMapCandidateNote() {
        return mListCandidateNote;
    }

    public void addCN(Candidate pCandidate , int pNote){
        mListCandidateNote.put(pCandidate,pNote);
    }
}
