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
    private List<Integer> mNotes = new ArrayList<Integer>();
    /**
     * key : la note
     * value : nombre de personne ayant donné cette note
     */
    private Map<Integer,Integer> mNbNote = new HashMap<Integer,Integer>();
    /**
     * Cette variable sera calculer au moment de l'appel de la fonction moyenneNote
     */
    private double mMoyenne = 0.0;
    /**
     * Le total des notes
     */
    private double mSomme = 0.0;

    public double getmSomme() {
        return mSomme;
    }

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
     * @param pNote
     */
    public void addNote(int pNote){
        mNotes.add(pNote);
    }
    /**
     * Supprime un note ( index en paramètre
     * @param pIndex
     */
    public void subNote(int pIndex){
        mNotes.remove(pIndex);
    }
    /**
     * Trie la liste de note par ordre croissant (Pour avoir la médiane)
     */
    public void trieNote() {
        Collections.sort(this.mNotes);
    }

    /**
     *
     * @return la moyenne des notes
     */
    public double moyenneNote(){
        double moy = 0.0;
        if(!mNotes.isEmpty()) {

            mMoyenne = sommeNote() / mNotes.size();
            return mMoyenne;
        }
        return moy;
    }

    /**
     *
     * @return la somme
     */
    public double sommeNote(){
        double sum = 0.0;
        if(!mNotes.isEmpty()) {
            for (Integer n : mNotes) {
                sum += n;
            }
            mSomme = sum;
            return sum;
        }
        return sum;
    }


    /**
     *
     * @return Liste de note
     */
    public List<Integer> getNotes() {
        return mNotes;
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
     * @return la moyenne s'elle a été déja calculer par cette méthode "moyenneNote"
     */
    public double getmMoyenne() {
        return mMoyenne;
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
                ", mNotes=" + mNotes +", mSomme= " + mSomme +", mMoyenne= "+mMoyenne+
                '}';
    }
}