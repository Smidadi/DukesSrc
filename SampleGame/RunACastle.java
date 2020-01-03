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
		c.setRevenu(c.getLevel() * 10);
	}
	
	static void updateTresorBaron(Castle c) {
		c.setTresor(c.getTresor() + (int) (c.getRevenu() * 0.1));
	}
	
	static void updateTresor(Castle c) {
		c.setTresor(c.getTresor() + c.getRevenu());
	}
	
	static void updateNiveau(Castle c) {
		c.setLevel(c.getLevel()+1);
	}
	
<<<<<<< HEAD
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
=======
	//Partie qui traite les intéractions entre trésorerie - réserve et Production
	static void removeCostOfProduction(Castle c) {
		switch(c.getProductionLine().getTabOfProduction().get(0)) {
>>>>>>> 8664d0d1458e27d4f9c50f9df7243c92992434f2
		case "Piquier" :
			c.setTresor(c.getTresor() - c.getProductionLine().getCostOfPiquier());
			break;
		case "Chevalier" :
			c.setTresor(c.getTresor() - c.getProductionLine().getCostOfChevalier());
			break;
		case "Onagre" :
			c.setTresor(c.getTresor() - c.getProductionLine().getCostOfOnagre());
			break;
		case "Améliorer" :
			c.setTresor(c.getTresor() - c.getProductionLine().getCostOfUpgrade());
			break;
		}
	}

	static void refundCostOfProduction(Castle c) {
		switch(c.getProductionLine().getTabOfProduction().get(0)) {
		case "Piquier" :
			c.setTresor(c.getTresor() + c.getProductionLine().getCostOfPiquier());
			break;
		case "Chevalier" :
			c.setTresor(c.getTresor() + c.getProductionLine().getCostOfChevalier());
			break;
		case "Onagre" :
<<<<<<< HEAD
			return new Onagre(c.getName()).getTemps();
		case "Améliorer" :
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
			else if(c.getTabOfProduction().get(0) == "Améliorer") {
				c.setTimeOfProduction(100 + 50*c.getNiveau());
			}
		}
	}
	
	static boolean checkTimeOfProduction(Castle c) {
		if(c.getTimeOfProduction() != 0) {
			return true;
=======
			c.setTresor(c.getTresor() + c.getProductionLine().getCostOfOnagre());
			break;
		case "Améliorer" :
			c.setTresor(c.getTresor() + c.getProductionLine().getCostOfUpgrade());
			break;
>>>>>>> 8664d0d1458e27d4f9c50f9df7243c92992434f2
		}
	}
	
	static void CollectProduction(Castle c) {
		switch(c.getProductionLine().getTabOfProduction().get(0)) {
		case "Piquier" :
			c.getTabTroupes().add(new Piquier(c.getOwner()));
			break;
		case "Chevalier" :
			c.getTabTroupes().add(new Chevalier(c.getOwner()));
			break;
		case "Onagre" :
			c.getTabTroupes().add(new Onagre(c.getOwner()));
			break;
		case "Améliorer" :
			updateNiveau(c);
			Production.increaseCostOfUpgrade(c);
			break;
		}
	}
	
	//Partie qui traite les intéractions entre une OST et un chateau
	static void changeOwner(ArrayList<Castle> tabOfCastle, Castle attacker, Castle defenser, ArrayList<Troupes> troopOfattacker) {
		for(int i = 0; i<tabOfCastle.size(); i++) {			
			if(defenser.getName() == tabOfCastle.get(i).getName()) {		
				tabOfCastle.get(i).setOwner(attacker.getOwner());
				tabOfCastle.get(i).getProductionLine().getTabOfProduction().clear();
				tabOfCastle.get(i).setColor(attacker.getColor());
				for(int k = 0; k<troopOfattacker.size(); k++) {
					tabOfCastle.get(i).getTabTroupes().add(troopOfattacker.get(k));
					troopOfattacker.remove(k);
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
