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

        Gson gson = new Gson();
        String json = gson.toJson(po);

        File lfile = new File(pathFileLinux+pathFileEnd+".json");
        try {
            boolean newFile = lfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(pathFileLinux+pathFileEnd+".json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(json);
    }

    /**
     * Méthode qui reconstruit un objet via un fichier .json
     * @param ppath
     * @param pclass
     * @return
     */
    public Object jsonToObject(String ppath, String pclass){

        String pathFileEnd="/src/json/"+ppath;

        Gson gson = new Gson();

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(pathFileLinux+pathFileEnd+".json"));

            switch(pclass){
                case "User":
                    return gson.fromJson(br, CUser.class);
                case "Candidate":
                    return gson.fromJson(br, CCandidat.class);
                case "Vote":
                    return gson.fromJson(br, CVote.class);
                case "Groupe":
                    return gson.fromJson(br, CGroupe.class);
                case "Particapnt":
                    return gson.fromJson(br, CParticipant.class);
                case "Organisateur":
                    return gson.fromJson(br, COrganisateur.class);
                default:
                    return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
