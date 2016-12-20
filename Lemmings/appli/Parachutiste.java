import géométrie.Direction;

public class Parachutiste extends Marcheur{
	private boolean aSauté;
	
	public Parachutiste(int x, int y, Direction d){
		super(x,y,d);
		aSauté = false;
	}
	
	public Parachutiste(int x, int y, Direction d,int id){
		super(x,y,d,id);
		aSauté = false;
	}
	
	public char getSymbole(){
		return 'P';
	}
	
	public Mulot déplacer(char[][] tab){ 
		if (!aSauté){
			if (super.peutChuter(tab)) {
				this.setCptChute(-1); // le parachutiste a son compteur de chute à 0 à chaque fois
				setDirection(Direction.SUD);
				return super.pas(getX(), getY(), Direction.SUD, tab);
			}
			if (aFiniDeSauter(tab)){
				this.aSauté = true;
				super.setAUtiliséSaCompétence();
				return super.pas(getX(), getY(), this.getSens(), tab); // après être redevenu un marcheur, il avance d'une case
			}
			return super.déplacer(tab);
		}
		else {
			return super.déplacer(tab);
		}
	}
	
	private boolean aFiniDeSauter(char[][] tab){
		if (getCptChute() != 0) { // il faut qu'il ait déjà tombé au moins d'une case
			if (this.getSens()==Direction.EST){
				return ((tab[this.getX()+1][this.getY()]=='-' || tab[this.getX()+1][this.getY()]=='|') && 
						(tab[this.getX()][this.getY()-1]=='|'));
				}
			}
			if (this.getSens()==Direction.OUEST){
				if (getY() != tab[0].length-1){
					return ((tab[this.getX()+1][this.getY()]=='-' || tab[this.getX()+1][this.getY()]=='|') && 
							(tab[this.getX()][this.getY()+1]=='|'));
				}
			}
		return false;
	}
	
}
