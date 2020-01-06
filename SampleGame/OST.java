package SampleGame;

import java.io.Serializable;
import java.util.ArrayList;


import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * La Class OST regroupe les informations nécessaire au déplacement de troupes 
 * et à l'attaque entre châteaux * 
 *
 *@param ostUnites
 *	le tableau des troupes présentent dans l'OST
 *@param MaxSpeed
 *	la vitesse maximale de l'OST (plus la valeur est élevé plus l'OST sera lent)
 *@param Target
 *	le château vers lequel l'OST est envoyé
 *@param TeamColor
 *	la couleur du propriétaire (donc la couleur des unités)
 *@param owner
 *	le château qui a envoyé l'OST
 *@param tabOfGeometricForm
 *	le tableau de l'ensemble des formes géométriques de l'OST
 *@param rectangle, circle, polygon
 *	les tableaux des formes qui sont affichées dans le jeu
 *@param targetX, targetY
 *	les coordonnées de la porte du château cible
 *@param inMovment
 *	permet de savoir si les unités ont été affiché dans le jeu et peuvent donc être déplacé
 *@param canAttack
 *	permet de savoir si toutes les unités sont arrivés au château cible
 */
public class OST implements Serializable {
	private ArrayList<Troupes> ostUnites;
	private int MaxSpeed;
	private Castle Target;
	private TeamColor TeamColor;
	private Castle owner;
	
	private ArrayList<GeometricForm> tabOfGeometricForm;
	
	transient private ArrayList<Rectangle> rectangle = new ArrayList<>();
	transient private ArrayList<Circle> circle = new ArrayList<>();
	transient private ArrayList<Polygon> polygon = new ArrayList<>();
	
	private int targetX;
	private int targetY;
	
	private boolean inMovment;
	private boolean canAttack;
	
	/**
	 * Constructeur de OST
	 * @param Target
	 * 	le château cible
	 * @param tab
	 * 	un tableau de 3 entier, qui représente le nombre d'onagres, de piquiers et enfin de chevaliers
	 * @param owner
	 * 	le château qui envoie l'OST
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 */
	OST(Castle Target, int tab[], Castle owner, ArrayList<Castle> tabOfCastle) {
		for(int i=0; i <tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getName() == owner.getName()) {
				this.TeamColor = tabOfCastle.get(i).getColor();
			}
		}
		this.owner = owner;
		this.ostUnites = new  ArrayList<Troupes>();
		this.Target = Target;

		this.MaxSpeed = 1;

		this.targetX = Target.getCastleDoor().getCenter().getX();
		this.targetY = Target.getCastleDoor().getCenter().getY();
		for(int i = 0; i < 3; i++) {
			if(tab[i]!= 0) {
				switch (i) {
					case 0:
						this.MaxSpeed = 3;
						while(tab[i] > 0) {
							this.ostUnites.add(new Onagre(owner.getName()));
							tab[i]--;
						}
						break;
					case 1:
						if(this.MaxSpeed < 2) {
							this.MaxSpeed = 2;
						}
						while(tab[i] > 0) {
							this.ostUnites.add(new Piquier(owner.getName()));
							tab[i]--;
						}
						break;
					case 2:
						while(tab[i] > 0) {
							this.ostUnites.add(new Chevalier(owner.getName()));
							tab[i]--;
						}
						break;
					default: 
						break;
				}
			}
		}
	}
	

	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	public int getMaxSpeed() {
		return MaxSpeed;
	}
	public ArrayList<Troupes> getOstUnites() {
		return ostUnites;
	}

	public void setOstUnites(ArrayList<Troupes> ostUnites) {
		this.ostUnites = ostUnites;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.MaxSpeed = maxSpeed;
	}
	public Castle getTarget() {
		return Target;
	}
	public void setTarget(Castle target) {
		this.Target = target;
	}
	public TeamColor getTeamColor() {
		return TeamColor;
	}
	public void setTeamColor(TeamColor TeamColor) {
		this.TeamColor = TeamColor;
	}
	public void setOwner(Castle owner) {
		this.owner = owner;
	}
	public Castle getOwner() {
		return owner;
	}
	public ArrayList<Rectangle> getRectangle() {
		return rectangle;
	}
	public void setRectangle(ArrayList<Rectangle> rectangle) {
		this.rectangle = rectangle;
	}
	public ArrayList<Circle> getCircle() {
		return circle;
	}
	public void setCircle(ArrayList<Circle> circle) {
		this.circle = circle;
	}
	public ArrayList<Polygon> getPolygon() {
		return polygon;
	}
	public void setPolygon(ArrayList<Polygon> polygon) {
		this.polygon = polygon;
	}
	public int getTargetX() {
		return targetX;
	}
	public void setTargetX(int x) {
		this.targetX = x;
	}
	public int getTargetY() {
		return targetY;
	}
	public void setTargetY(int y) {
		this.targetY = y;
	}
	public boolean getInMovment() {
		return inMovment;
	}
	public void setInMovment(boolean inMovment) {
		this.inMovment = inMovment;
	}
	public boolean getCanAttack() {
		return canAttack;
	}
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}
	public ArrayList<GeometricForm> getTabOfGeometricForm() {
		return tabOfGeometricForm;
	}
	public void setTabOfGeometricForm(ArrayList<GeometricForm> tabOfGeometricForm) {
		this.tabOfGeometricForm = tabOfGeometricForm;
	}	
}
