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
	private Scene scene;
	private Input input;
	private AnimationTimer gameLoop;
	private ArrayList<Castle> tabOfCastle = new ArrayList<>();
	private ArrayList<Text> tabOfText = new ArrayList<>();
	
	private Text status = NULL;
	private Text upgrade;
	
	Pane root;
	
	Castle selectedCastle;
	
	@Override 
	public void start(Stage primaryStage) throws Exception { 
		
		root = new Pane(); 
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT, Color.LIGHTGREEN); 
		primaryStage.setTitle("DukesOfTheRealm"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
		
		// create castle
		tabOfCastle = Build(5,5);
		printAllCastle(tabOfCastle, root);
		
		
		loadGame();
		
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// update money of castle
				tabOfCastle.forEach(castle -> RunACastle.updateRevenu(castle));
				tabOfCastle.forEach(castle -> RunACastle.updateTresor(castle)); // update visuellement a faire
				
				tabOfCastle.forEach(castle -> castle.getRectCastle().setOnMouseClicked(e -> {
					createPrintInfos();
					printInfos(castle);
					selectedCastle = castle;
				}));
				
				// upgrade status of castle (solution tempo : voir le changement du tresor)
				tabOfText.forEach(text -> text.setOnMouseClicked(e -> { 
					RunACastle.updateNiveau(selectedCastle); 
					status.setText(" ");
					upgrade.setText(" ");
					printInfos(selectedCastle);
				}));
				
				if(status != NULL) {
					status.setText(selectedCastle.getName() + 
					"\nNiveau : " + selectedCastle.getNiveau() +
					"\nRevenu : " + selectedCastle.getRevenu() +
					"\nTroupes : " + selectedCastle.getTabTroupes().size() +
					"\nTresor : " + selectedCastle.getTresor());
				}
				
			}

		};
		gameLoop.start();
	}
	
	private void loadGame() {
		input = new Input(scene);
		input.addListeners();
	}
	
	
	private void createPrintInfos() {
		Rectangle r = new Rectangle(10,10,200,100);
		r.setArcWidth(40);
		r.setArcHeight(40);
		r.setFill(Color.WHITE);
		root.getChildren().add(r);
	}
	
	// Affichage des informations du chateau
	private void printInfos(Castle c) {
		status = new Text(c.getName() + 
				"\nNiveau : " + c.getNiveau() +
				"\nRevenu : " + c.getRevenu() +
				"\nTroupes : " + c.getTabTroupes().size() +
				"\nTresor : " + c.getTresor()); // + prod
		status.setLayoutX(30);
		status.setLayoutY(30);
		tabOfText.add(status);
		root.getChildren().add(status);
		
		if(c.getName() == "Player") {
			upgrade = new Text("Améliorer");
			upgrade.setLayoutX(130);
			upgrade.setLayoutY(45);
			tabOfText.add(upgrade);
			root.getChildren().add(upgrade);
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


