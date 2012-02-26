import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Hello CodeMarker");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<String> currentLine = new ArrayList<String>();
		try {
			while ((line = input.readLine()) != null) { 
				currentLine.add(line);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			input.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
