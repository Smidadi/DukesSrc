package SampleGame;

import java.io.Serializable;

<<<<<<< HEAD
public class Piquier extends Troupes implements Serializable {
	
=======
public class Piquier extends Troupes {
	/**
	 * Constructeur de la class Piquier
	 * @see Troupes
	 * @param owner
	 * 	le nom du propriétaire de l'unité. ex : "player"
	 */
>>>>>>> 250f0e54d184f47c5b3a0b7da04154823a8e4913
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
