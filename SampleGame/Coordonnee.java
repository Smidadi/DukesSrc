package SampleGame;

/*import java.awt.Dimension;

Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
int height = (int)dimension.getHeight();
int width  = (int)dimension.getWidth();*/

public class Coordonnee {
	private int x, y;
	
	public Coordonnee() {
		this.x = (int) ( Settings.DOORSIZE + Settings.DUCSIZE + Math.random() * (Settings.SCENE_WIDTH - 2* (Settings.DOORSIZE + Settings.DUCSIZE)));
		this.y = (int) ( Settings.DOORSIZE + Settings.DUCSIZE + Math.random() * (Settings.SCENE_HEIGHT - 2* (Settings.DOORSIZE + Settings.DUCSIZE)));		
	}

	public Coordonnee(int dx, int dy) {
		this.x = dx;
		this.y = dy;
	}
	
	public static double distance(Coordonnee castle, Coordonnee newCastle){
        double d1 = castle.x - newCastle.x;
        double d2 = castle.y - newCastle.y;
        return Math.sqrt(d1*d1 + d2*d2);
    }
	
	public static double distance(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public static int distance(int x1, int x2) {
		return (int) Math.sqrt((x1-x2)*(x1-x2));
	}
	
	public static double distance(double x1, double x2) {
		return (double) Math.sqrt((x1-x2)*(x1-x2));
	}

	static Boolean inASquare(CastleStruct Castle, double x, double y, int space) {
		// Xmin <= x <= Xmax ET Ymin <= y <= Ymax
		int xMin = Castle.getCornerLT().getX() - space; //LEFT CORNER
		int xMax = Castle.getCornerRT().getX() + space;	//RIGHT CORNER
		int yMin = Castle.getCornerLT().getY() - space;	//TOP CORNER
		int yMax = Castle.getCornerLB().getY() + space; //TOP CORNER
		if( (xMin <= x && x <= xMax) && (yMin <= y && y <= yMax) ) {
			return true;
		}
		return false;
	}
	
	static Boolean onTheBorder(CastleStruct Castle, double x, double y, int space) {
		// Xmin <= x <= Xmax ET Ymin <= y <= Ymax
		int xMin = Castle.getCornerLB().getX() - space; //LEFT CORNER
		int xMax = Castle.getCornerRT().getX() + space;	//RIGHT CORNER
		int yMin = Castle.getCornerRT().getY() - space;	//TOP CORNER
		int yMax = Castle.getCornerLB().getY() + space; //TOP CORNER
		
		if( (xMin <= x && x <= xMax) && (yMin == y) ) {	//TOP BORDER
			return true;
		}else if( (xMin <= x && x <= xMax) && (yMax == y) ) {	//BOTTOM BORDER
			return true;
		}else if( (yMin <= y && y <= yMax) && (xMin == x) ) {	//LEFT BORDER
			return true;
		}else if( (yMin <= y && y <= yMax) && (xMax == x) ) {	//RIGHT BORDER
			return true;
		}
		return false;
	}
	
	static int onACorner(CastleStruct Castle, double x, double y, int space) {
		// Xmin <= x <= Xmax ET Ymin <= y <= Ymax
		int xCorner, yCorner;
		xCorner = Castle.getCornerLT().getX() - space;
		yCorner = Castle.getCornerLT().getY() - space;
		if( (xCorner == x &&  y == yCorner) ) {	//LT
			return 1;
		}
		xCorner = Castle.getCornerRT().getX() + space;
		yCorner = Castle.getCornerRT().getY() - space;
		if( (xCorner == x &&  y == yCorner) ) {	//RT
			return 2;
		}
		xCorner = Castle.getCornerLB().getX() - space;
		yCorner = Castle.getCornerLB().getY() + space;
		if( (xCorner == x &&  y == yCorner) ) {	//LB
			return 3;
		}
		xCorner = Castle.getCornerRB().getX() + space;
		yCorner = Castle.getCornerRB().getY() + space;
		if( (xCorner == x &&  y == yCorner) ) {	//RB
			return 4;
		}
		return 0;
	}
	
	
	/* ------------------ */
	/* ------------------ */
	
	@Override
	public String toString() {
		return "Coordonnee [x=" + x + ", y=" + y + "]";
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

}
