import static org.junit.Assert.*;

import org.junit.Test;

public class NiveauTest {

	@Test
	public void creerTourBlocstest() {
		Niveau n = new Niveau(20, 80, 15, 0, 18, 79, 2, 2, 2);
		n.creerTourMurs(18, 32, 7);
		n.afficher();
		for (int i = 18; i>18-7; --i){
			assertTrue(n.getSymboleAt(i, 32) == '|');
		}
	}
	
	@Test
	public void plateformetest() {
		Niveau n = new Niveau(20, 80, 15, 0, 18, 79, 2, 2, 2);
		n.plateforme(19, 0, 80);
		n.afficher();
		for (int i = 0; i<80; ++i){
			assertTrue(n.getSymboleAt(19, i) == '-');
		}
	}

}
