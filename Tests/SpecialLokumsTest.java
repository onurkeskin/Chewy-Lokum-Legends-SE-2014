import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SpecialLokumsTest {

	colorBombLokum cBLokum;
	stripedLokum sLokum;
	wrappedLokum wLokum;
	timeLokum tLokum;
	
	@Before
	public void setUp() throws Exception {
		cBLokum = new colorBombLokum(-4, 1);
		sLokum = new stripedLokum(-1, true);
		wLokum = new wrappedLokum(-2);
		tLokum = new timeLokum(1);
	}

	@Test
	public void colorBombLokumDestroyTest() {
		propertyPack pack = cBLokum.destroy();
	}

}
