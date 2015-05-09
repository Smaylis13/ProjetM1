package fr.univtln.m1dapm.g3.g3vote.Algorithme.VoteMajoritaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Majority Judjement
 * @author lyamsi
 */
public class CChoixVM {
    /**
     * Nom du candidat
     */
    private String mNom;
    /**
     * Description du candidat
     */
    private String mDescription;
    /**
     * Liste des notes qui vont être donner par les élécteurs
     */
    private List<Integer> mNote = new ArrayList<Integer>();
    /**
     * key : la note
     * value : nombre de personne ayant donné cette note
     */
    private Map<Integer,Integer> mNbNote = new HashMap<Integer,Integer>();

    /**
     * Création d'un candidat
     * @param pNom
     */
    public CChoixVM(String pNom){
        mNom = pNom;
        mDescription = "";
    }

    /**
     * Création d'un candidat
     * @param pNom
     * @param pDescription
     */
    public CChoixVM(String pNom, String pDescription){
        mNom = pNom;
        mDescription = pDescription;
    }

    /**
     * Ajoute à la liste d'un candidat une note donné par un élécteur
     * @param note
     */
    public void addNote(int note){
        mNote.add(note);
    }

    /**
     * Trie la liste de note par ordre croissant (Pour avoir la médiane)
     */
    public void trieNote() {
        Collections.sort(this.mNote);
    }

    /**
     *
     * @return Liste de note
     */
    public List<Integer> getNote() {
        return mNote;
    }

    /**
     *
     * @return Nom d'un candidat
     */
    public String getNom() {
        return mNom;
    }

    /**
     *
     * @return Description d'un candidat
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     *
     * @return map note/nb de note
     */
    public Map<Integer, Integer> getNbNote() {
        return mNbNote;
    }

    /**
     *
     * @param pDescription
     */
    public void setDescription(String pDescription) {
        this.mDescription = pDescription;
    }

    /**
     *
     * @param pNom
     */
    public void setNom(String pNom) {
        this.mNom = pNom;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "mNom='" + mNom + '\'' +
                ", mNote=" + mNote +
                '}';
    }
}