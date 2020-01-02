package SampleGame;

import java.util.ArrayList;

public class Troupes {
	
	protected String name;
	protected String Owner;
	protected int cout;
	protected int temps;
	protected int vitesse;
	protected int vie;
	protected int degat;
	protected GeometricForm shape;
	protected TeamColor Color;
	
	
	public static ArrayList<Troupes> createTroupes(String CastleName, int p, int c, int o) {
		ArrayList<Troupes> tab = new ArrayList<Troupes>();
		
		for(int i = 0; i < p; i++) {
			tab.add(new Piquier(CastleName));
		}
		
		for(int i = 0; i < c; i++) {
			tab.add(new Chevalier(CastleName));
		}
		
		for(int i = 0; i < o; i++) {
			tab.add(new Onagre(CastleName));
		}
		
		return tab;
	}
	
	public static void addToCastle(ArrayList<Troupes> tabTroupes, ArrayList<Troupes> troupesSend) {	// Ajout de la troupe envoyee a la troupe du chateau selectionne
		for(int i = 0; i < troupesSend.size(); i++) {
			tabTroupes.add(troupesSend.get(i));
		}
	}
	
	public static Boolean doDamage(Troupes soldier, ArrayList<Troupes> defenser) {	// Fonction recursive : inflige des degats a une unité
		// Choisir une unité aléatoire à attaquer	
		int i = (int) (Math.random() * defenser.size());
		
		while(soldier.degat != 0) {
			if(defenser.isEmpty()) {
				return true;
			}
			Troupes defenserUnderAttack = defenser.get(i);
			defenserUnderAttack.vie = defenserUnderAttack.vie - 1;
			soldier.degat = soldier.degat - 1;
			if(defenserUnderAttack.vie == 0) {
				defenser.remove(defenserUnderAttack);
				i = (int) (Math.random() * defenser.size());
			}
		}
		return false;
	}		
		
	public static Boolean attackACastle(ArrayList<Castle> tabOfCastle, Castle attacker, Castle defenser, ArrayList<Troupes> troopOfattacker, ArrayList<Troupes> troopOfdefenser) {	
		if(attacker.getName() == defenser.getName()) {
			RunACastle.addReinforcement(tabOfCastle, attacker, defenser, troopOfattacker);
		}
		if(troopOfdefenser.size() == 0) {
			RunACastle.changeOwner(tabOfCastle, attacker, defenser, troopOfattacker);
			return true;
		}else {
			for(int i=0; i<troopOfattacker.size(); i++) {
				if(doDamage(troopOfattacker.get(i), troopOfdefenser) == true) {
					troopOfattacker.remove(i);
					RunACastle.changeOwner(tabOfCastle, attacker, defenser, troopOfattacker);
					return true;
				}else {
					troopOfattacker.remove(i);
				}
			}
			return false;
		}
	}



	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	@Override
	public String toString() {
		return "Troupes : ";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public int getDegat() {
		return degat;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

}
