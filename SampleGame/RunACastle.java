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
		switch(p.get(0)) {
		case "Piquier" :
			c.setTresor(c.getTresor() + new Piquier(c.getName()).getCout());
			break;
		case "Chevalier" :
			c.setTresor(c.getTresor() + new Chevalier(c.getName()).getCout());
			break;
		case "Onagre" :
			c.setTresor(c.getTresor() + new Onagre(c.getName()).getCout());
			break;
		case "Améliorer" :
			c.setTresor(c.getTresor() + 1000*c.getNiveau());
			break;
		}
	}
	
	static int recoverCost(Castle c, ArrayList<String> p) {
		switch(p.get(0)) {
		case "Piquier" :
			return new Piquier(c.getName()).getTemps();
		case "Chevalier" :
			return new Chevalier(c.getName()).getTemps();
		case "Onagre" :
			return new Onagre(c.getName()).getTemps();
		case "Améliorer" :
			return 100+50*c.getNiveau();
		default :
			return 0;
		}
	}

	/* ----- GETTER ----- */
	/* ----- SETTER ----- */

}
