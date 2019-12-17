package SampleGame;

import java.util.ArrayList;


public class CastleStruct  {	
	
	private Coordonnee center, cornerLT, cornerLB, cornerRT, cornerRB;
	
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
		

	
	private Boolean invalideCoordonnee(Coordonnee center, String typeCastle, ArrayList<Castle> tabOfCastle) {
		for(int i=0;i<tabOfCastle.size();i++) {
			if(ConflicBetweenCastle(tabOfCastle.get(i).getCastle(), center, typeCastle)) {
				return false;
			}
		}
		return true;
	}

	private Boolean ConflicBetweenCastle(CastleStruct Castle, Coordonnee newCastle, String newType) {		
		int size;
		if( newType == "Duc" || newType == "Player") {
			size = Settings.DUCSIZE;
		}else {
			size = Settings.BARONSIZE;
		}
		Coordonnee cornerLT = new Coordonnee(newCastle.getX() - size, newCastle.getY() - size);
		Coordonnee cornerLB = new Coordonnee(newCastle.getX() - size, newCastle.getY() + size);
		Coordonnee cornerRT = new Coordonnee(newCastle.getX() + size, newCastle.getY() - size);
		Coordonnee cornerRB = new Coordonnee(newCastle.getX() + size, newCastle.getY() + size);
		if(inASquare(Castle, cornerLT) || inASquare(Castle, cornerLB) || inASquare(Castle, cornerRT) || inASquare(Castle, cornerRB)){
			return true;
		}else {
			return false;
		}
	}
	
	private Boolean inASquare(CastleStruct Castle, Coordonnee aPoint) {
		// Xmin <= x <= Xmax ET Ymin <= y <= Ymax
		int xMin = Castle.getCornerLT().getX() - Settings.DOORSIZE;
		int xMax = Castle.getCornerRT().getX() + Settings.DOORSIZE;
		int yMin = Castle.getCornerLT().getX() - Settings.DOORSIZE;
		int yMax = Castle.getCornerLB().getX() + Settings.DOORSIZE;
		if( (xMin <= aPoint.getX() && aPoint.getX() <= xMax) && (yMin <= aPoint.getY() && aPoint.getY() <= yMax) ) {
			return true;
		}
		return false;
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
