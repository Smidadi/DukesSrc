package SampleGame;

import java.io.Serializable;


public class TeamColor implements Serializable {
	public int r;
	public int g;
	public int b;
	
	/**
	 * Constructeur de la class TeamColor
	 * @param r
	 * @param g
	 * @param b
	 */
	TeamColor(int r, int g, int b){
		this.r = r;
		this.g = g;
		this.b = b;
	};
	
	/**
	 * Constructeur de la class TeamColor
	 * génére des valeurs aléatoires pour r,g,b
	 */
	TeamColor(){
		this.r = (int) (Math.random() * 255);
		this.g = (int) (Math.random() * 255);
		this.b = (int) (Math.random() * 255);
	}
}
