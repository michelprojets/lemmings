import java.util.Scanner;

import static outils.Outils.*;

/**
 * Application illustrant la lecture non bloquante de données au clavier.
 * De plus, ce programme illustre ce à quoi pourrait ressembler l'interface textuelle
 * du projet.
 */
public class AppliTexte {
	/** Plus VITESSE est grand, plus les mulots se déplacent rapidement */
	private static final double VITESSE = 5.;
	private static int nbTransformationG;
	private static int nbTransformationP;
	public static void main(String[] args)  {
		// un niveau de 40 colonnes et 20 lignes avec l'entrée en 3,1 et la sortie en 38,17
		Niveau niv1 = new Niveau(20, 80, 15, 0, 18, 79, 2, 2, 2);
		for (int i=32; i<40; ++i){
			niv1.creerTourMurs(18, i, 7);
		//	niv1.creerTourMurs(18, i, 19); // bloc qui monte jusqu'au plafond (test grimpeur)
		}
		niv1.plateforme(19, 0, 80);
		niv1.créerMurAt(18, 55); // faire un mur plus loin
		
		// une plateforme en 2,18 de largeur 37
		niv1.afficher();

		// naissance de mulot
	//	for (int i = 0; i < niv1.getNbMulotsApparait(); ++i){ // changement en fonction du nombre de tics
			niv1.naissance(); 
			
			niv1.afficher();
			pause(1/VITESSE);
			niv1.tic(); 
			niv1.afficher();

		// boucle de jeu
		boolean escape = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			// clavier() retourne vrai si des données sont prêtes à être lues
			if (clavier()) {
				String s = sc.next();
				// A priori le test suivant est inutile car sc.next() retourne toujours
				// un mot non-vide.
				if (s.length() >= 1) {
					switch (s.charAt(0)) {
					case 'q':
					case 'Q':
						escape = true;
						break;
					case 'g':
					case 'G':
						if (nbTransformationG < niv1.getNbTransformationGMax()){
							niv1.toGrimpeur(0);
							++nbTransformationG;
						}
						else{
							System.out.println("Vous avez atteint la limite de grimpeurs !");
							pause(1);
						}
						break;
					case 'p':
					case 'P':
						if (nbTransformationP < niv1.getNbTransformationPMax()){
							niv1.toParachutiste(0);
							++nbTransformationP;
						}
						else{
							System.out.println("Vous avez atteint la limite de parachutistes !");
							pause(1);
						}
						break;
					default:
						System.out.println("Mauvaise touche !");
						// une petite pause pour que le message puisse être lu à l'écran
						pause(1);
					}
				}
			} else
				niv1.tic();
			System.out.println("tapez (q) pour ABANDONNER / (g) pour le transformer en GRIMPEUR / (p) pour le transformer en PARACHUTISTE");
			niv1.afficher();
			pause(1/VITESSE);
		} while (!escape && !niv1.estFini());
	}
}
