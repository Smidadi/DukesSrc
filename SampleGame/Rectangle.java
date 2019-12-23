package SampleGame;


class Rectangle extends GeometricForm {
	private int width;
	private int height;
	private int x;
	private int y;
	private TeamColor color;
	
	Rectangle(CastleDoor CastleDoor, TeamColor color, int position){ //position  between 1 & 3
		Coordonnee door = CastleDoor.getCenter();
		char direction = CastleDoor.getDirection();
		if(direction == 'N' || direction == 'S') {
			switch(position) {
			case 1:
				this.x = door.getX()-15;
				this.y = door.getY();
				break;
			case 2:
				this.x = door.getX()-5;
				this.y = door.getY();
				break;
			case 3:
				this.x = door.getX()+5;
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
				this.y = door.getY()-5;
				break;
			case 3:
				this.x = door.getX();
				this.y = door.getY()+5;
				break;
			default:
				break;
			}
		}
		this.width = 10;
		this.height = 10;
		this.color = color;
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
	public TeamColor getColor() {
		return color;
	}
	public void setColor(TeamColor color) {
		this.color = color;
	}
}
