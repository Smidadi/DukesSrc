package SampleGame;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

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
				ost.dx = tabOfCastle.get(i).getCastle().getCenter().getX() - targetCastle.getCastle().getCenter().getX();
				ost.dy = tabOfCastle.get(i).getCastle().getCenter().getY() - targetCastle.getCastle().getCenter().getY();
				ost.x = targetCastle.getCastle().getCenter().getX();
				ost.y = targetCastle.getCastle().getCenter().getY();
			}
		}
	}
	
	public static void move(OST ost) {
		for(int i = 0; i < ost.getRectangle().size(); i++) {
			moveR(ost, ost.getRectangle().get(i));
		}
		for(int i = 0; i < ost.getCircle().size(); i++) {
			moveC(ost, ost.getCircle().get(i));
		}
		for(int i = 0; i < ost.getPolygon().size(); i++) {
			moveP(ost, ost.getPolygon().get(i));
		}
	}
	
	static void moveR(OST ost ,Rectangle r) {
		if(ost.getX() < r.getX()) {
			r.setX(r.getX() - ost.getMaxSpeed());
		}
		else if(ost.getX() > r.getX()) {
			r.setX(r.getX() + ost.getMaxSpeed());
		}
		else {
			if(ost.getY() < r.getY()) {
				r.setY(r.getY() - ost.getMaxSpeed());
			}
			else if(ost.getY() > r.getY()) {
				r.setY(r.getY() + ost.getMaxSpeed());
			}
		}
	}
	
	static void moveC(OST ost, Circle c) {
		if(ost.getX() < c.getCenterX()) {
			c.setCenterX(c.getCenterX() - ost.getMaxSpeed());
		}
		else if(ost.getX() > c.getCenterX()) {
			c.setCenterX(c.getCenterX() + ost.getMaxSpeed());
		}
		else {
			if(ost.getY() < c.getCenterY()) {
				c.setCenterY(c.getCenterY() - ost.getMaxSpeed());
			}
			else if(ost.getY() > c.getCenterY()) {
				c.setCenterY(c.getCenterY() + ost.getMaxSpeed());
			}
		}
	}
	
	static void moveP(OST ost, Polygon p) {
		
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
}
