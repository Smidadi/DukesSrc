package SampleGame;


public class TriangleForm extends GeometricForm {
	private Coordonnee s1 = new Coordonnee(0,0); 
	private Coordonnee s2 = new Coordonnee(0,0);
	private Coordonnee s3 = new Coordonnee(0,0);
	private TeamColor color;
	private String type = "triangle";
	
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
