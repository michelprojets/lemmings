import java.util.ArrayList;
import java.util.Arrays;

import géométrie.Direction;

/**
 * Classe représentant un niveau de jeu.
 * 
 * Attention, cette classe est intentionnellement mal conçue. En particulier, 
 * elle met en oeuvre des données redondantes.  
 */
public class Niveau {
	// nombre de lignes et de colonnes du niveau
	private int nbc, nbl;
	// ce qui doit être affiché
	private char[][] tab;
	// l'entrée et de la sortie 
	private Entrée e;
	private Sortie s;
	// liste des mulots 
	private ArrayList<Mulot> mulots;
	// liste des obstacles 
	private ArrayList<Obstacle> obstacles;
	// nombre de mulots qui apparaîssent
	private static final int NB_MULOTS_APPARAIT = 2;
	// nombre de mulots à faire sortir
	private int nbMulots;
	// nombre de mulots morts
	private int nbMorts;
	// nombre de transformation en grimpeur possible
	private int nbTransformationGMax;
	// nombre de transformation en parachustite possible
	private int nbTransformationPMax;
	
	private char get(int x, int y) {
		return tab[x][y];
	}
	

	private void set(int x, int y, char c) {
		tab[x][y] = c;
	}	
	
	public int getNbMulots(){
		return nbMulots;
	}

	public int getNbMulotsApparait() {
		return NB_MULOTS_APPARAIT;
	}
	
	public int getNbTransformationGMax(){
		return this.nbTransformationGMax;
	}
	
	public int getNbTransformationPMax(){
		return this.nbTransformationPMax;
	}
	
	// pour NiveauTest
	public char getSymboleAt(int x, int y){
		return tab[x][y];
	}
	
	/**
	 * Construit un niveau vide.
	 * @param nbc et nbl Nombre de lignes et de colonnes
	 * @param ex et ey Coordonnée de l'entrée
	 * @param sx et sy Coordonnée de la sortie
	 */
	public Niveau(int nbl, int nbc, int ex, int ey, int sx, int sy, int nbM, int nbG, int nbP) {
		mulots = new ArrayList<Mulot>();
		obstacles = new ArrayList<Obstacle>();
		this.nbc = nbc;
		this.nbl = nbl;
		nbMulots = nbM;
		nbMorts = 0;
		this.nbTransformationGMax = nbG;
		this.nbTransformationPMax = nbP;
		e = new Entrée(ex, ey);
		s = new Sortie(sx, sy);
		tab = new char[nbl][nbc];
		for (int i = 0; i<nbl ; ++i){
			Arrays.fill(tab[i], ' ');
		}
	}
	
	/**
	 * Ajoute une tour de blocs au niveau.
	 * @param gx et gy Coordonnée du point le plus bas de la tour
	 * @param hauteur est la hauteur de la tour
	 */
	public void creerTourMurs(int gx, int gy, int hauteur) {
		for (int i=gx; i>gx-hauteur; --i){
			créerMurAt(i,gy);
		}
	}
	
	public void créerMurAt(int gx, int gy){
		obstacles.add(new Mur(gx, gy));
	}
	
	/**
	 * Ajoute une plateforme au niveau.
	 * @param gx et gy Coordonnée du point gauche de la palteforme
	 * @param nb Longueur de la plateforme
	 */
	public void plateforme(int gx, int gy, int nb) { 
		for (int i=gy; i<gy+nb; ++i){
			obstacles.add(new Sol(gx,i));
		}
	}
	
	/**
	 * Affiche l'état courant du niveau.
	 */
	public void afficher() {
		e.dessiner(tab);
		s.dessiner(tab);
		for (Mulot m : mulots){
			if (m != null){
				m.dessiner(tab); // on place les mulots dans le tableau avant d'afficher le tout
			}
		}
		for (Obstacle ob : obstacles){
			ob.dessiner(tab); // on place les obstacles dans le tableau avant d'afficher le tout
		}
		for (char[] t : tab){
			for (char c : t){
				System.out.print(c);
			}
			System.out.println();
		}
	}

	/**
	 * Ajoute un marcheur au niveau.
	 */
	public void naissance() {
		// la case en dessous de l'entrée doit être libre
		if (get(e.getX() + 1, e.getY()) == '-' || get(e.getX() + 1, e.getY()) == '|')
			return;
		// un mulot est représenté par une entrée dans la liste
		// la référence est mise à null lorsqu'il sort du niveau
		mulots.add(new Marcheur(e.getX(), e.getY(), Direction.EST));
		set(e.getX(), e.getY(), mulots.get(0).getSymbole());
	}
	
	/**
	 * Transforme un marcheur en un grimpeur
	 */
	public void toGrimpeur(int id){
		System.out.println(getIndiceMulot(id));
		Direction sens = mulots.get(getIndiceMulot(id)).getSens();
		/*
		mulots.set(getIndiceMulot(id), new Grimpeur(mulots.get(getIndiceMulot(id)).getX(),
				mulots.get(getIndiceMulot(id)).getY(), mulots.get(getIndiceMulot(id)).getDirection()));
		*/
		mulots.add(new Grimpeur(mulots.get(getIndiceMulot(id)).getX(),
				mulots.get(getIndiceMulot(id)).getY(), mulots.get(getIndiceMulot(id)).getDirection(),getIndiceMulot(id)));
		mulots.remove(getIndiceMulot(id));
		mulots.get(mulots.size() - 1).setSens(sens);
		mulots.get(mulots.size() - 1).setId(id);
		
	}
	
	
	/**
	 * Transforme un marcheur en un parachutiste
	 */
	public void toParachutiste(int id){
		System.out.println(getIndiceMulot(id));
		Direction sens = mulots.get(getIndiceMulot(id)).getSens();
		/*
		mulots.set(getIndiceMulot(id), new Parachutiste(mulots.get(getIndiceMulot(id)).getX(),
				mulots.get(getIndiceMulot(id)).getY(), mulots.get(getIndiceMulot(id)).getDirection()));
		*/
		mulots.add(new Parachutiste(mulots.get(getIndiceMulot(id)).getX(),
				mulots.get(getIndiceMulot(id)).getY(), mulots.get(getIndiceMulot(id)).getDirection(),getIndiceMulot(id)));
		mulots.remove(getIndiceMulot(id));
		mulots.get(mulots.size() - 1).setSens(sens);
		mulots.get(mulots.size() - 1).setId(id);
	}
	
	/**
	 * Transforme un mulot quelconque en marcheur
	 */
	public void toMarcheur(int id){
		Direction sens = mulots.get(getIndiceMulot(id)).getSens();
		/*
		mulots.set(getIndiceMulot(id), new Marcheur(mulots.get(getIndiceMulot(id)).getX(),
				mulots.get(getIndiceMulot(id)).getY(), mulots.get(getIndiceMulot(id)).getDirection()));
		*/
		mulots.add(new Marcheur(mulots.get(getIndiceMulot(id)).getX(),
				mulots.get(getIndiceMulot(id)).getY(), mulots.get(getIndiceMulot(id)).getDirection(),getIndiceMulot(id)));
		mulots.remove(getIndiceMulot(id));
		mulots.get(mulots.size() - 1).setSens(sens);
		mulots.get(mulots.size() - 1).setId(id);
	}
	
	/**
	 * Fait avancer chaque mulot encore présent.
	 */
	public void tic() {
		for (int i = 0; i<mulots.size(); ++i) {
			//	System.out.println("Compteur de chute : " + mulots.get(i).getCptChute());
			if (mulots.get(i).estMort(tab)){
				++nbMorts;
				set(mulots.get(i).getX(), mulots.get(i).getY(), ' ');
			//	mulots.set(getIndiceMulot(m.getId()), null);
				mulots.remove(i);	
			}
			else {
				mulots.set(getIndiceMulot(mulots.get(i).getId()), mulots.get(i).déplacer(tab));
				if (mulots.get(i).getAUtiliséSaCompétence()){
					toMarcheur(mulots.get(i).getId());
				}
				// il est sorti ?
				if (mulots.get(i).getX()==s.getX() && mulots.get(i).getY()==s.getY()) {
					set(mulots.get(i).getX(), mulots.get(i).getY(), s.getSymbole()); 
					mulots.remove(i);
				}
			}
		}
	//	}
	}
	
	
	private int getIndiceMulot(int id){
		int cpt = 0;
		for(cpt = 0; cpt>mulots.size(); ++cpt){
				if (mulots.get(cpt).getId() == id)
					break;
		}
		return cpt;
	}
	
	/**
	 * Indique si le niveau est fini (i.e. tous les mulots sont sortis).
	 */
	public boolean estFini() {
		return (nbMorts == nbMulots || mulots.size()==0);
	}
	
}
