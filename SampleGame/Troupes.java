package SampleGame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * class mére de Piquier, Chevalier, Onagre
 * 	@see	Piquier
 * 	@see	Chevalier
 *	 @see	Onagre
 * @param name
 * 	le nom de l'unité
 * @param Owner
 * 	le nom du propriétaire de l'unité. ex : "player"
 * @param vie
 * 	le nombre de points de vie de l'unité. Utilisé lors d'une défense de château
 * @param degat
 * 	le nombre de points de degâts de l'unité. Utilisé lors des attaques de château
 * @param color
 * 	la couleur du propriétaire
 *
 */
public class Troupes implements Serializable {
	
	protected String name;
	protected String Owner;
	protected int vie;
	protected int degat;
	protected TeamColor Color;
	
	/**
	 * crée les tableaux d'unité pour les OST et les châteaux
	 * @see Castle
	 * @see OST
	 * 
	 * @param owner
	 * 	le propriétaire des unités
	 * @param p
	 * 	le nombre de piquiers
	 * @param c
	 * 	le nombre de chevaliers
	 * @param o
	 * 	le nombre d'onagre
	 * @return le tableau d'unité.
	 */
	public static ArrayList<Troupes> createTroupes(String owner, int p, int c, int o) {
		ArrayList<Troupes> tab = new ArrayList<Troupes>();
		
		for(int i = 0; i < p; i++) {
			tab.add(new Piquier(owner));
		}
		
		for(int i = 0; i < c; i++) {
			tab.add(new Chevalier(owner));
		}
		
		for(int i = 0; i < o; i++) {
			tab.add(new Onagre(owner));
		}
		
		return tab;
	}
	
	/**
	 * Utilisé pour ajouter des troupes à un château allié (même propriétaire)
	 * @param tabTroupes
	 * 	le tableau d'unité en réserve dans le château
	 * 	@see Castle
	 * @param troupesSend
	 * 	le tableau d'unité qui vont être transférées en réserve dans le château
	 */
	public static void addToCastle(ArrayList<Troupes> tabTroupes, ArrayList<Troupes> troupesSend) {	// Ajout de la troupe envoyee a la troupe du chateau selectionne
		for(int i = 0; i < troupesSend.size(); i++) {
			tabTroupes.add(troupesSend.get(i));
		}
	}
	
	/**
	 * Applique les dégats dune unité attaquante à la réserve d'unités d'un château
	 * @param soldier
	 * 	l'unité attaquante
	 * @param defenser
	 * 	le tableau d'unité en réserve dans le château
	 * @return true si le château est vide, false si il reste des unités dans le château
	 */
	public static Boolean doDamage(Troupes soldier, ArrayList<Troupes> defenser) {	// Fonction recursive : inflige des degats a une unit�
		// Choisir une unit� al�atoire � attaquer	
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
	
	/**
	 * Cette fonction gére l'arrivé d'une OST à un château
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * @param attacker
	 * 	le château attaquant
	 * @param defenser
	 * 	le château defenser
	 * @param troopOfattacker
	 * 	le tableau d'unité de l'OST 
	 * @param troopOfdefenser
	 * 	le tableau d'unité de la réserve du château attaqué
	 * @return	true lors de la prise d'un château (changement de propriétaire) sinon false
	 */
	public static Boolean attackACastle(ArrayList<Castle> tabOfCastle, Castle attacker, Castle defenser, ArrayList<Troupes> troopOfattacker, ArrayList<Troupes> troopOfdefenser) {	
		if(attacker.getOwner() == defenser.getOwner()) {
			RunACastle.addReinforcement(tabOfCastle, attacker, defenser, troopOfattacker);
			return false;
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
