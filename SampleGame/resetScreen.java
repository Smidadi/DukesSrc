package SampleGame;

import java.util.ArrayList;
import javafx.scene.text.Text;

import javafx.scene.layout.Pane;

public class resetScreen extends Main {
	static void resetTextScreen(Pane root, ArrayList<Text> tabOfText, ArrayList<OST> tabOfOST) {
		// Message pour le jeu en pause
		textPause = NULL;
		// Barre d'informations du chateau
		status = NULL;
		// (appuyer) pour ameliorer pour le niveau
		upgrade = new Text(" ");
		// (appuyer) pour envoyer des troupes
		sendTroupes = NULL;
		// Cible de l'attaque
		targetText = NULL;
		// (appuyer) pour annuler
		cancel = NULL;
		// choisir ses troupes
		troupesText = NULL;
		// (appuyer) pour valider
		validate = NULL;
		// (appuyer) pour produire troupes ou ameliorer
		product = NULL;
		inProduction = NULL;
		// (appuyer) pour ajouter des troupes a la production
		piquier = NULL;
		chevalier = NULL;
		onagre = NULL;
		remove = NULL;
		allProduction = NULL;
		// (appuyer) pour augmenter / diminuer nombre de troupse a envoyer
		up = NULL;
		down = NULL;
		// (appuyer)
		save = NULL;
		load = NULL;
		
		troupeToColor = NULL;
		
		selectedCastle = NULLL;
		
		tabOfOST.clear();
		tabOfText.clear();
	}	
}
