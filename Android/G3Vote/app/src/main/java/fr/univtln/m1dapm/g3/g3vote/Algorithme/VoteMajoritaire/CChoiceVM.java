package fr.univtln.m1dapm.g3.g3vote.Algorithme.VoteMajoritaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Majority Judjement
 * @author lyamsi
 */
public class CChoiceVM {
    /**
     * Nom du candidat
     */
    private String mName;
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
               /* private Map<Integer,Integer> mNbNote = new HashMap<Integer,Integer>();*/
    /**
     * Cette variable sera calculer au moment de l'appel de la fonction moyenneNote
     */
    private double mAvg = 0.0;
    /**
     * Le total des notes
     */
    private double mSum = 0.0;

    public double getmSum() {
        return mSum;
    }

    /**
     * Création d'un candidat
     * @param pNom
     */
    public CChoiceVM(String pNom){
        mName = pNom;
        mDescription = "";
    }

    /**
     * Création d'un candidat
     * @param pNom
     * @param pDescription
     */
    public CChoiceVM(String pNom, String pDescription){
        mName = pNom;
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
     * Supprime une note ( index en paramètre )
     * @param pIndex
     */
    public void subNote(int pIndex){
        mNotes.remove(pIndex);
    }
    /**
     * Trie la liste de note par ordre croissant (Pour avoir la médiane)
     */
    public void sortNote() {
        Collections.sort(this.mNotes);
    }

    /**
     *
     * @return la moyenne des notes obtenue
     */
    public double moyenneNote(){
        double moy = 0.0;
        if(!mNotes.isEmpty()) {

            mAvg = sommeNote() / mNotes.size();
            return mAvg;
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
            mSum = sum;
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
    public String getName() {
        return mName;
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
    public double getmAvg() {
        return mAvg;
    }

                /**
                 *
                 * @return map note/nb de note
                 */
               /* public Map<Integer, Integer> getNbNote() {
                    return mNbNote;
                }*/

    /**
     *
     * @param pDescription
     */
    public void setmDescription(String pDescription) {
        this.mDescription = pDescription;
    }

    /**
     *
     * @param pNom
     */
    public void setmName(String pNom) {
        this.mName = pNom;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "mNom='" + mName + '\'' +
                ", mNotes=" + mNotes +", mSum= " + mSum +", mAvg= "+mAvg+
                '}';
    }
}