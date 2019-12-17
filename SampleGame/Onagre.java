package SampleGame;

public class Onagre extends Troupes {

	Onagre() {
		this.name = "Onagre";
		this.cout = 1000;
		this.temps = 50;
		this.vitesse = 1;
		this.vie = 5;
		this.degat = 10;
	}
	

	@Override
	public String toString() {
		return super.toString() + name;
	}	
	
}
