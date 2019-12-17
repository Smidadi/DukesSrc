package SampleGame;

import java.util.ArrayList;

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle; 
import javafx.stage.Stage; 

public class Main extends Application {
	// A enlever + extends Application 
	private Scene scene;
	
	@Override 
	public void start(Stage primaryStage) throws Exception { 
		
		ArrayList<Castle> tabOfCastle = Build(5,5);
		

		Pane root = new Pane(); 
		
		//Rectangle(x,y,w,h);
		
		printAllCastle(tabOfCastle, root);
		
		for(int i=0 ; i<tabOfCastle.size() ; i++) {
			System.out.println("Name : " + tabOfCastle.get(i).getName() + "\n");
			Coordonnee c = tabOfCastle.get(i).getCastle().getCenter();
			for(int k=0; k<tabOfCastle.size() ; k++) {
				System.out.println("   Name : " + tabOfCastle.get(k).getName() + "|| distance : " + Coordonnee.distance(c, tabOfCastle.get(k).getCastle().getCenter()) + "\n");
			}
			
		}
		
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT, Color.LIGHTGREEN); 
		primaryStage.setTitle("DukesOfTheRealm"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
		
		
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
			//Castle
			int x = tabOfCastle.get(i).getCastle().getCornerLT().getX();
			int y = tabOfCastle.get(i).getCastle().getCornerLT().getY();
			double width = Coordonnee.distance(tabOfCastle.get(i).getCastle().getCornerLT(), tabOfCastle.get(i).getCastle().getCornerRT());
			double height = Coordonnee.distance(tabOfCastle.get(i).getCastle().getCornerLT(), tabOfCastle.get(i).getCastle().getCornerLB());
			//Door
			int Dx = tabOfCastle.get(i).getCastleDoor().getCornerLT().getX();
			int Dy = tabOfCastle.get(i).getCastleDoor().getCornerLT().getY();
			double Dwidth = Coordonnee.distance(tabOfCastle.get(i).getCastleDoor().getCornerLT(), tabOfCastle.get(i).getCastleDoor().getCornerRT());
			double Dheight = Coordonnee.distance(tabOfCastle.get(i).getCastleDoor().getCornerLT(), tabOfCastle.get(i).getCastleDoor().getCornerLB());
			// Draw rectangle
			Rectangle castle = new Rectangle(x, y, width, height);	
			Rectangle door = new Rectangle(Dx, Dy, Dwidth, Dheight);
			int r = tabOfCastle.get(i).getColor().r;
			int g = tabOfCastle.get(i).getColor().g;
			int b = tabOfCastle.get(i).getColor().b;
			castle.setFill(Color.rgb(r, g, b));
			door.setFill(Color.BROWN); 
			root.getChildren().add(castle); 
			root.getChildren().add(door); 
		}
		
	}
	// jusque la
	
	public static void main(String[] args) {
		
		
		// A enlever
		Application.launch(args); 
	}
	
	
}


