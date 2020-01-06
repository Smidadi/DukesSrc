package SampleGame;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * La Class Movement gére le déplacement des unités dans le jeu 
 * il s'agite de déplacer des carrés, des cercles et des triangles (polygon)
 *
 */
public class Movement {
	
	/**
	 * @param space
	 * ne peut être modifié, il s'agite de l'espace entre le château et sa bordure
	 */
	private final static int space = 10;
	
	/**
	 * La méthode move bouge les unités en fonction de leur formes géométriques.
	 * D'abord les rectangle puis les cercles puis les polygons.
	 * @param root
	 * 	il s'agit de la racine visuel du jeu
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * @param ost
	 * 	l'OST en déplacement
	 * @param sourceCastle
	 * 	le château qui a envoyé l'OST
	 * @param targetCastle
	 * 	le château cible de l'OST
	 * @param countSec
	 * 	timer égale à 60 toute le 1 seconde. Permet de gérer la vitesse des unités
	 */
	static void move(Pane root, ArrayList<Castle> tabOfCastle, OST ost, Castle sourceCastle, Castle targetCastle, int countSec) {
		// --------------------------------------------------------------------------------------------------------- //
		// --------------------------------------------- RECTANGLE ------------------------------------------------- //
		// --------------------------------------------------------------------------------------------------------- //
		/**
		 * vitesse d'un rectangle 60px/s
		 */
		if(countSec % ost.getMaxSpeed() == 0) {
			for(int i = 0; i < ost.getRectangle().size(); i++) {
				Rectangle r = ost.getRectangle().get(i);
				String direction;
				//sortie du chateau source
				if(inFrontOfTheDoor(sourceCastle, (r.getX()+5), ((r.getY()+5)), (space-1)) == true ) {	
					switch(sourceCastle.getCastleDoor().getDirection()) {
						case 'N':
							moveRectangle(ost, "UP", ost.getRectangle().get(i));
							break;
						case 'E':
							moveRectangle(ost, "RIGHT", ost.getRectangle().get(i));
							break;
						case 'S':
							moveRectangle(ost, "DOWN", ost.getRectangle().get(i));
							break;
						case 'W':
							moveRectangle(ost, "LEFT", ost.getRectangle().get(i));
							break;
						default:
							break;
					}
					//entrée dans le chateau cible
				}else if(inFrontOfTheDoor(targetCastle, (r.getX()+5), ((r.getY()+5)), (space*2)) == true){	
					switch(targetCastle.getCastleDoor().getDirection()) {
						case 'N':
							if((r.getX()+5) != ost.getTargetX()) {
								if((r.getX()+5) < ost.getTargetX()) {
									moveRectangle(ost, "RIGHT", ost.getRectangle().get(i));
								}else {
									moveRectangle(ost, "LEFT", ost.getRectangle().get(i));
								}
							}else {
								moveRectangle(ost, "DOWN", ost.getRectangle().get(i));
							}
							break;
						case 'E':
							if(((r.getY()+5)) != ost.getTargetY()) {
								if(((r.getY()+5)) < ost.getTargetY()) {
									moveRectangle(ost, "DOWN", ost.getRectangle().get(i));
			 					}else {
									moveRectangle(ost, "UP", ost.getRectangle().get(i));
								}
							}else {
								moveRectangle(ost, "LEFT", ost.getRectangle().get(i));
							}
							break;
						case 'S':
							if((r.getX()+5) != ost.getTargetX()) {
								if((r.getX()+5) < ost.getTargetX()) {
									moveRectangle(ost, "RIGHT", ost.getRectangle().get(i));
								}else {
									moveRectangle(ost, "LEFT", ost.getRectangle().get(i));
								}
							}else {
								moveRectangle(ost, "UP", ost.getRectangle().get(i));
							}
							break;
						case 'W':
							if(((r.getY()+5)) != ost.getTargetY()) {
								if(((r.getY()+5)) < ost.getTargetY()) {
									moveRectangle(ost, "DOWN", ost.getRectangle().get(i));
								}else {
									moveRectangle(ost, "UP", ost.getRectangle().get(i));
								}
							}else {
								moveRectangle(ost, "RIGHT", ost.getRectangle().get(i));
							}
							break;
						default:
							break;
					}
					//déplacement normal 
				}else if(inFrontOfTheDoor(sourceCastle,(r.getX()+5),((r.getY()+5)), (space-1)) == false &&
						inFrontOfTheDoor(targetCastle,(r.getX()+5),((r.getY()+5)), (space*2)) == false){	//déplacement entre les chateaux vers la cible
					direction = moveAroundACastle(tabOfCastle, ost, (r.getX()+5), ((r.getY()+5)));				
					if(direction == "freeToMove") {
						if((r.getX()+5) != ost.getTargetX()) {
							//si l'unité n'est pas en train de longer un chateau sur l'axe X then DO
							if((r.getX()+5) < ost.getTargetX()) {
								moveRectangle(ost, "RIGHT", ost.getRectangle().get(i));
							}else {
								moveRectangle(ost, "LEFT", ost.getRectangle().get(i));
							}
						}
						direction = moveAroundACastle(tabOfCastle, ost, (r.getX()+5), ((r.getY()+5)));
						if(direction == "freeToMove") {
							if(((r.getY()+5)) != ost.getTargetY()) {
								if(((r.getY()+5)) < ost.getTargetY()) {
									moveRectangle(ost, "DOWN", ost.getRectangle().get(i));
								}else {
									moveRectangle(ost, "UP", ost.getRectangle().get(i));
								}
							}
						}
					}else {
						moveRectangle(ost, direction, ost.getRectangle().get(i));
					}
				}
				if(ost.getRectangle().get(i).getX()+5 == ost.getTargetX() && ost.getRectangle().get(i).getY()+5 == ost.getTargetY()) {
					root.getChildren().remove(ost.getRectangle().get(i));
					ost.getRectangle().remove(ost.getRectangle().get(i));
				}
				if(ost.getRectangle().isEmpty() && ost.getCircle().isEmpty() && ost.getPolygon().isEmpty()) {
					ost.setInMovment(false);
					ost.setCanAttack(true);
				}
			}
		}
		
		// --------------------------------------------------------------------------------------------------------- //
		// ---------------------------------------------- CIRCLE --------------------------------------------------- //
		// --------------------------------------------------------------------------------------------------------- //
		/**
		 * vitesse d'un cercle 20px/s
		 */
		if(countSec % ost.getMaxSpeed() == 0) {
			for(int i = 0; i < ost.getCircle().size(); i++) {
				Circle c = ost.getCircle().get(i);
				String direction;
					//Sortie du chateau source
				if(inFrontOfTheDoor(sourceCastle, c.getCenterX(), c.getCenterY(), (space-1)) == true ) {	
					switch(sourceCastle.getCastleDoor().getDirection()) {
						case 'N':
							moveCircle(ost, "UP", ost.getCircle().get(i));
							break;
						case 'E':
							moveCircle(ost, "RIGHT", ost.getCircle().get(i));
							break;
						case 'S':
							moveCircle(ost, "DOWN", ost.getCircle().get(i));
							break;
						case 'W':
							moveCircle(ost, "LEFT", ost.getCircle().get(i));
							break;
						default:
							break;
					}
					//entrée dans le chateau cible
				}else if(inFrontOfTheDoor(targetCastle, c.getCenterX(), c.getCenterY(), (space*2)) == true){
					switch(targetCastle.getCastleDoor().getDirection()) {
						case 'N':
							if(c.getCenterX() != ost.getTargetX()) {
								if(c.getCenterX() < ost.getTargetX()) {
									moveCircle(ost, "RIGHT", ost.getCircle().get(i));
								}else {
									moveCircle(ost, "LEFT", ost.getCircle().get(i));
								}
							}else {
								moveCircle(ost, "DOWN", ost.getCircle().get(i));
							}
							break;
						case 'E':
							if(c.getCenterY() != ost.getTargetY()) {
								if(c.getCenterY() < ost.getTargetY()) {
									moveCircle(ost, "DOWN", ost.getCircle().get(i));
			 					}else {
									moveCircle(ost, "UP", ost.getCircle().get(i));
								}
							}else {
								moveCircle(ost, "LEFT", ost.getCircle().get(i));
							}
							break;
						case 'S':
							if(c.getCenterX() != ost.getTargetX()) {
								if(c.getCenterX() < ost.getTargetX()) {
									moveCircle(ost, "RIGHT", ost.getCircle().get(i));
								}else {
									moveCircle(ost, "LEFT", ost.getCircle().get(i));
								}
							}else {
								moveCircle(ost, "UP", ost.getCircle().get(i));
							}
							break;
						case 'W':
							if(c.getCenterY() != ost.getTargetY()) {
								if(c.getCenterY() < ost.getTargetY()) {
									moveCircle(ost, "DOWN", ost.getCircle().get(i));
								}else {
									moveCircle(ost, "UP", ost.getCircle().get(i));
								}
							}else {
								moveCircle(ost, "RIGHT", ost.getCircle().get(i));
							}
							break;
						default:
							break;
					}
					//déplacement normal 
				}else if(inFrontOfTheDoor(sourceCastle,c.getCenterX(),c.getCenterY(), (space-1)) == false &&
						inFrontOfTheDoor(targetCastle,c.getCenterX(),c.getCenterY(), (space*2)) == false){	//déplacement entre les chateaux vers la cible
					direction = moveAroundACastleC(tabOfCastle, ost, c);				
					if(direction == "freeToMove") {	//si l'unité n'est pas en train de longer un chateau sur l'axe X then DO
						if(c.getCenterX() != ost.getTargetX()) {
							if(c.getCenterX() < ost.getTargetX()) {
								moveCircle(ost, "RIGHT", ost.getCircle().get(i));
							}else {
								moveCircle(ost, "LEFT", ost.getCircle().get(i));
							}
						}
						direction = moveAroundACastleC(tabOfCastle, ost, c);
						if(direction == "freeToMove") {	//si l'unité n'est pas en train de longer un chateau sur l'axe X then DO
							if(c.getCenterY() != ost.getTargetY()) {
								if(c.getCenterY() < ost.getTargetY()) {
									moveCircle(ost, "DOWN", ost.getCircle().get(i));
								}else {
									moveCircle(ost, "UP", ost.getCircle().get(i));
								} 
							}
						}
					}
					else{
						moveCircle(ost, direction, ost.getCircle().get(i));
					}
				}
				if(ost.getCircle().get(i).getCenterX() == ost.getTargetX() && ost.getCircle().get(i).getCenterY() == ost.getTargetY()) {
					root.getChildren().remove(ost.getCircle().get(i));
					ost.getCircle().remove(ost.getCircle().get(i));
				}
				if(ost.getRectangle().isEmpty() && ost.getCircle().isEmpty() && ost.getPolygon().isEmpty()) {
					ost.setInMovment(false);
					ost.setCanAttack(true);
				}
			}
		}
		
		
		// --------------------------------------------------------------------------------------------------------- //
		// ---------------------------------------------- POLYGON -------------------------------------------------- //
		// --------------------------------------------------------------------------------------------------------- //
		/**
		 * vitesse d'un polygon 30px/s
		 */
		if(countSec % ost.getMaxSpeed() == 0) {
			for(int i = 0; i < ost.getPolygon().size(); i++) {
				ObservableList<Double> pos = ost.getPolygon().get(i).getPoints();
				double PolyX = pos.get(0);
				double PolyY = pos.get(1);
				String direction;
					//Sortie du chateau source
				if(inFrontOfTheDoor(sourceCastle, PolyX, (PolyY+5), (space-1)) == true ) {	
					switch(sourceCastle.getCastleDoor().getDirection()) {
						case 'N':
							movePolygon(ost, "UP", ost.getPolygon().get(i));
							break;
						case 'E':
							movePolygon(ost, "RIGHT", ost.getPolygon().get(i));
							break;
						case 'S':
							movePolygon(ost, "DOWN", ost.getPolygon().get(i));
							break;
						case 'W':
							movePolygon(ost, "LEFT", ost.getPolygon().get(i));
							break;
						default:
							break;
					}
					//entrée dans le chateau cible
				}else if(inFrontOfTheDoor(targetCastle, PolyX, (PolyY+5), (space*2)) == true){
					switch(targetCastle.getCastleDoor().getDirection()) {
						case 'N':
							if(PolyX != ost.getTargetX()) {
								if(PolyX < ost.getTargetX()) {
									movePolygon(ost, "RIGHT", ost.getPolygon().get(i));
								}else {
									movePolygon(ost, "LEFT", ost.getPolygon().get(i));
								}
							}else {
								movePolygon(ost, "DOWN", ost.getPolygon().get(i));
							}
							break;
						case 'E':
							if((PolyY+5) != ost.getTargetY()) {
								if((PolyY+5) < ost.getTargetY()) {
									movePolygon(ost, "DOWN", ost.getPolygon().get(i));
			 					}else {
									movePolygon(ost, "UP", ost.getPolygon().get(i));
								}
							}else {
								movePolygon(ost, "LEFT", ost.getPolygon().get(i));
							}
							break;
						case 'S':
							if(PolyX != ost.getTargetX()) {
								if(PolyX < ost.getTargetX()) {
									movePolygon(ost, "RIGHT", ost.getPolygon().get(i));
								}else {
									movePolygon(ost, "LEFT", ost.getPolygon().get(i));
								}
							}else {
								movePolygon(ost, "UP", ost.getPolygon().get(i));
							}
							break;
						case 'W':
							if((PolyY+5) != ost.getTargetY()) {
								if((PolyY+5) < ost.getTargetY()) {
									movePolygon(ost, "DOWN", ost.getPolygon().get(i));
								}else {
									movePolygon(ost, "UP", ost.getPolygon().get(i));
								}
							}else {
								movePolygon(ost, "RIGHT", ost.getPolygon().get(i));
							}
							break;
						default:
							break;
					}
					//déplacement normal 
				}else if(inFrontOfTheDoor(sourceCastle,PolyX,(PolyY+5), (space-1)) == false &&
						inFrontOfTheDoor(targetCastle,PolyX,(PolyY+5), (space*2)) == false){	//déplacement entre les chateaux vers la cible
					direction = moveAroundACastleP(tabOfCastle, ost, ost.getPolygon().get(i));				
					if(direction == "freeToMove") {	//si l'unité n'est pas en train de longer un chateau sur l'axe X then DO
						if(PolyX != ost.getTargetX()) {
							if(PolyX < ost.getTargetX()) {
								movePolygon(ost, "RIGHT", ost.getPolygon().get(i));
							}else {
								movePolygon(ost, "LEFT", ost.getPolygon().get(i));
							}
						}
						direction = moveAroundACastleP(tabOfCastle, ost, ost.getPolygon().get(i));
						if(direction == "freeToMove") {	//si l'unité n'est pas en train de longer un chateau sur l'axe X then DO
							if((PolyY+5) != ost.getTargetY()) {
								if((PolyY+5) < ost.getTargetY()) {
									movePolygon(ost, "DOWN", ost.getPolygon().get(i));
								}else {
									movePolygon(ost, "UP", ost.getPolygon().get(i));
								} 
							}
						}
					}else {
						movePolygon(ost, direction, ost.getPolygon().get(i));
					}
				}
				if(PolyX == ost.getTargetX() && (PolyY+5) == ost.getTargetY()) {
					root.getChildren().remove(ost.getPolygon().get(i));
					ost.getPolygon().remove(ost.getPolygon().get(i));
				}
				if(ost.getRectangle().isEmpty() && ost.getCircle().isEmpty() && ost.getPolygon().isEmpty()) {
					ost.setInMovment(false);
					ost.setCanAttack(true);
				}
			}
		}

		//fin du déplacement de l'OST arrivé au chateau cible
	}
	
	/**
	 * inFrontOfTheDoor vérifie si l'unité se trouve devant la porte du château castle
	 * @param castle
	 * 	le château
	 * @param formX
	 * 	la coordonnée x de l'unité
	 * @param formY
	 * 	la coordonnée x de l'unité
	 * @param spaceModifie
	 * 	l'espace devant la porte varie entre le château qui envoie l'OST et le château cible
	 * @return true si l'unité est devant la porte du château, sinon false
	 */
	static boolean inFrontOfTheDoor(Castle castle, double formX, double formY, int spaceModifie) {
		int CornerX1;
		int CornerY1;
		int CornerX2;
		int CornerY2;
		CastleDoor door = castle.getCastleDoor();
		CastleStruct c = castle.getCastle();
		switch(door.getDirection()) {
			case 'N': 
				CornerX1 = c.getCornerLT().getX();
				CornerY1 = c.getCornerLT().getY();
				CornerX2 = c.getCornerRT().getX();
				if( (formX >= CornerX1 && formX <= (CornerX2)) && (formY <= (CornerY1 + space) && formY >= (CornerY1 - spaceModifie)) ) {
					return true;
				}else {
					return false;
				}
			case 'E':
				CornerX1 = c.getCornerRT().getX();
				CornerY1 = c.getCornerRT().getY();
				CornerY2 = c.getCornerRB().getY();
				if( (formX >= (CornerX1 - space) && formX <= (CornerX1 + spaceModifie)) && (formY >= CornerY1 && formY <= CornerY2) ) {
					return true;
				}else {
					return false;
				}
			case 'S':
				CornerX1 = c.getCornerLB().getX();
				CornerY1 = c.getCornerLB().getY();
				CornerX2 = c.getCornerRB().getX();
				if( (formX >= (CornerX1) && formX <= (CornerX2)) && (formY >= (CornerY1 - space) && formY <= (CornerY1 + spaceModifie)) ) {
					return true;
				}else {
					return false;
				}
			case 'W':	
				CornerX1 = c.getCornerLT().getX();
				CornerY1 = c.getCornerLT().getY();
				CornerY2 = c.getCornerLB().getY();
				if( (formX <= (CornerX1 + space) && formX >= (CornerX1 - spaceModifie)) && (formY >= CornerY1 && formY <= (CornerY2)) ) {
					return true;
				}else {
					return false;
				}
			default:
				System.out.println("DEFAULT: inFrontOfTheDoor");
				return false;
		}
	}
	
	/**
	 * la méthode cherche si l'unité se trouve sur la bordure de l'un des châteaux du jeu
	 * @see Coordonnee.onTheBorder
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * @param ost
	 * 	l'OST auquel appartient l'unité
	 * @param x
	 * 	coordonnée en x de l'unité
	 * @param y
	 * 	coordonnée en y de l'unité
	 * @return le château autour duquel se trouve l'unité, sinon null
	 */
	static CastleStruct aroundACastle(ArrayList<Castle> tabOfCastle,OST ost, double x, double y) {
		for(int i=0; i < tabOfCastle.size(); i++) {
			if(Coordonnee.onTheBorder(tabOfCastle.get(i).getCastle(), x, y, space) == true) {
				return tabOfCastle.get(i).getCastle();
			}
		}
		return null;
	}
		
	// RECTANGLE
	/**
	 * Lorsqu'une unité se trouve sur la bordure d'un château,
	 * 	la méthode moveAroundACastle la déplace comme si elle était sur un rond-point 
	 * 	en tournant dans le sens anti-horaire. L'unité ne peut quitter la bordure du château que si 
	 * 	elle se trouve sur un coin de la bordure.
	 * Dans l'autre cas elle ne fait.
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * @param ost
	 * 	l'OST auquel appartient l'unité	
	 * @param x
	 * 	coordonnée en x de l'unité
	 * @param y
	 * 	coordonnée en y de l'unité
	 * @return la direction vers laquelle doit se deplacer l'unité si elle se trouve sur la bordure d'un château
	 * 	sinon "freeToMove" si elle ne se trouve pas sur une bordure
	 */
	static String moveAroundACastle(ArrayList<Castle> tabOfCastle,OST ost, double x, double y) {
		CastleStruct c = aroundACastle(tabOfCastle,ost,x,y);
		int targetX = ost.getTargetX(); 
		int targetY = ost.getTargetY(); 
		if(c != null) {
			int corner = Coordonnee.onACorner(c, x, y, space);
			if( corner == 0) {	// bouge le long d'un mur
				if(x <= c.getCornerLT().getX() - space && y <= c.getCornerLB().getY() + space) {
					return "DOWN";
				}else if(x >= c.getCornerRT().getX() + space && y >= c.getCornerRT().getY() - space) {
					return "UP";
				}else if(y >= c.getCornerLB().getY() + space && x <= c.getCornerRB().getX() + space) {
					return "RIGHT";
				}else if(y <= c.getCornerLT().getY() - space && x >= c.getCornerLT().getX() - space) {
					return "LEFT";
				}
			}else {
				return caseOfonACorner(corner, targetX, targetY, x, y);
			}
		}
		return "freeToMove";
	}
	
	/**
	 * Lorsqu'une unité se trouve sur la bordure d'un château,
	 * 	la méthode moveAroundACastleC la déplace comme si elle était sur un rond-point 
	 * 	en tournant dans le sens anti-horaire. L'unité ne peut quitter la bordure du château que si 
	 * 	elle se trouve sur un coin de la bordure.
	 * Dans l'autre cas elle ne fait.
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * @param ost
	 * 	l'OST auquel appartient l'unité	
	 * @param circle
	 * 	le cercle à déplacer
	 * @return la direction vers laquelle doit se deplacer l'unité si elle peut quitter la bordure d'un château
	 * 	elle peut retourner "OK" si elle déplace l'unité le long de la bordure 
	 * 	ou alors "freeToMove" si elle ne se trouve pas sur une bordure
	 */
	// CIRCLE
	static String moveAroundACastleC(ArrayList<Castle> tabOfCastle,OST ost, Circle circle) {
		double x =  circle.getCenterX();
		double y =  circle.getCenterY();
		CastleStruct c = aroundACastle(tabOfCastle,ost,x,y);
		int targetX = ost.getTargetX(); 
		int targetY = ost.getTargetY();
		int speed = 1;
		if(c != null) {
			int corner = Coordonnee.onACorner(c, x, y, space);
			if( corner == 0) {	// bouge le long d'un mur
				if(x == (c.getCornerLT().getX() - space) && y < (c.getCornerLB().getY() + space)) {
					moveCircle(ost, "DOWN", circle);
					return "OK";
				}else if(x == (c.getCornerRT().getX() + space) && y > (c.getCornerRT().getY() - space)) {
					moveCircle(ost, "UP", circle);
					return "OK";
				}else if(y == (c.getCornerLB().getY() + space) && x < (c.getCornerRB().getX() + space)) {
					moveCircle(ost, "RIGHT", circle);
					return "OK";
				}else if(y == (c.getCornerLT().getY() - space) && x > (c.getCornerLT().getX() - space)) {
					moveCircle(ost, "LEFT", circle);
					return "OK";
				}
			}else {
				return caseOfonACorner(corner, targetX, targetY, x, y);
			}
		}
		return "freeToMove";
	}
	
	/**
	 * Lorsqu'une unité se trouve sur la bordure d'un château,
	 * 	la méthode moveAroundACastleP la déplace comme si elle était sur un rond-point 
	 * 	en tournant dans le sens anti-horaire. L'unité ne peut quitter la bordure du château que si 
	 * 	elle se trouve sur un coin de la bordure.
	 * Dans l'autre cas elle ne fait.
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * @param ost
	 * 	l'OST auquel appartient l'unité	
	 * @param polygon
	 * 	le polygon à déplacer
	 * @return la direction vers laquelle doit se deplacer l'unité si elle peut quitter la bordure d'un château
	 * 	elle peut retourner "OK" si elle déplace l'unité le long de la bordure 
	 * 	ou alors "freeToMove" si elle ne se trouve pas sur une bordure
	 */
	static String moveAroundACastleP(ArrayList<Castle> tabOfCastle,OST ost, Polygon polygon) {
		ObservableList<Double> pos = polygon.getPoints();
		double PolyX = pos.get(0);
		double PolyY = pos.get(1);
		CastleStruct c = aroundACastle(tabOfCastle,ost, PolyX, (PolyY+5));
		int targetX = ost.getTargetX(); 
		int targetY = ost.getTargetY();
		if(c != null) {
			int corner = Coordonnee.onACorner(c, PolyX, (PolyY+5), space);
			if( corner == 0) {	// bouge le long d'un mur
				if(PolyX == (c.getCornerLT().getX() - space) && (PolyY+5) < (c.getCornerLB().getY() + space)) {
					movePolygon(ost, "DOWN", polygon);
					return "OK";
				}else if(PolyX == (c.getCornerRT().getX() + space) && (PolyY+5) > (c.getCornerRT().getY() - space)) {
					movePolygon(ost, "UP", polygon);
					return "OK";
				}else if( (PolyY+5) == (c.getCornerLB().getY() + space) && PolyX < (c.getCornerRB().getX() + space)) {
					movePolygon(ost, "RIGHT", polygon);;
					return "OK";
				}else if((PolyY+5) == (c.getCornerLT().getY() - space) && PolyX > (c.getCornerLT().getX() - space)) {
					movePolygon(ost, "LEFT", polygon);
					return "OK";
				}
			}else {
				return caseOfonACorner(corner, targetX, targetY, PolyX, (PolyY+5));
			}
		}
		return "freeToMove";
	}

	/**
	 * Cette méthode vérifie si l'unité peut sortir de la bordure d'un château pour rejoindre sa cible
	 * ou si elle doit continuer son contournement
	 * @param corner
	 * 	un entier qui représente le coin sur lequel se trouve l'unité
	 * 	@see Coordonnee.onACorner
	 * @param targetX
	 * 	la coordonnée X de la cible de l'unité
	 * @param targetY
	 * 	la coordonnée Y de la cible de l'unité
	 * @param x
	 * 	la coordonnée X de l'unité
	 * @param y
	 * 	la coordonnée Y de l'unité
	 * @return la direction vers laquelle doit se déplacer l'unité
	 */
	static String caseOfonACorner(int corner, int targetX,int targetY,double x, double y) {
		switch(corner) {
			case 1:	//LT	move DOWN or exit
				if(targetX >= x ) {
					return "DOWN";
				}else {
					return "LEFT";
				}
			case 2:	//RT	move LEFT or exit
				if(targetY >= y) {
					return "LEFT";
				}else {
					return "UP";
				}
			case 3:	//LB	move RIGHT or exit
				if(targetY <= y ) {
					return "RIGHT";
				}else {
					return "DOWN";
				}
			case 4:	//RB	move UP or exit
				if(targetX <= x) {
					return "UP";
				}else {
					return "RIGHT";
				}
			default:
				return "error";
		}
	}
	
	/**
	 * méthode qui déplace de 1 pixel un rectangle en fonction de la direction dir
	 * @param ost
	 * 	l'OST du rectangle r
	 * @param dir
	 * 	la direction vers laquelle doit se déplacer le rectangle
	 * @param r
	 * le rectangle à déplacer
	 */
	static void moveRectangle(OST ost, String dir, Rectangle r) {
		switch(dir) {
		case "LEFT" :
			r.setX(r.getX() -1);
			break;
		case "RIGHT" :
			r.setX(r.getX() +1);
			break;
		case "UP" :
			r.setY(r.getY() -1);
			break;
		case "DOWN" :
			r.setY(r.getY() +1);
			break;
		default:
			break;
		}
	}
	
	/**
	 * méthode qui déplace de 1 pixel un cercle en fonction de la direction dir
	 * @param ost
	 * 	l'OST du cercle c
	 * @param dir
	 * 	la direction vers laquelle doit se déplacer le cercle
	 * @param c
	 * le cercle à déplacer
	 */
	static void moveCircle(OST ost, String dir, Circle c) {
		switch(dir) {
			case "LEFT" :
				c.setCenterX(c.getCenterX() -1);
				break;
			case "RIGHT" :
				c.setCenterX(c.getCenterX() +1);
	
				break;
			case "UP" :
				c.setCenterY(c.getCenterY() -1);
				break;
			case "DOWN" :
				c.setCenterY(c.getCenterY() +1);
				break;
			default:
				break;
		}		
	}
	
	/**
	 * méthode qui déplace de 1 pixel un polygon en fonction de la direction dir
	 * @param ost
	 * 	l'OST du polygon p
	 * @param dir
	 * 	la direction vers laquelle doit se déplacer le polygon
	 * @param p
	 * le polygon à déplacer
	 */
	static void movePolygon(OST ost, String dir, Polygon p) {
		ObservableList<Double> pos = p.getPoints();
		switch(dir) {
		case "LEFT" :
			pos.set(0, pos.get(0) -1);
			pos.set(2, pos.get(2) -1);
			pos.set(4, pos.get(4) -1);
			break;
		case "RIGHT" :
			pos.set(0, pos.get(0) +1);
			pos.set(2, pos.get(2) +1);
			pos.set(4, pos.get(4) +1);
			break;
		case "UP" :
			pos.set(1, pos.get(1) -1);
			pos.set(3, pos.get(3) -1);
			pos.set(5, pos.get(5) -1);
			break;
		case "DOWN" :
			pos.set(1, pos.get(1) +1);
			pos.set(3, pos.get(3) +1);
			pos.set(5, pos.get(5) +1);
			break;
		default:
			break;
		}
	}
}
