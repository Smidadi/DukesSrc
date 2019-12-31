package SampleGame;


public class CircleForm extends GeometricForm {
	private int x;
	private int y;
	private int radius;
	private TeamColor color;
	private String type = "circle";
	
	 CircleForm(CastleDoor CastleDoor, TeamColor color,  int position){
		Coordonnee door = CastleDoor.getCenter();
		char direction = CastleDoor.getDirection();
		if(direction == 'N' || direction == 'S') {
			switch(position) {
			case 1:
				this.x = door.getX()-10;
				this.y = door.getY();
				break;
			case 2:
				this.x = door.getX();
				this.y = door.getY();
				break;
			case 3:
				this.x = door.getX()+10;
				this.y = door.getY();
				break;
			default:
				break;
			}
		}else{
			switch(position) {
			case 1:
				this.x = door.getX();
				this.y = door.getY()-10;
				break;
			case 2:
				this.x = door.getX();
				this.y = door.getY();
				break;
			case 3:
				this.x = door.getX();
				this.y = door.getY()+10;
				break;
			default:
				break;
			}
		}
		this.radius = 5;
		this.color = color;
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