import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author deniz
 *
 */
public class gameMapper {
	
	private static gameMapper instance;
	private int NLCount;
	private int SLCount;
	private ShapeLibrary shapeLib;
	
	HashMap<Integer, drawable> mapper;
	
	private gameMapper(){}
	
	public static gameMapper getInstance(){
		if (gameMapper.instance == null) instance = new gameMapper();
		return instance;
	}
	
	public void init(ShapeLibrary shapeLib,int NLCount, int SLCount){
		this.shapeLib = shapeLib;
		this.SLCount = SLCount;
		this.NLCount = NLCount;
		mapper = new HashMap<Integer, drawable>();
		readyMapping();
	}
	
	private void readyMapping(){
		ArrayList<drawable> possibleLokums = shapeLib.getDrawableLokums();
		ArrayList<drawable> possibleSLokums = shapeLib.getDrawableSLokums();
		ArrayList<drawable> possibleObstacles = shapeLib.getDrawableObstacles();
		
		

		for(int i = 1; i <= NLCount; i++){
			if(possibleLokums != null && possibleLokums.size() > 0){
			drawable nlokum = possibleLokums.remove(0);
			if(nlokum != null){
				mapper.put(i, nlokum);
			}} else {
				drawable putThis = ShapeLibrary.generateDrawableLokum();
				mapper.put(i, putThis);
			}
		}
		

		for(int i = -1; i >= -SLCount; i-- ){
			if(possibleSLokums != null && possibleSLokums.size() > 0){
			drawable slokum = possibleSLokums.remove(0);
			if(slokum != null){
				mapper.put(i, slokum);
			}} else {
				drawable putThis = ShapeLibrary.generateDrawableSLokum();
				mapper.put(i, putThis);
			}
		}
		
		if (possibleObstacles != null && possibleObstacles.size() > 0){
		drawable obstacle = possibleObstacles.get(0);
		if(obstacle != null){
			mapper.put(0, obstacle);
		}} else {
			drawable putThis = ShapeLibrary.generateDrawableobstacle();
			mapper.put(0, putThis);
		}
		
		
	}

	/**
	 * @param array
	 * @return drawable array which was taken 
	 * @requires an integer array which can have null positions
	 * @modifies 
	 * @ensures 
	 */
	public drawable[][] generateDrawableArray(Integer[][] array){
		drawable[][] toReturn = new drawable[array.length][array[0].length];
		
		for ( int i = 0; i<array.length; i++ ) {
			for (int j = 0; j< array[0].length; j++ ) {
				if(array[i][j] != null)
				toReturn[i][j] = mapper.get(array[i][j]);
			}
		}
		
		return toReturn;
	}
	
	/**
	 * @param i
	 * @return integers position in mapper which was given
	 * @requires an integer 
	 * @modifies
	 * @ensures
	 */
	public drawable mapThis(int i){
		return mapper.get(i);
	}
	
	
}
