package SampleGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class LoadSave extends Main {	
	static public void loadSave(ArrayList<Castle> tabOfCastle, ArrayList<OST> tabOfOST) {
		ObjectInputStream ois = null;
		try {
			FileInputStream in = new FileInputStream("save.ser");
			ois = new ObjectInputStream(in);
			
			// Récupération des chateaux
			for(int i = 0; i < tabOfCastle.size(); i++) {
				tabOfCastle.set(i, (Castle) ois.readObject());
				LoadSave.createRectangleOfCastle(tabOfCastle.get(i));
				LoadSave.createDoorOfCastle(tabOfCastle.get(i));
				//System.out.printl
			}
			
			// Récupération des OST
			for(int i =0; i < tabOfOST.size(); i++) {
				tabOfOST.set(i, (OST) ois.readObject());
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
	
	static public void createOST(OST ost) {
		for(int i = 0; i < ost.getTabOfGeometricForm().size(); i++) {
			//ost.getTabOfGeometricForm().set(i,);
		}
	}
	
	static public void createRectangleOfCastle(Castle c) {
		int x = c.getCastle().getCornerLT().getX();
		int y = c.getCastle().getCornerLT().getY();
		double w = Coordonnee.distance(c.getCastle().getCornerLT(), c.getCastle().getCornerRT());
		double h = Coordonnee.distance(c.getCastle().getCornerLT(), c.getCastle().getCornerLB());
		c.setRectCastle(new Rectangle(x,y,w,h));
	}
	
	static public void createDoorOfCastle(Castle c) {
		int x = c.getCastleDoor().getCornerLT().getX();
		int y = c.getCastleDoor().getCornerLT().getY();
		double w = Coordonnee.distance(c.getCastleDoor().getCornerLT(), c.getCastleDoor().getCornerRT());
		double h = Coordonnee.distance(c.getCastleDoor().getCornerLT(), c.getCastleDoor().getCornerLB());
		c.setRectDoor(new Rectangle(x,y,w,h));
	}
}
