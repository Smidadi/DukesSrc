package SampleGame;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

/**
 * @param x
 * 	coordonnée x du coin supérieur gauche
 * @param y
 * 	coordonnée y du coin supérieur gauche
 * @param width
 * @param height
 * @param color
 * 	la couleur du château
 * @param type
 * 	le type de la forme géométrique, nécessaire pour savoir comment la traiter

 */
class RectangleForm extends GeometricForm {
	private int width;
	private int height;
	private int x;
	private int y;
	private TeamColor color;
	private String type = "rectangle";
	
	/**
	 * Constructeur de la class RectangleForm
	 * @param CastleDoor
	 * 	permet d'avoir la position de la porte du château qui souhaite émettre une OST
	 * 	@see CastleDoor
	 * @param color
	 * 	la couleur qu'aura le rectangle
	 * @param position
	 * 	position par rapport a la porte, par exemple: 1 = gauche, 2 = centre et 3 = droite.
	 */
	RectangleForm(CastleDoor CastleDoor, TeamColor color, int position){ //position  between 1 & 3
		Coordonnee door = CastleDoor.getCenter();
		char direction = CastleDoor.getDirection();
		if(direction == 'N' || direction == 'S') {
			switch(position) {
			case 1:
				this.x = door.getX()-20;
				this.y = door.getY()-5;
				break;
			case 2:
				this.x = door.getX()-5;
				this.y = door.getY()-5;
				break;
			case 3:
				this.x = door.getX()+10;
				this.y = door.getY()-5;
				break;
			default:
				break;
			}
		}else{
			switch(position) {
			case 1:
				this.x = door.getX()-5;
				this.y = door.getY()-20;
				break;
			case 2:
				this.x = door.getX()-5;
				this.y = door.getY()-5;
				break;
			case 3:
				this.x = door.getX()-5;
				this.y = door.getY()+10;
				break;
			default:
				break;
			}
		}
		this.width = 10;
		this.height = 10;
		this.color = color;
	}
	
	 /**
	  * Permet de convertir un rectangle de javafx.scene.shape.rectangle en RectangleForm
	  * pour pouvoir le sauvegarder
	  * @param rectangle
	  * le rectangle à convertir pour la sauvegarde
	  * @param color
	  * la couleur du rectangle
	  */
	RectangleForm(Rectangle rectangle,TeamColor color){
		this.x = (int) rectangle.getX();
		this.y = (int) rectangle.getY();
		this.height = (int) rectangle.getHeight();
		this.width = (int) rectangle.getWidth();
		this.color = color;
	}
	
	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	@Override
	public String toString() {
		return type;
	}
	
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
	public TeamColor getColor() {
		return color;
	}
	public void setColor(TeamColor color) {
		this.color = color;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}

	//abstract --> no utility
		//circle
	public int getRadius() {
		return 0;
	}
		//triangle
	public Coordonnee getS1() {
		return null;
	}
	public Coordonnee getS2() {
		return null;
	}
	public Coordonnee getS3() {
		return null;
	}
}
