import géométrie.Direction;

public abstract class Mulot extends Composant{
	// chute mortelle
	private static final int CHUTE_MORTELLE = 4;
	// compteur de la chute
	private int cptChute;
	// compteur identifiant
	private static int idMulot = 0;
	// identifiant du mulot
	private int id;
	// direction du mulot
	private Direction direction;
	// sens du mulot (droite ou gauche)
	private Direction sens;
	
	public Mulot (int x, int y, Direction d){
		super(x,y);
		id = idMulot++;
		direction = d;
		sens = Direction.EST;
	}
	
	public Mulot (int x, int y, Direction d,int id){
		super(x,y);
		id = id;
		direction = d;
		sens = Direction.EST;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int chuteMortelle(){
		return CHUTE_MORTELLE;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public int getCptChute(){
		return cptChute;
	}
	
	public void setCptChute(int cpt){ 
		cptChute = cpt;
	}
	
	public boolean estMort(char[][] tab){	
	//	if (getCptChute() >= chuteMortelle() && (getDirection().getDx() != 1 || getDirection().getDy() != 0)) {
			// il faut non seulement que la chute est trop haute, mais aussi qu'il soit aussi tombé sur le sol donc que la direction != SUD
		if (getCptChute() >= chuteMortelle() && (tab[this.getX()+1][this.getY()]=='-' || tab[this.getX()+1][this.getY()]=='|')){
			return true;
		}
		return false;
	}
	public void setDirection(Direction d){
		direction = d;
	}
	
	public Direction getSens(){
		return sens;
	}
	
	public void setSens(Direction s){
		sens = s;
	}
	
	public abstract Mulot déplacer(char[][] tab);
	
	public abstract boolean getAUtiliséSaCompétence();
	
	public abstract char getSymbole();
	
}
