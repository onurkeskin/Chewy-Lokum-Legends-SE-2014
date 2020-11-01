import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Onur
 *
 */
public class saveLoadManagerTests {

	// Test Plan : test for saving and loading, for loading, we check env we saved before with env we loaded and check
	// with env we were playing before saving.
	// For saving, we save the game and check blob with what we saved. 
	
	
	gameEngine engine;
	chewyLokumController cont;
	gameBlock[][] env;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 * @requires
	 * @modifies
	 * @ensures
	 */
	@Before
	public void setUp() throws Exception {

		env = new gameBlock[][] {
				{ new lokum(1), new lokum(2), new lokum(3), new lokum(4), },
				{ new lokum(5), new lokum(2), new lokum(4), new lokum(1), },
				{ new lokum(2), new lokum(1), new lokum(3), new lokum(5), } };

		engine = gameEngine.getInstance();
		engine.init(100, 5, 0, env, new player());

		cont = chewyLokumController.getInstance();
		cont.init(1000, 100, 50, 1, 1);
	}

	@Test
	public void testLoad() {
		saveLoadManager.saveGame(engine, cont);
		chewyBlob blob = saveLoadManager.loadProperties();

		if (blob.getGoalScore() == cont.getScoreAim()
				&& blob.getLevel() == cont.getLevel()
				&& blob.getMovesleft() == cont.getPossibleMoveCount()
				&& blob.getScore() == cont.getPlayerScore()) {
			assertTrue(check(blob.getGameEnv(), env));
		}
	}

	private boolean check(gameBlock[][] a1, gameBlock[][] a2) {
		if (a1.length != a2.length || a1[0].length != a2[0].length)
			return false;

		for (int i = 0; i < a1.length; i++) {
			for (int j = 0; j < a1[0].length; j++) {
				if (a1[i][j].getType() != a2[i][j].getType())
					return false;
			}
		}
		return true;

	}
	
}
