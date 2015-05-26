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
    private Map<List<CCandidateBorda>,Integer> mRankingNb;
    /**
     * La taille de List<CCandidatBorda>
     */
    private int mSizeRanking;


    public CAlgoBorda() {
        mRankingNb = new HashMap<List<CCandidateBorda>,Integer>();
    }

    /**
     *
     * @param pList un classement fait par un élécteur
     * @return nombre de personne ayant fait le même classement
     */
    public int getN(List<CCandidateBorda> pList){
        return mRankingNb.get(pList);
    }

    /**
     * Quand une personne vote, c'est cette fonction qui devrais être appelée
     * Elle compte en même temps le nombre de votant ayant fait ce classement
     * @param pClassement
     */
    public void put(List<CCandidateBorda> pClassement){
        if(mRankingNb.containsKey(pClassement)){
            mRankingNb.put(pClassement, mRankingNb.get(pClassement) + 1 );
        }else{
            mRankingNb.put(pClassement,1);
        }
        mSizeRanking = mRankingNb.size();
    }

    /**
     *
     * @return candidat gagnant
     */
    public CCandidateBorda borda(){
        List<CCandidateBorda> lCandidates = mRankingNb.keySet().iterator().next();
        int lMax = 0;
        CCandidateBorda lWinner = null;
        for (CCandidateBorda c : lCandidates) {
            int lValue=0;
            for (Map.Entry<List<CCandidateBorda>, Integer> element : mRankingNb.entrySet()){
                int lPosition = element.getKey().indexOf(c);
                lValue += element.getValue()*(mSizeRanking-lPosition);
            }
            c.setmTotal(lValue);
            // chercher le max
            if (lValue > lMax){
                lMax = lValue;
                lWinner = c;
            }

        }
        return lWinner;
    }

}
