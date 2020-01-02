package SampleGame;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class Castle {
	
	private String name;
	private String type;
	private int niveau;
	private int tresor;	
	private int revenu;
	// private int production;  type : troops/amelioration + nb tours restant 
	private ArrayList<Troupes> tabTroupes = new ArrayList<Troupes>();
	private ArrayList<String> tabOfProduction = new ArrayList<String>();
	protected OST ost;
	private CastleStruct Castle;
	private CastleDoor CastleDoor;
	private TeamColor color;
	
	private Rectangle rectCastle;
	private Rectangle rectDoor;
	
	
	Castle(String type, ArrayList<Castle> tabOfCastle) {
		int nb = tabOfCastle.size();
		if(type == "Duc" || type == "Player") {
			if(type == "Player") {
                this.name = type;
                this.color = new TeamColor(220,20,60);
            }
            else {
                this.name = "Duc " + Integer.toString(nb);
                this.color = new TeamColor();
			}
			this.niveau = 1;
			this.tresor = 0;
			this.tabTroupes = Troupes.createTroupes(this.name,4,6,8); // Temporary 			
			this.Castle = new CastleStruct(type,tabOfCastle);
			this.CastleDoor = new CastleDoor(this.Castle.getCenter(),type);
		}
		
		if(type == "Baron") {
			this.name = "Baron " + Integer.toString(nb);
			this.niveau = 1 + (int) Math.random() * 5;
			this.tresor = 500 + (int) Math.random() * 1001;	// 500 - 1500			
			//this.tabTroupes = Troupes.createTroupes(1 + (int) Math.random() * 5, 1 + (int) Math.random() * 5, 1 + (int) Math.random() * 5); // temporaire
			this.Castle = new CastleStruct(type,tabOfCastle);
			this.CastleDoor = new CastleDoor(this.Castle.getCenter(),type);
			this.color = new TeamColor(128,128,128); //grey 
		}
		
		int x = this.Castle.getCornerLT().getX();
		int y = this.Castle.getCornerLT().getY();
		double w = Coordonnee.distance(this.Castle.getCornerLT(), this.Castle.getCornerRT());
		double h = Coordonnee.distance(this.Castle.getCornerLT(), this.Castle.getCornerLB());
		
		this.rectCastle = new Rectangle(x,y,w,h);
		
		int Dx = this.CastleDoor.getCornerLT().getX();
		int Dy = this.CastleDoor.getCornerLT().getY();
		double Dw = Coordonnee.distance(this.CastleDoor.getCornerLT(), this.CastleDoor.getCornerRT());
		double Dh = Coordonnee.distance(this.CastleDoor.getCornerLT(), this.CastleDoor.getCornerLB());
		
		this.rectDoor = new Rectangle(Dx,Dy,Dw,Dh);
		
		this.type = type;
		this.revenu = this.niveau * 10;
	}
	

	
	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public CastleStruct getCastle() {
		return Castle;
	}
	public void setCastle(CastleStruct castle) {
		Castle = castle;
	}
	public CastleDoor getCastleDoor() {
		return CastleDoor;
	}
	public void setCastleDoor(CastleDoor castleDoor) {
		CastleDoor = castleDoor;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public int getTresor() {
		return tresor;
	}
	public void setTresor(int d) {
		this.tresor = d;
	}
	public int getRevenu() {
		return revenu;
	}
	public void setRevenu(int revenu) {
		this.revenu = revenu;
	}
	public ArrayList<Troupes> getTabTroupes() {
		return tabTroupes;
	}
	public void setTabTroupes(ArrayList<Troupes> tabTroupes) {
		this.tabTroupes = tabTroupes;
	}
	public TeamColor getColor() {
		return color;
	}
	public void setColor(TeamColor color) {
		this.color = color;
	}
	public Rectangle getRectCastle() {
		return rectCastle;
	}
	public void setRectCastle(Rectangle rectCastle) {
		this.rectCastle = rectCastle;
	}
	public Rectangle getRectDoor() {
		return rectDoor;
	}
	public void setRectDoor(Rectangle rectDoor) {
		this.rectDoor = rectDoor;
	}
	public ArrayList<String> getTabOfProduction() {
		return tabOfProduction;
	}
	public void setTabOfProduction(ArrayList<String> tabOfProduction) {
		this.tabOfProduction = tabOfProduction;
	}
}