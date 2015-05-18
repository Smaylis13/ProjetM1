package fr.univtln.madapm.votemanager.metier;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

import fr.univtln.madapm.votemanager.metier.json.CJson;
import fr.univtln.madapm.votemanager.metier.user.CGroup;
import fr.univtln.madapm.votemanager.metier.user.COrganizer;
import fr.univtln.madapm.votemanager.metier.user.CParticipant;
import fr.univtln.madapm.votemanager.metier.vote.CCandidate;
import fr.univtln.madapm.votemanager.metier.vote.CType;
import fr.univtln.madapm.votemanager.metier.vote.CVote;

/**
 * Classe de test du code métier du serveur
 */
public class CTestMetier {

    public static void main(String[] args) {
        System.out.println("Démarage programme test métier");


        /**
         * Création des acteurs
         */

        CMap<CParticipant, String> votant = new CMap<>();
        CParticipant participant1 = new CParticipant(2, "Gérad Menvussa", "motdepasse");
        CParticipant participant2 = new CParticipant(3, "Eléanor Mamontre", "or");
        votant.put(participant1, "Pascalou");
        votant.put(participant2, "elen");
        //CGroup groupevotant = new CGroup(1, "le groupe de votant", "vote1", votant);

       // COrganizer organisateur = new COrganizer(1, "Pascal LARIVE", "Ylios", groupevotant);
       // System.out.println(organisateur);



        CMap<CCandidate, String> mapCandidat = new CMap<>();
       /* CCandidate candidat1 = new CCandidate(1, "banane", "c'est une banane", mVote);
        CCandidate candidat2 = new CCandidate(2, "cerise", "miam", mVote);
        System.out.println(candidat1);
        mapCandidat.put(candidat1, candidat1.getNomcandidat());
        mapCandidat.put(candidat2, candidat2.getNomcandidat());
        System.out.println("Map de candidats\n"+mapCandidat+"\n");*/


        /**
         * Création d'un vote
         */
/*
        CType type = new CType(1, "type1", "type");

        CVote vote1 = new CVote(1, "Vote", "Vote test", "aujourd'hui", "aujourd'hui", null, type, null, null,
                organisateur, organisateur.getGroupe().getMapgroupe(), mapCandidat);

        System.out.println("\nCréation du vote\n"+vote1);

        vote1.voteOrReplaceVote(participant1, "<Vote>banane</Vote>");
        vote1.voteOrReplaceVote(participant2, "<Vote>cerise</Vote>");

        System.out.println("\nVotes des participants\n" + vote1);*/

        /**
         * Opération sur les votes
         */

       /* vote1.deleteVote(participant2);

        System.out.println("Le participant 2 se retire de ce vote\n" + vote1);

        vote1.voteOrReplaceVote(participant1, "<Vote>cerise</Vote>");

        System.out.println("Le participant 1 change son vote pour ce vote"+vote1);

        System.out.println("Test JSON");

        CJson json = new CJson();
        json.objectToJson(candidat1);

        json.jsonToObject("CCandidat", "Candidat");*/


        System.out.println("Fin du programme test métier");
    }

}
