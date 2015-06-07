package fr.univtln.madapm.votemanager.communication.gcm;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
/**
 * Created by lyamsi on 01/06/15.
 */
public class CPost2Gcm {
	public static void post(String pTitle, String pMessage, String pRegId) {
		try {
			String url = "https://gcm-http.googleapis.com/gcm/send";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			String apiKey = "AIzaSyCo4UkA1SvJoEi443j4YtFdOopcNjhyHCU";

			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "key=" + apiKey);
			String urlParameters = "{\"registration_ids\":[\"";
			urlParameters += pRegId + "\"]";
			urlParameters += ",\"data\": {\"title\":\"" + pTitle + "\",\"message\":\"" + pMessage + "\"}}";

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();


		//print result
		System.out.println(response.toString());
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}