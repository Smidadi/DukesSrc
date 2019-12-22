package SampleGame;

import java.util.ArrayList;

public class RunACastle {	// w : 1500 ; h : 1000 pour la map
			
	static public int countTroupes(String t, ArrayList<Troupes> tab) {
		int cpt = 0;
		switch(t) {
		case "Piquier" :
			for(int i = 0; i < tab.size(); i++) {
				if(tab.get(i).getName() == t) {
					cpt++;
				}
			}
			return cpt;
		case "Chevalier" :
			for(int i = 0; i < tab.size(); i++) {
				if(tab.get(i).getName() == t) {
					cpt++;
				}
			}
			return cpt;
		case "Onagre" :
			for(int i = 0; i < tab.size(); i++) {
				if(tab.get(i).getName() == t) {
					cpt++;
				}
			}
			return cpt;
		}
		return cpt;
	}
	
	static void updateRevenu(Castle c) {
		c.setRevenu(c.getNiveau() * 10);
	}
	
	static void updateTresor(Castle c) {
		c.setTresor(c.getTresor() + c.getRevenu());
	}
	
	static void updateNiveau(Castle c) {
		int u = 1000 * c.getNiveau();
		if(c.getTresor() < u) {
			System.out.println("Amélioration impossible");
		}
		else {
			c.setNiveau(c.getNiveau()+1);
			c.setTresor(c.getTresor() - u);
		}
	}
	
	

	/* ----- GETTER ----- */
	/* ----- SETTER ----- */

}
