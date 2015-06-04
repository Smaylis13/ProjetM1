package fr.univtln.madapm.votemanager.gcm;

import fr.univtln.madapm.votemanager.communication.gcm.CContent;
import fr.univtln.madapm.votemanager.communication.gcm.CPost2Gcm;

/**
 * Created by igacha664 on 01/06/15.
 */
public class TestGCM {
    public static void main(String[] args) {

        System.out.println("Sending POST to GCM...");

        String apiKey = "AIzaSyCo4UkA1SvJoEi443j4YtFdOopcNjhyHCU";
        CContent content = createContent();

        CPost2Gcm.post(apiKey, content);
    }

    private static CContent createContent() {
        CContent c = new CContent();


c.addRegId("APA91bF-iJwh8UgXsx6HzNzbSCXt0LXYmMHwSuQhvbyhTU_UC5wQCu68rpBiA2Or5iIjSXs_JIa8I3WVmnhlF8RpFEgGpHPmdwXlwgRuxPc4dSqhz1_E5D1cocCGpejBL6zlsmWJGccgXhDP8wsunmNoRKCWIYq4eQ");
        /*c.addRegId("APA91bEhJ6JjSdLERh9LpCZ2kXGhHUdZj-qDPGk5qlzb7RvclVJ6o0JygKvnV6lYsREi2Ni8mSm66t0KW5TOoMiWFSRhUvmh8x3cQwfkd9UxrG4Hnbmfphaal3Txii7mFiukHAPCEIONsOQY8Vq6rso-o_KQAdVfkQ");
        c.addRegId("APA91bHmZ0E5p-_QoT3ddk4-Nz1kQ9ET3iYOGhxByU0GNtLss1GoQm2zm3QyZ6Eg86Iv40vvbERbfedinj1kldCkgtqGG3lLA6WdXEZdbl8IpkTBbYl6ucjG9RI_tOhsiCDBjcPU-jqutXG400bNSX9gKFqc7Bw6AQ");
        c.addRegId("APA91bFFA_pFxZ0RCfipuuAQCfWy3nJcDNrpu14-uwqzRwmMmSrNCmz2P5pbUtp7huokUnoVmxF_JxAcQRcd8_DYqePorYHGKhbFx8cs7ykGM0-KZbgtJ1-inq7O1uCRWdHu_4Msz5D0XCaaFJkLTfLf9G2qURAwWg");
        c.addRegId("APA91bFFA_pFxZ0RCfipuuAQCfWy3nJcDNrpu14-uwqzRwmMmSrNCmz2P5pbUtp7huokUnoVmxF_JxAcQRcd8_DYqePorYHGKhbFx8cs7ykGM0-KZbgtJ1-inq7O1uCRWdHu_4Msz5D0XCaaFJkLTfLf9G2qURAwWg");
        c.addRegId("APA91bFFA_pFxZ0RCfipuuAQCfWy3nJcDNrpu14-uwqzRwmMmSrNCmz2P5pbUtp7huokUnoVmxF_JxAcQRcd8_DYqePorYHGKhbFx8cs7ykGM0-KZbgtJ1-inq7O1uCRWdHu_4Msz5D0XCaaFJkLTfLf9G2qURAwWg");
        */c.createData("Test title", "Test message");

        return c;
    }
}
