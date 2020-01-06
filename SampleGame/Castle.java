package SampleGame;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

/**
	 * Il s'agit de classe qui regroupe les informations nécessaires au fonctionnement des chateaux.
	 *	@param name
	 * 	le nom du château, intribué dans l'ordre de création via un tableau. 
	 * 	@see Settings
	 * @param owner
	 * 	le propriétaire du chateau, détermine si il reçoit un OST en renfort ou si elle l'attaque.
	 * @param type
	 * 	le type du chateau, Duc ou Baron. Détermine sa taille, sa trésorie, etc...
	 * @param typeOwner
	 * 	le type du propriétaire, Duc ou Baron. Détermine les actions que peut entreprendre le chateau via l'IA.
	 * 	@see IA
	 * @param level
	 * 	le niveau actuel du château. Détermine son revenue et le coup de la future amélioration de niveau. 
	 * 	@see RunACastle
	 * @param tresor
	 * 	le tresor actuel du château. Détermine ce qu'il peut produire @see ProductionLine
	 * @param revenu
	 * 	le revenu du château. Le montant de florin que le château gagne par seconde.
	 * @param tabTroupes
	 * 	c'est un tableau qui regroupe l'ensemble des troupes en réserve dans le château.
	 * @productionLine
	 * 	@see production
	 * @param castle
	 * 	la structure du chateau, ses différentes coordonnées.
	 * 	@see CastleStruct
	 * @param CastleDoor
	 * 	la structure de la porte du château, ses différentes coordonnées.
	 * 	@see CastleDoor
	 * @param color
	 * 	la couleur du château et des des troupes de ce propriétaire (joueur/IA).
	 * 	@see TeamColor
	 * @param rectCastle
	 * 	la forme géométrique du château pour affichage à l'écran. Dessiner à partir des informations du paramètre castle
	 * @param rectDoor
	 * 	la forme géométrique de la porte du château pour affichage à l'écran. Dessiner à partir des informations du paramètre CastleDoor
	 *
	 * */
	 
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
	
	/**
	 * Il s'agit du constructeur de la class Castle
	 * @param type
	 * 	le type du chateau, Duc ou Baron.
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * @param nb
	 * 	permet de nommer différent les Ducs et Barons sans faire recours un à un tableau de nom
	 * @param tabOfCastleName
	 * 	@see Settings
	 * 	permet de nommer chaque chateau via un tableau de nom
	 */
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
		
		double x = this.Castle.getCornerLT().getX();
		double y = this.Castle.getCornerLT().getY();
		double w = Coordonnee.distance(this.Castle.getCornerLT(), this.Castle.getCornerRT());
		double h = Coordonnee.distance(this.Castle.getCornerLT(), this.Castle.getCornerLB());
		
		this.rectCastle = new Rectangle(x,y,w,h);
		
		double Dx = this.CastleDoor.getCornerLT().getX();
		double Dy = this.CastleDoor.getCornerLT().getY();
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