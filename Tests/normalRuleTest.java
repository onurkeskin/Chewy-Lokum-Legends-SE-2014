import static org.junit.Assert.*;

import javax.print.attribute.standard.MediaSize.Engineering;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class normalRuleTest {
	
	// Tests rules apply methods, close to % 80 + coverege, tests for rule in positions that are
	// greater than gameEnv, position that is in the env but close to side, not close to side and
	// size is less than game Env.
	
	normalRule rule;
	Integer[][] ruleEnv;

	gameBlock[][] env;

	@Before
	public void setUp() throws Exception {
		env = new gameBlock[][] {
				{ new lokum(1), new lokum(1), new lokum(1), new lokum(1), },
				{ new lokum(1), new lokum(1), new lokum(1), new lokum(1), },
				{ new lokum(1), new lokum(1), new lokum(1), new lokum(1), } };

		ruleEnv = new Integer[][] { { 1, 1 }, { 1, 1 } };

		gameEngine eng = gameEngine.getInstance();
		eng.init(100, 10, 10, env, new player());

		rule = new normalRule(ruleEnv, null, false, 1, 100);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetReturningBlock() {
		assertTrue(null == rule.getReturningBlock());
	}

	@Test
	public void testIsExplosive() {
		assertTrue(!rule.isExplosive());
	}
	
	//TESTS THAT TEST FOR RIGHT POSITIONS AND MUST DESTROY
	@Test
	public void testApplyRule() {
		propertyPack pack = rule.applyRule(env, new position(0,0));

		assertTrue((pack.getDestroyedPositions().get(0).equals(new position(0,0)) &&
				pack.getDestroyedPositions().get(1).equals(new position(0,1)) &&
				pack.getDestroyedPositions().get(2).equals(new position(1,0)) &&
				pack.getDestroyedPositions().get(3).equals(new position(1,1))));
	}

	@Test
	public void testApplyRuleScore() {
		propertyPack pack = rule.applyRule(env, new position(0,0));
		assertTrue(pack.getScore() == 100);
	}

//	TESTS THAT MIGHT FAIL IN FUTURE IF SOMETHING IS WRONGLY CHANGED IN APPLYIN RULES
	
	@Test
	public void testApplyRuleKinda(){
		propertyPack pack = rule.applyRule(env, new position(0,2));

		assertTrue((pack.getDestroyedPositions().get(0).equals(new position(0,2)) &&
				pack.getDestroyedPositions().get(1).equals(new position(0,3)) &&
				pack.getDestroyedPositions().get(2).equals(new position(1,2)) &&
				pack.getDestroyedPositions().get(3).equals(new position(1,3))));
	}
	
	@Test
	public void testApplyRuleScoreKinda() {
		propertyPack pack = rule.applyRule(env, new position(0,2));
		assertTrue(pack.getScore() == 100);
	}

// TESTS THAT TEST FOR COMPLETLY BAD SPOTS
	@Test
	public void testApplyRuleWrong(){
		propertyPack pack = rule.applyRule(env, new position(0,3));

		assertTrue(pack.getDestroyedPositions() == null);
	}
	
	@Test
	public void testApplyRuleScoreWrong() {
		propertyPack pack = rule.applyRule(env, new position(0,3));
		assertTrue(pack.getScore() == 0);
	}
}
