import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;


public class pancakes {

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
		//Structure = {{s}{s}{s}{s}}
		
		//check input.
		/*for (String[] s:lines){
			for (String str:s){
				System.out.print(str);
			}
			System.out.println();
		}*/
		
		//get String args[] version of input for testing.
		/*ArrayList<String[]> lines = new ArrayList<String[]>();
		lines.add(args);
		String[] txt = new String[1];
		txt[0]="0";
		lines.add(txt);*/
				
		//convert to int[]
		for (String[] s:lines){
			if (s[0].equals("0")){
				System.out.print("0");
			}else{
				int[] n = new int[s.length];
				for (int i = 0; i <= s.length-1;i++){
					System.out.print(s[i]+" ");
					n[i]=Integer.parseInt(s[i]);
				}
				System.out.println();
				pancake(n);
			}
		}
	}
	
	//calculate which to flip
	private static void pancake(int[] n) {
		int sorted = 0;
		int max = -1;
		int pos = -1;
		for(int I = 0; I < n.length; I++){
			max = -1;
			pos = -1;
			for (int x = sorted; x < n.length;x++){
				if (n[x]>max){ //find max.
					max = n[x];
					pos = x+1;
				}
			}
			n=flip(pos,n);
			sorted++;
			n=flip(sorted,n);
			if (orderCheck(n)){
				break;
			}
		}
		
		/*//do flips on n
		String flips = "";
		
		//check for max, move from pos to last unsorted. EG: 1 2 3 4 5, 5 in pos 5 is rank 1, makes 5 4 3 2 1, 4 pos 2 is rank 2, makes 5 4 3 2 1 ...
		int maxVal = 0;
		int maxPos = 0;
		int sorted = 0;
		for (int i = 0; i < n.length; i++){
			for (int x = sorted; x < n.length; x++){
				if (n[x]>maxVal){
					maxVal = n[x];
					maxPos = x+1;
				}
				sorted++;
				flips += maxPos+" "+sorted+" ";
				maxPos = 0;
				maxVal = 0;
			}
		}*/
		
		System.out.println("0");
	}
	
	private static boolean orderCheck(int[] n) {
		int last = Integer.MAX_VALUE;
		for (int i:n){
			if (i<last){
				last = i;
			}else{
				return false;
			}
		}
		return true;
	}

	//actually flipping.
	private static int[] flip(int I, int[] list){
		System.out.print(I + " ");
		Stack<Integer> s = new Stack<Integer>();
		for (int n = I-1; n < list.length; n++){
			s.push(list[n]);
		}
		for (int n = list.length-s.size(); n < list.length; n++){
			list[n] = s.pop();
		}
		return list;
	}

}
