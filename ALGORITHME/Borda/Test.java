package fr.univtln.bruno.exemple.bibliotheque.TestU2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static List<String> classement1 = new ArrayList<String>();
    public static List<String> classement2 = new ArrayList<String>();
    public static List<String> classement3 = new ArrayList<String>();
    public static List<String> classement4 = new ArrayList<String>();
    public static final int NB = 4;

    public static void main(String[] args) {

        classement1.add("A");classement1.add("B");classement1.add("C");classement1.add("D");
        classement2.add("B");classement2.add("C");classement2.add("D");classement2.add("A");
        classement3.add("C");classement3.add("D");classement3.add("B");classement3.add("A");
        classement4.add("D");classement4.add("C");classement4.add("B");classement4.add("A");

        Map<List<String>,Integer> mapTab = new HashMap<List<String>,Integer>();
        mapTab.put(classement1, 42);
        mapTab.put(classement2, 26);
        mapTab.put(classement3, 15);
        mapTab.put(classement4, 17);
        
        System.out.println(borda(mapTab));

    }

    public static String borda(Map<List<String>,Integer> pMapTab){
        Map<String,Integer> result = new HashMap<String,Integer>();
        List<String> candidates = pMapTab.keySet().iterator().next();
        for (String c : candidates) {
            int value=0;
            for (Map.Entry<List<String>, Integer> element : pMapTab.entrySet()){
                int position = element.getKey().indexOf(c);
                value += element.getValue()*(NB-position);
            }
            result.put(c,value);

        }
        // chercher le max
        int max = 0;String vainqueur="";
        for (Map.Entry<String, Integer> element : result.entrySet()) {
            if ( element.getValue() > max){
                max = element.getValue();
                vainqueur = element.getKey();
                vainqueur += " Avec "+max;

            }
        }
        return "Le gagnant : "+vainqueur;
    }
}
