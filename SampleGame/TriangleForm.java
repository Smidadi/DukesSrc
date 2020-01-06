package SampleGame;

import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;

/**
 * @param s1
 * 	coordonnée du sommet supérieur
 * @param s2
 * 	coordonnée du sommet inférieur gauche
 * @param s3
 * 	coordonnée du sommet inférieur droit
 * @param color
 * 	la couleur du château
 * @param type
 * 	le type de la forme géométrique, nécessaire pour savoir comment la traiter

 */
public class TriangleForm extends GeometricForm {
	private Coordonnee s1 = new Coordonnee(0,0); 
	private Coordonnee s2 = new Coordonnee(0,0);
	private Coordonnee s3 = new Coordonnee(0,0);
	private TeamColor color;
	private String type = "triangle";
	
	/**
	 * Constructeur de la class TriangleForm
	 * @param CastleDoor
	 * 	permet d'avoir la position de la porte du château qui souhaite émettre un OST
	 * 	@see CastleDoor
	 * @param color
	 * 	la couleur qu'aura le triangle
	 * @param position
	 * 	position par rapport a la porte, par exemple: 1 = gauche, 2 = centre et 3 = droite.
	 */
	TriangleForm(CastleDoor CastleDoor, TeamColor color,  int position){
		Coordonnee door = CastleDoor.getCenter();
		char direction = CastleDoor.getDirection();
		if(direction == 'N' || direction == 'S') {
			switch(position) {
			case 1:
				this.s1.setX(door.getX()-15);
				this.s1.setY(door.getY()-5);			
				break;
			case 2:
				this.s1.setX(door.getX());
				this.s1.setY(door.getY()-5);				
				break;
			case 3:
				this.s1.setX(door.getX()+15);
				this.s1.setY(door.getY()-5);	
				break;
			default:
				break;
			}
		}else{
			switch(position) {
			case 1:
				this.s1.setX(door.getX());
				this.s1.setY(door.getY() - 20);
				break;
			case 2:
				this.s1.setX(door.getX());
				this.s1.setY(door.getY() - 5);
				break;
			case 3:
				this.s1.setX(door.getX());
				this.s1.setY(door.getY() + 10);	
				break;
			default:
				break;
			}
		}
		this.s2.setX(s1.getX() + 5);
		this.s2.setY(s1.getY() + 10);
		this.s3.setX(s1.getX() - 5);
		this.s3.setY(s1.getY() + 10);
		this.color = color;
	}

	/**
	  * Permet de convertir un polygon de javafx.scene.shape.polygon en TriangleForm
	  * pour pouvoir le sauvegarder
	  * @param polygon
	  * le polygon à convertir pour la sauvegarde
	  * @param color
	  * la couleur du polygon
	  */
	TriangleForm(Polygon polygon, TeamColor color){
		ObservableList<Double> PointList = polygon.getPoints();
		this.s1.setX( PointList.get(0).intValue());
		this.s1.setX( PointList.get(1).intValue());		
		this.s2.setX( PointList.get(2).intValue());
		this.s2.setX( PointList.get(3).intValue());		
		this.s3.setX( PointList.get(4).intValue());
		this.s3.setX( PointList.get(5).intValue());
		this.color = color;
		
	}
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	@Override
	public String toString() {
		return type;
	}

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
	public int getX() {
		return 0;
	}
	public int getY() {
		return 0;
	}
		//circle
	public int getRadius() {
		return 0;
	}
		//rectangle
	public int getWidth() {
		return 0;
	}
	public int getHeight() {
		return 0;
	}
	
}
