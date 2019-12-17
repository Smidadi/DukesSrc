package SampleGame;


public class TeamColor {
	public int r;
	public int g;
	public int b;
	
	TeamColor(int r, int g, int b){
		this.r = r;
		this.g = g;
		this.b = b;
	};
	
	TeamColor(){
		this.r = (int) (Math.random() * 255);
		this.g = (int) (Math.random() * 255);
		this.b = (int) (Math.random() * 255);
	}
}
