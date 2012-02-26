import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class myFamily {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//fake input
		/*String line="1";
		ArrayList<String[]> lines = new ArrayList<String[]>();
		lines.add(line.split(" "));
		line = "3 1 2 4";
		lines.add(line.split(" "));*/
		
		
		//get input
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<String[]> lines = new ArrayList<String[]>();
		while ((line = input.readLine()) != null) {
			lines.add(line.split(" "));
		}
		input.close();
		
		//turn to ints
		ArrayList<ArrayList<Integer>> numbers = new ArrayList<ArrayList<Integer>>();
		for (String[] sA:lines){
			ArrayList<Integer> numberLine = new ArrayList<Integer>();
			for (String s:sA){
				numberLine.add(Integer.parseInt(s));
			}
			numbers.add(numberLine);
		}
		
		//process
		//test cases
		int testCases = numbers.remove(0).remove(0);
		for (ArrayList<Integer> numberLine:numbers){
			int n = numberLine.remove(0);
			Collections.sort(numberLine);
			int medianVal = numberLine.get(n/2);
			int minDistance = 0;
			for (int i:numberLine){
				minDistance+=Math.abs(i-medianVal);
			}
			System.out.println(minDistance);
		}
		
	}

}
