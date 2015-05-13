package fr.univtln.madapm.votemanager.metier.json;

import com.google.gson.Gson;
import fr.univtln.madapm.votemanager.metier.user.CGroupe;
import fr.univtln.madapm.votemanager.metier.user.COrganisateur;
import fr.univtln.madapm.votemanager.metier.user.CParticipant;
import fr.univtln.madapm.votemanager.metier.user.CUser;
import fr.univtln.madapm.votemanager.metier.vote.CCandidat;
import fr.univtln.madapm.votemanager.metier.vote.CVote;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;

/**
 * Created by civars169 on 12/05/15.
 * copyright Christian
 */
public class CJson {

    String slach = "\\";

    String pathFile=new File("").getAbsolutePath();
    String pathFileLinux=pathFile.replace(slach, "/");

    ObjectMapper mapper = new ObjectMapper();

    /**
     * Méthode qui converti un objet en Json et le place dans
     * le fichier correspondant au nom de sa classe
     * @param po
     */
    public void objectToJson(Object po){

        String pathFileEnd="/src/json/"+po.getClass().getSimpleName();

        Gson lgson = new Gson();
        String ljson = lgson.toJson(po);

        File lfile = new File(pathFileLinux+pathFileEnd+".json");
        try {
            boolean newFile = lfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(pathFileLinux+pathFileEnd+".json");
            writer.write(ljson);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ljson);
    }

    /**
     * Méthode qui reconstruit un objet via un fichier .json
     * @param ppath
     * @param pclass
     * @return
     */
    public Object jsonToObject(String ppath, String pclass){

        String pathFileEnd="/src/json/"+ppath;

        Gson lgson = new Gson();

        try {

            BufferedReader lbr = new BufferedReader(
                    new FileReader(pathFileLinux+pathFileEnd+".json"));

            switch(pclass){
                case "User":
                    return lgson.fromJson(lbr, CUser.class);
                case "Candidate":
                    return lgson.fromJson(lbr, CCandidat.class);
                case "Vote":
                    return lgson.fromJson(lbr, CVote.class);
                case "Groupe":
                    return lgson.fromJson(lbr, CGroupe.class);
                case "Particapnt":
                    return lgson.fromJson(lbr, CParticipant.class);
                case "Organisateur":
                    return lgson.fromJson(lbr, COrganisateur.class);
                default:
                    return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
