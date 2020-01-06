package SampleGame;



public class Chevalier extends Troupes {
	
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
