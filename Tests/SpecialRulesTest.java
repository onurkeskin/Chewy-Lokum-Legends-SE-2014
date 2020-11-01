import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Onur
 *
 */
public class SpecialRulesTest {

	colorBombRule cRule;
	stripedRule sRule;
	wrappedRule wRule;
	gameEngine engine;
	gameBlock[][] env;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		cRule = new colorBombRule(1);
		sRule = new stripedRule(true);
		wRule = new wrappedRule();

		env = new gameBlock[][] {
				{ new lokum(1), new lokum(2), new lokum(3), new lokum(4), },
				{ new lokum(5), new lokum(2), new lokum(4), new lokum(1), },
				{ new lokum(2), new lokum(1), new lokum(3), new lokum(5), } };

	}

	@Test
	public void testCBomb() {
		propertyPack pack = cRule.applyRule(env, new position(0, 0));
		assertTrue(pack.getDestroyedPositions().get(0).equals(new position(0, 0))
				&& pack.getDestroyedPositions().get(2).equals(new position(2, 1))
				&& pack.getDestroyedPositions().get(1).equals(new position(1, 3)));
	}

	@Test
	public void testS() {
		propertyPack pack = sRule.applyRule(env, new position(0, 0));
		assertTrue(pack.getDestroyedPositions().get(0).equals(new position(0, 0))
				&& pack.getDestroyedPositions().get(1).equals(new position(0, 1))
				&& pack.getDestroyedPositions().get(2).equals(new position(0, 2))
				&& pack.getDestroyedPositions().get(3).equals(new position(0, 3)));
	}

	@Test
	public void testW() {
		propertyPack pack = wRule.applyRule(env, new position(0, 0));
		assertTrue(pack.getDestroyedPositions().get(0).equals(new position(0, 0))
				&& pack.getDestroyedPositions().get(1).equals(new position(0, 1))
				&& pack.getDestroyedPositions().get(2).equals(new position(1, 0))
				&& pack.getDestroyedPositions().get(3).equals(new position(1, 1)));
	}

}
