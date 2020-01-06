package SampleGame;

import java.util.ArrayList;

public class IA {
	static void randomAction(Castle c, ArrayList<Castle> tabOfCastle, ArrayList<OST> tabOfOST) {
		int random = (int) (Math.random() * 2);
		int randUnite;
		int tab[] = new int[3];
		if(random == 0) {
			// Cas de production de troupes
			random = (int) (Math.random() * 3);
			switch(random) {
			case 0 : // Production d'un piquier
				if(c.getTresor() >= c.getProductionLine().getCostOfPiquier()) {
					c.getProductionLine().getTabOfProduction().add("Piquier");
					RunACastle.removeCostOfProduction(c);
					if(c.getProductionLine().getTimeLeft() == 0) {
						c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfPiquier());
					}
				}
				System.out.println("Impossible pour piquier");
				break;
			case 1 : // Production d'un chevalier
				if(c.getTresor() >= c.getProductionLine().getCostOfChevalier()) {
					c.getProductionLine().getTabOfProduction().add("Chevalier");
					RunACastle.removeCostOfProduction(c);
					if(c.getProductionLine().getTimeLeft() == 0) {
						c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfChevalier());
					}
				}
				System.out.println("Impossible pour chevalier");
				break;
			case 2 : // Production d'un onagre
				if(c.getTresor() >= c.getProductionLine().getCostOfOnagre()) {
					c.getProductionLine().getTabOfProduction().add("Onagre");
					RunACastle.removeCostOfProduction(c);
					if(c.getProductionLine().getTimeLeft() == 0) {
						c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfOnagre());
					}
				}
				System.out.println("Impossible pour onagre");
				break;
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
	
	static Castle randomTargetCastle(Castle c, ArrayList<Castle> tabOfCastle) {
		int randomTargetCastle = (int) (Math.random() * tabOfCastle.size());
		while(tabOfCastle.get(randomTargetCastle) == c) {
			randomTargetCastle = (int) (Math.random() * tabOfCastle.size());
		}
		return tabOfCastle.get(randomTargetCastle);
	}
	
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
