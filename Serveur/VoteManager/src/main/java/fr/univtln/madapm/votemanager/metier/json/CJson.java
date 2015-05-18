package fr.univtln.madapm.votemanager.metier.json;

import com.google.gson.Gson;
import fr.univtln.madapm.votemanager.metier.user.CGroup;
import fr.univtln.madapm.votemanager.metier.user.CUser;
import fr.univtln.madapm.votemanager.metier.vote.CCandidate;
import fr.univtln.madapm.votemanager.metier.vote.CVote;

import java.io.*;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
public class CJson {

    private String mSlach = "\\";

    private String mPathFile=new File("").getAbsolutePath();
    private String mPathFileLinux=mPathFile.replace(mSlach, "/");

    /**
     * Méthode qui converti un objet en Json et le place dans
     * le fichier correspondant au nom de sa classe
     * @param pobject Objet à passer en JSON
     */
    public void objectToJson(Object pobject){

        String lpathFileEnd="/src/json/"+pobject.getClass().getSimpleName();

        Gson lgson = new Gson();
        String ljson = lgson.toJson(pobject);

        File lfile = new File(mPathFileLinux+lpathFileEnd+".json");
        try {
            boolean newFile = lfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(mPathFileLinux+lpathFileEnd+".json");
            writer.write(ljson);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ljson);
    }

    /**
     * Méthode qui reconstruit un objet via un fichier .json
     * @param ppath Chemin du fichier .JSON
     * @param pclass Classe de l'objet du JSON
     * @return Objet du fichier JSON
     */
    public Object jsonToObject(String ppath, String pclass){

        String lpathFileEnd="/src/json/"+ppath;

        Gson lgson = new Gson();

        try {

            BufferedReader lbr = new BufferedReader(
                    new FileReader(mPathFileLinux+lpathFileEnd+".json"));

            switch(pclass){
                case "User":
                    return lgson.fromJson(lbr, CUser.class);
                case "Candidate":
                    return lgson.fromJson(lbr, CCandidate.class);
                case "Vote":
                    return lgson.fromJson(lbr, CVote.class);
                case "Groupe":
                    return lgson.fromJson(lbr, CGroup.class);
                default:
                    return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
