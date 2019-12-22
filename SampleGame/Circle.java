package SampleGame;

import java.util.ArrayList;

public class Circle extends GeometricForm {
	private int x;
	private int y;
	private int radius;
	
	 Circle(ArrayList<Castle> tabOfCastle,String owner){
		 Coordonnee door;
		 for(int i=0; i<tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getName() == owner) {
				door = tabOfCastle.get(i).getCastleDoor().getCenter();
				this.x = door.getX();
				this.y = door.getY();
				this.radius = 5;
			}
		 }
	 }

	
	 
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	 
	public int getX() {
			return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}	 
	
}
