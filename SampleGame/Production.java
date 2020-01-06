package SampleGame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *La class Production gére la production d'un château 
 * 
 *@param tabOfProduction
 * 	le tableau qui contient le nom des éléments présent dans la file de production
 *@param timeOfPiquier, timeOfChevalier, timeOfOnagre
 *	les différents temps de production selon le type d'unité, ne peux pas être modifié
 *@param costOfPiquier, costOfChevalier, costOfOnagre
 *	les différents coûts de production selon le type d'unité, ne peux pas être modifié
 *@param timeOfUpgrade
 *	le temps d'amélioration du château. Varie selon son niveau
 *	@see RunACastle
 *@param costOfUpgrade
 *	le coût d'amélioration du château. Varie selon son niveau
 *	@see RunACastle
 *@param timeLeft
 *		le temps restant de la production en cours.
 */
public class Production implements Serializable {
	private ArrayList<String> tabOfProduction = new ArrayList<String>();
	//troupes
	private final int timeOfPiquier = 5;
	private final int timeOfChevalier = 20;
	private final int timeOfOnagre = 50;
	private final int costOfPiquier = 100;
	private final int costOfChevalier = 500;
	private final int costOfOnagre = 1000;
	//castle
	private int timeOfUpgrade;
	private int costOfUpgrade;
	//
	private int timeLeft;
	
	/**
	 * Constructeur de la class Production
	 * initialise le coût et le temps de production de l'amélioration du château
	 * @param level
	 * 	le level du château
	 */
	Production(int level){
		this.timeOfUpgrade = 100 + 50 * level;
		this.costOfUpgrade = 1000 * level;
	}
		
	/**
	 * Augmente le temps et coût de l'amélioration du château c
	 * @param c
	 * 	le château
	 */
	static void increaseCostOfUpgrade(Castle c) {
		c.getProductionLine().setTimeOfUpgrade(100 + 50 * c.getLevel());
		c.getProductionLine().setTimeOfUpgrade(1000 * c.getLevel());
	}
	
	/**
	 * Actualise la file de production du chateau c
	 * retire le premier élément de la file
	 * redéfinie le temps restant de production
	 * @param c
	 * 	le chateau
	 */
	 static void updateProduction(Castle c) {
		c.getProductionLine().getTabOfProduction().remove(0);
		if(c.getProductionLine().getTabOfProduction().isEmpty() == false) {
			switch(c.getProductionLine().getTabOfProduction().get(0)) {
			case "Piquier":
				c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfPiquier());
				break;
			case "Chevalier":
				c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfChevalier());
				break;
			case "Onagre":
				c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfOnagre());
				break;
			case "Améliorer":
				c.getProductionLine().setTimeLeft(c.getProductionLine().getTimeOfUpgrade());
			default :
				break;
			}
		}
	}
		
	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */	
	
	public ArrayList<String> getTabOfProduction() {
		return tabOfProduction;
	}
	public void setTabOfProduction(ArrayList<String> tabOfProduction) {
		this.tabOfProduction = tabOfProduction;
	}
	public int getTimeOfPiquier() {
		return timeOfPiquier;
	}
	public int getTimeOfChevalier() {
		return timeOfChevalier;
	}
	public int getTimeOfOnagre() {
		return timeOfOnagre;
	}
	public int getCostOfPiquier() {
		return costOfPiquier;
	}
	public int getCostOfChevalier() {
		return costOfChevalier;
	}
	public int getCostOfOnagre() {
		return costOfOnagre;
	}
	public int getTimeOfUpgrade() {
		return timeOfUpgrade;
	}
	public void setTimeOfUpgrade(int timeOfUpgrade) {
		this.timeOfUpgrade = timeOfUpgrade;
	}
	public int getCostOfUpgrade() {
		return costOfUpgrade;
	}
	public void setCostOfUpgrade(int costOfUpgrade) {
		this.costOfUpgrade = costOfUpgrade;
	}
	public int getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}	
}
