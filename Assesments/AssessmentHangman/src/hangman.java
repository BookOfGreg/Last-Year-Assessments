import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.*;
import java.util.Stack;

/**
 * My implementation of HangMan
 * @author Greg
 *
 */
public class hangman {
	//options
	private int guesses = -1;							//allowed guesses
	private boolean showRemainingWordsToggle = false;	//toggles if you can see dict.length() between guesses
	//tools
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private DictReader dictReader = new DictReader();
	//working fields
	private HashMap<Integer, ArrayList<String>> allDict;
	private ArrayList<String> dict;					//holds current words remaining
	private int wordLength;							//holds word length
	
	private int unguessedLettersCount;				//holds blank spaces left
	private char[] guessedChars;		//holds all guessed letters
	
	private String anyRegexChar = "[a-z]";			//base case for any other chars, will be updated negating the guess+guessed chars as they are locked
	private String regexMask;						//describes filled in chars, _ is blank, any other character is itself
	private String[] regexPattern;					//holds all permutations of regex to check
	private ArrayList<ArrayList<String>> regexDict;	//holds all dicts that match regexPattern
	
	/**
	 * Read file and make data structure
	 */
	private hangman() {
		allDict = dictReader.getAsHashMap();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		hangman game = new hangman();
		boolean playAgain = false;
		do{
			game.setup();
			game.run();
			playAgain = game.readBool();
		}while (playAgain);
	}

	private void setup() {
		 //prepare for input		
		System.out.println("How long should the word be?");
		do{
			int index = readInt(true);
			dict=allDict.get(index);
			if (dict == null){
				System.out.println("There are no words I can pick that are "+index+" long. Please pick again"); 
			}else{
				unguessedLettersCount = index;
				wordLength = index;
				regexPattern = initRegex();
			}
		}while (dict == null);
		
		System.out.println("How many guesses would you like?");
		guesses = readInt(true);
		guessedChars = new char[guesses];
		for (int i = 0; i < guessedChars.length; i++){
			guessedChars[i]='_';
		}
		System.out.println("Would you like to know how many words there are left in the list?");
		showRemainingWordsToggle = readBool();
	}

	/**
	 * initialises the regex fields to equivalent blank
	 * @return
	 */
	private String[] initRegex() {
		String[] regex = new String[(int) Math.pow(2, wordLength)];
		regexMask = "";
		for (int i = 0; i < wordLength; i++){
			regex[i]=anyRegexChar;// =".";or ="[a-z]" also works
			regexMask += "_";
		}
		return regex;
	}

	private void run() {
		boolean win = false;
		do{
			if (showRemainingWordsToggle){
				System.out.println("There are "+dict.size()+" words remaining.");
			}
			if (guessedChars[0]!='_'){
				System.out.print("You have guessed the following letters:");
				for (char c:guessedChars){
					System.out.print(" "+c);
				}
				System.out.println();
			}
			System.out.println("You have "+guesses+" guesses remaining");
			System.out.println("Make a guess");
			char c = readChar(guessedChars);
			c = checkDuplicateChar(c);
			long startTime = System.nanoTime();
			makeGuess(c);
			System.out.println(regexMask);
			if (unguessedLettersCount == 0){
				System.out.print("You win!");
				win = true;
			}
			if (guesses <= 0){
				System.out.print("You ran out of guesses!");
			}
		}while (guesses > 0& ! win);
		if (win){
			System.out.println("You won! Would you like to play again?");
		}else{
			Random r = new Random();
			System.out.println("You lost, your word was "+dict.get(r.nextInt(dict.size()))+". Would you like to play again?");
		}
	}
	
	/**
	 * takes the character and filters all the words by it then finds the 
	 * largest family and sets it as the current working array.
	 * @param c the character that was guessed
	 */
	private void makeGuess(char c) {
		setupRegexPermutations(c,guessedChars);
		
		for (String word:dict){
			sortIntoRegexGroup(word,c);
		}
		loop:
		for (int i = 0; i < guessedChars.length; i++){ //add the character to the first unused slot in guessedChars
			if (guessedChars[i]=='_'){
				guessedChars[i]=c;
				break loop;
			}
		}
		dict=largestGroup();
	}

	/**
	 * Uses recursion to get an unguessed character from the user.
	 * @param c the character you are guessing.
	 * @return c when c is not already guessed.
	 */
	private char checkDuplicateChar(char c) {
		boolean duplicate = false;
		loop:
		for (char each:guessedChars){
			if (each==c){
				duplicate = true;
				break loop;
			}
		}
		if (duplicate){
			System.out.println("The letter "+c+" has already been guessed. Please guess another");
			checkDuplicateChar(readChar(guessedChars));
		}
		return c;
	}


	/**
	 * Creates the regex patterns.
	 * @param guessedChars The list of already guessed characters.
	 * @param guess The character you are guessing this round.
	 */
	private void setupRegexPermutations(char guess, char[] guessedChars) {
		int regexGroupCount = (int) Math.pow(2, wordLength);
		for (int x = 0; x < regexGroupCount; x++){
			String binStr = Integer.toBinaryString(x);
			while (binStr.length() < wordLength){
				binStr="0"+binStr;
			}
			regexPattern[x]="";
			boolean firstGuess = Character.toString(guessedChars[0])!="";
			String guessedCharsStr="";
			if (firstGuess){
				guessedCharsStr = new String(guessedChars).trim();
			}
			for (int i = 0; i < wordLength; i++){
				if (regexMask.charAt(i)=='_'){	//if open
					if (binStr.charAt(i)=='0'){ //if empty, fill with any char but guessed chars and current char
						regexPattern[x]+="[a-z&&[^"+ guessedCharsStr + guess +"]]";
					}else{						//if guessing, fill with current char
						regexPattern[x]+="["+ guess +"]";
					}
					
				}else{							//else insert already correct char
					regexPattern[x]+="["+regexMask.charAt(i)+"]";
				}
			}
		}
		regexDict = new ArrayList<ArrayList<String>>();
		for (int x = 0; x < regexGroupCount; x++){
			ArrayList<String> a = new ArrayList<String>();
			regexDict.add(a);
		}
	}

	/**
	 * puts the word into whichever arraylist corresponds to the matching pattern
	 * Must be run after setupRegexPermutations(char,char[]) as otherwise there will be no groups.
	 * @param word The word to be added to a group.
	 * @param guess The letter that was guessed.
	 */
	private void sortIntoRegexGroup(String word,char guess) {
		String strBinaryGroup="";
		for (int i = 0; i < word.length(); i++){
			if (word.charAt(i) == guess){
				strBinaryGroup+="1";
			}else{
				strBinaryGroup+="0";
			}
		}
		int binaryGroup = Integer.parseInt(strBinaryGroup, 2);
		ArrayList<String> r = regexDict.remove(binaryGroup);
		r.add(word);
		regexDict.add(binaryGroup, r);
	}
	
	/**
	 * puts the word into whichever arraylist corresponds to the matching pattern
	 * Must be run after setupRegexPermutations(char,char[]) as otherwise there will be no groups.
	 * @param word The word to be added to a group.
	 */
	private void sortIntoRegexGroup(String word) {
		Pattern[] patterns = new Pattern[regexPattern.length];
		loop:
		for (int i = 0; i < regexPattern.length; i++){
			patterns[i] = Pattern.compile(regexPattern[i],Pattern.CASE_INSENSITIVE);
			//System.out.println(regexPattern[i]+" "+word);
			Matcher m = patterns[i].matcher(word);
			if (m.matches()){
				ArrayList<String> r = regexDict.remove(i);
				r.add(word);
				regexDict.add(i, r);
				break loop;
			}
		}
	}
	
	/**
	 * returns the ArrayList with the most matching words and updates regex mask
	 * @return
	 */
	private ArrayList<String> largestGroup() {
		int max = -1;
		int maxPos = -1;
		
		//find max
		for (int i = 0; i < regexPattern.length; i++){
			int size = regexDict.get(i).size();
			if ( size > max){
				maxPos = i;
				max = size;
			}
		}
		//update guesses
		if (maxPos==0){
			guesses--;
		}
		
		//update mask with corresponding regex to max
		String[] group = regexPattern[maxPos].split("\\]\\[");
		group[0]=group[0].substring(1);//to fix first '['
		for (int i = 0; i < wordLength; i++){
			if (regexMask.charAt(i)=='_'){			//if blank
				if (!group[i].contains("a-z")){		//if not generic(aka a letter)
					regexMask=regexMask.substring(0, i)+group[i].charAt(0)+regexMask.substring(i+1, wordLength);//blank becomes letter
					unguessedLettersCount--;
				}
			}
		}
		return regexDict.get(maxPos);
	}
	
	
	private char readChar(char[] guessedChars) {
		String line = "nil";
		boolean complete = true;
		do{
			complete = true;
			try {
				line = input.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			if (Character.isLetter(line.charAt(0))){
				for (char c:guessedChars){ //check if char already guessed
					if (c==line.charAt(0)){
						complete = false;
						System.out.println("The letter \'"+c+"\' has already been guessed. Please guess again");
					}
				}
			}else{
				complete = false;
				System.out.println("Not a valid answer, will only accept letters between \'a\' and \'z\'. Please guess again.");
			}
			if (complete){
				return line.toUpperCase().charAt(0);
			}else{
				
			}
		}while (!complete);
		return 0;//some random value, code never reaches here. Loops above.
	}

	private boolean readBool() {
		String line = "nil";
		boolean complete = false;
		do{
			try {
				line = input.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if ((line.toUpperCase().equals("YES")) || (line.toUpperCase().equals("Y")) || (line.toUpperCase().equals("TRUE"))){
				return true;
			}else if ((line.toUpperCase().equals("NO"))||(line.toUpperCase().equals("N"))||(line.toUpperCase().equals("FALSE"))){
				return false;
			}
			if (!complete){
				System.out.println("Not a valid answer, will accept \"yes, y, true, no, n\" or \"false\". Please guess again.");
			}
		}while (!complete);
		return false;//some random value, the code will never get here as it keeps looping till it hits the real returns further up.
	}

	private int readInt(Boolean isPositive) {
		int index=Integer.MIN_VALUE;
		String line = "nil";
		do{
			try {
				line = input.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				index = Integer.parseInt(line);
			} catch (Exception e) {
				System.out.println(line + " is not a valid integer. Please pick again.");
			}
			if (isPositive){
				if (index <=0){
					System.out.println("The number chosen cannot be negative. Please pick again");
				}
			}
		}while ((index==Integer.MIN_VALUE)||((isPositive)&&(index <=0)));
		return index;
	}

}
