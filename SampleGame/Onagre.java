package SampleGame;


public class Onagre extends Troupes {

	Onagre( String owner) {
		this.name = "Onagre";
		this.Owner = owner;
		this.vitesse = 4;
		this.vie = 5;
		this.degat = 10;
	}
	

	@Override
	public String toString() {
		return super.toString() + name;
	}	
	
}
