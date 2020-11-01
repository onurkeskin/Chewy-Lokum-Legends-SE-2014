import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


public class highScoreManipTests {
	// Test Plan : test for getting scores, putting and checking scores if they are eligible.
	// %70 + path coverege
	
	
	highScoreManip manip;
	List<String> roll;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		manip = new highScoreManip("testHighScore.txt");
		roll = manip.getScores();
		System.out.println("asd");
	}
	
	@After
	public void tearDown() throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter("testHighScore.txt"));
		for(int i=0; i < roll.size();i++){
			writer.write(roll.get(i));
			writer.write("\n");
		}
		writer.close();
	}

	@Test
	public void testGetScores() {
		try {
			List<String> list = manip.getScores();
			String[] toBeList = new String[8];
			toBeList[0] = "1516565161/denizgg";
			toBeList[1] = "577300.0/mertALi";
			toBeList[2] = "5140.0/onur";
			toBeList[3] = "5060.0/asdasdasdasd";
			toBeList[4] = "400/pro";
			toBeList[5] = "300/good";
			toBeList[6] = "267.0/onur";
			toBeList[7] = "200/intermediate";
			
			boolean ok = true;
			for(int i = 0; i < list.size(); i++) {
					if(!(toBeList[i].equals(list.get(i)))){
						ok = false;
					}
			assertTrue(ok);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPutScore() {
		List<String> list = null;
		try {
			try {
				manip.putScore(240, "mert");
			} catch (IOException e) {
				e.printStackTrace();
			}
			list = manip.getScores();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		assertTrue(list.get(7).equals("240.0/mert"));
	}

	@Test
	public void testCheckScore() {
		assertTrue(4 == manip.checkScore(5000));
	}

}
