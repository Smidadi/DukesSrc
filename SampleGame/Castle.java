package SampleGame;

import java.util.ArrayList;

public class Castle {
	
	private String name;
	private String type;
	private int niveau;
	private int tresor;	
	private int revenu;
	// private int production;  type : troops/amelioration + nb tours restant 
	// ORdre de déplacement = target + troops 
	private ArrayList<Troupes> tabTroupes;
	private CastleStruct Castle;
	private CastleDoor CastleDoor;
	private TeamColor color;
	
	
	
	Castle(String type, ArrayList<Castle> tabOfCastle) {
		int nb = tabOfCastle.size();
		if(type == "Duc" || type == "Player") {
			if(type == "Player") {
				this.name = type;
			}
			else {
				this.name = "Duc " + Integer.toString(nb);
			}
			this.niveau = 1;
			this.tresor = 0;
			this.tabTroupes = Troupes.createTroupes(4,2,2); // Temporary 			
			this.Castle = new CastleStruct(type,tabOfCastle);
			this.CastleDoor = new CastleDoor(this.Castle.getCenter(),type);
			this.color = new TeamColor();
		}
		if(type == "Baron") {
			this.name = "Baron " + Integer.toString(nb);
			this.niveau = 1 + (int) Math.random() * 5;
			this.tresor = 500 + (int) Math.random() * 1001;	// 500 - 1500			
			this.tabTroupes = Troupes.createTroupes(1 + (int) Math.random() * 5, 1 + (int) Math.random() * 5, 1 + (int) Math.random() * 5); // temporaire
			this.Castle = new CastleStruct(type,tabOfCastle);
			this.CastleDoor = new CastleDoor(this.Castle.getCenter(),type);
			this.color = new TeamColor(128,128,128); //grey 
		}
		
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
	public void setTresor(int tresor) {
		this.tresor = tresor;
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
}