package fr.univtln.m1dapm.g3.g3vote.Algorithme.VoteMajoritaire;

import java.util.HashMap;
import java.util.Map;

/**
 * class élécteur contient une MAP clé/valeur avec comme clé un candidat et comme valeur la noté attribué
 *@author G3
 *
 */
public class CVotantVM{
    /**
     * MAP clé/valeur avec comme clé un candidat et comme valeur la noté attribué
     */
    private Map<CChoixVM,Integer> mListCandidateNote = new HashMap<CChoixVM, Integer>();
    public CVotantVM(){}

    /**
     *
     * @return la map clé/valeur <-> candidat/note
     */
    public Map<CChoixVM, Integer> getMapCandidateNote() {
        return mListCandidateNote;
    }

    /**
     * @param pCandidate
     * @param pNote
     * @return void
     */
    public void addCN(CChoixVM pCandidate , int pNote){
        mListCandidateNote.put(pCandidate,pNote);
    }
}