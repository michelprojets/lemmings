package outils;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * Classe regroupant deux m�thodes statiques utilitaires.
 *
 */
public class Outils {
	private static PushbackInputStream kbObserver = new PushbackInputStream(System.in);

	/**
	 * Indique si le buffer associ� au clavier contient des donn�es.
	 * @return Vrai si des donn�es sont pr�tes � �tre lue.
	 */
	public static boolean clavier() {
		try {
			return kbObserver.available() > 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Interrompt le programme pendant un laps de temps.
	 * @param secondes Dur�e (en secondes) de l'interruption.
	 */
	public static void pause(double secondes) {
		try {
			Thread.sleep((int) (1000 * secondes));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
