package SampleGame;

import java.util.ArrayList;

public class Onagre extends Troupes {

	Onagre(ArrayList<Castle> tabOfCastle, String CastleName) {
		this.name = "Onagre";
		this.Owner = CastleName;
		this.cout = 1000;
		this.temps = 50;
		this.vitesse = 1;
		this.vie = 5;
		this.degat = 10;
		this.shape = new Rectangle(tabOfCastle, CastleName);
	}
	

	@Override
	public String toString() {
		return super.toString() + name;
	}	
	
}
