package SampleGame;

import java.util.ArrayList;

public class Triangle extends GeometricForm {
	private Coordonnee s1, s2, s3;
	
	Triangle(ArrayList<Castle> tabOfCastle, String owner){
		Coordonnee door;
		 for(int i=0; i<tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getName() == owner) {
				door = tabOfCastle.get(i).getCastleDoor().getCenter();
				this.s1.setX(door.getX());
				this.s1.setY(door.getY());
				this.s2.setX(s1.getX() - 5);
				this.s2.setY(s1.getY() + 9);
				this.s3.setX(s1.getX() + 5);
				this.s3.setY(s1.getY() + 9);
			}
		 }
	}

	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	
	public Coordonnee getS1() {
		return s1;
	}
	public void setS1(Coordonnee s1) {
		this.s1 = s1;
	}
	public Coordonnee getS2() {
		return s2;
	}
	public void setS2(Coordonnee s2) {
		this.s2 = s2;
	}
	public Coordonnee getS3() {
		return s3;
	}
	public void setS3(Coordonnee s3) {
		this.s3 = s3;
	}
	
}
