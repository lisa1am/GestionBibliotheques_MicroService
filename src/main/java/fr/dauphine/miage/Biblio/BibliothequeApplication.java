package fr.dauphine.miage.Biblio;


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

	public static void main(String[] args) {

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

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}
		} catch (IOException e) {
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
