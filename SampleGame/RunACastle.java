package SampleGame;

import java.io.Serializable;
import java.util.ArrayList;

<<<<<<< HEAD
public class RunACastle implements Serializable {	// w : 1500 ; h : 1000 pour la map
=======
/**
 * 
 * 	La Class RunACastle implémente les fonctions nécessaire au fonctionnement d'un château
 *
 */
public class RunACastle {	// w : 1500 ; h : 1000 pour la map
>>>>>>> 250f0e54d184f47c5b3a0b7da04154823a8e4913
	
	/**
	 * Count le nombre d'unité ,dans la réserve du château, pour un type donné 
	 * @param typeUnit
	 * 	le type de l'unité (Piquier, Chevalier ou Onagre)
	 * @param tab
	 * 	le tableau des troupes en réserve
	 * @return le nombre d'unité du type donné
	 */
	static public int countTroupes(String typeUnit, ArrayList<Troupes> tab) {
		int cpt = 0;
		switch(typeUnit) {
		case "Piquier" :
			for(int i = 0; i < tab.size(); i++) {
				if(tab.get(i).getName().compareTo(typeUnit) == 0) {
					cpt++;
				}
			}
			return cpt;
		case "Chevalier" :
			for(int i = 0; i < tab.size(); i++) {
				if(tab.get(i).getName().compareTo(typeUnit) == 0) {
					cpt++;
				}
			}
			return cpt;
		case "Onagre" :
			for(int i = 0; i < tab.size(); i++) {
				if(tab.get(i).getName().compareTo(typeUnit) == 0) {
					cpt++;
				}
			}
			return cpt;
		}
		return cpt;
	}
	
	/**
	 * augmente le revenue du château c
	 * @param c
	 * 	le château
	 */
	static void updateRevenu(Castle c) {
		c.setRevenu(c.getLevel() * 10);
	}
	
	/**
	 * augmente le trésor du château c
	 * @param c
	 * 	le château
	 */
	static void updateTresor(Castle c) {
		c.setTresor(c.getTresor() + c.getRevenu());
	}
	
	/**
	 * augmente le niveau du château c
	 * @param c
	 * 	le château
	 */
	static void updateNiveau(Castle c) {
		c.setLevel(c.getLevel()+1);
	}	

	//Partie qui traite les intéractions entre trésorerie - réserve et Production.
	/**
	 * retire le coup de production du château c
	 * en fonction du premier élément de sa file de production
	 * @param c
	 * 	le château c
	 */
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

	/**
	 * retire la dernier production ajouter à la file de production du château c
	 * @param c
	 * 	le château
	 */
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
	
	/**
	 * ajoute l'unité à la réserve ou augmente le niveau et revenue
	 * lorsque le premier élément de la file du château est produit 
	 * @param c
	 * 	le château
	 */
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
	
	//Partie qui traite les intéractions entre un OST et un chateau
	/**
	 * Modifie le propriétaire d'un château après l'attaque d'un OST
	 * si il reste des unités(qui n'ont pas combattu), les ajoute à la réserve de ce château
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * @param attacker
	 * 	le château de l'attaquant
	 * @param defenser
	 * 	le château attaqué (qui a perdu)
	 * @param troopOfattacker
	 * 	le tableau des troupes restantes après attaque
	 */
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
	
	/**
	 * Ajoute les troupes d'un OST à un château allié (même propriétaire)
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * @param attacker
	 * 	le château émetteur
	 * @param defenser
	 * 	le château receveur
	 * @param troopOfattacker
	 * 	le tableau des troupes à transferer
	 */
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
