package SampleGame;

import java.io.Serializable;

public class Chevalier extends Troupes implements Serializable {
	
	Chevalier( String owner) {
		this.name = "Chevalier";
		this.Owner = owner;
		this.vitesse = 1;
		this.vie = 3;
		this.degat = 5;
	}

	@Override
	public String toString() {
		return super.toString() + name;
	}	
	
}
