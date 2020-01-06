package SampleGame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class SaveGame extends Main {
	static public void saveGame(ArrayList<Castle> tabOfCastle, ArrayList<OST> tabOfOST) {
		ObjectOutputStream oos = null;
		//ArrayList<>
		try {
			FileOutputStream out = new FileOutputStream("save.ser");
			oos = new ObjectOutputStream(out);
			// Sauvegarde des chateaux
			for(int i = 0; i < tabOfCastle.size(); i++) {
				oos.writeObject(tabOfCastle.get(i));
			}
			
			// Sauvegarde des OST
			for(int i = 0; i < tabOfOST.size(); i++) {
				tabOfOST.get(i).setTabOfGeometricForm(GeometricForm.newTabOfGeometricForm(tabOfOST.get(i)));
				oos.writeObject(tabOfOST.get(i));
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(oos != null) {
					oos.flush();
					oos.close();
				}
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
