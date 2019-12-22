package SampleGame;

import java.util.ArrayList;

class Rectangle extends GeometricForm {
	

	private int width;
	private int height;
	private int x;
	private int y;
	
	Rectangle(ArrayList<Castle> tabOfCastle, String owner){
		Coordonnee door;
		for(int i=0; i<tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getName() == owner) {
				door = tabOfCastle.get(i).getCastleDoor().getCenter();
				this.x = door.getX();
				this.y = door.getY();
				this.width = 10;
				this.width = 10;
			}
		}
	}
	
	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
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
}
