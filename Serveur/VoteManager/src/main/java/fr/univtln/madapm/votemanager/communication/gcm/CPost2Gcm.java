package fr.univtln.madapm.votemanager.communication.gcm;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by lyamsi on 01/06/15.
 */
public class CPost2Gcm {
	public static void post(String apiKey, CContent content) {
		try {

			URL url = new URL("https://android.googleapis.com/gcm/send");

            //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.univ-tln.fr", 3128));
            //HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");

			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key="+apiKey);

			conn.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();

			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

			mapper.writeValue(wr, content);

			wr.flush();

			wr.close();

			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL:" + url);
			System.out.println("Response Code: " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			System.out.println(response.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}