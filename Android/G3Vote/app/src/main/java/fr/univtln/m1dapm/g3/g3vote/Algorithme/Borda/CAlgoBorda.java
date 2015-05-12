package fr.univtln.m1dapm.g3.g3vote.Algorithme.Borda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyamsi on 12/05/15.
 */
public class CAlgoBorda {
    /**
     * Clé : liste de candidat trié par les élécteurs  (plusieurs classements)
     * value : nombre de candidat ayant préféré ce classement
     */
    private Map<List<CCandidatBorda>,Integer> mClassementNb;
    /**
     * La taille de List<CCandidatBorda>
     */
    private int mSizeClassement;

    public CAlgoBorda() {
        mClassementNb = new HashMap<List<CCandidatBorda>,Integer>();
    }

    /**
     *
     * @param pList un classement fait par un élécteur
     * @return nombre de personne ayant fait le même classement
     */
    public int getN(List<CCandidatBorda> pList){
        return mClassementNb.get(pList);
    }

    public void put(List<CCandidatBorda> pClassement){
        if(mClassementNb.containsKey(pClassement)){
            mClassementNb.put(pClassement, mClassementNb.get(pClassement) + 1 );
        }else{
            mClassementNb.put(pClassement,1);
        }
        mSizeClassement = mClassementNb.size();
    }

    /**
     *
     * @return candidat gagnant
     */
    public CCandidatBorda borda(){
        Map<CCandidatBorda,Integer> result = new HashMap<CCandidatBorda,Integer>();
        List<CCandidatBorda> candidates = mClassementNb.keySet().iterator().next();
        for (CCandidatBorda c : candidates) {
            int value=0;
            for (Map.Entry<List<CCandidatBorda>, Integer> element : mClassementNb.entrySet()){
                int position = element.getKey().indexOf(c);
                value += element.getValue()*(mSizeClassement-position);
            }
            c.setmTotal(value);
            result.put(c,value);

        }
        // chercher le max
        int max = 0;CCandidatBorda vainqueur = null;
        for (Map.Entry<CCandidatBorda, Integer> element : result.entrySet()) {
            if ( element.getValue() > max){
                max = element.getValue();
                vainqueur = element.getKey();
            }
        }
        return vainqueur;
    }

}
