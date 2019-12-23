package SampleGame;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage; 

public class Main extends Application {
	private static final Text NULL = null;
	private static final Castle NULLL = null;
	private Scene scene;
	private Input input;
	private AnimationTimer gameLoop;
	
	private ArrayList<Castle> tabOfCastle = new ArrayList<>();
	private ArrayList<Text> tabOfText = new ArrayList<>();
	private ArrayList<String> tabOfProduction = new ArrayList<>();
	// 0 : piquier | 1 : chevalier | 2 : onagre
	private ArrayList<Integer> ostUnites = new ArrayList<>();
	
	
	private Text status = NULL;
	private Text upgrade = new Text(" ");
	private Text sendTroupes = NULL;
	private Text targetText = NULL;
	private Text cancel = NULL;
	private Text troupesText = NULL;
	private Text upgradeTroupe = NULL;
	private Text reduceTroupe = NULL;
	private Text validate = NULL;
	
	// Troupes a envoyer dans l'OST
	private int p = 0;
	private int c = 0;
	private int o = 0;
	
	Rectangle createInfos;
	
	Pane root;
	
	Castle selectedCastle;
	Castle targetCastle = NULLL;
	
	@Override 
	public void start(Stage primaryStage) throws Exception { 
		
		root = new Pane(); 
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT, Color.LIGHTGREEN); 
		primaryStage.setTitle("DukesOfTheRealm"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
		
		// create castle
		tabOfCastle = Build(1,5);
		printAllCastle(tabOfCastle, root);		
		
		loadGame();
		
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// Ameliore le revenu + tresor du chateau
				tabOfCastle.forEach(castle -> RunACastle.updateRevenu(castle));
				tabOfCastle.forEach(castle -> RunACastle.updateTresor(castle)); 
				
				// Affiche les infos du chateau lors que l'on clique dessus
				tabOfCastle.forEach(castle -> castle.getRectCastle().setOnMouseClicked(e -> {
					if(targetText != NULL && targetCastle == NULLL) {
						targetCastle = castle;
						targetText.setText("Cible : " + targetCastle.getName());
					}
					else if(targetText == NULL) {
						tabOfText.clear();
						createPrintInfos();
						printInfos(castle);
						selectedCastle = castle;
					}
				}));
				
				// Interaction avec les zones de texte
				tabOfText.forEach(text -> text.setOnMouseClicked(e -> { 
					// Ameliore le niveau du chateau
					if(text == upgrade) {
						//tabOfProduction.add(text.getText());
						RunACastle.updateNiveau(selectedCastle); 
						status.setText(" ");
						printInfos(selectedCastle);
					}
					// Cree le texte du choix de la cible et du bouton annuler
					if(text == sendTroupes && targetText == NULL && cancel == NULL) {
						createInfos.setHeight(180);
						createSendTroupes();
					}
					// Annulation de l'envoi de troupes
					if(text == cancel || text == validate) {
						// Reinitialisation des variables troupes
						p = 0;
						c = 0;
						o = 0;
						// Ajoute les troupes a l'OST
						if(text == validate) {
							ostUnites.add(p);
							ostUnites.add(c);
							ostUnites.add(o);
							System.out.println("Ajout des troupes à l'OST reussi");
						}
						// Enleve le texte des troupes
						if(troupesText != NULL) {
							troupesText.setText(" ");
							troupesText = NULL;
						}
						// Enleve le texte chateau cible
						targetText.setText(" ");
						targetText = NULL;
						// Enleve le texte annuler
						cancel.setText(" ");
						cancel = NULL;
						tabOfText.remove(cancel);
						// Enleve le texte valider
						validate.setText(" ");
						validate = NULL;
						tabOfText.remove(validate);
						// Enleve le chateau cible
						targetCastle = NULLL;
						// Remettre la taille du rectangle a sa taille d'origine
						createInfos.setHeight(150);
					}
				}));
				
				
				// Modifications visuelles des infos du chateau selectionne
				if(status != NULL) {
					if(selectedCastle.getTresor() < 1000 * selectedCastle.getNiveau()) {
						upgrade.setText(" ");
					}
					else if(selectedCastle.getTresor() >= 1000 * selectedCastle.getNiveau() && selectedCastle.getName() == "Player"){
						upgrade.setText("Améliorer");
					}
					status.setText(selectedCastle.getName() + 
					"\nNiveau : " + selectedCastle.getNiveau() +
					"\nRevenu : " + selectedCastle.getRevenu() +
					"\nTroupes : " + selectedCastle.getTabTroupes().size() +
					"\nTresor : " + selectedCastle.getTresor() + " florins" +
					"\nProduction : " + tabOfProduction +
					"\n_________________");
				}
				
				if(targetCastle != NULLL && troupesText == NULL) {
					createTroupes();
					createInfos.setHeight(250);
				}
				
			}

		};
		gameLoop.start();
	}
	
	private void loadGame() {
		input = new Input(scene);
		input.addListeners();
	}
	
	// Creation du rectangle contenant les informations du chateau
	private void createPrintInfos() {
		createInfos = new Rectangle(10,10,200,150);
		createInfos.setArcWidth(40);
		createInfos.setArcHeight(40);
		createInfos.setFill(Color.WHITE);
		root.getChildren().add(createInfos);
	}
	
	// Choix d'une cible + bouton annuler
	private void createSendTroupes() {
		targetText = new Text("Cible : ?");
		targetText.setLayoutX(30);
		targetText.setLayoutY(165);
		root.getChildren().add(targetText);
		
		cancel = new Text("Annuler");
		cancel.setLayoutX(130);
		cancel.setLayoutY(165);
		tabOfText.add(cancel);
		root.getChildren().add(cancel);
	}
	
	// Affiche les troupes disponibles et à envoyer
	private void createTroupes() {
		upgradeTroupe = new Text("<- ");
		reduceTroupe = new Text(" ->");
		troupesText = new Text("Piquier : " + upgradeTroupe.getText() + p + " / " + RunACastle.countTroupes("Piquier", selectedCastle.getTabTroupes()) + reduceTroupe.getText() +
				"\nChevalier : " + upgradeTroupe.getText() + p + " / " + RunACastle.countTroupes("Chevalier", selectedCastle.getTabTroupes()) + reduceTroupe.getText() +
				"\nOnagre : " + upgradeTroupe.getText() + p + " / " + RunACastle.countTroupes("Onagre", selectedCastle.getTabTroupes()) + reduceTroupe.getText());
		troupesText.setLayoutX(30);
		troupesText.setLayoutY(190);
		root.getChildren().add(troupesText);
		
		validate = new Text("Valider");
		validate.setLayoutX(130);
		validate.setLayoutY(245);
		tabOfText.add(validate);
		root.getChildren().add(validate);
	}
	
	// Affichage des informations du chateau
	private void printInfos(Castle c) {
		status = new Text(c.getName() + 
				"\nNiveau : " + c.getNiveau() +
				"\nRevenu : " + c.getRevenu() +
				"\nTroupes : " + c.getTabTroupes().size() +
				"\nTresor : " + c.getTresor() + " florins" +
				"\nProduction : " + tabOfProduction +
				"\n_________________"); 
		status.setLayoutX(30);
		status.setLayoutY(30);
		root.getChildren().add(status);
		
		// Verifie si Ameliorer est dans tabOfText pour eviter la superposition du texte
		if(checkText(upgrade) == false) {
			upgrade = new Text();
			upgrade.setLayoutX(130);
			upgrade.setLayoutY(45);
			tabOfText.add(upgrade);
			root.getChildren().add(upgrade);
		}
		
		// Verifie si Envoyer des troupes est dans tabOfText pour eviter la superposition du texte
		if(checkText(sendTroupes) == false && c.getName() == "Player") {
			sendTroupes = new Text("Envoyer des troupes");
			sendTroupes.setLayoutX(30);
			sendTroupes.setLayoutY(150);
			tabOfText.add(sendTroupes);
			root.getChildren().add(sendTroupes);
		}
	}
	
	// Verifie si le text "Ameliorer" est dans le tableau pour le recreer
	private boolean checkText(Text tToCheck) {
		for(Text t : tabOfText) {
			if(t == tToCheck) {
				return true;
			}
		}
		return false;
	}
	
	private ArrayList<Castle> Build(int nbDuc, int nbBaron){
		ArrayList<Castle> tabOfCastle = new ArrayList<Castle>();
		tabOfCastle.add(new Castle("Player", tabOfCastle));
		for(int i=0 ; i<nbDuc; i++) {
			tabOfCastle.add(new Castle("Duc", tabOfCastle));
		}
		for(int i=0 ; i<nbBaron; i++) {
			tabOfCastle.add(new Castle("Baron", tabOfCastle));
		}
		return tabOfCastle;
	}
	
	private void printAllCastle(ArrayList<Castle> tabOfCastle, Pane root) {
		for(int i=0; i<tabOfCastle.size(); i++) {
			Rectangle castle = tabOfCastle.get(i).getRectCastle();	
			Rectangle door = tabOfCastle.get(i).getRectDoor();	
			int r = tabOfCastle.get(i).getColor().r;
			int g = tabOfCastle.get(i).getColor().g;
			int b = tabOfCastle.get(i).getColor().b;
			castle.setFill(Color.rgb(r, g, b));
			door.setFill(Color.BROWN); 
			root.getChildren().add(castle); 
			root.getChildren().add(door); 
		}
		
	}
	
	public static void main(String[] args) {
		Application.launch(args); 
	}
	
	
}


