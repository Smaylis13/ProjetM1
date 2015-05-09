package fr.univtln.m1dapm.g3.g3vote.Entite;

import java.util.Date;
import java.util.List;

/**
 * Created by ludo on 06/05/15.
 */
public class CVote {

    private int mIdVote;

    private String mNom;

    private String mDescription;

    private int mStatut;

    private List<CRegle> mListRegle;

    private List<CChoix> mListChoix;

    private List <CCandidat> mListCandidat;

    private Date mDebut;

    private Date mFin;

    private CResultat mResultat;

}
