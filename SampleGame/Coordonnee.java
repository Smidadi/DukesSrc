package SampleGame;


/*import java.awt.Dimension;

Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
int height = (int)dimension.getHeight();
int width  = (int)dimension.getWidth();*/

public class Coordonnee {
	private int x, y;
	

	

	public Coordonnee() {
		this.x = (int) (Settings.DOORSIZE + Settings.DUCSIZE + Math.random()* (Settings.SCENE_WIDTH - 2* (Settings.DOORSIZE + Settings.DUCSIZE)));
		this.y = (int) (Settings.DOORSIZE + Settings.DUCSIZE + Math.random()* (Settings.SCENE_HEIGHT - 2* (Settings.DOORSIZE + Settings.DUCSIZE)));		
	}

	public Coordonnee(int dx, int dy) {
		this.x = dx;
		this.y = dy;
	}
	
	public static double distance(Coordonnee castle, Coordonnee newCastle){
        double d1 = castle.x - newCastle.x;
        double d2 = castle.y - newCastle.y;
        return Math.sqrt(d1*d1 + d2*d2);
   }
	
	public static int distance(int x1, int x2) {
		return (int) Math.sqrt((x1-x2)*(x1-x2));
	}

	@Override
	public String toString() {
		return "Coordonnee [x=" + x + ", y=" + y + "]";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
	
}
