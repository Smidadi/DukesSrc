package SampleGame;

import java.util.ArrayList;

public class Chevalier extends Troupes {
	
	Chevalier(ArrayList<Castle> tabOfCastle, String CastleName) {
		this.name = "Chevalier";
		this.Owner = CastleName;
		this.cout = 500;
		this.temps = 20;
		this.vitesse = 6;
		this.vie = 3;
		this.degat = 5;
		this.shape = new Circle(tabOfCastle, CastleName);
	}

	@Override
	public String toString() {
		return super.toString() + name;
	}	
	
}
