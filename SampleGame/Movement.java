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
			Rectangle r = ost.getRectangle().get(i);
			//System.out.println("DST :" + targetCastle.getCastleDoor().getCenter().getX() + " - " + targetCastle.getCastleDoor().getCenter().getY() + " | UNITE :" + ((r.getX()+5)) + " - " + (((r.getY()+5))));
			//System.out.println("SRC :" + (inFrontOfTheDoor(sourceCastle,(r.getX()+5),((r.getY()+5)), (space-2))) + " | DST:" + inFrontOfTheDoor(targetCastle,(r.getX()+5),((r.getY()+5)),(space*2)));
			int speed = ost.getMaxSpeed();
			//arrivé à destination
			String direction;
			//sortie du chateau source
			if(inFrontOfTheDoor(sourceCastle, (r.getX()+5), ((r.getY()+5)), (space-2)) == true ) {	
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
				//entrée dans le chateau cible
			}else if(inFrontOfTheDoor(targetCastle, (r.getX()+5), ((r.getY()+5)), (space*2)) == true){	
				switch(targetCastle.getCastleDoor().getDirection()) {
					case 'N':
						if((r.getX()+5) != ost.getTargetX()) {
							if( Math.abs(ost.getTargetX() - (r.getX()+5)) < speed) {
								speed = (int) Math.abs(ost.getTargetX() - r.getX()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							if((r.getX()+5) < ost.getTargetX()) {
								moveRectangle(ost, "RIGHT", ost.getRectangle().get(i), speed);
							}else {
								moveRectangle(ost, "LEFT", ost.getRectangle().get(i), speed);
							}
						}else {
							if( Math.abs(ost.getTargetY()+5 - r.getY()) < speed) {
								speed = (int) Math.abs(ost.getTargetY() - r.getY()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							moveRectangle(ost, "DOWN", ost.getRectangle().get(i), speed);
						}
						break;
					case 'E':
						if(((r.getY()+5)) != ost.getTargetY()) {
							if( Math.abs(ost.getTargetY() - ((r.getY()+5))) < speed) {
								speed = (int) Math.abs(ost.getTargetY() - r.getY()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							if(((r.getY()+5)) < ost.getTargetY()) {
								moveRectangle(ost, "DOWN", ost.getRectangle().get(i), speed);
		 					}else {
								moveRectangle(ost, "UP", ost.getRectangle().get(i), speed);
							}
						}else {
							if( Math.abs(ost.getTargetX() - (r.getX()+5)) < speed) {
								speed = (int) Math.abs(ost.getTargetX() - (r.getX()+5)); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							moveRectangle(ost, "LEFT", ost.getRectangle().get(i), speed);
						}
						break;
					case 'S':
						if((r.getX()+5) != ost.getTargetX()) {
							if( Math.abs(ost.getTargetX() - (r.getX()+5)) < speed) {
								speed = (int) Math.abs(ost.getTargetX() - (r.getX()+5)); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							if((r.getX()+5) < ost.getTargetX()) {
								moveRectangle(ost, "RIGHT", ost.getRectangle().get(i), speed);
							}else {
								moveRectangle(ost, "LEFT", ost.getRectangle().get(i), speed);
							}
						}else {
							if( Math.abs(ost.getTargetY() - ((r.getY()+5))) < speed) {
								speed = (int) Math.abs(ost.getTargetY() - ((r.getY()+5))); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							moveRectangle(ost, "UP", ost.getRectangle().get(i), speed);
						}
						break;
					case 'W':
						if(((r.getY()+5)) != ost.getTargetY()) {
							if( Math.abs(ost.getTargetY() - ((r.getY()+5))) < speed) {
								speed = (int) Math.abs(ost.getTargetY() - ((r.getY()+5))); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							if(((r.getY()+5)) < ost.getTargetY()) {
								moveRectangle(ost, "DOWN", ost.getRectangle().get(i), speed);
							}else {
								moveRectangle(ost, "UP", ost.getRectangle().get(i), speed);
							}
						}else {
							if( Math.abs(ost.getTargetX() - (r.getX()+5)) < speed) {
								speed = (int) Math.abs(ost.getTargetX() - (r.getX()+5)); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							moveRectangle(ost, "RIGHT", ost.getRectangle().get(i), speed);
						}
						break;
					default:
						break;
				}
				//déplacement normal 
			}else if(inFrontOfTheDoor(sourceCastle,(r.getX()+5),((r.getY()+5)), (space-2)) == false &&
					inFrontOfTheDoor(targetCastle,(r.getX()+5),((r.getY()+5)), (space*2)) == false){	//déplacement entre les chateaux vers la cible
				direction = moveAroundACastle(tabOfCastle, ost, (r.getX()+5), ((r.getY()+5)));				
				if(direction == "freeToMove") {
					if((r.getX()+5) != ost.getTargetX()) {
						//si l'unité n'est pas en train de longer un chateau sur l'axe X then DO
						if( Math.abs(ost.getTargetX() - r.getX()) < speed) {
							speed = (int) Math.abs(ost.getTargetX() - r.getX()); //modifie la vitesse pour ne pas dépasser l'objectif
						}
						if((r.getX()+5) < ost.getTargetX()) {
							moveRectangle(ost, "RIGHT", ost.getRectangle().get(i), speed);
						}else {
							moveRectangle(ost, "LEFT", ost.getRectangle().get(i), speed);
						}
					}
					speed = ost.getMaxSpeed();
					direction = moveAroundACastle(tabOfCastle, ost, (r.getX()+5), ((r.getY()+5)));
					if(direction == "freeToMove") {
						if(((r.getY()+5)) != ost.getTargetY()) {
							if( Math.abs(ost.getTargetY() - ((r.getY()+5))) < speed) {
								speed = (int) Math.abs(ost.getTargetY() - ((r.getY()+5))); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							if(((r.getY()+5)) < ost.getTargetY()) {
								moveRectangle(ost, "DOWN", ost.getRectangle().get(i), speed);
							}else {
								moveRectangle(ost, "UP", ost.getRectangle().get(i), speed);
							}
						}
					}
				}else {
					moveRectangle(ost, direction, ost.getRectangle().get(i), speed);
				}
			}
			if(ost.getRectangle().get(i).getX()+5 == ost.getTargetX() && ost.getRectangle().get(i).getY()+5 == ost.getTargetY()) {
				root.getChildren().remove(ost.getRectangle().get(i));
				ost.getRectangle().remove(ost.getRectangle().get(i));
			}
		}
		// --------------------------------------------------------------------------------------------------------- //
		// ---------------------------------------------- CIRCLE --------------------------------------------------- //
		// --------------------------------------------------------------------------------------------------------- //
		for(int i = 0; i < ost.getCircle().size(); i++) {
			Circle c = ost.getCircle().get(i);
			int speed;
			String direction;
				//Sortie du chateau source
			if(inFrontOfTheDoor(sourceCastle, c.getCenterX(), c.getCenterY(), (space-1)) == true ) {	
				speed = 1;
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
				//entrée dans le chateau cible
			}else if(inFrontOfTheDoor(targetCastle, c.getCenterX(), c.getCenterY(), (space*2)) == true){
				speed = 1;
				switch(targetCastle.getCastleDoor().getDirection()) {
					case 'N':
						if(c.getCenterX() != ost.getTargetX()) {
							if( Math.abs(ost.getTargetX() - c.getCenterX()) < speed) {
								speed = (int) Math.abs(ost.getTargetX() - c.getCenterX()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							if(c.getCenterX() < ost.getTargetX()) {
								moveCircle(ost, "RIGHT", ost.getCircle().get(i), speed);
							}else {
								moveCircle(ost, "LEFT", ost.getCircle().get(i), speed);
							}
						}else {
							if( Math.abs(ost.getTargetY() - c.getCenterY()) < speed) {
								speed = (int) Math.abs(ost.getTargetY() - c.getCenterY()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							moveCircle(ost, "DOWN", ost.getCircle().get(i), speed);
						}
						break;
					case 'E':
						if(c.getCenterY() != ost.getTargetY()) {
							if( Math.abs(ost.getTargetY() - c.getCenterY()) < speed) {
								speed = (int) Math.abs(ost.getTargetY() - c.getCenterY()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							if(c.getCenterY() < ost.getTargetY()) {
								moveCircle(ost, "DOWN", ost.getCircle().get(i), speed);
		 					}else {
								moveCircle(ost, "UP", ost.getCircle().get(i), speed);
							}
						}else {
							if( Math.abs(ost.getTargetX() - c.getCenterX()) < speed) {
								speed = (int) Math.abs(ost.getTargetX() - c.getCenterX()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							moveCircle(ost, "LEFT", ost.getCircle().get(i), speed);
						}
						break;
					case 'S':
						if(c.getCenterX() != ost.getTargetX()) {
							if( Math.abs(ost.getTargetX() - c.getCenterX()) < speed) {
								speed = (int) Math.abs(ost.getTargetX() - c.getCenterX()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							if(c.getCenterX() < ost.getTargetX()) {
								moveCircle(ost, "RIGHT", ost.getCircle().get(i), speed);
							}else {
								moveCircle(ost, "LEFT", ost.getCircle().get(i), speed);
							}
						}else {
							if( Math.abs(ost.getTargetY() - c.getCenterX()) < speed) {
								speed = (int) Math.abs(ost.getTargetY() - c.getCenterX()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							moveCircle(ost, "UP", ost.getCircle().get(i), speed);
						}
						break;
					case 'W':
						if(c.getCenterY() != ost.getTargetY()) {
							if( Math.abs(ost.getTargetY() - c.getCenterX()) < speed) {
								speed = (int) Math.abs(ost.getTargetY() - c.getCenterY()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							if(c.getCenterY() < ost.getTargetY()) {
								moveCircle(ost, "DOWN", ost.getCircle().get(i), speed);
							}else {
								moveCircle(ost, "UP", ost.getCircle().get(i), speed);
							}
						}else {
							if( Math.abs(ost.getTargetX() - c.getCenterX()) < speed) {
								speed = (int) Math.abs(ost.getTargetX() - c.getCenterX()); //modifie la vitesse pour ne pas dépasser l'objectif
							}
							moveCircle(ost, "RIGHT", ost.getCircle().get(i), speed);
						}
						break;
					default:
						break;
				}
				//déplacement normal 
			}else if(inFrontOfTheDoor(sourceCastle,c.getCenterX(),c.getCenterY(), (space-1)) == false &&
					inFrontOfTheDoor(targetCastle,c.getCenterX(),c.getCenterY(), (space*2)) == false){	//déplacement entre les chateaux vers la cible
				speed = ost.getMaxSpeed();
				direction = moveAroundACastleC(tabOfCastle, ost, c);				
				if(direction == "freeToMove") {	//si l'unité n'est pas en train de longer un chateau sur l'axe X then DO
					if(c.getCenterX() != ost.getTargetX()) {
						if(c.getCenterX() < ost.getTargetX()) {
							speed = checkSpeed(tabOfCastle, ost, c.getCenterX(), c.getCenterY(), "RIGHT",false, speed);
							moveCircle(ost, "RIGHT", ost.getCircle().get(i), speed);
						}else {
							speed = checkSpeed(tabOfCastle, ost, c.getCenterX(), c.getCenterY(), "RIGHT",false, speed);
							moveCircle(ost, "LEFT", ost.getCircle().get(i), speed);
						}
					}
				}
				speed = ost.getMaxSpeed();
				direction = moveAroundACastleC(tabOfCastle, ost, c);
				if(direction == "freeToMove") {	//si l'unité n'est pas en train de longer un chateau sur l'axe X then DO
					if(c.getCenterY() != ost.getTargetY()) {
						if(c.getCenterY() < ost.getTargetY()) {
							speed = checkSpeed(tabOfCastle, ost, c.getCenterX(), c.getCenterY(), "DOWN",false, speed);
							moveCircle(ost, "DOWN", ost.getCircle().get(i), speed);
						}else {
							speed = checkSpeed(tabOfCastle, ost, c.getCenterX(), c.getCenterY(), "UP",false, speed);
							moveCircle(ost, "UP", ost.getCircle().get(i), speed);
						}
					}
				}else {
					moveCircle(ost, direction, ost.getCircle().get(i), speed);
				}
			}
			if(ost.getCircle().get(i).getCenterX() == ost.getTargetX() && ost.getCircle().get(i).getCenterY() == ost.getTargetY()) {
				root.getChildren().remove(ost.getCircle().get(i));
				ost.getCircle().remove(ost.getCircle().get(i));
			}
		}
		
		/*
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
				*/
		//fin du déplacement de l'OST arrivé au chateau cible

		if(ost.getRectangle().isEmpty() && ost.getCircle().isEmpty() && ost.getPolygon().isEmpty()) {
			ost.setCanAttack(true);
		}
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
	
	static int checkSpeed(ArrayList<Castle> tabOfCastle,OST ost, double x, double y, String direction,Boolean OnABorder, int speed) {
		double newX = x;
		double newY = y;
        switch(direction) {
            case "UP":
            	newY = y  - speed;
                for(int i=0; i < tabOfCastle.size(); i++) {
                	CastleStruct c = tabOfCastle.get(i).getCastle();
                	if(OnABorder == false) {	//se balade dans les champs
                		if(Coordonnee.inASquare(c, x, newY, (space-1)) == true) {                    	 
                    		return (int) Math.abs((c.getCornerLB().getY() + space) - y);
                        }
    	            	if( Math.abs(ost.getTargetY() - y) < speed ) {
    	            		System.out.println("UpY : " + Math.abs(ost.getTargetY() - y));
    	            		if(Math.abs(ost.getTargetY() - y) == 0) {
    	            			return (int) Math.abs(ost.getTargetY() - y + 1); //modifie la vitesse pour ne pas dépasser l'objectif
    	            		}
    	            		return (int) Math.abs(ost.getTargetY() - y);
    					}
                	}else {	//passe le long d'un chateau
                		if((c.getCornerRT().getY() + space) > newY) {
                			return (int) Math.abs((c.getCornerRT().getY() + space) - y);
                		}
                	}
                    
                }
                break;
            case "DOWN":
            	newY = y  + speed;
            	for(int i=0; i < tabOfCastle.size(); i++) {
                	CastleStruct c = tabOfCastle.get(i).getCastle();
                	if(OnABorder == false) {	//se balade dans les champs
		                if(Coordonnee.inASquare(c, x, newY, (space-1)) == true) {                    	 
		            		return (int) Math.abs((c.getCornerLT().getY() - space) - y);
		                }
		                if(Math.abs(ost.getTargetY() - y) < speed) {
		                	System.out.println("DownY : " + Math.abs(ost.getTargetY() - y));
		                	if(Math.abs(ost.getTargetY() - y) == 0) {
		                		return (int) Math.abs(ost.getTargetY() - y + 1); //modifie la vitesse pour ne pas dépasser l'objectif
		                	}
		                	return (int) Math.abs(ost.getTargetY() - y);
    					}
                	}else {	//passe le long d'un chateau
                		if((c.getCornerLB().getY() - space) < newY) {
                			return (int) Math.abs((c.getCornerRT().getY() - space) - y);
                		}
                	}
                }
                break;
            case "RIGHT":
            	newX = x  + speed;
            	for(int i=0; i < tabOfCastle.size(); i++) {
                	CastleStruct c = tabOfCastle.get(i).getCastle();
                	if(OnABorder == false) {	//se balade dans les champs
	                    if(Coordonnee.inASquare(c, newX, y, (space-1)) == true) {                    	 
	                		return (int) Math.abs((c.getCornerLT().getX() - space) - x);
	                    }
	                    if( Math.abs(ost.getTargetX() - x) < speed) {
	                    	System.out.println("RightX : " + Math.abs(ost.getTargetX() - x));
	                    	if(Math.abs(ost.getTargetX() - x) == 0) {
	                    		return (int) Math.abs(ost.getTargetX() - x + 1); //modifie la vitesse pour ne pas dépasser l'objectif
	                    	}
	                    	return (int) Math.abs(ost.getTargetX() - x); 
    					}
                	}else {	//passe le long d'un chateau
                		if((c.getCornerRB().getX() + space) < newX) {
                			return (int) Math.abs((c.getCornerRB().getX() + space) - x);
                		}
                	} 
                }
            case "LEFT":
            	newX = x  - speed;
            	for(int i=0; i < tabOfCastle.size(); i++) {
                	CastleStruct c = tabOfCastle.get(i).getCastle();
                	if(OnABorder == false) {	//se balade dans les champs
	                    if(Coordonnee.inASquare(c, newX, y, (space-1)) == true) {                    	 
	                		return (int) Math.abs((c.getCornerRT().getX() + space) - x);
	                    }
	                    if( Math.abs(ost.getTargetX() - x) < speed) {
	                    	System.out.println("LeftX : " + Math.abs(ost.getTargetX() - x));
	                    	if(Math.abs(ost.getTargetX() - x) == 0) {
	                    		return (int) Math.abs(ost.getTargetX() - x + 1); //modifie la vitesse pour ne pas dépasser l'objectif
	                    	}
    						return (int) Math.abs(ost.getTargetX() - x); 
    					}
                	}else {	//passe le long d'un chateau
                		if((c.getCornerLT().getX() - space) > newX) {
                			return (int) Math.abs((c.getCornerLT().getX() - space) - x);
                		}
                	} 
                }
                break;
            default:
                return speed;
        }
        return speed;
    }


	
	// RECTANGLE
	static String moveAroundACastle(ArrayList<Castle> tabOfCastle,OST ost, double x, double y) {
		CastleStruct c = aroundACastle(tabOfCastle,ost,x,y);
		int targetX = ost.getTargetX(); 
		int targetY = ost.getTargetY(); 
		if(c != null) {
			switch(Coordonnee.onACorner(c, x, y, space)){
				case 0:		// bouge le long d'un mur
					if(x == c.getCornerLT().getX() - space && y <= c.getCornerLB().getY() + space) {
						return "DOWN";
					}else if(x == c.getCornerRT().getX() + space && y >= c.getCornerRT().getY() - space) {
						return "UP";
					}else if(y == c.getCornerLB().getY() + space && x <= c.getCornerRB().getX() + space) {
						return "RIGHT";
					}else if(y == c.getCornerLT().getY() - space && x >= c.getCornerLT().getX() - space) {
						return "LEFT";
					}
				case 1:	//LT	move DOWN or exit
					if(targetX >= c.getCornerLT().getX()) {
						return "DOWN";
					}else {
						return "freeToMove";
					}
				case 2:	//RT	move LEFT or exit
					if(targetY >= c.getCornerRT().getY()) {
						return "LEFT";
					}else {
						return "freeToMove";
					}
				case 3:	//LB	move RIGHT or exit
					if(targetY <= c.getCornerLB().getY()) {
						return "RIGHT";
					}else {
						return "freeToMove";
					}
				case 4:	//RB	move UP or exit
					if(targetX <= c.getCornerRB().getX()) {
						return "UP";
					}else {
						return "freeToMove";
					}
				default:
					return "error";
			}
		}else {
			return "freeToMove";
		}
	}
	
	
	// CIRCLE
	static String moveAroundACastleC(ArrayList<Castle> tabOfCastle,OST ost, Circle circle) {
		double x = circle.getCenterX();
		double y = circle.getCenterY();
		CastleStruct c = aroundACastle(tabOfCastle,ost,x,y);
		int targetX = ost.getTargetX(); 
		int targetY = ost.getTargetY();
		int speed = ost.getMaxSpeed();
		if(c != null) {
			switch(Coordonnee.onACorner(c, x, y, space)){
				case 0:		// bouge le long d'un mur
					if(x == c.getCornerLT().getX() - space && y <= c.getCornerLB().getY() + space) {
						speed = checkSpeed(tabOfCastle, ost, circle.getCenterX(), circle.getCenterY(), "DOWN",false, speed);
						moveCircle(ost, "DOWN", circle, speed);
						return "OK";
					}else if(x == c.getCornerRT().getX() + space && y >= c.getCornerRT().getY() - space) {
						speed = checkSpeed(tabOfCastle, ost, circle.getCenterX(), circle.getCenterY(), "UP",false, speed);
						moveCircle(ost, "UP", circle, speed);
						return "OK";
					}else if(y == c.getCornerLB().getY() + space && x <= c.getCornerRB().getX() + space) {
						speed = checkSpeed(tabOfCastle, ost, circle.getCenterX(), circle.getCenterY(), "RIGHT",false, speed);
						moveCircle(ost, "RIGHT", circle, speed);
						return "OK";
					}else if(y == c.getCornerLT().getY() - space && x >= c.getCornerLT().getX() - space) {
						speed = checkSpeed(tabOfCastle, ost, circle.getCenterX(), circle.getCenterY(), "LEFT",false, speed);
						moveCircle(ost, "LEFT", circle, speed);
						return "OK";
					}
				case 1:	//LT	move DOWN or exit
					if(targetX >= (c.getCornerLT().getX() - space)) {
						return "DOWN";
					}else {
						return "freeToMove";
					}
				case 2:	//RT	move LEFT or exit
					if(targetY >= c.getCornerRT().getY() - space) {
						return "LEFT";
					}else {
						return "freeToMove";
					}
				case 3:	//LB	move RIGHT or exit
					if(targetY <= c.getCornerLB().getY() + space) {
						return "RIGHT";
					}else {
						return "freeToMove";
					}
				case 4:	//RB	move UP or exit
					if(targetX <= c.getCornerRB().getX() + space) {
						return "UP";
					}else {
						return "freeToMove";
					}
				default:
					return "error";
			}
		}else {
			return "freeToMove";
		}
	}

	static void moveRectangle(OST ost, String dir, Rectangle r, int speed) {
		//System.out.println("Ciblex : " + ost.getX() + " | Rx : " + r.getX());
		//System.out.println("Cibley : " + ost.getY() + " | Ry : " + r.getY());
		switch(dir) {
		case "LEFT" :
			r.setX(r.getX() -speed);
			break;
		case "RIGHT" :
			r.setX(r.getX() +speed);
			break;
		case "UP" :
			r.setY(r.getY() -speed);
			break;
		case "DOWN" :
			r.setY(r.getY() +speed);
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
				c.setCenterX(c.getCenterX() -speed);
				break;
			case "RIGHT" :
				c.setCenterX(c.getCenterX() +speed);
	
				break;
			case "UP" :
				c.setCenterY(c.getCenterY() -speed);
				break;
			case "DOWN" :
				c.setCenterY(c.getCenterY() +speed);
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
