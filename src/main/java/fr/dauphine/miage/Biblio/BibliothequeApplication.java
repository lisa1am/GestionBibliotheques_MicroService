package fr.dauphine.miage.Biblio;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
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

	public static URL req;
	public static HttpURLConnection con = null;

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
		int nbchoix = 10;
		for (int i = 1; i <= nbchoix; i++) {
			choix.add(i);
		}

		while (!endProgram) {
			System.out.println("1. Consulter un livre via son isbn");
			System.out.println("2. Consulter la liste des livres d'un auteur");
			System.out.println("3. Consulter les informations d'un lecteur avec son identifiant");
			System.out.println("4. Consulter les lecteurs avec un nom");
			System.out.println("5. Consulter les prets d'un lecteur");
			System.out.println("6. Consulter les pret d'un livre");
			System.out.println("7. Consulter un pret par isbn et identifiant lecteur");
			System.out.println("8. Consulter les prets par date de pret");
			System.out.println("9. Consulter les prets en cours");
			System.out.println("10. Exit");

			while (!bonchoix) {
				System.out.println("Veuillez faire un choix :");
				strInt = sc.nextInt();

				if (choix.contains(strInt)) {
					bonchoix = true;
				} else {
					System.out.println("ERROR Veuillez faire un choix parmis les propositions ci-dessous");
					System.out.println("1. Consulter un livre via son isbn");
					System.out.println("2. Consulter la liste des livres d'un auteur");
					System.out.println("3. Consulter les informations d'un lecteur avec son identifiant");
					System.out.println("4. Consulter les lecteurs avec un nom");
					System.out.println("5. Consulter les prets d'un lecteur");
					System.out.println("6. Consulter les pret d'un livre");
					System.out.println("7. Consulter un pret par isbn et identifiant lecteur");
					System.out.println("8. Consulter les prets par date de pret");
					System.out.println("9. Consulter les prets en cours");
					System.out.println("10. Exit");

				}
			}

			boolean succes = false;
			String commandString = "";
			int c = 0;
			URL req;
			HttpURLConnection con = null;
			String isbn = "";
			String auteur;
			String titre;
			String editeur;
			Long edition;
			Long idf = new Long(-1);
			String genre;
			String nom;
			String prenom;
			String date_naissance;
			String adresse;
			String datepret;
			String dateretour;
			JSONObject jo = null;
			JSONArray joArray = null;
			if (strInt == 1) {
				while (!succes) {
					System.out.println("Veuillez indiquer l'isbn (q pour exit): ");
					if(c==0) {
						sc.nextLine();
						c++;
					}
					commandString = sc.nextLine();
					if(!commandString.equals("q")) {

						// CODE POUR RÉCUPÉRER CHAQUE CHAMP SEUL
						// METTRE Long id = jo.getLong("id") si on veur récupérer un Long au lieu de string

						jo = urlRetObject("http://localhost:8003/livres/isbn/" + commandString);

					}

					if (jo!=null || commandString.equals("q")) {
						//Afficher
						toStringObject("livre",jo);
						if (commandString.equals("q")) {
							System.out.println("----- Fin de l'opération de recherche de livre par isbn, exit...");
						}
						succes = true;
						commandString = "";
					} else {
						System.out.println("Mauvais isbn, le livre n'existe pas dans notre base ! Pour exit tapez q");
						commandString = "";
					}
				}
			}
			if (strInt == 2) {
				while (!succes) {
					System.out.println("Veuillez indiquer l'auteur (q pour exit): ");
					if(c==0) {
						sc.nextLine();
						c++;
					}
					commandString = sc.nextLine();
					if(!commandString.equals("q")) {

						// CODE POUR RÉCUPÉRER CHAQUE CHAMP SEUL
						// METTRE Long id = jo.getLong("id") si on veur récupérer un Long au lieu de string
						try {
							joArray = urlRetArray("http://localhost:8003/livres/auteur/" + commandString.replaceAll(" ", "%20"));
							if (joArray.length() > 0 || commandString.equals("q")) {
								for (int i = 0; i < joArray.length(); i++) {
									jo = joArray.getJSONObject(i);
									toStringObject("livre", jo);
								}
								if (commandString.equals("q")) {
									System.out.println("----- Fin de l'opération de recherche de livres d'un auteur, exit...");
								}
								succes = true;
							} else {
								System.out.println("Mauvais auteur, aucun livre n'existe pas dans notre base ! Pour exit tapez q");
							}
						} catch (JSONException e) {
							System.out.println(e.getMessage());
						}
					}
					else{
						succes = true;
					}
				}
			}
			if (strInt == 3) {
				while (!succes) {
					System.out.println("Veuillez indiquer l'identifiant du lecteur (q pour exit): ");
					if(c==0) {
						sc.nextLine();
						c++;
					}
					commandString = sc.nextLine();
					if(!commandString.equals("q")) {

						// CODE POUR RÉCUPÉRER CHAQUE CHAMP SEUL
						// METTRE Long id = jo.getLong("id") si on veur récupérer un Long au lieu de string

						jo = urlRetObject("http://localhost:8001/lecteurs/idf/" + commandString);


					}

					if (jo!=null || commandString.equals("q")) {
						toStringObject("lecteur",jo);
						if (commandString.equals("q")) {
							System.out.println("----- Fin de l'opération de recherche de lecteur par idf, exit...");
						}
						succes = true;
					} else {
						System.out.println("Mauvais idf, le lecteur n'existe pas dans notre base ! Pour exit tapez 0");
					}
				}
			}
			if (strInt == 4) {
				while (!succes) {
					System.out.println("Veuillez indiquer le nom (q pour exit): ");
					if(c==0) {
						sc.nextLine();
						c++;
					}
					commandString = sc.nextLine();
					if(!commandString.equals("q")) {
						// CODE POUR RÉCUPÉRER CHAQUE CHAMP SEUL
						// METTRE Long id = jo.getLong("id") si on veur récupérer un Long au lieu de string
						try {
							joArray = urlRetArray("http://localhost:8001/lecteurs/nom/" + commandString);
							if (joArray.length() > 0 || commandString.equals("q")) {
								for (int i = 0; i < joArray.length(); i++) {
									jo = joArray.getJSONObject(i);
									toStringObject("lecteur",jo);
								}
								if (commandString.equals("q")) {
									System.out.println("----- Fin de l'opération de recherche de lecteurs par nom, exit...");
								}
								succes = true;
							} else {
								System.out.println("Mauvais nom, aucun lecteur n'existe pas dans notre base ! Pour exit tapez q");
							}
						} catch (JSONException e) {
							System.out.println(e.getMessage());
						}
					}
					else{
						succes = true;
					}
				}
			}
			if(strInt==5){
				while (!succes) {
					System.out.println("Veuillez indiquer l'identifiant du lecteur (q pour exit): ");
					if(c==0) {
						sc.nextLine();
						c++;
					}
					commandString = sc.nextLine();
					if(!commandString.equals("q")) {

						// CODE POUR RÉCUPÉRER CHAQUE CHAMP SEUL
						// METTRE Long id = jo.getLong("id") si on veur récupérer un Long au lieu de string
						try {
							joArray = urlRetArray("http://localhost:8002/prets/lecteur/" + commandString);
							if (joArray.length() > 0 || commandString.equals("q")) {
								for (int i = 0; i < joArray.length(); i++) {
									jo = joArray.getJSONObject(i);
									toStringObject("pret",jo);
								}
								if (commandString.equals("q")) {
									System.out.println("----- Fin de l'opération de recherche de prets par lecteur, exit...");
								}
								succes = true;
							} else {
								System.out.println("Mauvais idf, le pret n'existe pas dans notre base ! Pour exit tapez q");
							}
						} catch (JSONException e) {
							System.out.println(e.getMessage());
						}
					}
					else{
						succes = true;
					}
				}
			}
			if(strInt==6){
				while (!succes) {
					System.out.println("Veuillez indiquer l'isbn (q pour exit): ");
					if(c==0) {
						sc.nextLine();
						c++;
					}
					commandString = sc.nextLine();
					if(!commandString.equals("q")) {

						// CODE POUR RÉCUPÉRER CHAQUE CHAMP SEUL
						// METTRE Long id = jo.getLong("id") si on veur récupérer un Long au lieu de string
						try {
							joArray = urlRetArray("http://localhost:8002/prets/isbn/" + commandString);
							if (joArray.length() > 0 || commandString.equals("q")) {
								for (int i = 0; i < joArray.length(); i++) {
									jo = joArray.getJSONObject(i);
									toStringObject("pret",jo);
								}
								if (commandString.equals("q")) {
									System.out.println("----- Fin de l'opération de recherche de pret par isbn, exit...");
								}
								succes = true;
							} else {
								System.out.println("Mauvais isbn, le pret n'existe pas dans notre base ! Pour exit tapez q");
							}
						} catch (JSONException e) {
							System.out.println(e.getMessage());
						}
					}
					else{
						succes = true;
					}
				}
			}
			if(strInt == 7){
				while (!succes) {
					System.out.println("Veuillez indiquer l'isbn (q pour exit): ");
					if(c==0) {
						sc.nextLine();
						c++;
					}
					commandString = sc.nextLine();
					System.out.println("Veuillez indiquer l'identifiant lecteur (q pour exit): ");
					String commandString2 = sc.nextLine();
					if(!commandString.equals("q") && !commandString2.equals("q")) {

						// CODE POUR RÉCUPÉRER CHAQUE CHAMP SEUL
						// METTRE Long id = jo.getLong("id") si on veur récupérer un Long au lieu de string

						jo = urlRetObject("http://localhost:8002/prets/lecteur/"+commandString2+"/isbn/" + commandString);


					}

					if (jo!=null || commandString.equals("q") || commandString2.equals("q")) {
						//Afficher
						toStringObject("pret",jo);
						if (commandString.equals("q") || commandString2.equals("q")) {
							System.out.println("----- Fin de l'opération de recherche de pret par isbn/identifiant, exit...");
						}
						succes = true;
					} else {
						System.out.println("Mauvais couple isbn/identifiant lecteur, le pret n'existe pas dans notre base ! Pour exit tapez q");
					}
				}
			}
			if(strInt==8){
				while (!succes) {
					System.out.println("Veuillez indiquer la date de pret (q pour exit): ");
					if(c==0) {
						sc.nextLine();
						c++;
					}
					commandString = sc.nextLine();
					if(!commandString.equals("q")) {

						// CODE POUR RÉCUPÉRER CHAQUE CHAMP SEUL
						// METTRE Long id = jo.getLong("id") si on veur récupérer un Long au lieu de string
						try {
							joArray = urlRetArray("http://localhost:8002/prets/date/" + commandString);
							if (joArray.length() > 0 || commandString.equals("q")) {
								for (int i = 0; i < joArray.length(); i++) {
									jo = joArray.getJSONObject(i);
									toStringObject("pret",jo);
								}
								if (commandString.equals("q")) {
									System.out.println("----- Fin de l'opération de recherche de pret par date de pret, exit...");
								}
								succes = true;
							} else {
								System.out.println("Mauvaise date de pret, aucun pret n'existe pas dans notre base ! Pour exit tapez q");
							}
						} catch (JSONException e) {
							System.out.println(e.getMessage());
						}
					}
					else{
						succes = true;
					}
				}
			}
			if(strInt==9){
				while (!succes) {
					System.out.println("Vous allez voir les prets en cours, tapez entrer (q pour exit): ");
					if(c==0) {
						sc.nextLine();
						c++;
					}
					commandString = sc.nextLine();
					if(!commandString.equals("q")) {

						// CODE POUR RÉCUPÉRER CHAQUE CHAMP SEUL
						// METTRE Long id = jo.getLong("id") si on veur récupérer un Long au lieu de string
						try {
							joArray = urlRetArray("http://localhost:8002/prets/encours/");
							if (joArray.length() > 0 || commandString.equals("q")) {
								for (int i = 0; i < joArray.length(); i++) {
									jo = joArray.getJSONObject(i);
									toStringObject("pret",jo);
								}
								if (commandString.equals("q")) {
									System.out.println("----- Fin de l'opération de recherche de pret par date de pret, exit...");
								}
								succes = true;
							} else {
								System.out.println("Aucun pret en cours n'existe pas dans notre base ! Pour exit tapez q");
							}
						} catch (JSONException e) {
							System.out.println(e.getMessage());
						}
					}
					else{
						succes = true;
					}
				}
			}
			if(strInt==10){
				endProgram = true;
			}
			bonchoix = false;
		}


		System.out.println("----- Fin du scenario !!");
		System.exit(1);

	}

	public static JSONArray urlRetArray(String url) throws IOException {
		StringBuffer response= new StringBuffer();
		JSONArray joArray=null;
		req = new URL(url);
		con = (HttpURLConnection) req.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}else {
			System.out.println("GET request not worked");
		}
		try {
			joArray =  new JSONArray(response.toString());
		}catch(JSONException j){
			System.out.println(j.getMessage());
		}
		return  joArray;
	}

	public static JSONObject urlRetObject(String url) throws IOException {
		StringBuffer response= new StringBuffer();
		JSONObject jo=null;
		req = new URL(url);
		con = (HttpURLConnection) req.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}else {
			System.out.println("GET request not worked");
		}
		try {
			jo =  new JSONObject(response.toString());
		}catch(JSONException j){
			System.out.println(j.getMessage());
		}
		return  jo;
	}

	public static void toStringObject(String object, JSONObject jo){
		try {
			if (object.equals("lecteur")) {
				Long idf = jo.getLong("idf");
				String genre = jo.getString("genre");
				String nom = jo.getString("nom");
				String prenom = jo.getString("prenom");
				String date_naissance = jo.getString("date_naissance");
				String adresse = jo.getString("adresse");

				System.out.println("Identifiant lecteur : "+idf);
				System.out.println("Identité : "+genre+" "+nom+" "+prenom);
				System.out.println("Date de naissance : "+date_naissance);
				System.out.println("Adresse : "+adresse);
				System.out.println("------------------------------------------");
			}
			if (object.equals("pret")) {
				Long lecteur = jo.getLong("lecteur");
				String isbn = jo.getString("isbn");
				String datepret = jo.getString("date_pret");
				String dateretour = jo.getString("date_retour");

				System.out.println("Isbn : "+isbn);
				System.out.println("Identifiant Lecteur : "+lecteur);
				System.out.println("Date du pret : "+datepret);
				if (!dateretour.equals("null")) {
					System.out.println("Date du retour : "+dateretour);
				}
				else{
					System.out.println("Le livre n'est pas encore retourné");
				}
				System.out.println("------------------------------------------");
			}
			if (object.equals("livre")) {
				String titre = jo.getString("titre");
				String auteur = jo.getString("auteur");
				String isbn = jo.getString("isbn");
				String editeur = jo.getString("editeur");
				Long edition = jo.getLong("edition");

				System.out.println("Titre : "+titre);
				System.out.println("Auteur : "+auteur);
				System.out.println("Isbn : "+isbn);
				System.out.println("Editeur : "+editeur);
				System.out.println("Edition : "+edition);
				System.out.println("------------------------------------------");
			}
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
	}

}
