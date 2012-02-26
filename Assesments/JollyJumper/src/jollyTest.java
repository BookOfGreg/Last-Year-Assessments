//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.ArrayList;


public class jollyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws IOException {
		//get input
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for (String s:args){
			ints.add(Integer.parseInt(s));
		}
		boolean isJolly = JollyCheck(ints);
		if (isJolly) {
			System.out.println("Jolly");
		}else{
			System.out.println("Not Jolly");
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
}