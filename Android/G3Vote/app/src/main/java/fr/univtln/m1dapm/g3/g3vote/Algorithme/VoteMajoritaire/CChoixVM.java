package fr.univtln.m1dapm.g3.g3vote.Algorithme.VoteMajoritaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CChoix;

/**
 * Majority Judjement
 * @author G3
 */
public class CChoixVM extends CChoix{
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