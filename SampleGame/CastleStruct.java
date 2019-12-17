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
			if(inASquare(castle, cornerLT) == true || inASquare(castle, cornerLB) == true || inASquare(castle, cornerRT) == true || inASquare(castle, cornerRB) == true){
				return false;
			}
		}
		return true;
	}
	
	private Boolean inASquare(CastleStruct Castle, Coordonnee aPoint) {
		// Xmin <= x <= Xmax ET Ymin <= y <= Ymax
		int xMin = Castle.getCornerLT().getX() - Settings.DOORSIZE; //LEFT CORNER
		int xMax = Castle.getCornerRT().getX() + Settings.DOORSIZE;	//RIGHT CORNER
		int yMin = Castle.getCornerLT().getY() - Settings.DOORSIZE;	//TOP CORNER
		int yMax = Castle.getCornerLB().getY() + Settings.DOORSIZE; //TOP CORNER
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
