package SampleGame;

import java.io.Serializable;

public class CastleDoor implements Serializable {
	
	/**
	 * @param center, cornerLT, cornerLB, cornerRT, cornerRB
	 * 	le centre de la porte et ses 4 coins, 
	 * 	utilisé pour la création du rectangle à afficher et pour gérer les déplacements. 
	 * 	@Coordonnee
	 * @param direction
	 * 	définit aléatoirement, donne côté du château où sera placé la porte.
	 * 	Nécessaire pour savoir ou faire sortir et entrer les unités
	 * 	@Coordonnee
	 */
	private Coordonnee center, cornerLT, cornerLB, cornerRT, cornerRB;
	private char direction; //N , E , S , W
	
	/**
	 * Constructeur de la class CastleDoor
	 * @param center2
	 * 	le centre du château auquel appartient cette porte
	 * @param typeCastle
	 * 	permet de déterminer la largeur du château en fonction de son type. @see Settings
	 */
	CastleDoor(Coordonnee center2, String typeCastle) {	
		int sizeDW = Settings.DOORSIZE;
		int sizeDH = Settings.DOORTICKNESS; //Pixels
		int Side = (int) (Math.random() * 4);
		int size;
		if(typeCastle == "Duc" || typeCastle == "Player") {
			size = Settings.DUCSIZE;
		}else {
			size = Settings.BARONSIZE;
		}
		switch(Side) {
			case 0: //North
				this.center = new Coordonnee(center2.getX() , center2.getY() - size);
				this.direction = 'N';
				break;
			case 1:	//East
				this.center = new Coordonnee(center2.getX() + size , center2.getY());
				this.direction = 'E';
				break;
			case 2:	//South
				this.center = new Coordonnee(center2.getX() , center2.getY() + size);
				this.direction = 'S';
				break;
			case 3:	//West
				this.center = new Coordonnee(center2.getX() - size, center2.getY());
				this.direction = 'W';
				break;
			default:
				break;
		}
		if(Side == 0 || Side == 2) {
			this.cornerLT = new Coordonnee(this.center.getX() - sizeDW, this.center.getY() - sizeDH);
			this.cornerLB = new Coordonnee(this.center.getX() - sizeDW, this.center.getY() + sizeDH);
			this.cornerRT = new Coordonnee(this.center.getX() + sizeDW, this.center.getY() - sizeDH);
			this.cornerRB = new Coordonnee(this.center.getX() + sizeDW, this.center.getY() + sizeDH);
		}else {
			this.cornerLT = new Coordonnee(this.center.getX() - sizeDH, this.center.getY() - sizeDW);
			this.cornerLB = new Coordonnee(this.center.getX() - sizeDH, this.center.getY() + sizeDW);
			this.cornerRT = new Coordonnee(this.center.getX() + sizeDH, this.center.getY() - sizeDW);
			this.cornerRB = new Coordonnee(this.center.getX() + sizeDH, this.center.getY() + sizeDW);
		}
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
	public char getDirection() {
		return direction;
	}

	
}
