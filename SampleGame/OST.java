package SampleGame;

import java.util.ArrayList;

public class OST {
	private int ostUnites[];
	private int MaxSpeed;
	private String TargetName;
	
	OST(){
		this.ostUnites = new int[3];
		this.MaxSpeed = 0;
	}
	
	static void addInOST(int tab[]) {
		
	}


	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	public int getMaxSpeed() {
		return MaxSpeed;
	}
	public int[] getOstUnites() {
		return ostUnites;
	}

	public void setOstUnites(int[] ostUnites) {
		this.ostUnites = ostUnites;
	}

	public void setMaxSpeed(int maxSpeed) {
		MaxSpeed = maxSpeed;
	}
	public String getTargetName() {
		return TargetName;
	}
	public void setTargetName(String targetName) {
		TargetName = targetName;
	}
}
