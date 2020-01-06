package SampleGame;


public class Onagre extends Troupes {
	/**
	 * Constructeur de la class Onagre
	 * @see Troupes
	 * @param owner
	 * 	le nom du propriétaire de l'unité. ex : "player"
	 */
	Onagre( String owner) {
		this.name = "Onagre";
		this.Owner = owner;
		this.vie = 5;
		this.degat = 10;
	}
	

	@Override
	public String toString() {
		return super.toString() + name;
	}	
	
}
