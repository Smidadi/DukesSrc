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
			System.out.println("Am�lioration impossible");
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
		case "Am�liorer" :
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
		case "Am�liorer" :
			return 100+50*c.getNiveau();
		default :
			return 0;
		}
	}
	
	
	static void getTimeOfProduction(Castle c) {
		if(c.getTimeOfProduction() == 0) {
			if(c.getTabOfProduction().get(0) == "Piquier") {
				c.setTimeOfProduction(new Piquier(c.getName()).getTemps());
			}
			else if(c.getTabOfProduction().get(0) == "Chevalier") {
				c.setTimeOfProduction(new Chevalier(c.getName()).getTemps());
			}
			else if(c.getTabOfProduction().get(0) == "Onagre") {
				c.setTimeOfProduction(new Onagre(c.getName()).getTemps());
			}
			else if(c.getTabOfProduction().get(0) == "Am�liorer") {
				c.setTimeOfProduction(100 + 50*c.getNiveau());
			}
		}
	}
	
	static boolean checkTimeOfProduction(Castle c) {
		if(c.getTimeOfProduction() != 0) {
			return true;
		}
		return false;
	}
	
	static void changeTimeOfProduction(Castle c) {
		getTimeOfProduction(c);
		if(c.getTabOfProduction().get(0) == "Améliorer") {
			updateNiveau(c);
			c.getTabOfProduction().remove(0);
			if(!c.getTabOfProduction().isEmpty()) {
				getTimeOfProduction(c);
			}
			return;
		}
		else if(c.getTabOfProduction().get(0) == "Piquier") {
			c.getTabOfProduction().remove(0);
			c.getTabTroupes().add(new Piquier(c.getOwner()));
			if(!c.getTabOfProduction().isEmpty()) {
				getTimeOfProduction(c);
			}
			return;
		}
	}
	
	static void changeOwner(ArrayList<Castle> tabOfCastle, Castle attacker, Castle defenser, ArrayList<Troupes> troopOfattacker) {
		for(int i = 0; i<tabOfCastle.size(); i++) {			
			if(defenser.getName() == tabOfCastle.get(i).getName()) {		
				tabOfCastle.get(i).setOwner(attacker.getOwner());
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
			if(defenser.getName() == tabOfCastle.get(i).getName()) {
				for(int k = 0; k<troopOfattacker.size(); k++) {
					tabOfCastle.get(i).getTabTroupes().add(troopOfattacker.get(k));
					troopOfattacker.remove(k);
				}
			}
		}
	}
}
