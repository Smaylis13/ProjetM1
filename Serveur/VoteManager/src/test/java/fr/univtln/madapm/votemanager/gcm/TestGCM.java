package fr.univtln.madapm.votemanager.gcm;

import fr.univtln.madapm.votemanager.communication.gcm.CPost2Gcm;

/**
 * Created by igacha664 on 01/06/15.
 */
public class TestGCM {
    private static final String sRegId =  "APA91bHI5SlWbINc_kO-7JHJd9cuAQscky6IFDeitSyEXBwnp4SetqUQY8mF4icS8EWbuqMMiM7tEjEZHMPg6Evt5i6TZ-bw4rmwA4Wpg_9hpVS0GR0ii9Cr5u218bYrG8EqEgIRPZ1UlZnE-Q50JG3f4wxwnah5tg";
	public static void main(String[] args) throws Exception {

		System.out.println("\nSend Http POST request...");
		CPost2Gcm.post("Title", "Message test",sRegId);

	}
}
