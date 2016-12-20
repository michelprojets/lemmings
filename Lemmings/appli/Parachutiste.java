import g�om�trie.Direction;

public class Parachutiste extends Marcheur{
	private boolean aSaut�;
	
	public Parachutiste(int x, int y, Direction d){
		super(x,y,d);
		aSaut� = false;
	}
	
	public Parachutiste(int x, int y, Direction d,int id){
		super(x,y,d,id);
		aSaut� = false;
	}
	
	public char getSymbole(){
		return 'P';
	}
	
	public Mulot d�placer(char[][] tab){ 
		if (!aSaut�){
			if (super.peutChuter(tab)) {
				this.setCptChute(-1); // le parachutiste a son compteur de chute � 0 � chaque fois
				setDirection(Direction.SUD);
				return super.pas(getX(), getY(), Direction.SUD, tab);
			}
			if (aFiniDeSauter(tab)){
				this.aSaut� = true;
				super.setAUtilis�SaComp�tence();
				return super.pas(getX(), getY(), this.getSens(), tab); // apr�s �tre redevenu un marcheur, il avance d'une case
			}
			return super.d�placer(tab);
		}
		else {
			return super.d�placer(tab);
		}
	}
	
	private boolean aFiniDeSauter(char[][] tab){
		if (getCptChute() != 0) { // il faut qu'il ait d�j� tomb� au moins d'une case
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
