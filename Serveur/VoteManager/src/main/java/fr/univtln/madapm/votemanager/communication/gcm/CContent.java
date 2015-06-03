package fr.univtln.madapm.votemanager.communication.gcm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by igacha664 on 01/06/15.
 */
public class CContent implements Serializable {

    private List<String> registration_ids;
    private Map<String, String> data;

    public void addRegId(String regId) {
        if(registration_ids == null) {
            registration_ids = new LinkedList<>();
        }

        registration_ids.add("APA91bFFA_pFxZ0RCfipuuAQCfWy3nJcDNrpu14-uwqzRwmMmSrNCmz2P5pbUtp7huokUnoVmxF_JxAcQRcd8_DYqePorYHGKhbFx8cs7ykGM0-KZbgtJ1-inq7O1uCRWdHu_4Msz5D0XCaaFJkLTfLf9G2qURAwWg");
    }

    public void createData(String title, String message) {
        if(data == null) {
            data = new HashMap<>();
        }

        data.put("title", title);
        data.put("message", message);
    }
}

