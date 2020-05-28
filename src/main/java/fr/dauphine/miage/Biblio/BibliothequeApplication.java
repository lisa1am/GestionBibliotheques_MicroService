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

		/*URL obj = null;
		try {
			obj = new URL("http://localhost:8003/livres/isbn/2");
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
		}*/


		System.out.println("\n\n\nConnexion au service de gestion des livres ... ");
		System.out.println("...........");
		URL urlLivre = new URL("http://localhost:8003/h2-console");
		HttpURLConnection conLivre = null;
		conLivre = (HttpURLConnection) urlLivre.openConnection();
		int responseCode = conLivre.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("Connexion établie !\n\n\n");
		} else {
			System.out.println(responseCode + " : Impossible d'atteindre le service. Veuillez vérifier qu'il est bien démarré.");
		}

		System.out.println("\n\n\nConnexion au service de gestion des lecteurs ... ");
		System.out.println("...........");
		URL urlLecteur = new URL("http://localhost:8002/h2-console");
		HttpURLConnection conLecteur = null;
		conLecteur = (HttpURLConnection) urlLecteur.openConnection();
		responseCode = conLecteur.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("Connexion établie !\n\n\n");
		} else {
			System.out.println(responseCode + " : Impossible d'atteindre le service. Veuillez vérifier qu'il est bien démarré.");
		}

		System.out.println("\n\n\nConnexion au service de gestion des prêts ... ");
		System.out.println("...........");
		URL urlPret = new URL("http://localhost:8001/h2-console");
		HttpURLConnection conPret = null;
		conPret = (HttpURLConnection) urlPret.openConnection();
		responseCode = conPret.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("Connexion établie !\n\n\n");
		} else {
			System.out.println(responseCode + " : Impossible d'atteindre le service. Veuillez vérifier qu'il est bien démarré.");
		}


		boolean endProgram = false;
		boolean bonchoix = false;
		int strInt = 0;
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> choix = new ArrayList<Integer>();
		int nbchoix = 4;
		for (int i = 1; i <= nbchoix; i++) {
			choix.add(i);
		}

		while (!endProgram) {
			System.out.println("1. Consulter un livre via son isbn");
			System.out.println("2. Consulter un lecteur via son id");
			System.out.println("3. Consulter l'historique des prêts");
			System.out.println("4. Exit");

			while (!bonchoix) {
				System.out.println("Veuillez faire un choix :");
				strInt = sc.nextInt();

				if (choix.contains(strInt)) {
					bonchoix = true;
				} else {
					System.out.println("ERROR Veuillez faire un choix parmis les propositions ci-dessous");
					System.out.println("1. Consulter un livre via son isbn");
					System.out.println("2. Consulter un lecteur via son id");
					System.out.println("3. Consulter l'historique des prêts");
					System.out.println("4. Exit");
				}
			}

			boolean succes = false;
			String commandString = "";
			int c = 0;
			URL req;
			HttpURLConnection con = null;
			if (strInt == 1) {
				String isbn = "";
				String auteur;
				String titre;
				String editeur;
				Long edition;
				JSONObject jo = null;
				while (!succes) {
					System.out.println("Veuillez indiquer l'isbn (0 pour exit): ");
					if(c==0) {
						sc.nextLine();
						c++;
					}
					commandString = sc.nextLine();
					req = new URL("http://localhost:8003/livres/isbn/" + commandString);
					con = (HttpURLConnection) req.openConnection();
					con.setRequestMethod("GET");
					con.setRequestProperty("User-Agent", "Mozilla/5.0");
					responseCode = con.getResponseCode();
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
						try {
							jo = new JSONObject(response.toString());
							titre = jo.getString("titre");
							auteur = jo.getString("auteur");
							isbn = jo.getString("isbn");
							editeur = jo.getString("editeur");
							edition = jo.getLong("edition");
						}
						catch(JSONException e){
							System.out.println(e.getMessage());
						}
					} else {
						System.out.println("GET request not worked");
					}

					if (isbn.equalsIgnoreCase(commandString) || commandString.equals("0")) {
						//Afficher
						System.out.println(jo);
						if (commandString.equals("0")) {
							System.out.println("----- Fin de l'opération de recherche de livre par isbn, exit...");
						}
						succes = true;
						commandString = "";
					} else {
						System.out.println("Mauvais isbn, le livre n'existe pas dans notre base ! Pour exit tapez 0");
						commandString = "";
					}
				}
			}
			if (strInt == 2) {

			}
			if (strInt == 3) {
				System.out.println("Exécuter le choix 3");
			}
			if (strInt == 4) {
				endProgram = true;
			}
			bonchoix = false;
		}


		System.out.println("----- Fin du scenario !!");
		System.exit(1);

	}

}
