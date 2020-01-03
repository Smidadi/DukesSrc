package SampleGame;

public class Settings {
	static final String tabOfCastleName[] = {"Versailles","Castelnaud","Chambord","Chenonceau","Villandry",
			"Cheverny","Vaux-le-Vicomte","Fontainebleau","Cathare","Ussé",
			"Amboise","Valençay","Chantilly","Bonaguil","Pierrefonds",
			"Citadelles de l'oubli","Eguisheim ","Carcassonne","Peyrepertuse","Montségur"};
	
	public static final double SCENE_WIDTH = 1500;
    public static final double SCENE_HEIGHT = 1000;
    
    public static final int DUCSIZE = (int) (SCENE_HEIGHT *  0.05);
    public static final int BARONSIZE = (int) (SCENE_HEIGHT *  0.04);
    public static final int DOORSIZE = (int) (SCENE_HEIGHT *  0.03);
    
    static final int INFORMATIONSCASTLEXY = 10;
    static final int INFORMATIONSWIDTH = 225;
    static final int INFORMATIONSHEIGHT = 150;
    static final int EXTENDINFORMATIONHEIGHT = 180;
    static final int MAXHEIGHT = 250;
}
