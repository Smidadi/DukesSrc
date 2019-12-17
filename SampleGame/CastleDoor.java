package SampleGame;

public class CastleDoor {
	
	private Coordonnee center, cornerLT, cornerLB, cornerRT, cornerRB;
	
	CastleDoor(Coordonnee center2, String typeCastle) {	
		int sizeDW = Settings.DOORSIZE;
		int sizeDH = 2; //Pixels
		int Side = (int) (Math.random() * 4);
		int size;
		if(typeCastle == "Duc" || typeCastle == "Player") {
			size = Settings.DUCSIZE;
		}else {
			size = Settings.BARONSIZE;
		}
		switch(Side) {
			case 0: //North
				this.center = new Coordonnee(center2.getX() , center2.getY() + size);
				break;
			case 1:	//East
				this.center = new Coordonnee(center2.getX() + size , center2.getY());
				break;
			case 2:	//South
				this.center = new Coordonnee(center2.getX() , center2.getY() - size);
				break;
			case 3:	//West
				this.center = new Coordonnee(center2.getX() - size, center2.getY());
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

	
}
