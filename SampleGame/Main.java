package SampleGame;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage; 

public class Main extends Application {
	protected static final Text NULL = null;
	protected static final Castle NULLL = null;
	private Scene scene;
	private Input input;
	private AnimationTimer gameLoop, pauseLoop;
	Pane root;
	
	ArrayList<Castle> tabOfCastle = new ArrayList<>();
	ArrayList<Text> tabOfText = new ArrayList<>();
	ArrayList<OST> tabOfOST = new ArrayList<>();
		
	// Message pour le jeu en pause
	protected static Text textPause = NULL;
	// Barre d'informations du chateau
	protected static Text status = NULL;
	// (appuyer) pour ameliorer pour le niveau
	protected static Text upgrade = new Text(" ");
	// (appuyer) pour envoyer des troupes
	protected static Text sendTroupes = NULL;
	// Cible de l'attaque
	protected static Text targetText = NULL;
	// (appuyer) pour annuler
	protected static Text cancel = NULL;
	// choisir ses troupes
	protected static Text troupesText = NULL;
	// (appuyer) pour valider
	protected static Text validate = NULL;
	// (appuyer) pour produire troupes ou ameliorer
	protected static Text product = NULL;
	protected static Text inProduction = NULL;
	// (appuyer) pour ajouter des troupes a la production
	protected static Text piquier = NULL;
	protected static Text chevalier = NULL;
	protected static Text onagre = NULL;
	protected static Text remove = NULL;
	protected static Text allProduction = NULL;
	// (appuyer) pour augmenter / diminuer nombre de troupse a envoyer
	protected static Text up = NULL;
	protected static Text down = NULL;
	// (appuyer)
	protected static Text save = NULL;
	protected static Text load = NULL;
	
	private Text upLine = new Text("\n_________________");
	
	protected static String troupeToChange;
	protected static Text troupeToColor = NULL;
	
	// Troupes a envoyer dans l'OST
	private int p = 0;
	private int c = 0;
	private int o = 0;
	
	// Timer de boucle
	private int countSec = 0;
	private int countSecIA = 0;
	
	// Panneaux d'affichages des troupes ou de la production
	Rectangle createInfos;
	Rectangle createProduct;
	Rectangle createEscape;
		
	// Cheateau liés à l'affichage
	static Castle selectedCastle;
	Castle targetCastle = NULLL;
	
	private boolean makePause;
	
	@Override 
	public void start(Stage primaryStage) throws Exception { 
		root = new Pane(); 
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT, Color.rgb(154,205,50)); 
		primaryStage.setTitle("DukesOfTheRealm"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
		
		// Creation des chateau
		tabOfCastle = Build(5,7,Settings.tabOfCastleName); // maximum 20 chateaux 
		printAllCastle(tabOfCastle, root);		
		
		loadGame();
		
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				processInput(input, now);
				
				// Timer qui actualise à intervalle régulier le trésor de tous les chateaux
				if(countSec == 60) {
					tabOfCastle.forEach(castle -> {
						if(castle.getType() == "Baron") {
							RunACastle.updateTresorBaron(castle);
						}
						else {
							RunACastle.updateTresor(castle); 
						}
					});
				}
				
				// Interaction avec les chateaux
				tabOfCastle.forEach(castle -> castle.getRectCastle().setOnMouseClicked(e -> {
					// Affiche les informations du chateau séléctionné
					if(targetText == NULL && inProduction == NULL) {
						tabOfText.clear();
						createPrintInfos();
						selectedCastle = castle;
						printInfos(selectedCastle);
					}
				}));
				
				// Interaction avec les zones de texte
				tabOfText.forEach(text -> text.setOnMouseClicked(e -> { 
					// Ameliore le niveau du chateau
					if(selectedCastle.getOwner() == "Player") {
						if(text == upgrade) {
							if(!selectedCastle.getProductionLine().getTabOfProduction().contains("Améliorer") 
									&& selectedCastle.getTresor() >= selectedCastle.getProductionLine().getCostOfUpgrade() * selectedCastle.getLevel()) { // si il n'y  a pas déjà une amélioration
								selectedCastle.getProductionLine().getTabOfProduction().add("Améliorer");	//add
								RunACastle.removeCostOfProduction(selectedCastle);	//cost
								updateStatus();
								if(selectedCastle.getProductionLine().getTimeLeft() == 0) {
									selectedCastle.getProductionLine().setTimeLeft(selectedCastle.getProductionLine().getTimeOfUpgrade());	//timer
								}
							}
						}
						// Cree le panneau pour la production des troupes
						if(text == product && targetText == NULL) {
							createProduction();
							// Force la mise en pause du jeu pour la production
							makePauseProcess();
						}
						// Cree le texte du choix de la cible et du bouton annuler
						if(text == sendTroupes && targetText == NULL && cancel == NULL) {
							createInfos.setHeight(Settings.EXTENDINFORMATIONHEIGHT);
							createSendTroupes();
							makePauseProcess();
						}
					}
				}));
				
				// Modifications visuelles des informations du chateau selectionné
				if(status != NULL) {
					if(selectedCastle.getTresor() >= selectedCastle.getProductionLine().getCostOfUpgrade() * selectedCastle.getLevel() && selectedCastle.getOwner() == "Player" 
							&& !selectedCastle.getProductionLine().getTabOfProduction().contains("Améliorer")){
						upgrade.setText("> Améliorer <\n" + selectedCastle.getProductionLine().getCostOfUpgrade() * selectedCastle.getLevel() +" florins");
					}
					else if(selectedCastle.getOwner() != "Player") {
						upgrade.setText(" ");
					}
					
					if(selectedCastle.getOwner() == "Player") {
						product.setText("> Produire <\n" + (selectedCastle.getProductionLine().getTimeLeft()) + "s");
					}
					
					updateStatus();
				}
				
				// Actualisation du temps de production des chateaux 
				tabOfCastle.forEach(castle -> {
					if(!castle.getProductionLine().getTabOfProduction().isEmpty()) {
						if(countSec == 60) {
							castle.getProductionLine().setTimeLeft(castle.getProductionLine().getTimeLeft() - 1);
						}
						if(castle.getProductionLine().getTimeLeft() == 0) {
							RunACastle.CollectProduction(castle);
							Production.updateProduction(castle);
						}
					}	
				});
				
				tabOfOST.forEach(ost -> {
					if(ost.getInMovment() == true) {
						// Affichage des ost
						if(ost.getTabOfGeometricForm().size() != 0 && countSec == 60) {
							printUnites(ost,ost.getTabOfGeometricForm());
						}
						Movement.move(root, tabOfCastle, ost, ost.getOwner(), ost.getTarget());
						
						if(ost.getCanAttack() == true) {
							if(Troupes.attackACastle(tabOfCastle, ost.getOwner(), ost.getTarget(), ost.getOstUnites(), ost.getTarget().getTabTroupes()) == true) {
								int r = ost.getOwner().getColor().r;
								int g = ost.getOwner().getColor().g;
								int b = ost.getOwner().getColor().b;
								ost.getTarget().getRectCastle().setFill(Color.rgb(r, g, b));
								tabOfOST.remove(ost);
							}
						}
					}
				});
				
				// Timer du jeu
				if(countSec == 60) {
					countSec = 0;
					countSecIA++;
				}
				
				if(countSecIA == 1) {
					tabOfCastle.forEach(castle -> {
						if(castle.getTypeOwner() == "Duc") {
							IA.randomAction(castle, tabOfCastle, tabOfOST);
						}
					});
					countSecIA = 0;					
				}
				
				countSec++;
			}
			
			// Fonction qui force la mise en pause du jeu pour la production de troupes ou l'envoi de troupes
			private void makePauseProcess() {
				makePause = true;
				gameLoop.stop();
				pauseLoop.start();
			}
			
			// Gère les entrées claviers
			private void processInput(Input input, long now) {
				if(input.isPause() == true && makePause == false) {
					createEscape();
					gameLoop.stop();
					pauseLoop.start();
				}	
			}
		};
		gameLoop.start();
		
		pauseLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				setTextPause(); // Affiche le message de mise en pause
				processInputPause(input, now);
				
				// Choix de la cible de l'attaque
				tabOfCastle.forEach(castle -> castle.getRectCastle().setOnMouseClicked(e -> {
					if(status != NULL && targetText == NULL && castle.getRectCastle() != createEscape) {
						selectedCastle = castle;
						updateStatus();
					}
					if(selectedCastle != castle && targetText != NULL && inProduction == NULL && targetCastle == NULLL) {
						targetCastle = castle;
						targetText.setText("Cible : " + targetCastle.getName());
						createInfos.setHeight(Settings.MAXHEIGHT);
						createTroupes();
					}
				}));
				
				tabOfText.forEach(text -> text.setOnMouseClicked(e -> {
					if(text == save) {
						SaveGame.saveGame(tabOfCastle, tabOfOST);
					}
					else if(text == load) {
						loadOldGame();
					}
					// Gere la production des troupes
					if(text == product && targetText == NULL) {
						createProduction();
					}
					if(inProduction != NULL) {
						if(text == piquier && selectedCastle.getTresor() >= selectedCastle.getProductionLine().getCostOfPiquier() 
								&& selectedCastle.getProductionLine().getTabOfProduction().size() < 7) {
							selectedCastle.setTresor(selectedCastle.getTresor() - selectedCastle.getProductionLine().getCostOfPiquier());	//cost
							selectedCastle.getProductionLine().getTabOfProduction().add("Piquier");	//add
							if(selectedCastle.getProductionLine().getTimeLeft() == 0) {
								selectedCastle.getProductionLine().setTimeLeft(selectedCastle.getProductionLine().getTimeOfPiquier());	//timer	
							}
						}
						if(text == chevalier && selectedCastle.getTresor() >= selectedCastle.getProductionLine().getCostOfChevalier() 
								&& selectedCastle.getProductionLine().getTabOfProduction().size() < 7) {
							selectedCastle.setTresor(selectedCastle.getTresor() - selectedCastle.getProductionLine().getCostOfChevalier());	//cost
							selectedCastle.getProductionLine().getTabOfProduction().add("Chevalier");	//add
							if(selectedCastle.getProductionLine().getTimeLeft() == 0) {
								selectedCastle.getProductionLine().setTimeLeft(selectedCastle.getProductionLine().getTimeOfChevalier());	//timer
							}
						}
						if(text == onagre && selectedCastle.getTresor() >= selectedCastle.getProductionLine().getCostOfOnagre() 
								&& selectedCastle.getProductionLine().getTabOfProduction().size() < 7) {
							selectedCastle.setTresor(selectedCastle.getTresor() - selectedCastle.getProductionLine().getCostOfOnagre());	//cost
							selectedCastle.getProductionLine().getTabOfProduction().add("Onagre");	//add
							if(selectedCastle.getProductionLine().getTimeLeft() == 0) {
								selectedCastle.getProductionLine().setTimeLeft(selectedCastle.getProductionLine().getTimeOfOnagre());	//timer
							}
						}
						updateStatus();
						inProduction.setText("Que voulez-vous produire ?" +
								 "\nNombre de production maximale possible : 7" + 
								 "\n\n\nEn production : " + selectedCastle.getProductionLine().getTabOfProduction());
					}
					// Gere l'annulation du dernier élèment de la liste
					if(text == remove && !selectedCastle.getProductionLine().getTabOfProduction().isEmpty()) {
						RunACastle.removeAProduction(selectedCastle);	
						updateStatus();
						inProduction.setText("Que voulez-vous produire ?" +
								 "\nNombre de production maximale possible : 7" + 
								 "\n\n\nEn production : " + selectedCastle.getProductionLine().getTabOfProduction());
					}
					// Gere le cote hors production de troupes
					if(text == sendTroupes && targetText == NULL && cancel == NULL) {
						createInfos.setHeight(Settings.EXTENDINFORMATIONHEIGHT);
						createSendTroupes();
					}
					// Création d'une OST
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
					
					// Annulation ou validation de l'envoi de troupes
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
							// Ajoute les troupes à l'OST
							if(text == validate && (p != 0 || c != 0 || o != 0)) {
								// 0 : onagre | 1 : piquier | 2 : chevalier
								int tab[] = new int[3];
								tab[0] = o;
								tab[1] = p;
								tab[2] = c;
								OST ost = new OST(targetCastle, tab, selectedCastle, tabOfCastle);
								selectedCastle.setTabTroupes(Troupes.createTroupes(selectedCastle.getName(), 
										RunACastle.countTroupes("Piquier", selectedCastle.getTabTroupes()) - p, 
										RunACastle.countTroupes("Chevalier", selectedCastle.getTabTroupes()) - c,
										RunACastle.countTroupes("Onagre", selectedCastle.getTabTroupes()) - o));
								ost.setInMovment(true);
								ost.setTabOfGeometricForm( GeometricForm.tabOfGeometricForm(ost, tabOfCastle));
								tabOfOST.add(ost);
								// Reinitialisation des variables troupes
								p = 0;
								c = 0;
								o = 0;
							}
							// Enleve la possibilité d'augmenter et de diminuer
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
							// Enleve le chateau cible
							targetText.setText(" ");
							targetText = NULL;
							targetCastle = NULLL;
							// Remettre le panneau status à sa taille d'origine
							createInfos.setHeight(Settings.INFORMATIONSHEIGHT);
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
						makePauseProcessPause();
					}
				}));
			}
			
			// Force la mise en route du jeu après validation de l'envoi de troupes ou de l'envoi de la production
			private void makePauseProcessPause() {
				removeTextPause();
				makePause = false;
				pauseLoop.stop();
				gameLoop.start();
			}
			
			// Gère les entrées claviers
			private void processInputPause(Input input, long now) {
				if(input.isPause() == false && makePause == false) {
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
					removeTextPause();
					removeEscape();
					pauseLoop.stop();
					gameLoop.start();
				}
			}
		};
	}
	
	private void loadGame() {
		input = new Input(scene);
		input.addListeners();
	}
	
	// Affiche le texte "JEU EN PAUSE" lors de la mise en pause du jeu
	private void setTextPause() {
		if(textPause == NULL) {
			textPause = new Text("JEU EN PAUSE");
			textPause.setStyle("-fx-font-size: 60px;");
			textPause.setLayoutX(Settings.SCENE_WIDTH / 1.37);
			textPause.setLayoutY(Settings.TEXTX * 2.5);
			textPause.setFill(Color.RED);
			root.getChildren().add(textPause);
		}
	}
	
	// Enlève le texte "JEU EN PAUSE" lors de la mise en route du jeu
	private void removeTextPause() {
		textPause.setText(" ");
		textPause = NULL;
	}	
	
	// Actualise les informations du château après quelques changements
	private void updateStatus() {
		status.setText(selectedCastle.getName() + " | Maître : " + selectedCastle.getOwner() +
				"\nNiveau : " + selectedCastle.getLevel() +
				"\nRevenu : " + selectedCastle.getRevenu() +
				"\nTroupes : " + RunACastle.countTroupes("Piquier", selectedCastle.getTabTroupes()) + "P | " + 
				RunACastle.countTroupes("Chevalier", selectedCastle.getTabTroupes()) + "C | " + 
				RunACastle.countTroupes("Onagre", selectedCastle.getTabTroupes()) + "O" +
				"\nTresor : " + selectedCastle.getTresor() + " florins\n" +
				upLine.getText());
		
		if(selectedCastle.getOwner() == "Player" && sendTroupes != NULL) {
			allProduction.setText("Production : " + selectedCastle.getProductionLine().getTabOfProduction().size());
			sendTroupes.setText("> Envoyer des troupes <");
		}
		else if(selectedCastle.getOwner() == "Duc" || selectedCastle.getOwner() == "Baron" && sendTroupes != NULL){
			sendTroupes.setText(" ");
		}
	}
	
	private void loadOldGame() {
		root.getChildren().clear();
		LoadSave.loadSave(tabOfCastle, tabOfOST);
		resetScreen.resetTextScreen(root, tabOfText);
		printAllCastle(tabOfCastle, root);
	}
	
	private void createEscape() {
		createEscape = new Rectangle(Settings.TEXTX, Settings.SCENE_HEIGHT / 1.12, 190, 80);
		createEscape.setArcWidth(Settings.RECTANGLE_ARC);
		createEscape.setArcHeight(Settings.RECTANGLE_ARC);
		createEscape.setFill(Color.WHITE);
		root.getChildren().add(createEscape);
		
		save = new Text("> Sauvegarder <" + upLine.getText());
		save.setLayoutX(createEscape.getX() + 15);
		save.setLayoutY(createEscape.getY() + 25);
		tabOfText.add(save);
		root.getChildren().add(save);
		
		load = new Text("> Charger la dernière partie <");
		load.setLayoutX(createEscape.getX() + 15);
		load.setLayoutY(createEscape.getY() + 65);
		tabOfText.add(load);
		root.getChildren().add(load);
	}
	
	private void removeEscape() {
		if(save != NULL && load != NULL) {
			save.setText(" ");
			load.setText(" ");
			tabOfText.remove(save);
			tabOfText.remove(load);
			root.getChildren().remove(save);
			root.getChildren().remove(load);
			root.getChildren().remove(createEscape);
		}
	}
	
	// Creation du rectangle contenant les informations du chateau
	private void createPrintInfos() {
		createInfos = new Rectangle(Settings.INFORMATIONSCASTLEXY,Settings.INFORMATIONSCASTLEXY,Settings.INFORMATIONSWIDTH,Settings.INFORMATIONSHEIGHT);
		createInfos.setArcWidth(Settings.RECTANGLE_ARC);
		createInfos.setArcHeight(Settings.RECTANGLE_ARC);
		createInfos.setFill(Color.WHITE);
		root.getChildren().add(createInfos);
	}
	
	// Creation rectangle + texte pour produire des troupes ou ameliorer
	private void createProduction() {
		createProduct = new Rectangle(Settings.SCENE_WIDTH/3,Settings.SCENE_HEIGHT/4,500,300);
		createProduct.setArcWidth(Settings.RECTANGLE_ARC);
		createProduct.setArcHeight(Settings.RECTANGLE_ARC);
		createProduct.setFill(Color.WHITE);
		root.getChildren().add(createProduct);
		
		inProduction = new Text("Que voulez-vous produire ?" +
				 "\nNombre de production maximale possible : 7" + 
				 "\n\n\nEn production : " + selectedCastle.getProductionLine().getTabOfProduction());
		inProduction.setLayoutX(createProduct.getX() + 20);
		inProduction.setLayoutY(createProduct.getY() + 30);
		root.getChildren().add(inProduction);
		
		cancel = new Text("> Quitter <");
		cancel.setLayoutX(createProduct.getX() + 400);
		cancel.setLayoutY(createProduct.getY() + 30);
		cancel.setFill(Color.DARKRED);
		tabOfText.add(cancel);
		root.getChildren().add(cancel);
		
		validate = new Text("> Valider <");
		validate.setLayoutX(createProduct.getX() + 400);
		validate.setLayoutY(createProduct.getY() + 275);
		validate.setFill(Color.GREEN);
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
		targetText.setLayoutX(Settings.TEXTX);
		targetText.setLayoutY(Settings.EXTENDINFORMATIONHEIGHT - 15);
		root.getChildren().add(targetText);
		
		cancel = new Text("> Annuler <");
		cancel.setLayoutX(Settings.INFORMATIONSWIDTH - 60);
		cancel.setLayoutY(Settings.EXTENDINFORMATIONHEIGHT - 15);
		tabOfText.add(cancel);
		root.getChildren().add(cancel);
	}
	
	// Cree les boutons augmenter / diminuer nombre de troupes
	private void createButtonUpDown(){
		down = new Text(" - ");
		up = new Text(" + ");
		
		down.setLayoutX(Settings.INFORMATIONSHEIGHT + 20);
		down.setLayoutY(Settings.INFORMATIONSWIDTH - 15);
		tabOfText.add(down);
		root.getChildren().add(down);
		
		up.setLayoutX(Settings.EXTENDINFORMATIONHEIGHT + 10);
		up.setLayoutY(Settings.INFORMATIONSWIDTH - 15);
		tabOfText.add(up);
		root.getChildren().add(up);
	}
	
	// Affiche les troupes disponibles et à envoyer
	private void createTroupes() {		
		piquier = new Text("> Piquier < : " + p + " / " + RunACastle.countTroupes("Piquier", selectedCastle.getTabTroupes()));
		chevalier = new Text("> Chevalier < : " + c + " / " + RunACastle.countTroupes("Chevalier", selectedCastle.getTabTroupes()));
		onagre = new Text("> Onagre < : " + o + " / " + RunACastle.countTroupes("Onagre", selectedCastle.getTabTroupes()));
		
		piquier.setLayoutX(Settings.INFORMATIONSCASTLEXY * 3);
		piquier.setLayoutY(Settings.EXTENDINFORMATIONHEIGHT + 10);
		tabOfText.add(piquier);
		root.getChildren().add(piquier);
		
		chevalier.setLayoutX(Settings.INFORMATIONSCASTLEXY * 3);
		chevalier.setLayoutY(Settings.INFORMATIONSWIDTH - 15);
		tabOfText.add(chevalier);
		root.getChildren().add(chevalier);
		
		onagre.setLayoutX(Settings.INFORMATIONSCASTLEXY * 3);
		onagre.setLayoutY(Settings.INFORMATIONSWIDTH + 5);
		tabOfText.add(onagre);
		root.getChildren().add(onagre);
		
		validate = new Text("> Valider <");
		validate.setLayoutX(Settings.INFORMATIONSWIDTH - 60);
		validate.setLayoutY(Settings.MAXHEIGHT);
		tabOfText.add(validate);
		root.getChildren().add(validate);
	}
	
	// Affichage des informations du chateau
	private void printInfos(Castle c) {
		status = new Text(" "); 
		status.setLayoutX(Settings.INFORMATIONSCASTLEXY * 3);
		status.setLayoutY(Settings.INFORMATIONSCASTLEXY * 3);
		root.getChildren().add(status);
		
		// Verifie si Ameliorer est dans tabOfText pour eviter la superposition du texte
		if(!tabOfText.contains(upgrade)) {
			upgrade = new Text("> Améliorer <\n" + selectedCastle.getProductionLine().getCostOfUpgrade() * selectedCastle.getLevel() +" florins ");
			upgrade.setLayoutX(Settings.INFORMATIONSHEIGHT - 10);
			upgrade.setLayoutY(Settings.INFORMATIONSCASTLEXY * 4 + 5);
			tabOfText.add(upgrade);
			root.getChildren().add(upgrade);
		}
		// Verifie si Envoyer des troupes est dans tabOfText pour eviter la superposition du texte
		if(!tabOfText.contains(sendTroupes) && c.getOwner() == "Player") {
			sendTroupes = new Text("> Envoyer des troupes <");
			sendTroupes.setLayoutX(Settings.INFORMATIONSCASTLEXY * 3);
			sendTroupes.setLayoutY(Settings.INFORMATIONSHEIGHT);
			tabOfText.add(sendTroupes);
			root.getChildren().add(sendTroupes);
		}
		
		// Verifie si Produire est dans tabOfText pour eviter la superposition du texte
		if(!tabOfText.contains(product) && c.getOwner() == "Player") {
			product = new Text("> Produire <");
			product.setLayoutX(Settings.INFORMATIONSHEIGHT - 10);
			product.setLayoutY(Settings.INFORMATIONSHEIGHT - 40);
			tabOfText.add(product);
			root.getChildren().add(product);
		}
		
		if(c.getOwner() == "Player") {
			allProduction = new Text("Production : " + c.getProductionLine().getTabOfProduction().size());
			allProduction.setLayoutX(Settings.INFORMATIONSCASTLEXY * 3);
			allProduction.setLayoutY(Settings.INFORMATIONSHEIGHT - 40);
			root.getChildren().add(allProduction);
		}
	}
	
	private ArrayList<Castle> Build(int nbDuc, int nbBaron, String tabOfCastleName[]){ //max 20 chateaux !!!
		ArrayList<Castle> tabOfCastle = new ArrayList<Castle>();
		tabOfCastle.add(new Castle("Player", tabOfCastle, 0, tabOfCastleName));
		for(int i=0 ; i<nbDuc; i++) {
			tabOfCastle.add(new Castle("Duc", tabOfCastle, i+1, tabOfCastleName));
		}
		for(int i=0 ; i<nbBaron; i++) {
			tabOfCastle.add(new Castle("Baron", tabOfCastle, i+1, tabOfCastleName));
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
		int r = tabOfGeometricForm.get(0).getColor().r;
		int g = tabOfGeometricForm.get(0).getColor().g;
		int b = tabOfGeometricForm.get(0).getColor().b;
		int i = 0;
		int cpt = 0;
		while(cpt < 3) {
			if(tabOfGeometricForm.size() == 0) {
				return;
			}
			switch(tabOfGeometricForm.get(i).getType()){
				case "rectangle":
					Rectangle onagre = new Rectangle(tabOfGeometricForm.get(i).getX(),tabOfGeometricForm.get(i).getY(),(double) tabOfGeometricForm.get(i).getWidth(),(double) tabOfGeometricForm.get(i).getHeight());
					onagre.setFill(Color.rgb(r, g, b));
					ost.getRectangle().add(onagre);
					root.getChildren().add(onagre); 
					tabOfGeometricForm.remove(i);
					break;
				case "triangle":
					Polygon piquier = new Polygon();
					piquier.getPoints().addAll(new Double[]{
						    (double) tabOfGeometricForm.get(i).getS1().getX(), (double) tabOfGeometricForm.get(i).getS1().getY(),
						    (double) tabOfGeometricForm.get(i).getS2().getX(), (double) tabOfGeometricForm.get(i).getS2().getY(),
						    (double) tabOfGeometricForm.get(i).getS3().getX(), (double) tabOfGeometricForm.get(i).getS3().getY()});
					piquier.setFill(Color.rgb(r, g, b));
					ost.getPolygon().add(piquier);
					root.getChildren().add(piquier); 
					tabOfGeometricForm.remove(i);
					break;
				case "circle":
					Circle chevalier = new Circle((double) tabOfGeometricForm.get(i).getX(),(double) tabOfGeometricForm.get(i).getY(),(double) tabOfGeometricForm.get(i).getRadius());
					chevalier.setFill(Color.rgb(r, g, b));
					ost.getCircle().add(chevalier);
					root.getChildren().add(chevalier); 
					tabOfGeometricForm.remove(i);
					break;
				default:
					System.out.println("erreur affichage troupe : type Unknow");
					break;
			}
			cpt++;
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args); 
	}
	
	
}