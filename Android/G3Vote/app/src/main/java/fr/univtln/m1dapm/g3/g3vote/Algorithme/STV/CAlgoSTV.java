package fr.univtln.m1dapm.g3.g3vote.Algorithme.STV;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoix;
import fr.univtln.m1dapm.g3.g3vote.Entite.CRegle;
import fr.univtln.m1dapm.g3.g3vote.Entite.CResultat;

/**
 * Created by ludo on 05/05/15.
 */
class CAlgoSTV extends AAlgorithme {

    int mQuota;

    public CAlgoSTV() {
        super();
    }

    /// Methode d'initialisation du vote (charge les regles)
    protected void initVote()
    {
        getRegles();

        Calcul_Quota();
    }

    /// Charge la liste des regles du vote de la BDD
    private void getRegles()
    {
        mRegles = new ArrayList<CRegle>();

    }

    /// Charge la liste des choix faits par les votants de la BDD
    private List<CChoix> getChoix()
    {
        ArrayList<CChoix> lChoice = new ArrayList<CChoix>();

        return lChoice;
    }

    /// Methode de calcule de l'algorithme STV
    private CResultat Calul()
    {
        CResultat lResult =  new CResultat();




        return  lResult;
    }

    /// Methode de calcul du quota necessaire a l'election
    private void Calcul_Quota()
    {
        mQuota = 0;
    }
}
