package SampleGame;


import java.io.Serializable;


public class Coordonnee implements Serializable {
	private int x;
	private int y;
	
	/**
	 * Constructeur de la class Coordonnee
	 * sans paramétre pour le création d'un point aléatoire dans le cadre du jeu
	 */
	public Coordonnee() {
		this.x =  (int) ( Settings.DOORSIZE + Settings.DUCSIZE + Math.random() * (Settings.SCENE_WIDTH - 2* (Settings.DOORSIZE + Settings.DUCSIZE)));
		this.y =  (int) ( Settings.DOORSIZE + Settings.DUCSIZE + Math.random() * (Settings.SCENE_HEIGHT - 2* (Settings.DOORSIZE + Settings.DUCSIZE)));		
	}

	/**
	 * Constructeur de la class Coordonnée
	 * @param x
	 * @param y
	 */
	public Coordonnee(int x, int y) {
		this.x =  x;
		this.y =  y;
	}
	
	/**
	 * Calcule la distance entre 2 points
	 * utile pour calculer la hauteur / largueur d'un rectangle par exemple.
	 * @param pt1
	 * @param pt2
	 * @return	la distance entre les 2 points
	 */
	public static double distance(Coordonnee pt1, Coordonnee pt2){
		int d1 = pt1.x - pt2.x;
		int d2 = pt1.y - pt2.y;
        return Math.sqrt(d1*d1 + d2*d2);
    }
	
	/**
	* Calcule la distance entre 2 points
	 * utile pour calculer la hauteur / largueur d'un rectangle par exemple.
	 * @param x1 y1
	 * 	premier point
	 * @param x2 y2
	 * 	deuxième point
	 * @return	la distance entre les 2 points
	 */
	public static double distance(int x1, int y1, int x2, int y2) {
		int dx = x1 - x2;
		int dy = y1 - y2;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	/**
	 * Calcule la distance entre 2 points sur le même axe
	 * @param x1
	 * @param x2
	 * @return la distance entre 2 points
	 */
	public static int distance(int x1, int x2) {
		return (int) Math.sqrt((x1-x2)*(x1-x2));
	}
	
	/**
	 * Vérifie si un point se trouve dans un carré + un espace
	 * @param Castle
	 * 	le "carré"
	 * @param x
	 * @param y
	 * @param space
	 * 		l'espace réservé au château en plus de sa taille actuelle
	 * @return	true si le point est dans le château sinon false
	 */
	static Boolean inASquare(CastleStruct Castle, int x, int y, int space) {
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
	
	/**
	 * Vérifie si un point se trouve dans un carré + un espace
	 * @param Castle
	 * 	le "carré"
	 * @param x
	 * @param y
	 * @return	true si le point est dans le château sinon false
	 */
	static Boolean inASquare(CastleStruct Castle, Coordonnee aPoint) {
		// Xmin <= x <= Xmax ET Ymin <= y <= Ymax
		double xMin = Castle.getCornerLT().getX() - Settings.DOORSIZE; //LEFT CORNER
		double xMax = Castle.getCornerRT().getX() + Settings.DOORSIZE;	//RIGHT CORNER
		double yMin = Castle.getCornerLT().getY() - Settings.DOORSIZE;	//TOP CORNER
		double yMax = Castle.getCornerLB().getY() + Settings.DOORSIZE; //TOP CORNER
		if( (xMin <= aPoint.getX() && aPoint.getX() <= xMax) && (yMin <= aPoint.getY() && aPoint.getY() <= yMax) ) {
			return true;
		}
		return false;
	}
	
	/**
	 * Vérifie si un point se trouve à "space" distance d'un château,
	 * il est alors dans sa bordure
	 * @param Castle
	 * 	le château autour du quel on vérifie si le point est
	 * @param x
	 * @param y
	 * @param space
	 * 	la distance à laquelle doit se trouver le point pour être sur la bordure
	 * @return
	 */
	static Boolean onTheBorder(CastleStruct Castle, double x, double y, int space) {
		// Xmin <= x <= Xmax ET Ymin <= y <= Ymax

		int xMin = Castle.getCornerLB().getX() - space; //LEFT CORNER
		int xMax = Castle.getCornerRT().getX() + space;	//RIGHT CORNER
		int yMin = Castle.getCornerRT().getY() - space;	//TOP CORNER
		int yMax = Castle.getCornerLB().getY() + space; //BOTTOM CORNER
		
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
	
	/**
	 * Vérifie si un point se trouve sur un coin de la bordure du château
	 * @param Castle
	 * 	le château autour du quel on vérifie si le point est
	 * @param x
	 * @param y
	 * @param space
	 * 	la distance à laquelle doit se trouver le point pour être sur la bordure
	 * @return
	 */
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
	public void setX(int double1) {
		this.x = double1;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

}
