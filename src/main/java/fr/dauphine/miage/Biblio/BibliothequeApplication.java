package fr.dauphine.miage.Biblio;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BibliothequeApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(BibliothequeApplication.class, args);

		System.out.println("CA MARCHEEEEE !");

		URL obj = null;
		try {
			obj = new URL("http://localhost:8003/livres/isbn/1");
			HttpURLConnection con = null;
			con = (HttpURLConnection) obj.openConnection();


			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code : " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();


				// CODE POUR RÉCUPÉRER CHAQUE CHAMP SEUL
				// METTRE Long id = jo.getLong("id") si on veur récupérer un Long au lieu de string
				System.out.println(response.toString());
				JSONObject jo = new JSONObject(response.toString());
				String titre = jo.getString("titre");
				String auteur = jo.getString("auteur");
				String isbn = jo.getString("isbn");
				String editeur = jo.getString("editeur");
				Long edition = jo.getLong("edition");
				System.out.println(titre);
			} else {
				System.out.println("GET request not worked");
			}
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}

		boolean endProgram = false;
		boolean bonchoix = false;
		int str = 0;
		ArrayList<Integer> choix = new ArrayList<Integer>();
		int nbchoix = 4;
		for(int i = 1;i<=nbchoix;i++){
			choix.add(i);
		}



		System.out.println("\n\n\nConnexion au service de gestion des livres ... ");
		System.out.println("...........");
		URL urlLivre = new URL("http://localhost:8003/h2-console");
		HttpURLConnection conLivre = null;
		conLivre = (HttpURLConnection) urlLivre.openConnection();
		int responseCode = conLivre.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("Connexion établie !\n\n\n");
		}else{
			System.out.println(responseCode+" : Impossible d'atteindre le service. Veuillez vérifier qu'il est bien démarré.");
		}

		System.out.println("\n\n\nConnexion au service de gestion des lecteurs ... ");
		System.out.println("...........");
		URL urlLecteur = new URL("http://localhost:8002/h2-console");
		HttpURLConnection conLecteur = null;
		conLecteur = (HttpURLConnection) urlLecteur.openConnection();
		responseCode = conLecteur.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("Connexion établie !\n\n\n");
		}else{
			System.out.println(responseCode+" : Impossible d'atteindre le service. Veuillez vérifier qu'il est bien démarré.");
		}

		System.out.println("\n\n\nConnexion au service de gestion des prêts ... ");
		System.out.println("...........");
		URL urlPret = new URL("http://localhost:8001/h2-console");
		HttpURLConnection conPret = null;
		conPret = (HttpURLConnection) urlPret.openConnection();
		responseCode = conPret.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("Connexion établie !\n\n\n");
		}else{
			System.out.println(responseCode+" : Impossible d'atteindre le service. Veuillez vérifier qu'il est bien démarré.");
		}





			while(!endProgram){
			System.out.println("1. Ajoutez un lecteur");
			System.out.println("2. Consulter les lecteurs");
			System.out.println("3. Consulter l'historique des prêts");
			System.out.println("4. Exit");

			while(!bonchoix){
				Scanner sc = new Scanner(System.in);
				System.out.println("Veuillez faire un choix :");
				str = sc.nextInt();

				if(choix.contains(str)){
					bonchoix = true;
				}
				else{
					System.out.println("ERROR Veuillez faire un choix parmis les propositions ci-dessous");
					System.out.println("1. Ajoutez un lecteur");
					System.out.println("2. Consulter les lecteurs");
					System.out.println("3. Consulter l'historique des prêts");
					System.out.println("4. Exit");
				}
			}

			if(str == 1){
				System.out.println("Exécuter le choix 1");
				// Code get lecteur


			}
			if(str == 2){
				System.out.println("Exécuter le choix 2");
			}
			if(str == 3){
				System.out.println("Exécuter le choix 3");
			}
			if(str == 4){
				endProgram = true;
			}
			bonchoix = false;
		}

		System.out.println("fin du scenario !!");

	}

}
