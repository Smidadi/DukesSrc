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
		c.setNiveau(c.getNiveau()+1);
	}
	
	static void reduceTresor(Castle c) {
		int u = 1000 * c.getNiveau();
		if(c.getTresor() < u) {
			System.out.println("Amélioration impossible");
		}
		else {
			c.setTresor(c.getTresor() - u);
		}
	}	
	
	static void removeProduction(Castle c, ArrayList<String> p) {
		if(p.get(0) == "Piquier") {
			c.setTresor(c.getTresor() + new Piquier(c.getName()).getCout());
		}
		else if(p.get(0) =="Chevalier") {
			c.setTresor(c.getTresor() + new Chevalier(c.getName()).getCout());
		}
		else if(p.get(0) == "Onagre") {
			c.setTresor(c.getTresor() + new Onagre(c.getName()).getCout());
		}
		else if(p.get(0) == "Améliorer") {
			c.setTresor(c.getTresor() + 1000*c.getNiveau());
		}
	}
	

	/* ----- GETTER ----- */
	/* ----- SETTER ----- */

}
