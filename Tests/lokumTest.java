import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class lokumTest {
	lokum lokum1;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		lokum1 = new lokum(2);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsObstacle() {
		assertTrue(!lokum1.isObstacle());
	}

	@Test
	public void testReturnSR() {
		assertTrue(lokum1.returnSR() == null);
	}
	
	@Test
	public void testOnDestroy(){
		propertyPack pack = lokum1.destroy();
		assertTrue(pack == null);
	}


}
