package SampleGame;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Movement {
	
	private final static int space = 10;
	
	static void move(Pane root, ArrayList<Castle> tabOfCastle, OST ost, Castle sourceCastle, Castle targetCastle, int countSec) {
		// --------------------------------------------------------------------------------------------------------- //
		// --------------------------------------------- RECTANGLE ------------------------------------------------- //
		// --------------------------------------------------------------------------------------------------------- //
		if(countSec % ost.getMaxSpeed() == 0) {
			for(int i = 0; i < ost.getRectangle().size(); i++) {
				Rectangle r = ost.getRectangle().get(i);
				//arrivé à destination
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
		
	static CastleStruct aroundACastle(ArrayList<Castle> tabOfCastle,OST ost, double x, double y) {
		for(int i=0; i < tabOfCastle.size(); i++) {
			if(Coordonnee.onTheBorder(tabOfCastle.get(i).getCastle(), x, y, space) == true) {
				return tabOfCastle.get(i).getCastle();
			}
		}
		return null;
	}
		
	// RECTANGLE
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
