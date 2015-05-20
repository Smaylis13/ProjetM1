package fr.univtln.m1dapm.g3.g3vote.Algorithme.UninominalMajoritaire;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import fr.univtln.m1dapm.g3.g3vote.Algorithme.AAlgorithme;
import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;

/**
 * Created by Pierre on 20/05/2015.
 */
public class CVoteUninominalMajoritaire extends AAlgorithme{

    Map<CCandidat,Integer> mMapCandidat;// associasion des candidat a leur nombre de vote
    List<CCandidat> mListeCandidat;//liste des candidat
    @Override
    protected void initVote() {

    }

    public  CVoteUninominalMajoritaire(){

    }

    protected void initVote(List<CCandidat> pListeCandidat) {
        /** initialise le vote avec pour argument la liste des candidat**/
        mListeCandidat=new ArrayList<CCandidat>();
        mMapCandidat=new Hashtable<CCandidat, Integer>();
        for (int i =0;i<pListeCandidat.size();i++){//mise en place de la map et de la liste pour ce vote

            mListeCandidat.add(pListeCandidat.get(i));
            mMapCandidat.put(pListeCandidat.get(i), 0);
        }
    }

    public void nouveauVote(CCandidat pchoix){// ajoute un au nombre de vote pour le candidat
        /** permet de valider le choix d'un votant avec pour argument le candidat choisi **/
        Integer pnbvote;
        pnbvote=mMapCandidat.get(pchoix);
        mMapCandidat.put(pchoix,pnbvote+1);
    }


    public CCandidat resultat(){// renvoi le candidat avec le plus de vote pour lui
        /** donne le resultat du vote sans parametre avec pour retour le candidat avec le plus de vote pour lui  **/
        Integer pnbvotemax=0;
        Integer pnbvote;
        CCandidat vainqueur = new CCandidat() ;
        for (int i=0;i<mMapCandidat.size();i++){
            pnbvote=mMapCandidat.get(mListeCandidat.get(i));
            if (pnbvote>pnbvotemax){
                pnbvotemax=pnbvote;
                vainqueur=mListeCandidat.get(i);
            }
        }

       return vainqueur;
    }

}
