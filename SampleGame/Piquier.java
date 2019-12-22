package SampleGame;

import java.util.ArrayList;

public class Piquier extends Troupes {
	
	Piquier(ArrayList<Castle> tabOfCastle, String CastleName) {
		this.name = "Piquier";
		this.Owner = CastleName;
		this.cout = 100;
		this.temps = 5;
		this.vitesse = 2;
		this.vie = 1;
		this.degat = 1;
		this.shape = new Triangle(tabOfCastle, CastleName);
	}

	
	// toString

	@Override
	public String toString() {
		return super.toString() + name;
	}

}
