

public abstract class Obstacle extends Composant{
	
	public Obstacle(int x, int y){
		super(x,y);
	}
	
	public boolean estVisible(){
		return true;
	}
	
}
