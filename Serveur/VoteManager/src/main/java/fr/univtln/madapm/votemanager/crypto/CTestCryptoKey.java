package fr.univtln.madapm.votemanager.crypto;

/**
 * Created by civars169 on 21/05/15.
 * copyright Christian
 */

/**
 * Classe de test sur la génération de clef de cryptage privé et public
 */
public class CTestCryptoKey {
    public static void main(String[] args) {

        System.out.println("Test de clef à échanger avec l'appli");

        CKeyGenerator keyGenerator = new CKeyGenerator();
        System.out.println(keyGenerator);

        keyGenerator.createKey();

        System.out.println("Fin du test");

    }
}
