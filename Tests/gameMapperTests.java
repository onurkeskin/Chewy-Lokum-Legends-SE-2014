import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */


public class gameMapperTests {

	// Test Plan: This class can only have a fault if it fails to produce an adequate drawable array for
	// a integer array. %100 path coverege
	
	gameMapper mapper;
	ShapeLibrary lib;
	Integer[][] env;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	@Before
	public void setUp() throws Exception {
		mapper = gameMapper.getInstance();
		lib = new ShapeLibrary();
		
		
		mapper.init(lib, 6, 6);
		
		env = new Integer[][] {{ 1, 2, 3, 4,},
  				{ 5, 2, 4, 1,},
				{ 2, 1, 3, 5,}};
	}


	@After
	public void tearDown() throws Exception {
	}


	/**
	 * Test method for {@link gameMapper#generateDrawableArray(int[][])}.
	 */
	@Test
	public void testGenerateDrawableArray() {
		//tests if method can put all the blocks a mapping.
		drawable[][] toTest = mapper.generateDrawableArray(env);
		for(int i = 0; i< toTest.length; i++){
			for(int j = 0; j< toTest[0].length; j++){
				if( !(env[i][j] == null && toTest[i][j] == null) || !(env[i][j] != null && toTest[i][j] != null)) 
					assertFalse(true);
			}
		}
		assertTrue(true);
	}
}
