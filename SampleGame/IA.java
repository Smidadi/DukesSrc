package SampleGame;

import java.util.ArrayList;

/**
 *  La class IA gère les actions à effectuer par les châteaux non jouables
 *
 */

public class IA {
	
	/**
	 * Attribue une action aléatoire à effectuer à un château non jouable à l'aide de
	 * variables randoms
	 * @param c
	 * 	château ciblé pour effectuer l'action
	 * @param tabOfCastle
	 * 	tableau de tous les châteaux présents dans le jeu
	 * @param tabOfOST
	 * 	tableau de tous les OST présents sur la map
	 */
	
	static void randomAction(Castle c, ArrayList<Castle> tabOfCastle, ArrayList<OST> tabOfOST) {
		int random = (int) (Math.random() * 2);
		int randUnite;
		int tab[] = new int[3];
		if(random == 0) {
			// Cas de production de troupes
			random = (int) (Math.random() * 4);
			switch(random) {
			case 0 : // Production d'un piquier
				if(c.getTresor() >= c.getProductionLine().getCostOfPiquier()) {
					c.getProductionLine().getTabOfProduction().add("Piquier");
					RunACastle.removeCostOfProduction(c);
					if(c.getProductionLine().getTimeLeft() == 0) {
						c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfPiquier());
					}
				}
				break;
			case 1 : // Production d'un chevalier
				if(c.getTresor() >= c.getProductionLine().getCostOfChevalier()) {
					c.getProductionLine().getTabOfProduction().add("Chevalier");
					RunACastle.removeCostOfProduction(c);
					if(c.getProductionLine().getTimeLeft() == 0) {
						c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfChevalier());
					}
				}
				break;
			case 2 : // Production d'un onagre
				if(c.getTresor() >= c.getProductionLine().getCostOfOnagre()) {
					c.getProductionLine().getTabOfProduction().add("Onagre");
					RunACastle.removeCostOfProduction(c);
					if(c.getProductionLine().getTimeLeft() == 0) {
						c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfOnagre());
					}
				}
				break;
			case 3 :
				if(c.getTresor() >= c.getProductionLine().getCostOfUpgrade() && c.getTypeOwner().compareTo("Duc") == 0) {
					c.getProductionLine().getTabOfProduction().add("Améliorer");
					RunACastle.removeCostOfProduction(c);
					if(c.getProductionLine().getTimeLeft() == 0) {
						c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfUpgrade());
					}
				}
			default :
				break;
			}
		} else if(random == 1) {
			randUnite = (int) (Math.random() * 3);
			OST ost;
			switch(randUnite) {
			case 0 : // Un type de troupes à envoyer
				setInTabRandomUnite(c, randomUnite((int) (Math.random() * 3)), tab);
				
				ost = new OST(randomTargetCastle(c, tabOfCastle), tab, c, tabOfCastle);
				ost.setInMovment(true);
				ost.setTabOfGeometricForm(GeometricForm.tabOfGeometricForm(ost, tabOfCastle));
				tabOfOST.add(ost);
				break;
			case 1 :
				String randToEscape = randomUnite((int) (Math.random() * 3));
				setInTabRandomUnite(c, randToEscape , tab);
				String randToTest = randomUnite((int) (Math.random() * 3));
				
				while(randToEscape == randToTest ) {
					randToTest = randomUnite((int) (Math.random() * 3));
				}
				setInTabRandomUnite(c, randomUnite((int) (Math.random() * 2)), tab);
				
				ost = new OST(randomTargetCastle(c, tabOfCastle), tab, c, tabOfCastle);
				ost.setInMovment(true);
				ost.setTabOfGeometricForm(GeometricForm.tabOfGeometricForm(ost, tabOfCastle));
				tabOfOST.add(ost);
				break;
			case 2 :
				setInTabRandomUnite(c, "Piquier" , tab);
				setInTabRandomUnite(c, "Chevalier" , tab);
				setInTabRandomUnite(c, "Onagre" , tab);
				
				ost = new OST(randomTargetCastle(c, tabOfCastle), tab, c, tabOfCastle);
				ost.setInMovment(true);
				ost.setTabOfGeometricForm(GeometricForm.tabOfGeometricForm(ost, tabOfCastle));
				tabOfOST.add(ost);
				break;
			default :
				System.out.println("No type found");
				break;
			}
		}
	}
	
	/**
	 * Selon le type d'unité choisie : l'algorithme choisi un nombre aléatoire entre 0 et le nombre
	 * maximal de troupes de l'unité choisie à envoyer
	 * @param c
	 * 	château ciblé pour effectuer l'action
	 * @param unite
	 * 	unité à envoyer
	 * @param tab
	 * 	tableau contenant l'OST
	 */
	
	static void setInTabRandomUnite(Castle c, String unite, int[] tab) {
		if(unite == "Piquier") {
			if(RunACastle.countTroupes(unite, c.getTabTroupes()) == 1) {
				tab[1] = (int) (Math.random() * 2);
			}
			else {
				tab[1] = (int) (Math.random() * RunACastle.countTroupes(unite, c.getTabTroupes()));
			}
		}
		else if(unite == "Chevalier") {
			if(RunACastle.countTroupes(unite, c.getTabTroupes()) == 1) {
				tab[2] = (int) (Math.random() * 2);
			}
			else {
				tab[2] = (int) (Math.random() * RunACastle.countTroupes(unite, c.getTabTroupes()));
			}
		}
		else if(unite == "Onagre") {
			if(RunACastle.countTroupes(unite, c.getTabTroupes()) == 1) {
				tab[0] = (int) (Math.random() * 2);
			}
			else {
				tab[0] = (int) (Math.random() * RunACastle.countTroupes(unite, c.getTabTroupes()));
			}
		}
		else {
			System.out.println("No type found");
		}
	}
	
	/**
	 * Choisi un château cible random dans le jeu
	 * @param c
	 * 	château ciblé pour effectuer l'action : permet de vérifier s'il ne s'enfoi pas des troupes 
	 * 	à lui-même
	 * @param tabOfCastle
	 * 	cherche un château parmis ceux présents dans le jeu
	 * @return	retourne le château à attaquer
	 */
	
	static Castle randomTargetCastle(Castle c, ArrayList<Castle> tabOfCastle) {
		int randomTargetCastle = (int) (Math.random() * tabOfCastle.size());
		while(tabOfCastle.get(randomTargetCastle) == c) {
			randomTargetCastle = (int) (Math.random() * tabOfCastle.size());
		}
		return tabOfCastle.get(randomTargetCastle);
	}
	
	/**
	 * Choisi une unité random
	 * @param random
	 * 	variable random permettant de retourner la troupe choisi
	 * @return retourne la troupe à envoyer
	 */
	
	static String randomUnite(int random) {
		switch(random) {
		case 0 :
			return "Piquier";
		case 1 :
			return "Chevalier";
		case 2 :
			return "Onagre";
		default :
			return "NO TYPE";
		}
	}
}
