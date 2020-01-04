package SampleGame;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Movement {
	
	private final static int space = 10;
	
	static void move(Pane root, ArrayList<Castle> tabOfCastle, OST ost, Castle sourceCastle, Castle targetCastle) {
		//RECTANGLE
		for(int i = 0; i < ost.getRectangle().size(); i++) {
			double rectX = ost.getRectangle().get(i).getX();
			double rectY = ost.getRectangle().get(i).getY();
			Rectangle r = ost.getRectangle().get(i);
			int speed = ost.getMaxSpeed();
			if(inFrontOfTheDoor(sourceCastle.getCastleDoor(),rectX,rectY) == true) {	//sortie du chateau source
				switch(sourceCastle.getCastleDoor().getDirection()) {
					case 'N':
						moveRectangle(ost, "UP", ost.getRectangle().get(i), speed);
						break;
					case 'E':
						moveRectangle(ost, "RIGHT", ost.getRectangle().get(i), speed);
						break;
					case 'S':
						moveRectangle(ost, "DOWN", ost.getRectangle().get(i), speed);
						break;
					case 'W':
						moveRectangle(ost, "LEFT", ost.getRectangle().get(i), speed);
						break;
					default:
						break;
				}
			}else if(inFrontOfTheDoor(targetCastle.getCastleDoor(),rectX,rectY) == true){	//entrée dans le chateau cible
				switch(targetCastle.getCastleDoor().getDirection()) {
					case 'N':
						moveRectangle(ost, "DOWN", ost.getRectangle().get(i), speed);
						break;
					case 'E':
						moveRectangle(ost, "LEFT", ost.getRectangle().get(i), speed);
						break;
					case 'S':
						moveRectangle(ost, "UP", ost.getRectangle().get(i), speed);
						break;
					case 'W':
						moveRectangle(ost, "RIGHT", ost.getRectangle().get(i), speed);
						break;
					default:
						break;
				}
			}else {	//déplacement entre les chateaux vers la cible
				if(r.getX() != ost.getTargetX()) {
					if(ost.getRectDodgeX().get(i)== false) {	
						if( Math.abs(ost.getTargetX() - r.getX()) < speed) {
							speed = (int) Math.abs(ost.getTargetX() - r.getX()); //modifie la vitesse pour ne pas dépasser l'objectif
						}
						if(r.getX() < ost.getTargetX()) {
							if(closeToACastle(tabOfCastle, ost, r.getX(), r.getY(), "RIGHT", speed) == false) { //pas d'obstacle
								moveRectangle(ost, "RIGHT", ost.getRectangle().get(i), speed);
								ost.getRectDodgeY().set(i, false);
							}else {	
								moveRectangle(ost, whereToGo(tabOfCastle, r.getX(), r.getY(), "RIGHT"), ost.getRectangle().get(i), speed);
								ost.getRectDodgeY().set(i, true);
							}
						}else {
							if(closeToACastle(tabOfCastle, ost, r.getX(), r.getY(), "LEFT", speed) == false) {	//pas d'obstacle
								moveRectangle(ost, "LEFT", ost.getRectangle().get(i), speed);
								ost.getRectDodgeY().set(i, false);
							}else {	//début de longement d'un chateau sur l'axe Y
								moveRectangle(ost, whereToGo(tabOfCastle, r.getX(), r.getY(), "LEFT"), ost.getRectangle().get(i), speed);
								ost.getRectDodgeY().set(i, true);
							}
						}
					}
				}
				speed = ost.getMaxSpeed();
				if(ost.getRectDodgeY().get(i)== false) {	//si l'unité n'est pas en train de longer un chateau sur l'axe Y then DO
					if(r.getY() != ost.getTargetY()) {
						if( Math.abs(ost.getTargetY() - r.getY()) < speed) {
							speed = (int) Math.abs(ost.getTargetY() - r.getY()); //modifie la vitesse pour ne pas dépasser l'objectif
						}
						if(r.getY() < ost.getTargetY()) {
							if(closeToACastle(tabOfCastle, ost, r.getX(), r.getY(), "DOWN", speed) == false) { //pas d'obstacle
								moveRectangle(ost, "DOWN", ost.getRectangle().get(i), speed);
								ost.getRectDodgeX().set(i, false);
							}else {	//début de longement d'un chateau sur l'axe X
								moveRectangle(ost, whereToGo(tabOfCastle, r.getX(), r.getY(), "DOWN"), ost.getRectangle().get(i), speed);
								ost.getRectDodgeX().set(i, true);
							}
						}else {
							if(closeToACastle(tabOfCastle, ost, r.getX(), r.getY(), "UP", speed) == false) {	//pas d'obstacle
								moveRectangle(ost, "UP", ost.getRectangle().get(i), speed);
								ost.getRectDodgeX().set(i, false);
							}else {	//début de longement d'un chateau sur l'axe X
								moveRectangle(ost, whereToGo(tabOfCastle, r.getX(), r.getY(), "UP"), ost.getRectangle().get(i), speed);
								ost.getRectDodgeX().set(i, true);
							}
						}
					}
				}
				if(ost.getRectangle().get(i).getX() == ost.getTargetX() && ost.getRectangle().get(i).getY() == ost.getTargetY()) {
					root.getChildren().remove(ost.getRectangle().get(i));
					ost.getRectangle().remove(ost.getRectangle().get(i));
				}
			}
		}
		//CIRCLE
		for(int i = 0; i < ost.getCircle().size(); i++) {
			double circleX = ost.getCircle().get(i).getCenterX() ;
			double circleY = ost.getCircle().get(i).getCenterY()-5;
			int speed = ost.getMaxSpeed();
			if(inFrontOfTheDoor(sourceCastle.getCastleDoor(),circleX,circleY) == true) {	//sortie du chateau source
				switch(sourceCastle.getCastleDoor().getDirection()) {
					case 'N':
						moveCircle(ost, "UP", ost.getCircle().get(i), speed);
						break;
					case 'E':
						moveCircle(ost, "RIGHT", ost.getCircle().get(i), speed);
						break;
					case 'S':
						moveCircle(ost, "DOWN", ost.getCircle().get(i), speed);
						break;
					case 'W':
						moveCircle(ost, "LEFT", ost.getCircle().get(i), speed);
						break;
					default:
						break;
				}
			}else if(inFrontOfTheDoor(targetCastle.getCastleDoor(),circleX,circleY) == true){	//entrée dans le chateau cible
				switch(sourceCastle.getCastleDoor().getDirection()) {
					case 'N':
						moveCircle(ost, "DOWN", ost.getCircle().get(i), speed);
						break;
					case 'E':
						moveCircle(ost, "LEFT", ost.getCircle().get(i), speed);
						break;
					case 'S':
						moveCircle(ost, "UP", ost.getCircle().get(i), speed);
						break;
					case 'W':
						moveCircle(ost, "RIGHT", ost.getCircle().get(i), speed);
						break;
					default:
						break;
				}
			}else {	//déplacement entre les chateaux vers la cible
				
				
			}
		}
		//POLYGON
		for(int i = 0; i < ost.getPolygon().size(); i++) {
			ObservableList<Double> pos = ost.getPolygon().get(i).getPoints();
			double PolyX = pos.get(0);
			double PolyY = pos.get(1);
			int speed = ost.getMaxSpeed();
			if(inFrontOfTheDoor(sourceCastle.getCastleDoor(),PolyX,PolyY) == true) {	//sortie du chateau source
				switch(sourceCastle.getCastleDoor().getDirection()) {
					case 'N':
						movePolygon(ost, "UP", ost.getPolygon().get(i), speed);
						break;
					case 'E':
						movePolygon(ost, "RIGHT", ost.getPolygon().get(i), speed);
						break;
					case 'S':
						movePolygon(ost, "DOWN", ost.getPolygon().get(i), speed);
						break;
					case 'W':
						movePolygon(ost, "LEFT", ost.getPolygon().get(i), speed);
						break;
					default:
						break;
				}
			}else if(inFrontOfTheDoor(targetCastle.getCastleDoor(),PolyX,PolyY) == true){	//entrée dans le chateau cible
				switch(sourceCastle.getCastleDoor().getDirection()) {
					case 'N':
						movePolygon(ost, "DOWN", ost.getPolygon().get(i), speed);
						break;
					case 'E':
						movePolygon(ost, "LEFT", ost.getPolygon().get(i), speed);
						break;
					case 'S':
						movePolygon(ost, "UP", ost.getPolygon().get(i), speed);
						break;
					case 'W':
						movePolygon(ost, "RIGHT", ost.getPolygon().get(i), speed);
						break;
					default:
						break;
				}
			}else {	//déplacement entre les chateaux vers la cible
				
			}
		}
		//fin du déplacement de l'OST arrivé au chateau cible
		if(ost.getRectangle().isEmpty() && ost.getCircle().isEmpty() && ost.getPolygon().isEmpty()) {
			ost.setCanAttack(true);
		}
	}
	
	static boolean inFrontOfTheDoor(CastleDoor door, double formX, double formY) {
		int doorCornerX;
		int doorCornerY;
		switch(door.getDirection()) {
			case 'N': 
				doorCornerX = door.getCornerLT().getX();
				doorCornerY = door.getCornerLT().getY();
				if( (doorCornerX < formX && formX < (doorCornerX + 3*space)) && (doorCornerY > formY && formY > (doorCornerY - 3*space)) ) {
					return true;
				}else {
					return false;
				}
			case 'E':
				doorCornerX = door.getCornerRT().getX();
				doorCornerY = door.getCornerRT().getY();
				if( (doorCornerX > formX && formX < (doorCornerX + 3*space)) && (doorCornerY < formY && formY < (doorCornerY + 3*space)) ) {
					return true;
				}else {
					return false;
				}
			case 'S':
				doorCornerX = door.getCornerLB().getX();
				doorCornerY = door.getCornerLB().getY();
				if( (doorCornerX < formX && formX < (doorCornerX + 3*space)) && (doorCornerY < formY && formY < (doorCornerY + 3*space)) ) {
					return true;
				}else {
					return false;
				}
			case 'W':	
				doorCornerX = door.getCornerLT().getX();
				doorCornerY = door.getCornerLT().getY();
				if( (doorCornerX < formX && formX > (doorCornerX - 3*space)) && (doorCornerY < formY && formY < (doorCornerY + 3*space)) ) {
					return true;
				}else {
					return false;
				}
			default:
				return false;
		}
	}
	
	static boolean closeToACastle(ArrayList<Castle> tabOfCastle, OST ost, double x, double y, String direction, int speed) {
		switch(direction) {
			case "UP":
				y = y  - speed;
				break;
			case "DOWN":
				y = y  - speed;
				break;
			case "RIGHT":
				x = x  + speed;
				break;
			case "LEFT":
				x = x  - speed;
				break;
			default:
				return false;
		}
		for(int i=0; i < tabOfCastle.size(); i++) {
			if(Coordonnee.inASquare(tabOfCastle.get(i).getCastle(), x, y, space) == true) {
				return true;
			}
		}
		return false;
	}
	
	static String whereToGo(ArrayList<Castle> tabOfCastle, double x, double y, String direction) {
		double minD = Settings.SCENE_WIDTH; //pour initialiser
		CastleStruct c = tabOfCastle.get(0).getCastle();;
		for(int i = 0; i < tabOfCastle.size(); i++) {
			double x2  = tabOfCastle.get(i).getCastle().getCenter().getX();
			double y2  = tabOfCastle.get(i).getCastle().getCenter().getY();
			double d = Coordonnee.distance(x,y,x2,y2);
			if(minD > d) {
				minD = d;
				c = tabOfCastle.get(i).getCastle();
			}
		}
		double xCorner1;
		double yCorner1;
		double xCorner2;
		double yCorner2;
		switch(direction) {
			case "UP":
				xCorner1 = c.getCornerLB().getX();
				yCorner1 = c.getCornerLB().getY();
				xCorner2 = c.getCornerRB().getX();
				yCorner2 = c.getCornerRB().getY();
				if(Coordonnee.distance(x,y,xCorner1,yCorner1) < Coordonnee.distance(x,y,xCorner2,yCorner2)) {
					return "LEFT";
				}else {
					return "RIGHT";
				}
			case "DOWN":
				xCorner1 = c.getCornerLT().getX();
				yCorner1 = c.getCornerLT().getY();
				xCorner2 = c.getCornerRT().getX();
				yCorner2 = c.getCornerRT().getY();
				if(Coordonnee.distance(x,y,xCorner1,yCorner1) < Coordonnee.distance(x,y,xCorner2,yCorner2)) {
					return "RIGHT";
				}else {
					return "LEFT";
				}
			case "RIGHT":
				xCorner1 = c.getCornerLT().getX();
				yCorner1 = c.getCornerLT().getY();
				xCorner2 = c.getCornerLB().getX();
				yCorner2 = c.getCornerLB().getY();
				if(Coordonnee.distance(x,y,xCorner1,yCorner1) < Coordonnee.distance(x,y,xCorner2,yCorner2)) {
					return "UP";
				}else {
					return "DOWN";
				}
			case "LEFT":
				xCorner1 = c.getCornerRT().getX();
				yCorner1 = c.getCornerRT().getY();
				xCorner2 = c.getCornerRB().getX();
				yCorner2 = c.getCornerRB().getY();
				if(Coordonnee.distance(x,y,xCorner1,yCorner1) < Coordonnee.distance(x,y,xCorner2,yCorner2)) {
					return "UP";
				}else {
					return "DOWN";
				}
			default:
				return "error";
		}
	}
	static void moveRectangle(OST ost, String dir, Rectangle r, int speed) {
		//System.out.println("Ciblex : " + ost.getX() + " | Rx : " + r.getX());
		//System.out.println("Cibley : " + ost.getY() + " | Ry : " + r.getY());
		switch(dir) {
		case "LEFT" :
			if(r.getX() < ost.getTargetX()) {
				r.setX(ost.getTargetX());
			}
			else {
				r.setX(r.getX() -speed);
			}
			break;
		case "RIGHT" :
			if(r.getX() > ost.getTargetX()) {
				r.setX(ost.getTargetX());
			}
			else {
				r.setX(r.getX() +speed);
			}
			break;
		case "UP" :
			if(r.getY() < ost.getTargetY()) {
				r.setY(ost.getTargetY());
			}
			else {
				r.setY(r.getY() -speed);
			}
			break;
		case "DOWN" :
			if(r.getY() > ost.getTargetY()) {
				r.setY(ost.getTargetY());
			}
			else {
				r.setY(r.getY() +speed);
			}
			break;
		default:
			break;
		}
	}
	
	static void moveCircle(OST ost, String dir, Circle c, int speed) {
		//System.out.println("Ciblex : " + ost.getX() + " | Rx : " + c.getCenterX());
		//System.out.println("Cibley : " + ost.getY() + " | Ry : " + c.getCenterY());
		switch(dir) {
		case "LEFT" :
			if(c.getCenterX() < ost.getTargetX()) {
				c.setCenterX(ost.getTargetX());
			}
			else {
				c.setCenterX(c.getCenterX() -speed);
			}
			break;
		case "RIGHT" :
			if(c.getCenterX() > ost.getTargetX()) {
				c.setCenterX(ost.getTargetX());
			}
			else {
				c.setCenterX(c.getCenterX() +speed);
			}
			break;
		case "UP" :
			if(c.getCenterY() < ost.getTargetY()) {
				c.setCenterY(ost.getTargetY());
			}
			else {
				c.setCenterY(c.getCenterY() -speed);
			}
			break;
		case "DOWN" :
			if(c.getCenterY() > ost.getTargetY()) {
				c.setCenterY(ost.getTargetY());
			}
			else {
				c.setCenterY(c.getCenterY() +speed);
			}
			break;
		default:
			break;
		}
	}
	
	static void movePolygon(OST ost, String dir, Polygon p, int speed) {
		ObservableList<Double> pos = p.getPoints();
		//System.out.println("Ciblex : " + ost.getX() + " | Px : " + pos.get(0));
		switch(dir) {
		case "LEFT" :
			if(pos.get(0) < ost.getTargetX()) {
				pos.set(0, (double) ost.getTargetX());
				pos.set(2, (double) ost.getTargetX() - Coordonnee.distance(pos.get(0), pos.get(2)));
				pos.set(4, (double) ost.getTargetX() + Coordonnee.distance(pos.get(0), pos.get(4)) + 2);

			}
			else {
				pos.set(0, pos.get(0) -speed);
				pos.set(2, pos.get(2) -speed);
				pos.set(4, pos.get(4) -speed);
			}
			break;
		case "RIGHT" :
			if(pos.get(0) > ost.getTargetX()) {
				pos.set(0, (double) ost.getTargetX());
				pos.set(2, (double) ost.getTargetX() - Coordonnee.distance(pos.get(0), pos.get(2)) + 2);
				pos.set(4, (double) ost.getTargetX() + Coordonnee.distance(pos.get(0), pos.get(4)));
			}
			else {
				pos.set(0, pos.get(0) +speed);
				pos.set(2, pos.get(2) +speed);
				pos.set(4, pos.get(4) +speed);
			}
			break;
		case "UP" :
			if(pos.get(1) < ost.getTargetY()) {
				pos.set(1, (double) ost.getTargetY());
				pos.set(3, (double) ost.getTargetY() - Coordonnee.distance(pos.get(1), pos.get(3)));
				pos.set(5, (double) ost.getTargetY() + Coordonnee.distance(pos.get(1), pos.get(5)));				
			}
			else {
				pos.set(1, pos.get(1) -speed);
				pos.set(3, pos.get(3) -speed);
				pos.set(5, pos.get(5) -speed);
			}
			break;
		case "DOWN" :
			if(pos.get(1) > ost.getTargetY()) {
				pos.set(1, (double) ost.getTargetY());
				pos.set(3, (double) ost.getTargetY() - Coordonnee.distance(pos.get(1), pos.get(3)));
				pos.set(5, (double) ost.getTargetY() + Coordonnee.distance(pos.get(1), pos.get(5)));				
			}
			else {
				pos.set(1, pos.get(1) +speed);
				pos.set(3, pos.get(3) +speed);
				pos.set(5, pos.get(5) +speed);
			}
			break;
		default:
			break;
		}
	}
}
