package SampleGame;

import java.util.ArrayList;

public class OST {
	private ArrayList<Troupes> ostUnites;
	private int MaxSpeed;
	private String TargetName;
	
	OST(){
		this.ostUnites = new ArrayList<Troupes>();
		this.MaxSpeed = 0;
	}
	
	void addInOST(Troupes Troops) {
		
	}


	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	public ArrayList<Troupes> getOstUnites() {
		return ostUnites;
	}
	public void setOstUnites(ArrayList<Troupes> ostUnites) {
		this.ostUnites = ostUnites;
	}
	public int getMaxSpeed() {
		return MaxSpeed;
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
