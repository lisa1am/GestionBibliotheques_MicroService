package fr.dauphine.miage.Biblio;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class BibliothequeApplication {

	public static void main(String[] args) {

		SpringApplication.run(BibliothequeApplication.class, args);

		System.out.println("CA MARCHEEEEE !");

		URL obj = null;
		try {
			obj = new URL("http://localhost:8000/livres/isbn/1");
		HttpURLConnection con = null;
		con = (HttpURLConnection) obj.openConnection();


		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
