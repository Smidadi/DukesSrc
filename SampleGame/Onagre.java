package SampleGame;

import java.io.Serializable;

<<<<<<< HEAD
public class Onagre extends Troupes implements Serializable {

=======
public class Onagre extends Troupes {
	/**
	 * Constructeur de la class Onagre
	 * @see Troupes
	 * @param owner
	 * 	le nom du propriétaire de l'unité. ex : "player"
	 */
>>>>>>> 250f0e54d184f47c5b3a0b7da04154823a8e4913
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
