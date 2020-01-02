package SampleGame;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class OST {
	private ArrayList<Troupes> ostUnites;
	private int MaxSpeed;
	private String TargetName;
	private TeamColor TeamColor;
	private String owner;
	
	private ArrayList<Rectangle> rectangle = new ArrayList<>();
	private ArrayList<Circle> circle = new ArrayList<>();
	private ArrayList<Polygon> polygon = new ArrayList<>();
	
	private int x;
	private int y;
	private int dx;
	private int dy;
	
	private boolean inMovment;
	private boolean canAttack;
	
	OST(String TargetName, int tab[], String owner, ArrayList<Castle> tabOfCastle) {
		for(int i=0; i <tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getName() == owner) {
				this.TeamColor = tabOfCastle.get(i).getColor();
			}
		}
		this.owner = owner;
		this.ostUnites = new  ArrayList<Troupes>();
		this.TargetName = TargetName;
		this.MaxSpeed = 6;
		for(int i = 0; i < 3; i++) {
			if(tab[i]!= 0) {
				switch (i) {
					case 0:
						this.MaxSpeed = 1;
						while(tab[i] > 0) {
							this.ostUnites.add(new Onagre(owner));
							tab[i]--;
						}
						break;
					case 1:
						if(this.MaxSpeed == 6) {
							this.MaxSpeed = 2 ;
						}
						while(tab[i] > 0) {
							this.ostUnites.add(new Piquier(owner));
							tab[i]--;
						}
						break;
					case 2:
						while(tab[i] > 0) {
							this.ostUnites.add(new Chevalier(owner));
							tab[i]--;
						}
						break;
					default: 
						break;
				}
			}
		}
	}
	
	public static void distanceCastles(ArrayList<Castle> tabOfCastle, OST ost, Castle targetCastle) {
		for(int i = 0; i < tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getName() == ost.owner) {
				ost.x = targetCastle.getCastleDoor().getCenter().getX();
				ost.y = targetCastle.getCastleDoor().getCenter().getY();
			}
		}
	}
	
	public static void move(Pane root, OST ost, Castle player, Castle targetCastle) {
		// RECTANGLE
		for(int i = 0; i < ost.getRectangle().size(); i++) {
			// LEFT
			if(player.getCastle().getCenter().getX() > ost.getX() && ost.getRectangle().get(i).getX() != ost.getX()) {
				moveR(ost, "LEFT", ost.getRectangle().get(i));
			}
			// RIGHT
			else if(player.getCastle().getCenter().getX() < ost.getX() && ost.getRectangle().get(i).getX() != ost.getX()) {
				moveR(ost, "RIGHT", ost.getRectangle().get(i));
			}
			// UP OR DOWN
			else if(ost.getRectangle().get(i).getX() == ost.getX()) {
				// UP
				if(player.getCastle().getCenter().getY() > ost.getY() && ost.getRectangle().get(i).getY() != ost.getY()) {
					moveR(ost, "UP", ost.getRectangle().get(i));
				}
				// DOWN
				else if(player.getCastle().getCenter().getY() < ost.getY() && ost.getRectangle().get(i).getY() != ost.getY()) {
					moveR(ost, "DOWN", ost.getRectangle().get(i));
				}
			}
			if(ost.getRectangle().get(i).getX() == ost.getX() && ost.getRectangle().get(i).getY() == ost.getY()) {
				ost.setCanAttack(true);
				root.getChildren().remove(ost.getRectangle().get(i));
				//ost.getRectangle().remove(ost.getRectangle().get(i));
			}
		}
		// CIRCLE
		for(int i = 0; i < ost.getCircle().size(); i++) {
			// LEFT
			if(player.getCastle().getCenter().getX() > ost.getX() && ost.getCircle().get(i).getCenterX() != ost.getX()) {
				moveC(ost, "LEFT", ost.getCircle().get(i));
			}
			// RIGHT
			else if(player.getCastle().getCenter().getX() < ost.getX() && ost.getCircle().get(i).getCenterX() != ost.getX()) {
				moveC(ost, "RIGHT", ost.getCircle().get(i));
			}
			// UP OR DOWN
			if(ost.getCircle().get(i).getCenterX() == ost.getX()) {
				// UP
				if(player.getCastle().getCenter().getY() > ost.getY() && ost.getCircle().get(i).getCenterY() != ost.getY()) {
					moveC(ost, "UP", ost.getCircle().get(i));
				}
				// DOWN
				else if(player.getCastle().getCenter().getY() < ost.getY() && ost.getCircle().get(i).getCenterY() != ost.getY()) {
					moveC(ost, "DOWN", ost.getCircle().get(i));
				}
			}
			if(ost.getCircle().get(i).getCenterX() == ost.getX() && ost.getCircle().get(i).getCenterY() == ost.getY()) {
				ost.setCanAttack(true);
				root.getChildren().remove(ost.getCircle().get(i));
				//ost.getCircle().remove(ost.getCircle().get(i));
			}
			
		}
		// POLYGON
		for(int i = 0; i < ost.getPolygon().size(); i++) {
			ObservableList<Double> pos = ost.getPolygon().get(i).getPoints();
			// LEFT
			if(player.getCastle().getCenter().getX() > ost.getX() && pos.get(0) != ost.getX()) {
				moveP(ost, "LEFT", ost.getPolygon().get(i));
			}
			// RIGHT
			else if(player.getCastle().getCenter().getX() < ost.getX() && pos.get(0) != ost.getX()) {
				moveP(ost, "RIGHT", ost.getPolygon().get(i));
			}
			// UP OR DOWN
			if(pos.get(0) == ost.getX()) {
				// UP
				if(player.getCastle().getCenter().getY() > ost.getY() && pos.get(1) != ost.getY()) {
					moveP(ost, "UP", ost.getPolygon().get(i));
				}
				// DOWN
				else if(player.getCastle().getCenter().getY() < ost.getY() && pos.get(1) != ost.getY()) {
					moveP(ost, "DOWN", ost.getPolygon().get(i));
				}
			}
			if(pos.get(0) == ost.getX() && pos.get(1) == ost.getY()) {
				ost.setCanAttack(true);
				root.getChildren().remove(ost.getPolygon().get(i));
				//ost.getPolygon().remove(ost.getPolygon().get(i));
			}
		}
	}
	
	static void moveR(OST ost, String dir, Rectangle r) {
		//System.out.println("Ciblex : " + ost.getX() + " | Rx : " + r.getX());
		//System.out.println("Cibley : " + ost.getY() + " | Ry : " + r.getY());
		switch(dir) {
		case "LEFT" :
			if(r.getX() < ost.getX()) {
				r.setX(ost.getX());
			}
			else {
				r.setX(r.getX() - ost.getMaxSpeed());
			}
			break;
		case "RIGHT" :
			if(r.getX() > ost.getX()) {
				r.setX(ost.getX());
			}
			else {
				r.setX(r.getX() + ost.getMaxSpeed());
			}
			break;
		case "UP" :
			if(r.getY() < ost.getY()) {
				r.setY(ost.getY());
			}
			else {
				r.setY(r.getY() - ost.getMaxSpeed());
			}
			break;
		case "DOWN" :
			if(r.getY() > ost.getY()) {
				r.setY(ost.getY());
			}
			else {
				r.setY(r.getY() + ost.getMaxSpeed());
			}
			break;
		default:
			break;
		}
	}
	
	static void moveC(OST ost, String dir, Circle c) {
		//System.out.println("Ciblex : " + ost.getX() + " | Rx : " + c.getCenterX());
		//System.out.println("Cibley : " + ost.getY() + " | Ry : " + c.getCenterY());
		switch(dir) {
		case "LEFT" :
			if(c.getCenterX() < ost.getX()) {
				c.setCenterX(ost.getX());
			}
			else {
				c.setCenterX(c.getCenterX() - ost.getMaxSpeed());
			}
			break;
		case "RIGHT" :
			if(c.getCenterX() > ost.getX()) {
				c.setCenterX(ost.getX());
			}
			else {
				c.setCenterX(c.getCenterX() + ost.getMaxSpeed());
			}
			break;
		case "UP" :
			if(c.getCenterY() < ost.getY()) {
				c.setCenterY(ost.getY());
			}
			else {
				c.setCenterY(c.getCenterY() - ost.getMaxSpeed());
			}
			break;
		case "DOWN" :
			if(c.getCenterY() > ost.getY()) {
				c.setCenterY(ost.getY());
			}
			else {
				c.setCenterY(c.getCenterY() + ost.getMaxSpeed());
			}
			break;
		default:
			break;
		}
	}
	
	static void moveP(OST ost, String dir, Polygon p) {
		ObservableList<Double> pos = p.getPoints();
		//System.out.println("Ciblex : " + ost.getX() + " | Px : " + pos.get(0));
		switch(dir) {
		case "LEFT" :
			if(pos.get(0) < ost.getX()) {
				pos.set(0, (double) ost.getX());
				pos.set(2, (double) ost.getX() - Coordonnee.distance(pos.get(0), pos.get(2)));
				pos.set(4, (double) ost.getX() + Coordonnee.distance(pos.get(0), pos.get(4)) + 2);

			}
			else {
				pos.set(0, pos.get(0) - ost.getMaxSpeed());
				pos.set(2, pos.get(2) - ost.getMaxSpeed());
				pos.set(4, pos.get(4) - ost.getMaxSpeed());
			}
			break;
		case "RIGHT" :
			if(pos.get(0) > ost.getX()) {
				pos.set(0, (double) ost.getX());
				pos.set(2, (double) ost.getX() - Coordonnee.distance(pos.get(0), pos.get(2)) + 2);
				pos.set(4, (double) ost.getX() + Coordonnee.distance(pos.get(0), pos.get(4)));
			}
			else {
				pos.set(0, pos.get(0) + ost.getMaxSpeed());
				pos.set(2, pos.get(2) + ost.getMaxSpeed());
				pos.set(4, pos.get(4) + ost.getMaxSpeed());
			}
			break;
		case "UP" :
			if(pos.get(1) < ost.getY()) {
				pos.set(1, (double) ost.getY());
				pos.set(3, (double) ost.getY() - Coordonnee.distance(pos.get(1), pos.get(3)));
				pos.set(5, (double) ost.getY() + Coordonnee.distance(pos.get(1), pos.get(5)));				
			}
			else {
				pos.set(1, pos.get(1) - ost.getMaxSpeed());
				pos.set(3, pos.get(3) - ost.getMaxSpeed());
				pos.set(5, pos.get(5) - ost.getMaxSpeed());
			}
			break;
		case "DOWN" :
			if(pos.get(1) > ost.getY()) {
				pos.set(1, (double) ost.getY());
				pos.set(3, (double) ost.getY() - Coordonnee.distance(pos.get(1), pos.get(3)));
				pos.set(5, (double) ost.getY() + Coordonnee.distance(pos.get(1), pos.get(5)));				
			}
			else {
				pos.set(1, pos.get(1) + ost.getMaxSpeed());
				pos.set(3, pos.get(3) + ost.getMaxSpeed());
				pos.set(5, pos.get(5) + ost.getMaxSpeed());
			}
			break;
		default:
			break;
		}
	}

	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	public int getMaxSpeed() {
		return MaxSpeed;
	}
	public ArrayList<Troupes> getOstUnites() {
		return ostUnites;
	}

	public void setOstUnites(ArrayList<Troupes> ostUnites) {
		this.ostUnites = ostUnites;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.MaxSpeed = maxSpeed;
	}
	public String getTargetName() {
		return TargetName;
	}
	public void setTargetName(String targetName) {
		this.TargetName = targetName;
	}
	public TeamColor getTeamColor() {
		return TeamColor;
	}
	public void setTeamColor(TeamColor TeamColor) {
		this.TeamColor = TeamColor;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return owner;
	}
	public ArrayList<Rectangle> getRectangle() {
		return rectangle;
	}
	public void setRectangle(ArrayList<Rectangle> rectangle) {
		this.rectangle = rectangle;
	}
	public ArrayList<Circle> getCircle() {
		return circle;
	}
	public void setCircle(ArrayList<Circle> circle) {
		this.circle = circle;
	}
	public ArrayList<Polygon> getPolygon() {
		return polygon;
	}
	public void setPolygon(ArrayList<Polygon> polygon) {
		this.polygon = polygon;
	}
	public int getDx() {
		return dx;
	}
	public void setDx(int x) {
		this.dx = x;
	}
	public int getDy() {
		return dy;
	}
	public void setDy(int y) {
		this.dy = y;
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
	public boolean getInMovment() {
		return inMovment;
	}
	public void setInMovment(boolean inMovment) {
		this.inMovment = inMovment;
	}
	public boolean getCanAttack() {
		return canAttack;
	}
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}
}
