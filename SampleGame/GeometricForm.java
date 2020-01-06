package SampleGame;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class GeometricForm implements Serializable {
	
	/**
	 * Class mére de CircleForm, RectangleForm, TriangleForm
	 */
	
	
	/**
	 * Créer un tableau de GeometricForm lors de la création d'une OST.
	 * @see	CircleForm
	 * @see	RectangleForm
	 * @see	TriangleForm
	 * @param ost
	 * 	l'OST pour lequel on crée un tableau de GeometricForm
	 * @param tabOfCastle
	 * 	l'ArrayList qui regroupe l'ensemble des châteaux du jeu.
	 * 	Sert à trouver la position de la porte du château qui souhaite émettre un OST
	 * @return	tableau de GeometricForm
	 */
	static ArrayList<GeometricForm> tabOfGeometricForm( OST ost, ArrayList<Castle> tabOfCastle) {
		ArrayList<GeometricForm> tabOfGeometricForm = new ArrayList<GeometricForm>();
		for(int i=0; i<tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getName() == ost.getOwner().getName()) {
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
						pos = 1;
					}else {
						pos++;
					}
				}
			}
		}
		return tabOfGeometricForm;
	}
	
	/**
	 * Créer un tableau de GeometricForm lors de la sauvegarde du jeu en cours
	 * @param ost
	 * 	l'OST dont il faut sauvegarder la position de chacune des unités en déplacement
	 * @return	un tableau de GeometricForm
	 */
	static ArrayList<GeometricForm> newTabOfGeometricForm( OST ost) {
		ArrayList<GeometricForm> tabOfGeometricForm = new ArrayList<GeometricForm>();
		for(int i=0; i<ost.getRectangle().size(); i++) {
			tabOfGeometricForm.add(new RectangleForm(ost.getRectangle().get(i), ost.getTeamColor()));
		}
		for(int i=0; i<ost.getCircle().size(); i++) {
			tabOfGeometricForm.add(new CircleForm(ost.getCircle().get(i), ost.getTeamColor()));
		}
		for(int i=0; i<ost.getPolygon().size(); i++) {
			tabOfGeometricForm.add(new TriangleForm(ost.getPolygon().get(i), ost.getTeamColor()));
		}
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
