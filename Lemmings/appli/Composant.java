
public abstract class Composant {

	private int x, y;
	
	public Composant(int x, int y) {
	  placerA(x, y);
	}
	
	public int getX() {
	  return x;
	}
	
	public int getY() {
	  return y;
	}
	
	public void placerA(int x, int y) {
	  this.x = x;
	  this.y = y;
	}
	
	public void dessiner(char[][] tab) {
	  tab[x][y] = getSymbole();
	}
	
	public abstract char getSymbole();
}
