import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BoggleBoard {
	private final int BOARD_WIDTH = 5;
	private final int BOARD_HEIGHT = 5;
	private final int NUM_OF_DICE = 25;
	private final int DICE_SIDES = 6;
	private char[][] dice = new char[NUM_OF_DICE][];
	private boolean[] diceTaken = new boolean[NUM_OF_DICE];
	private char[][] board = new char[BOARD_WIDTH][BOARD_HEIGHT];
	private char[] charsOnBoard = new char[BOARD_WIDTH*BOARD_HEIGHT];
	private HashMap<String,ArrayList<int[]>> charPosList = new HashMap<String,ArrayList<int[]>>();  //Key String is a single char.toString() to make it an object., value is a list of coordinates.
	
	
	public BoggleBoard() {
		makeDice();
		makeBoard();
		makeCharPosList();
		makeCharList();
	}
	
	public char[] getCharsOnBoard(){
		return charsOnBoard;
	}
	
	private void makeCharPosList() {
		for (int x=0; x < BOARD_WIDTH; x++){
			for (int y=0; y < BOARD_HEIGHT; y++){
				String str = Character.toString(board[x][y]);
				ArrayList<int[]> charList = charPosList.remove(str);
				if (charList==null){
					charList = new ArrayList<int[]>();
				}
				charList.add(new int[] {x,y});
				charPosList.put(str, charList);
			}
		}
	}

	/**
	 * Replace board as your own. For testing.
	 * @param newBoard Must be char[BOARD_WIDTH][BOARD_HEIGHT]
	 * @return if successfully changed to new board
	 */
	public boolean forceBoard(char[][] newBoard){
		if (newBoard.length == BOARD_WIDTH){
			if (newBoard.length == BOARD_HEIGHT){
				board = newBoard;
				return true;
			}
		}
		return false;
	}

	/**
	 * makes a list of every letter avaliable to them.
	 */
	private void makeCharList() {
		int uniqueChars = 0;
		for (int i=0; i < BOARD_WIDTH; i++){
			for (int j=0; j < BOARD_HEIGHT; j++){
				if (!inCharList(board[i][j])){
					charsOnBoard[uniqueChars]=board[i][j];
					uniqueChars++;
				}
			}
		}
	}

	/**
	 * checks if a letter exists
	 * @param c the char to check
	 * @return true if board contains c
	 */
	private boolean inCharList(char c) {
		for (char ch:charsOnBoard){
			if (ch==c){
				return true;
			}
		}
		return false;
	}

	/**
	 * generates a legal BOARD_WIDTH*BOARD_HEIGHT board with NUM_OF_DICE dice avaliable.
	 */
	private void makeBoard() {
		Random r = new Random();
		for (int i=0; i < BOARD_WIDTH; i++){
			for (int j=0; j < BOARD_HEIGHT; j++){
				int x;
				do{
					x = r.nextInt(NUM_OF_DICE);
				}while (diceTaken[x]);
				diceTaken[x]=true;
				board[i][j]=dice[x][r.nextInt(DICE_SIDES)];
			}
		}
	}

	private void makeDice() {
		for (int i = 0; i < NUM_OF_DICE; i++){
			diceTaken[i]=false;
		}
		//To improve, put in text file and While not EOF, dice[i]=readln(),i++
		dice[0]=new char[]{'A','A','A','F','R','S'};
		dice[1]=new char[]{'A','A','E','E','E','E'};
		dice[2]=new char[]{'A','A','F','I','R','S'};
		dice[3]=new char[]{'A','D','E','N','N','N'};
		dice[4]=new char[]{'A','E','E','E','E','M'};
		dice[5]=new char[]{'A','E','E','G','M','U'};
		dice[6]=new char[]{'A','E','G','M','N','N'};
		dice[7]=new char[]{'A','F','I','R','S','Y'};
		dice[8]=new char[]{'B','J','K','Q','X','Z'};//TODO special case for Q
		dice[9]=new char[]{'C','C','E','N','S','T'};
		dice[10]=new char[]{'C','E','I','I','L','T'};
		dice[11]=new char[]{'C','E','I','L','P','T'};
		dice[12]=new char[]{'C','E','I','P','S','T'};
		dice[13]=new char[]{'D','D','H','N','O','T'};
		dice[14]=new char[]{'D','H','H','L','O','R'};
		dice[15]=new char[]{'D','H','L','N','O','R'};
		dice[16]=new char[]{'D','H','L','N','O','R'};
		dice[17]=new char[]{'E','I','I','I','T','T'};
		dice[18]=new char[]{'E','M','O','T','T','T'};
		dice[19]=new char[]{'E','N','S','S','S','U'};
		dice[20]=new char[]{'F','I','P','R','S','Y'};
		dice[21]=new char[]{'G','O','R','R','V','W'};
		dice[22]=new char[]{'I','P','R','R','R','Y'};
		dice[23]=new char[]{'N','O','O','T','U','W'};
		dice[24]=new char[]{'O','O','O','T','T','U'};
	}
	
	public void printBoard(){
		for (int i=0; i < BOARD_WIDTH; i++){
			for (int j=0; j < BOARD_HEIGHT; j++){
				System.out.print(board[i][j]+",");
			}
			System.out.println();
		}
	}
	
	public char[][] getBoard(){
		return board;
	}

	public boolean hasWord(String s) {
		return hasWord(s, null);//Default values for recursive hasWord do not need to be externally known.
	}

	private boolean hasWord(String s, ArrayList<int[]> coordList) {
		char c = s.toCharArray()[0];
		String s2;
		if (c=='Q'){ //the next char must be U
			s2 = s.substring(2);
		}else{
			s2 = s.substring(1);
		}
		ArrayList<int[]> value = charPosList.get(Character.toString(c));
		if (value!=null){									//if value isn't null there are at lest 1 char c on the board to check.
			boolean hasTheWord = false;
			for (int[] coords:value){
				if (coordList!=null){						//if coordList ins't null, it isn't the first character.
					if (!taken(coords,coordList)){
						if (neighbour(coords,coordList)){	//if the coord is next to the last coord
							if (s2.isEmpty()){
								return true;				//if the rest of the string is empty then this is the finishing condition and all letters fit on the boars
							}else{							//if the rest of the string is not empty then we need to process the rest of the letters.
								coordList.add(coords);
								hasTheWord= hasWord(s2,coordList);
							}
						}else{
							if (s2.isEmpty()){
								hasTheWord= false;
							}
						}
															//if it is not next to the last coord, ignore it, if last then fail.
					}else{
						if (s2.isEmpty()){
							hasTheWord= false;
						}
					} 										//if the position is taken,ignore, if last then fail.
				}else{										//if coordList is null, it is the first character.
					if (s.isEmpty()){
						return true;						//if the rest of the string is empty then this is the finishing condition and all letters fit on the boars
					}else{									//if the rest of the string is not empty then we need to process the rest of the letters.
						coordList = new ArrayList<int[]>();
						coordList.add(coords);
						hasTheWord= hasWord(s2,coordList);
					}
				}
			}
			if(hasTheWord){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	private boolean neighbour(int[] coords, ArrayList<int[]> coordList) {
		int[] lastCoords = coordList.get(coordList.size()-1);
		if ((Math.abs(coords[0]-lastCoords[0])<=1)&&(Math.abs(coords[1]-lastCoords[1])<=1)){
			return true;
		}
		return false;
	}


	private boolean taken(int[] coords, ArrayList<int[]> coordList) {
		for (int[] c:coordList)				//for every coordinate in the list
			if (intArrayEquals(c,coords)){	//if any match the current coord
				return true;				//it is taken
			}
		return false;
	}

	private boolean intArrayEquals(int[] c, int[] coords) {
		if (c.length!=coords.length){
			return false;
		}
		for (int i = 0; i < c.length;i++){
			if (c[i]!=coords[i]){
				return false;
			}
		}
		return true;
	}
	
}
