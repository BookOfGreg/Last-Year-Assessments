import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class jollyJumper {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws IOException {
		//get input
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<String[]> lines = new ArrayList<String[]>();
		while ((line = input.readLine()) != null) {
			lines.add(line.split(" "));
		}
		input.close();
		
		//run jumpers on each
		for (String[] s:lines){
			boolean isJolly = JollyCheck(parse(s));
			if (isJolly) {
				System.out.println("Jolly");
			}else{
				System.out.println("Not Jolly");
			}
		}
	}

	private static boolean JollyCheck(ArrayList<Integer> parsed) {
		boolean[] n = new boolean[parsed.size()-1];
		
		for (int i = 1; i< parsed.size();i++){//check if diff(current,last) is in n.
			int x = Math.abs(parsed.get(i) - parsed.get(i-1));
			if ((x<=n.length)&&(x>0)){ //if x in n set true for that int.
				n[i-1] = true;
			}
		}
		
		for (int i = 0; i < n.length; i++){ //if a number is unchecked
			if (!n[i]){
				return false;
			}
		}
		return true;
	}
	
	/*private static boolean JollyCheck(int[] parsed) {
		System.out.print(parsed.length);
		//boolean[] n = new boolean[parsed.length-1];
		
		for (int i = 0; i < n.length; i++){//init array
			n[i]=false;
		}
		
		for (int i = 1; i< parsed.length;i++){//check if diff(current,last) is in n.
			int x = Math.abs(parsed[i] - parsed[i-1]);
			if (x<n.length){ //if x in n set true for that int.
				n[i] = true;
			}
		}
		
		for (int i = 0; i < n.length; i++){ //if a number is unchecked
			if (!n[i]){
				return false;
			}
		}
		return true;
	}*/

	private static ArrayList<Integer> parse(String[] s) { //knock off the first digit since it seems to be the value of n
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		for (int i = 1; i < s.length; i++){
			intArray.add(Integer.parseInt(s[i]));
		}
		return intArray;
	}

}
