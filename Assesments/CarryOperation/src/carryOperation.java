import java.io.*;
public class carryOperation {

	public carryOperation(){
		String[] args = getStrings();
		for (int i = 1; i < args.length; i+=2){
			if (Integer.parseInt(args[i]) != 0){
				int carry = calculateCarry(args[i],args[i+1]);
				if (carry ==0){
					System.out.println("No carry operations.");
				}else{
					System.out.println(carry+" carry operations.");
				}
			}
		}
	}


	private String[] getStrings(){
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		String currentLine="";
		try {
			while ((line = input.readLine()) != null) { 
				currentLine += " "+ line;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			input.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String[] strings = currentLine.split(" ");
		return strings;
	}


	public int calculateCarry(String string, String string2) {
		char[] reverse = new StringBuffer(string).reverse().toString().toCharArray();
		char[] reverse2 = new StringBuffer(string2).reverse().toString().toCharArray();
		int carryTotal = 0;
		int carry = 0;
		for (int i = 0; i<reverse.length; i++){
			int int1 = Integer.parseInt(Character.toString(reverse[i]));
			int int2 = 0;
			if (i < reverse2.length){
				int2 = Integer.parseInt(Character.toString(reverse2[i]));
			}
			if ((int1 + int2 + carry)>9){
				carry = 1;
				carryTotal+=1;
			}
			else{
				carry = 0;
			}
		}
		return carryTotal;
	}

	public static void main(String[] args) {
		new carryOperation();
	}

}
