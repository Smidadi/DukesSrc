package SampleGame;

import java.io.Serializable;

<<<<<<< HEAD
public class Chevalier extends Troupes implements Serializable {
	
=======

public class Chevalier extends Troupes {
	/**
	 * Constructeur de la class Chevalier
	 * @see Troupes
	 * @param owner
	 * 	le nom du propriétaire de l'unité. ex : "player"
	 */
>>>>>>> 250f0e54d184f47c5b3a0b7da04154823a8e4913
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
