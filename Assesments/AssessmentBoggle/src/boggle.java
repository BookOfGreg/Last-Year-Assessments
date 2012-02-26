//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class boggle {
	//tools
	//private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private DictReader dictReader = new DictReader();
	private BoggleBoard board;
	private ArrayList<String> dictionary;
	private ArrayList<String> wordList = new ArrayList<String>();
	
	public boggle() {
		setup();
	}

	/**
	 * Creates the board, retrieves the dictionary and excludes 
	 * words with chars not on the board.
	 */
	private void setup() {
		board = new BoggleBoard();
		dictionary = dictReader.getAsArrayList();
		dictionary = excludeChars(dictionary,board.getCharsOnBoard());
		board.printBoard();
	}

	/**
	 * Automatically excludes any words that contain letters that do not exist on the board at all.
	 * @param dictionary2 the dictionary that will be filtered
	 * @param charsOnBoard the range of characters that the words must consist of.
	 * @return the filtered dictionary
	 */
	private ArrayList<String> excludeChars(ArrayList<String> dictionary2, char[] charsOnBoard) {
		//TODO
		ArrayList<String> shortDict = new ArrayList<String>();
		String chars = new String(charsOnBoard);
		String regex = "["+chars+"]*";
		Pattern p = Pattern.compile(regex);
		for (String s:dictionary2){
			Matcher m = p.matcher(s);
			if (m.matches()){
				shortDict.add(s);
			}
		}
		return shortDict;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boggle game = new boggle();
		game.run();
	}

	/**
	 * checks if each word exists on the board
	 */
	private void run() {
		for (String s:dictionary){
			if (board.hasWord(s)){
				wordList.add(s);
			}
		}
		for (String s:wordList){
			System.out.println(s);
		}
	}

}
