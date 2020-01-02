package SampleGame;

import java.util.ArrayList;

public class RunACastle {	// w : 1500 ; h : 1000 pour la map
	
	static public int countTroupes(String typeUnit, ArrayList<Troupes> tab) {
		int cpt = 0;
		switch(typeUnit) {
		case "Piquier" :
			for(int i = 0; i < tab.size(); i++) {
				if(tab.get(i).getName() == typeUnit) {
					cpt++;
				}
			}
			return cpt;
		case "Chevalier" :
			for(int i = 0; i < tab.size(); i++) {
				if(tab.get(i).getName() == typeUnit) {
					cpt++;
				}
			}
			return cpt;
		case "Onagre" :
			for(int i = 0; i < tab.size(); i++) {
				if(tab.get(i).getName() == typeUnit) {
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
	
	static void updateTresorBaron(Castle c) {
		c.setTresor(c.getTresor() + (int) (c.getRevenu() * 0.1));
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
	
	// o p c
	static ArrayList<Troupes> removeOST(Castle c, int ost[]) {
		for(int i = 0; i < c.getTabTroupes().size(); i++) {
			if(c.getTabTroupes().get(i).getName() == "Onagre" && ost[0] != 0) {
				c.getTabTroupes().remove(i);
				ost[0]--;
			}
			if(c.getTabTroupes().get(i).getName() == "Piquier" && ost[1] != 0) {
				c.getTabTroupes().remove(i);
				ost[1]--;
			}
			if(c.getTabTroupes().get(i).getName() == "Chevalier" && ost[2] != 0) {
				c.getTabTroupes().remove(i);
				ost[2]--;
			}
		}
		System.out.println(c.getTabTroupes());
		return c.getTabTroupes();
	}
	
	static void changeOwner(ArrayList<Castle> tabOfCastle, Castle attacker, Castle defenser, ArrayList<Troupes> troopOfattacker) {
		for(int i = 0; i<tabOfCastle.size(); i++) {			
			if(defenser.getName() == tabOfCastle.get(i).getName()) {		
				tabOfCastle.get(i).setOwner(attacker.getOwner());
				String n = Integer.toString(getNbOfCastle(tabOfCastle, attacker.getOwner())); //creation d'un nom basé sur le nombre de chateau de l'attaquant
				tabOfCastle.get(i).setName(attacker.getName() + "-" + n);
				tabOfCastle.get(i).getTabOfProduction().clear();
				tabOfCastle.get(i).setColor(attacker.getColor());
				for(int k = 0; k<troopOfattacker.size(); k++) {
					tabOfCastle.get(i).getTabTroupes().add(troopOfattacker.get(k));
				}				
			}
		}
	} 
	
	
	static void addReinforcement(ArrayList<Castle> tabOfCastle, Castle attacker, Castle defenser, ArrayList<Troupes> troopOfattacker) {
		for(int i = 0; i<tabOfCastle.size(); i++) {
			if(defenser.getOwner() == tabOfCastle.get(i).getOwner()) {
				for(int k = 0; k<troopOfattacker.size(); k++) {
					tabOfCastle.get(i).getTabTroupes().add(troopOfattacker.get(k));
				}
			}
		}
	}
	
	static int getNbOfCastle(ArrayList<Castle> tabOfCastle, String Owner) {
		int nbOfCastle = 0;
		for(int i = 0; i<tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getOwner() == Owner) {
				nbOfCastle++;
			}
		}
		return nbOfCastle;
	}
}
