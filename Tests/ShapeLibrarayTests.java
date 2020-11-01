import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ShapeLibrarayTests {
	ShapeLibrary lib;
	
	// Test Plan : 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		lib = new ShapeLibrary();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddDrawableLokum() {
		drawable smt = new drawable() {
			public void draw(Graphics paramGraphics, position pos, position size) {
				Random gen = new Random();
				paramGraphics.setColor(new Color(gen.nextInt(255),gen.nextInt(255),gen.nextInt(255)));
				paramGraphics.fillRoundRect(pos.getX(), pos.getY(), size.getX(), size.getY(),20,20);
			}
		};
	
		
		lib.addDrawableLokum(smt);
		assertTrue(smt == lib.getDrawableLokums().get(lib.getDrawableLokums().size()-1));
	}
	
	@Test
	public void testAddDrawableLokumFLokum() {
		drawable shape = ShapeLibrary.generateDrawableLokum();
		lib.addDrawableLokum(shape);
		assertTrue(shape == lib.getDrawableLokums().get(lib.getDrawableLokums().size()-1));
	}
	
	@Test
	public void testAddDrawableLokumSLokum() {
		drawable shape = ShapeLibrary.generateDrawableSLokum();
		lib.addDrawableLokum(shape);
		assertTrue(shape == lib.getDrawableLokums().get(lib.getDrawableLokums().size()-1));
	}
	
	@Test
	public void testAddDrawableLokumObstacle() {
		drawable shape = ShapeLibrary.generateDrawableobstacle();
		lib.addDrawableLokum(shape);
		assertTrue(shape == lib.getDrawableLokums().get(lib.getDrawableLokums().size()-1));
	}
	
	
	
	@Test
	public void testAdddrawableSLokum() {
		drawable smt = new drawable() {
			public void draw(Graphics paramGraphics, position pos, position size) {
				Random gen = new Random();
				paramGraphics.setColor(new Color(gen.nextInt(255),gen.nextInt(255),gen.nextInt(255)));
				paramGraphics.fillRoundRect(pos.getX(), pos.getY(), size.getX(), size.getY(),20,20);
			}
		};
		lib.adddrawableSLokum(smt);
		assertTrue(smt == lib.getDrawableSLokums().get(lib.getDrawableSLokums().size()-1));
	}


}
