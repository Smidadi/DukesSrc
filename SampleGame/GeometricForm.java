package SampleGame;

import java.util.ArrayList;


public abstract class GeometricForm {
	
	static ArrayList<GeometricForm> tabOfGeometricForm( OST ost, ArrayList<Castle> tabOfCastle) {
		ArrayList<GeometricForm> tabOfGeometricForm = new ArrayList<GeometricForm>();
		for(int i=0; i<tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getName() == ost.getOwner()) {
				int pos = 1;
				for(int k=0; k<ost.getOstUnites().size(); k++) {
					switch (ost.getOstUnites().get(k).getName()) {
						case "Onagre":
							tabOfGeometricForm.add(new RectangleForm(tabOfCastle.get(i).getCastleDoor(), ost.getTeamColor(), pos));
							break;
						case "Piquier":
							tabOfGeometricForm.add(new TriangleForm(tabOfCastle.get(i).getCastleDoor(), ost.getTeamColor(), pos));
							break;
						case "Chevalier":
							tabOfGeometricForm.add(new CircleForm(tabOfCastle.get(i).getCastleDoor(), ost.getTeamColor(), pos));
							break;
						default: 
							break;
					}
					if(pos == 3) {
						pos =1;
					}else {
						pos++;
					}
				}
			}
		}
		System.out.println("fin creation" + tabOfGeometricForm.size());
		return tabOfGeometricForm;
	}
	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	public abstract TeamColor getColor();
	public abstract String getType();
	
	//rectangle
	public abstract int getWidth();
	public abstract int getHeight();
	
	//circle
	public abstract int getX();
	public abstract int getY();
	public abstract int getRadius();
	
	//Triangle
	public abstract Coordonnee getS1();
	public abstract Coordonnee getS2();
	public abstract Coordonnee getS3();

}
