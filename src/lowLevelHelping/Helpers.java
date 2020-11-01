import java.util.LinkedList;


public class Helpers {

	public static LinkedList<position> filledPos(Object[][] asda){
		LinkedList<position> toReturn = new LinkedList<position>();
		
		for(int i = 0; i < asda.length; i++){
			for(int j = 0; j < asda[0].length; j++){
				if(asda[i][j] != null) toReturn.add(new position(i,j));
			}
		}	
		return toReturn;
		
	}
	
	
	public static boolean eligiblePos(position pos, gameBlock[][] env) {
		int x1 = pos.getX();
		int y1 = pos.getY();

		if (x1 >= 0 && x1 < env.length && y1 >= 0 && y1 < env[0].length
				&& env[x1][y1] != null && env[x1][y1].getType() != 0)
			return true;
		else
			return false;
	}
	
}
