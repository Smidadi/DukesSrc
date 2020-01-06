package SampleGame;



public class Chevalier extends Troupes {
	/**
	 * Constructeur de la class Chevalier
	 * @see Troupes
	 * @param owner
	 * 	le nom du propriétaire de l'unité. ex : "player"
	 */
	Chevalier( String owner) {
		this.name = "Chevalier";
		this.Owner = owner;
		this.vie = 3;
		this.degat = 5;
	}

	@Override
	public String toString() {
		return super.toString() + name;
	}	
	
}
