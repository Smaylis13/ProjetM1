package fr.univtln.madapm.votemanager.metier;

/**
 * Created by civars169 on 05/05/15.
 * copyright Christian
 */

import fr.univtln.madapm.votemanager.metier.json.CJson;
import fr.univtln.madapm.votemanager.metier.user.CUser;
import fr.univtln.madapm.votemanager.metier.vote.*;

import java.util.ArrayList;

/**
 * Classe de test du code métier du serveur
 */
public class CTestMetier {

    public static void main(String[] args) {
        System.out.println("Démarage programme test métier");


        /**
         * Création des acteurs
         */

        CUser utilisateur1 = new CUser(2, "Gérad.Menvussa@lycos.fr", "motdepasse");
        CUser utilisateur2 = new CUser(3, "Eléanor.Mamontre@lol.fr", "or");
        CUser utilisateur3 = new CUser(1, "Pascal.Larivé@banquise.fr", "Ylios");

        java.util.List<CUser> participants = new ArrayList<CUser>();
        participants.add(utilisateur2);
        participants.add(utilisateur3);

        System.out.println(participants);

        /**
         * Création d'un vote
         */
        System.out.println("\nCréation du vote\n");

        CType type1 = new CType(1, "type1", "type");
        CType type2 = new CType(2, "type2", "type");
        java.util.List<CType> types = new ArrayList<CType>();
        types.add(type1);
        types.add(type2);

        CRule regle1 = new CRule(1, "Regle1");
        CRule regle2 = new CRule(2, "Regle2");
        java.util.List<CRule> regles = new ArrayList<CRule>();
        regles.add(regle1);
        regles.add(regle2);

        CVote vote = new CVote();

        CCandidate candidate1 = new CCandidate(1, "cerise", "Je suis juteuse", vote);
        CCandidate candidate2 = new CCandidate(2, "banane", "J'ai la banane lol", vote);
        CCandidate candidate3 = new CCandidate(3, "ananas", "Je suis jaune", vote);
        java.util.List<CCandidate> candidates = new ArrayList<CCandidate>();
        candidates.add(candidate1);
        candidates.add(candidate2);
        candidates.add(candidate3);

        CResult result1 = new CResult(1, 3, vote, candidate1);
        CResult result2 = new CResult(2, 1, vote, candidate2);
        CResult result3 = new CResult(3, 2, vote, candidate3);
        java.util.List<CResult> results = new ArrayList<CResult>();
        results.add(result1);
        results.add(result2);
        results.add(result3);

        vote = new CVote(1, "Vote", "Vote test", "aujourd'hui", "aujourd'hui", results, types, regles, true,
                utilisateur1, candidates);

        System.out.println(vote);

        System.out.println("\nVotes des participants\n");

        vote.addChoice(utilisateur2, candidate1, 1);
        vote.addChoice(utilisateur3, candidate3, 1);

        System.out.println(results);

        /**
         * Opération sur les votes
         */
        System.out.println("\n\nCréation du vote\n");

        CUser newparticipant = new CUser(0, "coucou@coucou.fr", "root");
        participants.add(newparticipant);

        vote.addChoice(newparticipant, candidate1, 1);

        System.out.println("Test JSON");

        CJson json = new CJson();
        json.objectToJson(vote);

        json.jsonToObject("CVote", "Vote");


        System.out.println("Fin du programme test métier");
    }

}
