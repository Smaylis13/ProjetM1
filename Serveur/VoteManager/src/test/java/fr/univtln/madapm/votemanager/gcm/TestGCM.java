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

    public static CContent createContent() {
        CContent c = new CContent();

        c.addRegId("APA91bGT289J49BhVlN-zfGFsv2GDWXpzspObigCflVmgh9q0MNT9pWrFcCuIPZSpJFU1Go7m0L51DfLJbpbDOXQw1w3UDYQXTyByfq0m3FVI28-6VY5hmUWvMGmYlNtEPnxeCvtasECXFwhlpAzAfZeU3nmwo8hPA");

        c.createData("Test title", "Test message");

        return c;
    }
}
