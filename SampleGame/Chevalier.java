package SampleGame;



public class Chevalier extends Troupes {
	
	Chevalier( String CastleName) {
		this.name = "Chevalier";
		this.Owner = CastleName;
		this.cout = 500;
		this.temps = 20;
		this.vitesse = 6;
		this.vie = 3;
		this.degat = 5;
	}

	@Override
	public String toString() {
		return super.toString() + name;
	}	
	
}
