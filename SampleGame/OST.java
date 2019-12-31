package SampleGame;

import java.util.ArrayList;

public class OST {
	private ArrayList<Troupes> ostUnites;
	private int MaxSpeed;
	private String TargetName;
	private TeamColor TeamColor;
	private String owner;
	
	
	OST(String TargetName, int tab[], String owner, ArrayList<Castle> tabOfCastle) {
		for(int i=0; i <tabOfCastle.size(); i++) {
			if(tabOfCastle.get(i).getName() == owner) {
				this.TeamColor = tabOfCastle.get(i).getColor();
			}
		}
		this.owner = owner;
		this.ostUnites = new  ArrayList<Troupes>();
		this.TargetName = TargetName;
		this.MaxSpeed = 6;
		for(int i = 0; i < 3; i++) {
			if(tab[i]!= 0) {
				switch (i) {
					case 0:
						this.MaxSpeed = 1;
						while(tab[i] > 0) {
							this.ostUnites.add(new Onagre(owner));
							tab[i]--;
						}
						break;
					case 1:
						if(this.MaxSpeed == 6) {
							this.MaxSpeed = 2 ;
						}
						while(tab[i] > 0) {
							this.ostUnites.add(new Piquier(owner));
							tab[i]--;
						}
						break;
					case 2:
						while(tab[i] > 0) {
							this.ostUnites.add(new Chevalier(owner));
							tab[i]--;
						}
						break;
					default: 
						break;
				}
			}
		}
	}


	
	/* ----- GETTER ----- */
	/* ----- SETTER ----- */
	
	public int getMaxSpeed() {
		return MaxSpeed;
	}
	public ArrayList<Troupes> getOstUnites() {
		return ostUnites;
	}

	public void setOstUnites(ArrayList<Troupes> ostUnites) {
		this.ostUnites = ostUnites;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.MaxSpeed = maxSpeed;
	}
	public String getTargetName() {
		return TargetName;
	}
	public void setTargetName(String targetName) {
		this.TargetName = targetName;
	}
	public TeamColor getTeamColor() {
		return TeamColor;
	}
	public void setTeamColor(TeamColor TeamColor) {
		this.TeamColor = TeamColor;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return owner;
	}
}
