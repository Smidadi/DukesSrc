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
	private int ostUnites[] = new int[3];
	
	// Barre d'informations du chateau
	private Text status = NULL;
	// (appuyer) pour ameliorer pour le niveau
	private Text upgrade = new Text(" ");
	// (appuyer) pour envoyer des troupes
	private Text sendTroupes = NULL;
	// Cible de l'attaque
	private Text targetText = NULL;
	// (appuyer) pour annuler
	private Text cancel = NULL;
	// choisir ses troupes
	private Text troupesText = NULL;
	private Text upgradeTroupe = NULL;
	private Text reduceTroupe = NULL;
	// (appuyer) pour valider
	private Text validate = NULL;
	// (appuyer) pour produire troupes ou ameliorer
	private Text product = NULL;
	private Text inProduction = NULL;
	// (appuyer) pour ajouter des troupes a la production
	private Text piquier = NULL;
	private Text chevalier = NULL;
	private Text onagre = NULL;
	private Text remove = NULL;
	
	private Text upLine = new Text("\n_________________");
	
	// Troupes a envoyer dans l'OST
	private int p = 0;
	private int c = 0;
	private int o = 0;
	
	private int countTour = 0;
	private int countTourProd = 0;
	private int countSec = 0;
	
	Rectangle createInfos;
	Rectangle createProduct;
	
	Pane root;
	
	OST ost = new OST();
	
	Castle selectedCastle;
	Castle targetCastle = NULLL;
	
	@Override 
	public void start(Stage primaryStage) throws Exception { 
		
		root = new Pane(); 
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT, Color.rgb(154,205,50)); 
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
				if(countSec == 30) {
					tabOfCastle.forEach(castle -> RunACastle.updateTresor(castle)); 
				}
				
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
						if(!tabOfProduction.contains("Améliorer")) {
							RunACastle.reduceTresor(selectedCastle);
							tabOfProduction.add(text.getText());
							status.setText(" ");
						}
					}
					// Cree le texte du choix de la cible et du bouton annuler
					if(text == sendTroupes && targetText == NULL && cancel == NULL) {
						createInfos.setHeight(180);
						createSendTroupes();
					}
					// Cree le panneau pour la production des troupes
					if(text == product) {
						createProduction();
					}
					if(text == piquier) {
						tabOfProduction.add("Piquier");
					}
					if(text == chevalier) {
						tabOfProduction.add("Chevalier");
					}
					if(text == onagre) {
						tabOfProduction.add("onagre");
					}
					if(text == remove) {
						tabOfProduction.remove(tabOfProduction.size() - 1);
					}
					// Annulation de l'envoi de troupes
					if(text == cancel || text == validate) {
						if(inProduction != NULL) {
							inProduction.setText(" ");
							inProduction = NULL;
							root.getChildren().remove(createProduct);
							
							piquier.setText(" ");
							piquier = NULL;
							
							chevalier.setText(" ");
							chevalier = NULL;
							
							onagre.setText(" ");
							onagre = NULL;
							
							remove.setText(" ");
							remove = NULL;
						}
						else {
							// Reinitialisation des variables troupes
							p = 0;
							c = 0;
							o = 0;
							// Ajoute les troupes a l'OST
							if(text == validate) {
								ost.setTargetName(targetCastle.getName());
								ostUnites[0] = p;
								ostUnites[1] = c;
								ostUnites[2] = o;
								ost.setOstUnites(ostUnites);
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
							// Enleve le chateau cible
							targetCastle = NULLL;
							// Remettre la taille du rectangle a sa taille d'origine
							createInfos.setHeight(150);
						}						
						// Enleve le texte annuler
						cancel.setText(" ");
						cancel = NULL;
						tabOfText.remove(cancel);
						// Enleve le texte valider
						if(validate != NULL) {
							validate.setText(" ");
							validate = NULL;
							tabOfText.remove(validate);
						}
						
					}
				}));
				
				
				// Modifications visuelles des infos du chateau selectionne
				if(status != NULL) {
					if(selectedCastle.getTresor() < 1000 * selectedCastle.getNiveau()) {
						upgrade.setText(" ");
					}
					else if(selectedCastle.getTresor() >= 1000 * selectedCastle.getNiveau() && selectedCastle.getName() == "Player" && !tabOfProduction.contains("Améliorer")){
						upgrade.setText("Améliorer");
					}
					status.setText(selectedCastle.getName() + 
					"\nNiveau : " + selectedCastle.getNiveau() +
					"\nRevenu : " + selectedCastle.getRevenu() +
					"\nTroupes : " + selectedCastle.getTabTroupes().size() +
					"\nTresor : " + selectedCastle.getTresor() + " florins" +
					"\nProduction : " + tabOfProduction.size() +
					upLine.getText());
					
					if(inProduction != NULL) {
						inProduction.setText("Que voulez-vous produire ?" +
											 "\n\n\nEn production : " + tabOfProduction);
					}
				}
				
				if(targetCastle != NULLL && troupesText == NULL) {
					createTroupes();
					createInfos.setHeight(250);
				}
				
				if(!tabOfProduction.isEmpty()) {
					if(tabOfProduction.get(0) == "Améliorer" && countTourProd == (100 + 50*selectedCastle.getNiveau())) {
						RunACastle.updateNiveau(selectedCastle); 
						countTourProd = 0;
						tabOfProduction.remove(0);
					}
					
					if(countSec == 60) {
						countTourProd++;
					}

				}
				// Compte les secondes du jeu
				if(countSec == 60) {
					countTour++;
					countSec = 0;
				}
				countSec++;
				
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
	
	// Creation rectangle + texte pour produire des troupes ou ameliorer
	private void createProduction() {
		createProduct = new Rectangle(Settings.SCENE_WIDTH/3,Settings.SCENE_HEIGHT/4,500,300);
		createProduct.setArcWidth(40);
		createProduct.setArcHeight(40);
		createProduct.setFill(Color.WHITE);
		root.getChildren().add(createProduct);
		
		inProduction = new Text(" ");
		inProduction.setLayoutX(createProduct.getX() + 20);
		inProduction.setLayoutY(createProduct.getY() + 30);
		root.getChildren().add(inProduction);
		
		cancel = new Text("Quitter");
		cancel.setLayoutX(createProduct.getX() + 400);
		cancel.setLayoutY(createProduct.getY() + 30);
		tabOfText.add(cancel);
		root.getChildren().add(cancel);
		
		validate = new Text("Valider");
		validate.setLayoutX(createProduct.getX() + 400);
		validate.setLayoutY(createProduct.getY() + 275);
		tabOfText.add(validate);
		root.getChildren().add(validate);
		
		piquier = new Text("> Piquier <");
		piquier.setLayoutX(createProduct.getX() + 20);
		piquier.setLayoutY(createProduct.getY() + 150);
		tabOfText.add(piquier);
		root.getChildren().add(piquier);
		
		chevalier = new Text("> Chevalier <");
		chevalier.setLayoutX(createProduct.getX() + 220);
		chevalier.setLayoutY(createProduct.getY() + 150);
		tabOfText.add(chevalier);
		root.getChildren().add(chevalier);
		
		onagre = new Text("> Onagre <");
		onagre.setLayoutX(createProduct.getX() + 420);
		onagre.setLayoutY(createProduct.getY() + 150);
		tabOfText.add(onagre);
		root.getChildren().add(onagre);
		
		remove = new Text("> Annuler la dernière production <");
		remove.setLayoutX(createProduct.getX() + 20);
		remove.setLayoutY(createProduct.getY() + 200);
		tabOfText.add(remove);
		root.getChildren().add(remove);
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
		if(!tabOfText.contains(upgrade)) {
			upgrade = new Text();
			upgrade.setLayoutX(140);
			upgrade.setLayoutY(45);
			tabOfText.add(upgrade);
			root.getChildren().add(upgrade);
		}
		
		// Verifie si Envoyer des troupes est dans tabOfText pour eviter la superposition du texte
		if(!tabOfText.contains(sendTroupes) && c.getName() == "Player") {
			sendTroupes = new Text("Envoyer des troupes");
			sendTroupes.setLayoutX(30);
			sendTroupes.setLayoutY(150);
			tabOfText.add(sendTroupes);
			root.getChildren().add(sendTroupes);
		}
		
		// Verifie si Produire est dans tabOfText pour eviter la superposition du texte
		if(!tabOfText.contains(product) && c.getName() == "Player") {
			product = new Text("Produire");
			product.setLayoutX(140);
			product.setLayoutY(110);
			tabOfText.add(product);
			root.getChildren().add(product);
		}
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


