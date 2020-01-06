package SampleGame;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class Castle implements Serializable  {
	private String name;
	private String owner;
	private String type;
	private String typeOwner;
	
	private int level;
	
	private int tresor;	
	private int revenu;
	
	private ArrayList<Troupes> tabTroupes = new ArrayList<Troupes>();
	
	private Production productionLine;
	
	private CastleStruct Castle;
	private CastleDoor CastleDoor;
	private TeamColor color;
	
	transient private Rectangle rectCastle;
	transient private Rectangle rectDoor;
	
	
	Castle(String type, ArrayList<Castle> tabOfCastle, int nb, String tabOfCastleName[]) {
		if(type == "Duc" || type == "Player") {
			if(type == "Player") {
                this.name = tabOfCastleName[tabOfCastle.size()];
                this.owner = type;
                this.color = new TeamColor(220,20,60);
                this.tabTroupes = Troupes.createTroupes(this.name,2,2,2); // Temporary
            }
            else {
                this.name = tabOfCastleName[tabOfCastle.size()];
                this.owner = "Duc " + Integer.toString(nb);
                this.color = new TeamColor();
                this.tabTroupes = Troupes.createTroupes(this.name,2,2,2); // Temporary
			}
			this.level = 1;
			this.tresor = 0;
			this.revenu = this.level * 10;
			this.Castle = new CastleStruct(type,tabOfCastle);
			this.CastleDoor = new CastleDoor(this.Castle.getCenter(),type);
		}
		
		if(type == "Baron") {
			this.name = tabOfCastleName[tabOfCastle.size()];
			this.owner = "Baron " + Integer.toString(nb);
			this.level = 1 + (int) Math.random() * 5;
			this.tresor = 500 + (int) Math.random() * 1001;	// 500 - 1500			
			this.tabTroupes = Troupes.createTroupes(this.name,(int) (Math.random() * 6),(int) (Math.random() * 6),(int) (Math.random() * 6)); // temporaire
			this.Castle = new CastleStruct(type,tabOfCastle);
			this.CastleDoor = new CastleDoor(this.Castle.getCenter(),type);
			this.color = new TeamColor(128,128,128); //grey 
			this.revenu = this.level;
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
		
		this.productionLine = new Production(this.level);
		this.type = type;
		this.typeOwner = type;
		
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Production getProductionLine() {
		return productionLine;
	}
	public void setProductionLine(Production productionLine) {
		this.productionLine = productionLine;
	}
	public String getTypeOwner() {
		return typeOwner;
	}
	public void setTypeOwner(String typeOwner) {
		this.typeOwner = typeOwner;
	}	
}