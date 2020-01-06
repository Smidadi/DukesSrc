package SampleGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

/**
 * La class LoadSave charge les données de la dernière partie sauvegardée dans la nouvelle partie
 * 	Vérifie l'existence du fichier "save.ser" et va lire son contenue pour 
 * 	récupérer les données de tabOfCastle et de tabOfOST de l'ancienne partie
 * 	ainsi que la taille du tableau tabOfOST pour récréer un nouveau tableau 
 * 	avec le nombre exact d'OST
 */

public class LoadSave extends Main {	
	static public void loadSave(ArrayList<Castle> tabOfCastle, ArrayList<OST> tabOfOST) {
		ObjectInputStream ois = null;
		try {
			File f = new File("save.ser");
			if(f.exists()) {
				FileInputStream in = new FileInputStream("save.ser");
				ois = new ObjectInputStream(in);
				
				// Récupération des chateaux
				for(int i = 0; i < tabOfCastle.size(); i++) {
					tabOfCastle.set(i, (Castle) ois.readObject());
					LoadSave.createRectangleOfCastle(tabOfCastle.get(i));
					LoadSave.createDoorOfCastle(tabOfCastle.get(i));
				}
				
				// Récupération de la taille de tabOfOST
				int sizeTabOfOST = ois.read();
	
				// Récupération des OST
				for(int i =0; i < sizeTabOfOST; i++) {
					tabOfOST.add(i, (OST) ois.readObject());
				}
			}
		} catch(java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
		      e.printStackTrace();
	    } finally {
	    	try {
	    		if(ois != null) {
	    			ois.close();
	    		}
	    	} catch(IOException ex) {
	            ex.printStackTrace();
	    	}
	    }
	}
	
	/**
	 * Recrée les châteaux sur la map
	 * @param c
	 * 	accède au château pour recréer les formes à l'écran
	 */
	
	static public void createRectangleOfCastle(Castle c) {
		double x = c.getCastle().getCornerLT().getX();
		double y = c.getCastle().getCornerLT().getY();
		double w = Coordonnee.distance(c.getCastle().getCornerLT(), c.getCastle().getCornerRT());
		double h = Coordonnee.distance(c.getCastle().getCornerLT(), c.getCastle().getCornerLB());
		c.setRectCastle(new Rectangle(x,y,w,h));
	}
	
	/**
	 * Recrée les portes des châteaux sur la map
	 * @param c
	 * 	accède au château pour recréer les portes sur les châteaux à l'écran
	 */
	
	static public void createDoorOfCastle(Castle c) {
		double x = c.getCastleDoor().getCornerLT().getX();
		double y = c.getCastleDoor().getCornerLT().getY();
		double w = Coordonnee.distance(c.getCastleDoor().getCornerLT(), c.getCastleDoor().getCornerRT());
		double h = Coordonnee.distance(c.getCastleDoor().getCornerLT(), c.getCastleDoor().getCornerLB());
		c.setRectDoor(new Rectangle(x,y,w,h));
	}
}
