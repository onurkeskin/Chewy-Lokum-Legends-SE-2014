import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;



/**
 * @author Kartel
 *
 */
public class highScoreManip {

	String fileLoc ;
	
	public highScoreManip(String fileLoc){
		this.fileLoc = fileLoc;
	}
	
/**
 * @return
 * @throws FileNotFoundException
 * @requires Nothing
 * @modifies Nothing
 * @ensures A List
 */
	
public List<String> getScores() throws FileNotFoundException{
		BufferedReader reader = new BufferedReader(new FileReader(fileLoc));
		List<String> list = new LinkedList<String>();
		try{
			String line = reader.readLine();
			while (line != null){
				list.add(line);
				line = reader.readLine();
			}
			reader.close();
		}
		catch(Exception e){
			
		}
		
		return list;
	}


/**
 * @return
 * @throws FileNotFoundException
 * @requires Score und Name
 * @modifies Bir Dosyay›
 * @ensures A Modified File
 */
public void putScore(double score, String name) throws IOException{
		List<String> strings = getScores();
		List<Double> intScores = getIntegerScores(strings);
		
		int location = checkScore(score);
				
		if(location != -1){
		String toWrite = score +"/" + name ;
			strings = putScoreOnList(strings, location, toWrite);
		try{	
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileLoc));
			for(int i = 0; i<strings.size();i++){
			writer.write(strings.get(i));
			writer.write("\n");}
			writer.close();
		}catch (Exception e){
			
		}
		}
	}


/**
 * @return
 * @throws FileNotFoundException
 * @requires Nothing
 * @modifies Nothing
 * @ensures A List
 */
private List<String> putScoreOnList(List<String> list,int location,String score){
	list.remove(list.size()-1);
	list.add(location, score);
	return list;
}

/**
 * @return
 * @throws FileNotFoundException
 * @requires Nothing
 * @modifies Nothing
 * @ensures A List
 */
private List<Double> getIntegerScores(List<String> toUse){
	List<Double> toReturn = new LinkedList<Double>();
	for ( int i = 0;i<toUse.size();i++){
		String toManip = toUse.get(i);
		String[] parts = toManip.split("/");
		toReturn.add(Double.parseDouble(parts[0]));
	}
	return toReturn;
}

/**
 * @return
 * @throws FileNotFoundException
 * @requires getScore
 * @modifies Nothing
 * @ensures if not found -1, or Lokum's location in the list
 */
public int checkScore(double score){
	
	List<Double> currentScores;
	try {
		currentScores = getIntegerScores(getScores());
	} catch (FileNotFoundException e) {
		return -1;
	}
	int location = -1;
	for(int i = 0; i<currentScores.size();i++){
		if(score>currentScores.get(i)) {
			location = i;
			break;
		}
	}
	
	return location;
}
}
