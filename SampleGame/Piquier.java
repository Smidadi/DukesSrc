package SampleGame;


public class Piquier extends Troupes {
	/**
	 * Constructeur de la class Piquier
	 * @see Troupes
	 * @param owner
	 * 	le nom du propriétaire de l'unité. ex : "player"
	 */
	Piquier(String owner) {
		this.name = "Piquier";
		this.Owner = owner;
		this.vie = 1;
		this.degat = 1;
	}

	
	// toString

	@Override
	public String toString() {
		return super.toString() + name;
	}

}
