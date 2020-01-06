package SampleGame;

import java.io.Serializable;
import java.util.ArrayList;


public class CastleStruct implements Serializable {	
	/**
	 * @param center, cornerLT, cornerLB, cornerRT, cornerRB
	 * 	le centre du château et ses 4 coins, 
	 * 	utilisé pour la création du rectangle à afficher et pour gérer les déplacements. 
	 * 	@Coordonnee
	 */
	private Coordonnee center, cornerLT, cornerLB, cornerRT, cornerRB;
	/**
	 * Constructeur de la class CastleStruct
	 * @param typeCastle
	 * 	permet de déterminer la largeur du château en fonction de son type. @see Settings
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu. Il y est ajouté à la fin de sa création
	 */
	CastleStruct(String typeCastle, ArrayList<Castle> tabOfCastle) {
		
		Coordonnee c = new Coordonnee();
		while(invalideCoordonnee(c, typeCastle, tabOfCastle) == false ) {
			c = new Coordonnee();
		}
		this.center = c;
		int size;
		if(typeCastle == "Duc" || typeCastle == "Player") {
			size = Settings.DUCSIZE;
		}else {
			size = Settings.BARONSIZE;
		}
		this.cornerLT = new Coordonnee(center.getX() - size, center.getY() - size);
		this.cornerLB = new Coordonnee(center.getX() - size, center.getY() + size);
		this.cornerRT = new Coordonnee(center.getX() + size, center.getY() - size);
		this.cornerRB = new Coordonnee(center.getX() + size, center.getY() + size);
	}
		

	/**
	 * Vérifie si le château peut être placé à cette coordonnée
	 *
	 * @param center
	 * 	coordonnée générée aléatoirement, à tester.
	 * @param typeCastle
	 * 	permet de déterminer la largeur du château en fonction de son type. @see Settings
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * @return un boolean qui indique si le château créé avec center est en collision avec les châteaux déjà créés.
	 */
	private Boolean invalideCoordonnee(Coordonnee center, String typeCastle, ArrayList<Castle> tabOfCastle) {
		int size;
		if( typeCastle == "Duc" || typeCastle == "Player") {
			size = Settings.DUCSIZE;
		}else {
			size = Settings.BARONSIZE;
		}
		Coordonnee cornerLT = new Coordonnee(center.getX() - size, center.getY() - size);
		Coordonnee cornerLB = new Coordonnee(center.getX() - size, center.getY() + size);
		Coordonnee cornerRT = new Coordonnee(center.getX() + size, center.getY() - size);
		Coordonnee cornerRB = new Coordonnee(center.getX() + size, center.getY() + size);
		for(int i=0;i<tabOfCastle.size();i++) {
			CastleStruct castle = tabOfCastle.get(i).getCastle();
			if(Coordonnee.inASquare(castle, cornerLT) == true || Coordonnee.inASquare(castle, cornerLB) == true || Coordonnee.inASquare(castle, cornerRT) == true || Coordonnee.inASquare(castle, cornerRB) == true){
				return false;
			}
			
		}
		if(cornerLT.getX() < Settings.INFORMATIONSWIDTH + 10 && cornerLT.getY() < Settings.MAXHEIGHT) {
			return false;
		}
		return true;
	}
			

	/* ----- GETTER ----- */
	/* ----- SETTER ----- */

	
	public Coordonnee getCenter() {
		return center;
	}
	public void setCenter(Coordonnee center) {
		this.center = center;
	}
	public Coordonnee getCornerLT() {
		return cornerLT;
	}
	public void setCornerLT(Coordonnee cornerLT) {
		this.cornerLT = cornerLT;
	}
	public Coordonnee getCornerLB() {
		return cornerLB;
	}
	public void setCornerLB(Coordonnee cornerLB) {
		this.cornerLB = cornerLB;
	}
	public Coordonnee getCornerRT() {
		return cornerRT;
	}
	public void setCornerRT(Coordonnee cornerRT) {
		this.cornerRT = cornerRT;
	}
	public Coordonnee getCornerRB() {
		return cornerRB;
	}
	public void setCornerRB(Coordonnee cornerRB) {
		this.cornerRB = cornerRB;
	}	
	
	
}
