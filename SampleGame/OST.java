package SampleGame;

import java.util.ArrayList;


import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;


public class OST {
	private ArrayList<Troupes> ostUnites;
	private int MaxSpeed;
	private Castle Target;
	private TeamColor TeamColor;
	private Castle owner;
	
	private ArrayList<GeometricForm> tabOfGeometricForm;
	
	private ArrayList<Rectangle> rectangle = new ArrayList<>();
	private ArrayList<Circle> circle = new ArrayList<>();
	private ArrayList<Polygon> polygon = new ArrayList<>();
	
	private ArrayList<Boolean> rectDodgeX = new ArrayList<>();
	private ArrayList<Boolean> rectDodgeY = new ArrayList<>();
	private ArrayList<Boolean> circleDodgeX = new ArrayList<>();
	private ArrayList<Boolean> circleDodgeY = new ArrayList<>();
	private ArrayList<Boolean> polyDodgeX = new ArrayList<>();
	private ArrayList<Boolean> polyDodgeY = new ArrayList<>();
	
	private int targetX;
	private int targetY;
	
	private boolean inMovment;
	private boolean canAttack;
	
	OST(Castle Target, int tab[], Castle owner, ArrayList<Castle> tabOfCastle) {
		for(int i=0; i <tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getName() == owner.getName()) {
				this.TeamColor = tabOfCastle.get(i).getColor();
			}
		}
		this.owner = owner;
		this.ostUnites = new  ArrayList<Troupes>();
		this.Target = Target;
		this.MaxSpeed = 6;
		this.targetX = Target.getCastleDoor().getCenter().getX();
		this.targetY = Target.getCastleDoor().getCenter().getY();
		for(int i = 0; i < 3; i++) {
			if(tab[i]!= 0) {
				switch (i) {
					case 0:
						this.MaxSpeed = 1;
						while(tab[i] > 0) {
							this.ostUnites.add(new Onagre(owner.getName()));
							tab[i]--;
						}
						break;
					case 1:
						if(this.MaxSpeed == 6) {
							this.MaxSpeed = 2 ;
						}
						while(tab[i] > 0) {
							this.ostUnites.add(new Piquier(owner.getName()));
							tab[i]--;
						}
						break;
					case 2:
						while(tab[i] > 0) {
							this.ostUnites.add(new Chevalier(owner.getName()));
							tab[i]--;
						}
						break;
					default: 
						break;
				}
			}
		}
	}
	
	static void initDodgeBoolean(OST ost) {
		for(int i = 0; i < ost.getRectangle().size(); i++) {
			ost.getRectDodgeX().add(false);
			ost.getRectDodgeY().add(false);
		}
		for(int i = 0; i < ost.getCircle().size(); i++) {
			ost.getCircleDodgeX().add(false);
			ost.getCircleDodgeY().add(false);
		}
		for(int i = 0; i < ost.getPolygon().size(); i++) {
			ost.getPolyDodgeX().add(false);
			ost.getPolyDodgeY().add(false);
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
	public Castle getTarget() {
		return Target;
	}
	public void setTarget(Castle target) {
		this.Target = target;
	}
	public TeamColor getTeamColor() {
		return TeamColor;
	}
	public void setTeamColor(TeamColor TeamColor) {
		this.TeamColor = TeamColor;
	}
	public void setOwner(Castle owner) {
		this.owner = owner;
	}
	public Castle getOwner() {
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
	public int getTargetX() {
		return targetX;
	}
	public void setTargetX(int x) {
		this.targetX = x;
	}
	public int getTargetY() {
		return targetY;
	}
	public void setTargetY(int y) {
		this.targetY = y;
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
	public ArrayList<GeometricForm> getTabOfGeometricForm() {
		return tabOfGeometricForm;
	}
	public void setTabOfGeometricForm(ArrayList<GeometricForm> tabOfGeometricForm) {
		this.tabOfGeometricForm = tabOfGeometricForm;
	}

// RECTANGLE
	public ArrayList<Boolean> getRectDodgeX() {
		return rectDodgeX;
	}
	public void setRectDodgeX(ArrayList<Boolean> rectDodgeX) {
		this.rectDodgeX = rectDodgeX;
	}
	public ArrayList<Boolean> getRectDodgeY() {
		return rectDodgeY;
	}
	public void setRectDodgeY(ArrayList<Boolean> rectDodgeY) {
		this.rectDodgeY = rectDodgeY;
	}
//CIRCLE
	public ArrayList<Boolean> getCircleDodgeX() {
		return circleDodgeX;
	}
	public void setCircleDodgeX(ArrayList<Boolean> circleDodgeX) {
		this.circleDodgeX = circleDodgeX;
	}
	public ArrayList<Boolean> getCircleDodgeY() {
		return circleDodgeY;
	}
	public void setCircleDodge(ArrayList<Boolean> circleDodgeY) {
		this.circleDodgeY = circleDodgeY;
	}
//POLYGON
	public ArrayList<Boolean> getPolyDodgeY() {
		return polyDodgeY;
	}
	public void setPolyDodgeY(ArrayList<Boolean> polyDodgeY) {
		this.polyDodgeY = polyDodgeY;
	}		
	public ArrayList<Boolean> getPolyDodgeX() {
		return polyDodgeX;
	}
	public void setPolyDodgeX(ArrayList<Boolean> polyDodgeX) {
		this.polyDodgeX = polyDodgeX;
	}	
}
