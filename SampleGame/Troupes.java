package SampleGame;

import java.util.ArrayList;
import java.util.Random;

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
	
	
	public static ArrayList<Troupes> createTroupes( ArrayList<Castle> tabOfCastle, String CastleName, int p, int c, int o) {
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
	
	public void getDamage(ArrayList<Troupes> tab) {	// Fonction recursive : inflige degats a une troupe
		// Choisir un random pour la troupe a eliminer		
		Random r = new Random();
		int rand = r.nextInt(tab.size());
		Troupes troupe_to_attack = tab.get(rand);
		
		
		// Preserver les valeurs de depart
		int d = 0;
		int v = troupe_to_attack.vie;
		
		// Degat inflige a la cible
		troupe_to_attack.vie = troupe_to_attack.vie - this.degat;
		
		// Verification d'une cible morte ou non
		if(troupe_to_attack.vie < 0) {
			d = v - this.degat;
			if(d < 0) {
				d = -d;
			}
			tab.remove(troupe_to_attack);
		}
		
		// Verification de toutes les troupes mortes
		if(tab.isEmpty()) {
			tab.clear();
			d = 0;
			// case winner
		}
		
		// Appel recursif si degat en trop
		if(d != 0 && !tab.isEmpty()) {
			troupe_to_attack.degat = d;
			troupe_to_attack.getDamage(tab);
		}
	}		
	
	public static void addToCastle(ArrayList<Troupes> tabTroupes, ArrayList<Troupes> troupesSend) {	// Ajout de la troupe envoyee a la troupe du chateau selectionne
		for(int i = 0; i < troupesSend.size(); i++) {
			tabTroupes.add(troupesSend.get(i));
		}
	}
	
	public static void attackACastle(ArrayList<Troupes> tabTroupes, ArrayList<Troupes> troupesSend) {	// Fonction recursive : attaque un chateau ennemi avec la troupe envoye par le chateau attaquant
		// Choisir un random pour la troupe qui attaque		
		Random r = new Random();
		int rand = r.nextInt(troupesSend.size());
		Troupes troupe_wich_attack = troupesSend.get(rand);	
		
		// Appel de la fonction attaquer
		troupe_wich_attack.getDamage(tabTroupes);
		troupesSend.remove(troupe_wich_attack);
		
		attackACastle(tabTroupes, troupesSend);
		
		// REGLER DANS CLASSE SUPP TABTROUPES VIDE
		
		// PENSER A UNE FONCTION SEDEFEND() POUR IA : isattack() dans classe supp
		// creer fonction enMouvement(tab) avec troupes � emmener
	}
	
	public void defendHimself(ArrayList<Troupes> tabTroupes, ArrayList<Troupes> enemyTroupes) {	// Appel � attackACastle avec une troupe ennemie comme attaquant
		attackACastle(tabTroupes, enemyTroupes);
	}
	
	static void printTroops(){
		
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
