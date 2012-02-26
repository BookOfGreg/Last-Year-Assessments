import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class shellSort {
	static int posnX = 1;
	static int lennX = -1;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//get input
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<String> lines = new ArrayList<String>();
		while ((line = input.readLine()) != null) {
			lines.add(line);
		}
		input.close();
		//Structure of input = {s,s,s,s}
		
		//Test Cases
		int k = Integer.parseInt(lines.get(0));
		for (int kLoop = 0; kLoop < k; kLoop++){ //For each test case
			lennX=Integer.parseInt(lines.get(posnX));
			posnX = sortShells(posnX,lennX,lines);
			System.out.println();
		}

				
	}

	private static int sortShells(int startPos, int length, ArrayList<String> lines) {
		ArrayList<String> target = new ArrayList<String>();
		ArrayList<String> current = new ArrayList<String>();
		int endPos = startPos + 1 + (2*length); //2n+1 from start position
		
		//separate
		for (int x = startPos+1; x < endPos; x++){
			if (x < startPos+1+length){
				current.add(lines.get(x));
			}else{
				target.add(lines.get(x));
			}
		}
		
		//sort
		int lastPos = Integer.MAX_VALUE;
		for (int x = target.size()-1; x >= 0; x-=1){//get bottom string in target
			String tarStr = target.get(x);
			for (int t = 0; t<current.size();t++){//find string in current
				if (current.get(t).equals(tarStr)){
					if (t > lastPos){// if pos is less than lastPos then nothing:else move to top 
						current = move(t,current);
						lastPos = 0;
					}else{
						lastPos = t;//store its pos as lastPos.
					}
				}
			}
		}
		return endPos;
	}

	private static ArrayList<String> move(int t, ArrayList<String> current) {
		System.out.println(current.get(t));
		current.add(0,current.remove(t));
		return current;
	}


}
