import g�om�trie.Direction;

public class Marcheur extends Mulot{
	private boolean aUtilis�SaComp�tence;
	
	public Marcheur(int x, int y, Direction d){
		super(x, y, d);
		aUtilis�SaComp�tence = false;
	}
	
	public Marcheur(int x, int y, Direction d,int id){
		super(x, y, d,id);
		aUtilis�SaComp�tence = false;
	}
	
	public boolean getAUtilis�SaComp�tence(){
		return this.aUtilis�SaComp�tence;
	}
	
	public void setAUtilis�SaComp�tence(){
		aUtilis�SaComp�tence = true;
	}
	
	public char getSymbole(){
		return 'M';
	}
	
	
	public Mulot d�placer(char[][] tab){ // on retourne le mulot pour qu'on puisse l'int�grer � la liste
		if (!peutChuter(tab)){
		//	setDirection(Direction.EST); // si sa direction �tait EST, c'est bon, mais si OUEST, il va foncer dans le mur (instantan�ment) donc inverse la direction
			if (!peutAvancer(getSens(), tab)) {
				setSens(getSens().inverser()); // changement de sens
			}
			setDirection(getSens()); // MaJ de la direction
		//	if (!estMort()){
				super.setCptChute(0); // on le remet � 0 s'll ne tombe plus
		//	}
			return pas(getX(), getY(), getDirection(), tab); // d�placement suivant la direction 
		}
		else {
			super.setCptChute(getCptChute() + 1); // chaque chute successives, le compteur augmente
			setDirection(Direction.SUD);
			return pas(getX(), getY(), getDirection(), tab); // s'il peut chuter, il chute
		}
	}
	
	public boolean peutAvancer(Direction d, char[][] tab) {
		if (d.getDx() == 0 && d.getDy() == 1) { // d.equals(Direction.EST) ?
			if (getY() + 1 < tab[0].length && (tab[getX()][getY() + 1] != '|' || tab[getX()][getY() + 1] == 'S'))
				//	&& (tab[getX() + 1][getY() + 1] == '-' || tab[getX() + 1][getY() + 1] == '|')) // pas besoin sinon il ne tombe pas
				return true;
		}
		if (d.getDx() == 0 && d.getDy() == -1) { // d.equals(Direction.OUEST) ?
			if (getY() - 1 >= 0 && (tab[getX()][getY() - 1] != '|' || tab[getX()][getY() - 1] == 'S'))
				//	&& (tab[getX() + 1][getY() - 1] == '-' || tab[getX() + 1][getY() - 1] == '|')) // pas besoin sinon il ne tombe pas
				return true;
		}
		return false;
	}
	
	public boolean peutChuter(char[][] tab) {
		if (getX() + 1 < tab.length && (tab[getX() + 1][getY()] == ' ' || tab[getX() + 1][getY()] == 'S' || tab[getX() + 1][getY()] == 'M'))
			return true;
		return false;
	}
	
	public Mulot pas(int x, int y, Direction d, char[][] tab) {
	//	Mulot m = iniMulot(x + d.getDx(), y + d.getDy(), d); // on utilise une m�thode commune aux diff�rentes professions
	//	m.setSens(this.getSens());
	//	m.setId(getId());
		this.placerA(x + d.getDx(), y + d.getDy());
		char symbole = tab[x][y];
		tab[x][y] = ' ';; // on efface le mulot
		tab[this.getX()][this.getY()] = symbole; // le revoila juste a cote
		return this;
	}
	
}
