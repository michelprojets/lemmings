import g�om�trie.Direction;

public class Grimpeur extends Marcheur{
	// il ne peut grimper qu'une seule fois
	private boolean aGrimp�; 
	
	public Grimpeur(int x, int y, Direction d){
		super(x,y,d);
		aGrimp� = false;
	}
	
	public Grimpeur(int x, int y, Direction d,int id){
		super(x,y,d,id);
		aGrimp� = false;
	}
	
	public char getSymbole(){
		return 'G';
	}
	
	public Mulot d�placer(char[][] tab) {
		if (!aGrimp�){
			if (peutGrimper(getSens(), tab) && this.aGrimp� == false) {
				setDirection(Direction.NORD);
				return super.pas(getX(), getY(), Direction.NORD, tab);
			}	
			if (aFiniDeGrimper(tab)){
				this.aGrimp� = true;
				super.setAUtilis�SaComp�tence();
				return super.pas(getX(), getY(), this.getSens(), tab); // apr�s �tre redevenu un marcheur, il avance d'une case
			}
			return super.d�placer(tab);
		}
		else {
			return super.d�placer(tab);
		}
	}

	private boolean aFiniDeGrimper(char[][] tab){
		if (this.getSens()==Direction.EST){
		//	return (tab[this.getX()+1][this.getY()]==' ' && tab[this.getX()][this.getY()+1]==' ' && tab[this.getX()+1][this.getY()+1]=='|'); // rajouter 'G'...	
			if (getY() != tab[0].length-1){ // pour ne pas provoquer une exception outofbound
				return ((tab[this.getX()+1][this.getY()]!='-' && tab[this.getX()+1][this.getY()]!='|' && tab[this.getX()][this.getY()+1]!='|' &&
						tab[this.getX()+1][this.getY()+1]=='|') || getX()==0);
			}
		}
		if (this.getSens()==Direction.OUEST){ 
		//	return (tab[this.getX()+1][this.getY()]==' ' && tab[this.getX()][this.getY()-1]==' ' && tab[this.getX()+1][this.getY()-1]=='|');
			if (getY() != 0){ // pour ne pas provoquer une exception outofbound
				return ((tab[this.getX()+1][this.getY()]!='-' && tab[this.getX()+1][this.getY()]!='|' && tab[this.getX()][this.getY()-1]!='|' &&
						tab[this.getX()+1][this.getY()-1]=='|') || getX()==0);
			}
		}
		return false;
	}
	
	private boolean peutGrimper(Direction d, char[][] tab) {
		if (d.getDx() == 0 && d.getDy() == 1) { // d.equals(Direction.EST) ?
			if (getY() == tab[0].length-1 || getX()==0) // pour ne pas provoquer une exception outofbound
				return false;
			if (tab[getX()][getY() + 1] == '|')
				return true;
		}
		if (d.getDx() == 0 && d.getDy() == -1 ) { // d.equals(Direction.OUEST) ?
			if (getY() == 0 || getX()==0) // pour ne pas provoquer une exception outofbound
				return false;
			if (tab[getX()][getY() - 1] == '|')
				return true;
		}
		return false;
	}
	
}
