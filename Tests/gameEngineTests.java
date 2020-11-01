import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class gameEngineTests {
	// Test Plan: This class is tested most importantly for its ability to swap locations,
	// other methods are private and incorporated with applyChanges.
	
	
	gameEngine engine;
	  
	public ExpectedException exception = ExpectedException.none();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		gameBlock[][] env = new gameBlock[][] {{ new lokum(1), new lokum(2), new lokum(3), new lokum(4),},
  				{ new lokum(5), new lokum(2), new lokum(4), new lokum(1),},
				{ new lokum(2), new lokum(1), new lokum(3), new lokum(5),}};
		
		engine = gameEngine.getInstance();
		engine.init(100, 5, 0, env, new player());
	}

	@After
	public void tearDown() throws Exception {
	}

	// GAME ENGINE TESTS
	// Glass Box: %100 coverege with test1 and test2
	@Test
	public void testApplyChanges1() {
		// tests the case which a given input is legit thus it should perform the swap.
		int a1 = engine.gameAsInt()[1][2];
		int a2 = engine.gameAsInt()[1][3];
		if(a1 != 0 && a2 != 0){
		List<pair<position>> asda = new LinkedList<pair<position>>();
		asda.add(new pair<position>(new position(1,2),new position(1,3)));
		engine.applyChanges(asda);
		int b1 = engine.gameAsInt()[1][2];
		int b2 = engine.gameAsInt()[1][3];
		
		assertTrue(a1 == b2 && a2 == b1);}
		else assertTrue(true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testApplyChanges2() {
		// tests the case which given input is illegal, trying to swap locations further than 1 block.
		int a1 = engine.gameAsInt()[0][0];
		int a2 = engine.gameAsInt()[1][3];
		List<pair<position>> asda = new LinkedList<pair<position>>();
		asda.add(new pair<position>(new position(0,0),new position(1,3)));
		engine.applyChanges(asda);
	}
	// END OF GAME ENGINE TESTS
	
	
	@Test
	public void testApplyChangesSpecial() {
		//This method should change places of lokums nevertheless, so its outcome should be always swapping.
		int a1 = engine.gameAsInt()[1][0];
		int a2 = engine.gameAsInt()[1][3];
		if(a1 != 0 && a2 != 0){
		List<pair<position>> asda = new LinkedList<pair<position>>();
		asda.add(new pair<position>(new position(1,0),new position(1,3)));
		engine.applyChangesSpecial(asda);
		int b1 = engine.gameAsInt()[1][0];
		int b2 = engine.gameAsInt()[1][3];
		
		assertTrue(a1 == b2 && a2 == b1);}
		else assertTrue(true);
	}
	
	@Test
	public void testRepChanges() {
		assertFalse(!engine.repOK());
		
		List<pair<position>> asda = new LinkedList<pair<position>>();
		asda.add(new pair<position>(new position(1,0),new position(1,3)));
		engine.applyChangesSpecial(asda);
		
		System.out.println(engine.toString());
		assertFalse(!engine.repOK());
		
		// AFTER USING TOSTRING : 
//		
//		PLAYER: name: score: 0
//		RULES: RuleControl@12e84396
//		GAME ENVIRONMENT: x: 0 y: 0 type: 1| x: 0 y: 1 type: 2| x: 0 y: 2 type: 3| x: 0 y: 3 type: 4|
//		 x: 1 y: 0 type: 1| x: 1 y: 1 type: 2| x: 1 y: 2 type: 4| x: 1 y: 3 type: 5|
//		 x: 2 y: 0 type: 2| x: 2 y: 1 type: 1| x: 2 y: 2 type: 3| x: 2 y: 3 type: 5|
//
//		SLOKUM QUEUE[]
//		TIMERS: total time: 100remaining time: 0
		
	}
}
