package SampleGame;

import javafx.scene.shape.Circle;

/**
 * @param x
 * 	coordonnée x du centre du cercle
 * @param y
 * 	coordonnée y du centre du cercle
 * @param radius
 * 	le rayon du cercle
 * @param color
 * 	la couleur du château
 * @param type
 * 	le type de la forme géométrique, nécessaire pour savoir comment la traiter

 */
public class CircleForm extends GeometricForm {
	private int x;
	private int y;
	private int radius;
	private TeamColor color;
	private String type = "circle";
	
	/**
	 * Constructeur de la class CircleForm
	 * @param CastleDoor
	 * 	permet d'avoir la position de la porte du château qui souhaite émettre une OST
	 * 	@see CastleDoor
	 * @param color
	 * 	la couleur qu'aura le cercle
	 * @param position
	 * 	position par rapport a la porte, par exemple: 1 = gauche, 2 = centre et 3 = droite.
	 */
	 CircleForm(CastleDoor CastleDoor, TeamColor color,  int position){
		Coordonnee door = CastleDoor.getCenter();
		char direction = CastleDoor.getDirection();
		if(direction == 'N' || direction == 'S') {
			switch(position) {
			case 1:
				this.x = door.getX()-15;
				this.y = door.getY();
				break;
			case 2:
				this.x = door.getX();
				this.y = door.getY();
				break;
			case 3:
				this.x = door.getX()+15;
				this.y = door.getY();
				break;
			default:
				break;
			}
		}else{
			switch(position) {
			case 1:
				this.x = door.getX();
				this.y = door.getY()-15;
				break;
			case 2:
				this.x = door.getX();
				this.y = door.getY();
				break;
			case 3:
				this.x = door.getX();
				this.y = door.getY()+15;
				break;
			default:
				break;
			}
		}
		this.radius = 5;
		this.color = color;
	 }	 
	 
	 /**
	  * Permet de convertir un cercle de javafx.scene.shape.Circle en CircleForm
	  * pour pouvoir le sauvegarder
	  * @param circle
	  * le cercle à convertir pour la sauvegarde
	  * @param color
	  * la couleur du cercle
	  */
	 CircleForm(Circle circle,TeamColor color){
			this.x = (int) circle.getCenterX();
			this.y = (int) circle.getCenterY();
			this.radius = (int) circle.getRadius();
			this.color = color;
	}
	 
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	 
	@Override
	public String toString() {
		return type;
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
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
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
		//rectangle
	public int getWidth() {
		return 0;
	}
	public int getHeight() {
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
