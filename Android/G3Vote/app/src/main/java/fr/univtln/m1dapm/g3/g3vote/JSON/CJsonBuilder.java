package fr.univtln.m1dapm.g3.g3vote.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;

/**
 * Created by ludo on 05/05/15.
 */
public class CJsonBuilder {

    public static JSONObject createUserObject(CUser pUser)
    {
        JSONObject lUserOBJ = new JSONObject();
        try {
            lUserOBJ.put("mEmail", pUser.getEmail().isEmpty()? "unknow" : pUser.getEmail());
            lUserOBJ.put("mPassword", pUser.getPassword().isEmpty()? "unknow" : pUser.getPassword());
            lUserOBJ.put("mFirstName", pUser.getFirstName().isEmpty()? "unknow" : pUser.getFirstName());
            lUserOBJ.put("mName", pUser.getName().isEmpty()? "unknow" : pUser.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lUserOBJ;
    }
}
