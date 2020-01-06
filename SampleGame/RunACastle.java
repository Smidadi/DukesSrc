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
		c.setTresor(c.getTresor() + c.getRevenu());
	}
	
	static void updateTresor(Castle c) {
		c.setTresor(c.getTresor() + c.getRevenu());
	}
	
	static void updateNiveau(Castle c) {
		c.setLevel(c.getLevel()+1);
	}	

	//Partie qui traite les intéractions entre trésorerie - réserve et Production
	static void removeCostOfProduction(Castle c) {
		switch(c.getProductionLine().getTabOfProduction().get(0)) {
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
		default :
			break;
		}
	}

	static void removeAProduction(Castle c) {
		switch(c.getProductionLine().getTabOfProduction().get(c.getProductionLine().getTabOfProduction().size()-1)) {
		case "Piquier" :
			c.setTresor(c.getTresor() + c.getProductionLine().getCostOfPiquier());
			break;
		case "Chevalier" :
			c.setTresor(c.getTresor() + c.getProductionLine().getCostOfChevalier());
			break;
		case "Onagre" :
			c.setTresor(c.getTresor() + c.getProductionLine().getCostOfOnagre());
			break;
		case "Améliorer" :
			c.setTresor(c.getTresor() + c.getProductionLine().getCostOfUpgrade());
			break;
		default :
			break;
		}
		c.getProductionLine().getTabOfProduction().remove(c.getProductionLine().getTabOfProduction().size()-1); //remove the last production
		if(c.getProductionLine().getTabOfProduction().size() == 0) {
			c.getProductionLine().setTimeLeft(0);
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
			updateRevenu(c);
			updateNiveau(c);
			Production.increaseCostOfUpgrade(c);
			break;
		default :
			break;
		}
	}
	
	//Partie qui traite les intéractions entre une OST et un chateau
	static void changeOwner(ArrayList<Castle> tabOfCastle, Castle attacker, Castle defenser, ArrayList<Troupes> troopOfattacker) {
		for(int i = 0; i<tabOfCastle.size(); i++) {			
			if(defenser.getName() == tabOfCastle.get(i).getName()) {		
				tabOfCastle.get(i).setOwner(attacker.getOwner());
				tabOfCastle.get(i).setTypeOwner(attacker.getTypeOwner());
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
