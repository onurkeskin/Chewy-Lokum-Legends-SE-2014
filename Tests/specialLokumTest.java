import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class specialLokumTest {
	specialLokum lokum1;
	wrappedRule rule;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		rule = new wrappedRule();
		lokum1 = new specialLokum(-1, rule);
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGetRule() {
		assertTrue(rule == lokum1.getRule());
	}

	@Test
	public void testGetType() {
		assertTrue(lokum1.getType() < 0);
	}

	@Test
	public void testIsObstacle() {
		assertTrue(!lokum1.isObstacle());
	}
	
	@Test
	public void testOnDestroy(){
		propertyPack pack = lokum1.destroy();
		assertTrue(pack.getRule() != null);
	}
}
