import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Greg
 *
 */
public class DictReader {
	private InputScan inputScan = new InputScan("sowpods.txt");
	
	/**
	 * gets the dictionary as a String ArrayList
	 * @return String ArrayList of the whole file
	 */
	public ArrayList<String> getAsArrayList(){
		ArrayList<String> dict = new ArrayList<String>();
		while (!inputScan.isEmpty()){
			dict.add(inputScan.getNextString());
		}		
		return dict;
	}
	
	/**
	 * May cause problems due to arraylists moving things into null slots, 
 	 * cannot simply implement as array due to unknown length of max word.
	 * @return
	 */
	public ArrayList<ArrayList<String>> getAs2DArrayList(){
		ArrayList<ArrayList<String>> splitDict = new ArrayList<ArrayList<String>>();
		while (!inputScan.isEmpty()){
			//TODO test that arraylist does not collapse the array on empty slots somehow. Will cause arrayoutofbounds error
			String newStr = inputScan.getNextString();
			ArrayList<String> s = splitDict.remove(newStr.length());
			s.add(newStr);
			splitDict.add(newStr.length(),s);
		}
		return splitDict;
	}
	
	/**
	 * Uses HashMap in a way that the word length is the key to each bucket
	 * This causes collisions on purpose but each bucket is its own ArrayList.
	 * @return
	 */
	public HashMap<Integer, ArrayList<String>> getAsHashMap(){
		HashMap<Integer, ArrayList<String>> splitDict = new HashMap<Integer, ArrayList<String>>();
		while (!inputScan.isEmpty()){
			String newStr = inputScan.getNextString();
			ArrayList<String> s = (ArrayList<String>) splitDict.get(newStr.length());
			if (s==null){
				s=new ArrayList<String>();
			}
			s.add(newStr);
			splitDict.put(newStr.length(), s);
		}
		return splitDict;
	}
}
