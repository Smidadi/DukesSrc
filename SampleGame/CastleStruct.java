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
			if(Coordonnee.inASquare(castle, cornerLT.getX(),cornerLT.getY(),Settings.DOORSIZE) == true 
				|| Coordonnee.inASquare(castle, cornerLB.getX(),cornerLB.getY(),Settings.DOORSIZE) == true 
				|| Coordonnee.inASquare(castle, cornerRT.getX(),cornerRT.getY(),Settings.DOORSIZE) == true 
				|| Coordonnee.inASquare(castle, cornerRB.getX(),cornerRB.getY(),Settings.DOORSIZE) == true){
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
