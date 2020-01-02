package SampleGame;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
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
	private ArrayList<OST> tabOfOST = new ArrayList<>();
		
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
	private Text allProduction = NULL;
	// Augmenter / diminuer nombre de troupse a envoyer
	private Text up = NULL;
	private Text down = NULL;
	
	private Text upLine = new Text("\n_________________");
	
	private String troupeToChange;
	private Text troupeToColor = NULL;
	
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
		
	Castle selectedCastle;
	Castle targetCastle = NULLL;
	Castle target;
	
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
				//if(countSec == 30) {
					tabOfCastle.forEach(castle -> {
						if(castle.getType() == "Baron") {
							RunACastle.updateTresorBaron(castle);
						}
						else {
							RunACastle.updateTresor(castle); 
						}
					});
				//}
				
				// Affiche les infos du chateau lors que l'on clique dessus
				tabOfCastle.forEach(castle -> castle.getRectCastle().setOnMouseClicked(e -> {
					// Choix de la cible du chateau
					if(targetText != NULL && targetCastle == NULLL) {
						targetCastle = castle;
						target = castle;
						targetText.setText("Cible : " + targetCastle.getName());
					}
					else if(targetText == NULL && inProduction == NULL) {
						tabOfText.clear();
						createPrintInfos();
						printInfos(castle);
						selectedCastle = castle;
					}
				}));
				
				// Interaction avec les zones de texte
				tabOfText.forEach(text -> text.setOnMouseClicked(e -> { 
					// Ameliore le niveau du chateau
					if(selectedCastle.getOwner() == "Player") {
						if(text == upgrade) {
							if(!selectedCastle.getTabOfProduction().contains("Améliorer")) {
								RunACastle.reduceTresor(selectedCastle);
								selectedCastle.getTabOfProduction().add("Améliorer");
							}
							upgrade.setText(" ");
						}
						// Cree le texte du choix de la cible et du bouton annuler
						if(text == sendTroupes && targetText == NULL && cancel == NULL) {
							createInfos.setHeight(180);
							createSendTroupes();
						}
						// Cree le panneau pour la production des troupes
						if(text == product && targetText == NULL) {
							createProduction();
						}
						// Gere la production des troupes
						if(inProduction != NULL) {
							if(text == piquier && selectedCastle.getTresor() >= 100 && selectedCastle.getTabOfProduction().size() < 7) {
								selectedCastle.setTresor(selectedCastle.getTresor() - 100);
								//selectedCastle.setTimeOfProduction(new Piquier(selectedCastle.getName()).getTemps());
								selectedCastle.getTabOfProduction().add("Piquier");
							}
							if(text == chevalier && selectedCastle.getTresor() >= 500 && selectedCastle.getTabOfProduction().size() < 7) {
								selectedCastle.setTresor(selectedCastle.getTresor() - 500);
								//selectedCastle.setTimeOfProduction(new Chevalier(selectedCastle.getName()).getTemps());
								selectedCastle.getTabOfProduction().add("Chevalier");
							}
							if(text == onagre && selectedCastle.getTresor() >= 1000 && selectedCastle.getTabOfProduction().size() < 7) {
								selectedCastle.setTresor(selectedCastle.getTresor() - 1000);
								//selectedCastle.setTimeOfProduction(new Onagre(selectedCastle.getName()).getTemps());
								selectedCastle.getTabOfProduction().add("Onagre");
							}
						}
						// Gere le cote hors production de troupes
						if(inProduction == NULL) {
							if((text == piquier || text == chevalier || text == onagre) && up == NULL && down == NULL) {
								createButtonUpDown();
							}
							if(troupeToColor != NULL) {
								troupeToColor.setFill(Color.BLACK);
							}
							if(text == piquier) {
								troupeToChange = "Piquier";
								troupeToColor = piquier;
							}
							else if(text == chevalier) {
								troupeToChange = "Chevalier";
								troupeToColor = chevalier;
							}
							else if(text == onagre) {
								troupeToChange = "Onagre";
								troupeToColor = onagre;
							}
							if(troupeToColor != NULL) {
								troupeToColor.setFill(Color.DARKTURQUOISE);
							}
							// Augmentation du nombre de troupes
							if(text == up) {
								switch (troupeToChange) {
								case "Piquier" :
									if(p != RunACastle.countTroupes("Piquier", selectedCastle.getTabTroupes())) {
										p++;
										piquier.setText("> Piquier < : " + p + " / " + RunACastle.countTroupes("Piquier", selectedCastle.getTabTroupes()));
									}
									break;
								case "Chevalier" :
									if(c != RunACastle.countTroupes("Chevalier", selectedCastle.getTabTroupes())) {
										c++;
										chevalier.setText("> Chevalier < : " + c + " / " + RunACastle.countTroupes("Chevalier", selectedCastle.getTabTroupes()));
									}
									break;
								case "Onagre" :
									if(o != RunACastle.countTroupes("Onagre", selectedCastle.getTabTroupes())) {
										o++;
										onagre.setText("> Onagre < : " + o + " / " + RunACastle.countTroupes("Onagre", selectedCastle.getTabTroupes()));
									}
									break;
								}
							}
							if(text == down) {
								switch (troupeToChange) {
								case "Piquier" :
									if(p != 0) {
										p--;
										piquier.setText("> Piquier < : " + p + " / " + RunACastle.countTroupes("Piquier", selectedCastle.getTabTroupes()));
									}
									break;
								case "Chevalier" :
									if(c != 0) {
										c--;
										chevalier.setText("> Chevalier < : " + c + " / " + RunACastle.countTroupes("Chevalier", selectedCastle.getTabTroupes()));
									}
									break;
								case "Onagre" :
									if(o != 0) {
										o--;
										onagre.setText("> Onagre < : " + o + " / " + RunACastle.countTroupes("Onagre", selectedCastle.getTabTroupes()));
									}
									break;
								}
							}
						}
						
						// Gere annulation du dernier element de la liste
						if(text == remove && !selectedCastle.getTabOfProduction().isEmpty()) {
							RunACastle.removeProduction(selectedCastle, selectedCastle.getTabOfProduction());
							selectedCastle.getTabOfProduction().remove(selectedCastle.getTabOfProduction().size() - 1);
							product.setText("> Produire <");
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
								// Ajoute les troupes a l'OST
								if(text == validate && (p != 0 || c != 0 || o != 0)) {
									// 0 : onagre | 1 : piquier | 2 : chevalier
									int tab[] = new int[3];
									tab[0] = o;
									tab[1] = p;
									tab[2] = c;
									OST ost = new OST(targetCastle.getName(),tab, "Player", tabOfCastle);
									// Affichage des ost
									ArrayList<GeometricForm> tabOfGeometricForm = GeometricForm.tabOfGeometricForm(ost, tabOfCastle);
									selectedCastle.setTabTroupes(Troupes.createTroupes(selectedCastle.getName(), RunACastle.countTroupes("Piquier", selectedCastle.getTabTroupes()) - p, RunACastle.countTroupes("Chevalier", selectedCastle.getTabTroupes()) - c,RunACastle.countTroupes("Onagre", selectedCastle.getTabTroupes()) - o));									
									printUnites(ost, tabOfGeometricForm);
									OST.distanceCastles(tabOfCastle, ost, targetCastle);
									ost.setInMovment(true);
									tabOfOST.add(ost);
									// Reinitialisation des variables troupes
									p = 0;
									c = 0;
									o = 0;
								}
								// Enleve augmentation et diminution
								if(up != NULL && down != NULL) {
									up.setText(" ");
									up = NULL;
									down.setText(" ");
									down = NULL;
								}
								// Enleve le texte des troupes
								if(targetCastle != NULLL) {
									piquier.setText(" ");
									piquier = NULL;
									chevalier.setText(" ");
									chevalier = NULL;
									onagre.setText(" ");
									onagre = NULL;
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
					}
				}));
				
				
				// Modifications visuelles des infos du chateau selectionne
				if(status != NULL) {
					if(selectedCastle.getTresor() < 1000 * selectedCastle.getNiveau()) {
						upgrade.setText(" ");
					}
					else if(selectedCastle.getTresor() >= 1000 * selectedCastle.getNiveau() && selectedCastle.getOwner() == "Player" && !selectedCastle.getTabOfProduction().contains("Améliorer")){
						upgrade.setText("> Améliorer <\n" + 1000 * selectedCastle.getNiveau() +" florins");
					}
					status.setText(selectedCastle.getName() + 
					"\nNiveau : " + selectedCastle.getNiveau() +
					"\nRevenu : " + selectedCastle.getRevenu() +
					"\nTroupes : " + RunACastle.countTroupes("Piquier", selectedCastle.getTabTroupes()) + "P | " + 
					RunACastle.countTroupes("Chevalier", selectedCastle.getTabTroupes()) + "C | " + 
					RunACastle.countTroupes("Onagre", selectedCastle.getTabTroupes()) + "O" +
					"\nTresor : " + selectedCastle.getTresor() + " florins\n" +
					upLine.getText());
					
					if(selectedCastle.getOwner() == "Player") {
						allProduction.setText("Production : " + selectedCastle.getTabOfProduction().size());
						if(inProduction != NULL) {
							inProduction.setText("Que voulez-vous produire ?" +
												 "\nNombre de production maximale possible : 7" + 
												 "\n\n\nEn production : " + selectedCastle.getTabOfProduction());
						}
					}
				}
				
				if(targetCastle != NULLL && troupesText == NULL && piquier == NULL) {
					createInfos.setHeight(250);
					createTroupes();
				}
				
				if(selectedCastle != NULLL && !selectedCastle.getTabOfProduction().isEmpty()) {
					if(countSec == 60) {
						countTourProd++;
						//product.setText("> Produire <\n" + (selectedCastle.getTimeOfProduction() - 1) + "s");
					}
					//selectedCastle.setTimeOfProduction(selectedCastle.getTimeOfProduction() - countTourProd);
					if(selectedCastle.getTabOfProduction().get(0) == "Améliorer" && countTourProd == (100 + 50*selectedCastle.getNiveau())) {
						RunACastle.updateNiveau(selectedCastle); 
						countTourProd = 0;
						selectedCastle.getTabOfProduction().remove(0);
					}
					
					else if(selectedCastle.getTabOfProduction().get(0) == "Piquier" && countTourProd == 5) {
						selectedCastle.getTabTroupes().add(new Piquier(selectedCastle.getName()));
						countTourProd = 0;
						selectedCastle.getTabOfProduction().remove(0);
					}
					
					else if(selectedCastle.getTabOfProduction().get(0) == "Chevalier" && countTourProd == 20) {
						selectedCastle.getTabTroupes().add(new Chevalier(selectedCastle.getName()));
						countTourProd = 0;
						selectedCastle.getTabOfProduction().remove(0);
					}
					
					else if(selectedCastle.getTabOfProduction().get(0) == "Onagre" && countTourProd == 50) {
						selectedCastle.getTabTroupes().add(new Onagre(selectedCastle.getName()));
						countTourProd = 0;
						selectedCastle.getTabOfProduction().remove(0);
					}
					
				}
				else {
					if(product != NULL) {
						product.setText("> Produire <");
					}
					countTourProd = 0;
				}	
				
				tabOfOST.forEach(ost -> {
					if(ost.getInMovment() == true /*&& ost.getOwner() == selectedCastle.getOwner()*/) {
						OST.move(root, ost, selectedCastle, target);
						if(ost.getCanAttack() == true) {
							if(Troupes.attackACastle(tabOfCastle, selectedCastle, target, ost.getOstUnites(), target.getTabTroupes()) == true) {
								int r = selectedCastle.getColor().r;
								int g = selectedCastle.getColor().g;
								int b = selectedCastle.getColor().b;
								target.getRectCastle().setFill(Color.rgb(r, g, b));
								
							}
						}
					}
				});
				
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
		createInfos = new Rectangle(10,10,210,150);
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
		
		cancel = new Text("> Quitter <");
		cancel.setLayoutX(createProduct.getX() + 400);
		cancel.setLayoutY(createProduct.getY() + 30);
		tabOfText.add(cancel);
		root.getChildren().add(cancel);
		
		validate = new Text("> Valider <");
		validate.setLayoutX(createProduct.getX() + 400);
		validate.setLayoutY(createProduct.getY() + 275);
		tabOfText.add(validate);
		root.getChildren().add(validate);
		
		piquier = new Text("> Piquier < \n100 florins");
		piquier.setLayoutX(createProduct.getX() + 20);
		piquier.setLayoutY(createProduct.getY() + 150);
		tabOfText.add(piquier);
		root.getChildren().add(piquier);
		
		chevalier = new Text("> Chevalier < \n500 florins");
		chevalier.setLayoutX(createProduct.getX() + 220);
		chevalier.setLayoutY(createProduct.getY() + 150);
		tabOfText.add(chevalier);
		root.getChildren().add(chevalier);
		
		onagre = new Text("> Onagre < \n1000 florins");
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
		
		cancel = new Text("> Annuler <");
		cancel.setLayoutX(130);
		cancel.setLayoutY(165);
		tabOfText.add(cancel);
		root.getChildren().add(cancel);
	}
	
	// Cree les boutons augmenter / diminuer nombre de troupes
	private void createButtonUpDown(){
		down = new Text(" - ");
		up = new Text(" + ");
		
		down.setLayoutX(150);
		down.setLayoutY(210);
		tabOfText.add(down);
		root.getChildren().add(down);
		
		up.setLayoutX(170);
		up.setLayoutY(210);
		tabOfText.add(up);
		root.getChildren().add(up);
	}
	
	// Affiche les troupes disponibles et à envoyer
	private void createTroupes() {		
		piquier = new Text("> Piquier < : " + p + " / " + RunACastle.countTroupes("Piquier", selectedCastle.getTabTroupes()));
		chevalier = new Text("> Chevalier < : " + c + " / " + RunACastle.countTroupes("Chevalier", selectedCastle.getTabTroupes()));
		onagre = new Text("> Onagre < : " + o + " / " + RunACastle.countTroupes("Onagre", selectedCastle.getTabTroupes()));
		
		piquier.setLayoutX(30);
		piquier.setLayoutY(190);
		tabOfText.add(piquier);
		root.getChildren().add(piquier);
		
		chevalier.setLayoutX(30);
		chevalier.setLayoutY(210);
		tabOfText.add(chevalier);
		root.getChildren().add(chevalier);
		
		onagre.setLayoutX(30);
		onagre.setLayoutY(230);
		tabOfText.add(onagre);
		root.getChildren().add(onagre);
		
		validate = new Text("> Valider <");
		validate.setLayoutX(130);
		validate.setLayoutY(245);
		tabOfText.add(validate);
		root.getChildren().add(validate);
	}
	
	// Affichage des informations du chateau
	private void printInfos(Castle c) {
		status = new Text(" "); 
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
		if(!tabOfText.contains(sendTroupes) && c.getOwner() == "Player") {
			sendTroupes = new Text("> Envoyer des troupes <");
			sendTroupes.setLayoutX(30);
			sendTroupes.setLayoutY(150);
			tabOfText.add(sendTroupes);
			root.getChildren().add(sendTroupes);
		}
		
		// Verifie si Produire est dans tabOfText pour eviter la superposition du texte
		if(!tabOfText.contains(product) && c.getOwner() == "Player") {
			product = new Text("> Produire <");
			product.setLayoutX(140);
			product.setLayoutY(110);
			tabOfText.add(product);
			root.getChildren().add(product);
		}
		
		if(c.getOwner() == "Player") {
			allProduction = new Text("Production : " + c.getTabOfProduction().size());
			allProduction.setLayoutX(30);
			allProduction.setLayoutY(110);
			root.getChildren().add(allProduction);
		}
	}
	
	private ArrayList<Castle> Build(int nbDuc, int nbBaron){
		ArrayList<Castle> tabOfCastle = new ArrayList<Castle>();
		tabOfCastle.add(new Castle("Player", tabOfCastle, 0));
		for(int i=0 ; i<nbDuc; i++) {
			tabOfCastle.add(new Castle("Duc", tabOfCastle, i+1));
		}
		for(int i=0 ; i<nbBaron; i++) {
			tabOfCastle.add(new Castle("Baron", tabOfCastle, i+1));
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
	
	private void printUnites(OST ost, ArrayList<GeometricForm> tabOfGeometricForm) {
		ArrayList<Rectangle> rTab = new ArrayList<>();
		ArrayList<Circle> cTab = new ArrayList<>();
		ArrayList<Polygon> pTab = new ArrayList<>();
		int r = tabOfGeometricForm.get(0).getColor().r;
		int g = tabOfGeometricForm.get(0).getColor().g;
		int b = tabOfGeometricForm.get(0).getColor().b;
		for(int i=0; i < tabOfGeometricForm.size(); i++) {
			switch(tabOfGeometricForm.get(i).getType()){
				case "rectangle":
					Rectangle onagre = new Rectangle(tabOfGeometricForm.get(i).getX(),tabOfGeometricForm.get(i).getY(),(double) tabOfGeometricForm.get(i).getWidth(),(double) tabOfGeometricForm.get(i).getHeight());
					onagre.setFill(Color.rgb(r, g, b));
					rTab.add(onagre);
					root.getChildren().add(onagre); 
					break;
				case "triangle":
					Polygon piquier = new Polygon();
					piquier.getPoints().addAll(new Double[]{
						    (double) tabOfGeometricForm.get(i).getS1().getX(), (double) tabOfGeometricForm.get(i).getS1().getY(),
						    (double) tabOfGeometricForm.get(i).getS2().getX(), (double) tabOfGeometricForm.get(i).getS2().getY(),
						    (double) tabOfGeometricForm.get(i).getS3().getX(), (double) tabOfGeometricForm.get(i).getS3().getY()});
					piquier.setFill(Color.rgb(r, g, b));
					pTab.add(piquier);
					root.getChildren().add(piquier); 
					break;
				case "circle":
					Circle chevalier = new Circle((double) tabOfGeometricForm.get(i).getX(),(double) tabOfGeometricForm.get(i).getY(),(double) tabOfGeometricForm.get(i).getRadius());
					chevalier.setFill(Color.rgb(r, g, b));
					cTab.add(chevalier);
					root.getChildren().add(chevalier); 
					break;
				default:
					System.out.println("erreur affichage troupe : type Unknow");
					break;
			}
		}
		ost.setCircle(cTab);
		ost.setPolygon(pTab);
		ost.setRectangle(rTab);
	}
	
	public static void main(String[] args) {
		Application.launch(args); 
	}
	
	
}