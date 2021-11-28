package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ReversiBoard implements Serializable{
	private static final long serialVersionUID = 20211103001L;
	static final int NUM_SPACES = 64;
	private int turn;
	private HashMap<Integer, Disk> occupiedSpaces;
	private ArrayList<Integer> emptySpaces;
	
	
	enum Rows {
		HORIZONTAL (new int[][] {{0, 1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 11, 12, 13, 14, 15}, {16, 17, 18, 19, 20, 21, 22, 23},
					{24, 25, 26, 27, 28, 29, 30, 31}, {32, 33, 34, 35, 36, 37, 38, 39}, {40, 41, 42, 43, 44, 45, 46, 47},
					{ 48, 49, 50, 51, 52, 53, 54, 55}, {56, 57, 58, 59, 60, 61, 62, 63}}),
		
		VERTICAL (new int[][] {{0,8,16,24,32,40,48,56}, {1,9,17,25,33,41,49,57}, {2,10,18,26,34,42,50,58},{3,11,19,27,35,43,51,59},
					{4,12,20,28,36,44,52,60}, {5,13,21,29,37,45,53,61}, {6,14,22,30,38,46,54,62}, {7,15,23,31,39,47,55,63}}),
		
		DIAGONAL (new int[][] {{40,49,58}, {32,41,50,59}, {24,33,42,51,60}, {16,25,34,43,52,61}, {8,17,26,35,44,53,62}, 
				{0,9,18,27,36,45,54,63}, {1,10,19,28,37,46,55}, {2,11,20,29,38,47}, {3,12,21,30,39}, {4,13,22,31}, {5,14,23}, {47,54,61},
				{39,46,53,60}, {31,38,45,52,59}, {23,30,37,44,51,58}, {15,22,29,36,43,50,57}, {7,14,21,28,35,42,49,56},
				{6,13,20,27,34,41,48}, {5,12,19,26,33,40}, {4,11,18,25,32}, {3,10,17,24}, {2,9,16}});
		
		private int[][] rows;

		Rows(int[][] rows) {
			this.rows = rows;
		}
		
	}
	
	/**
	 * No-arg constructor
	 */
	public ReversiBoard() {
		occupiedSpaces = new HashMap<Integer, Disk>();
		emptySpaces = new ArrayList<Integer>();
		turn = 1;
		setupBoard();
	}
	

	/*
	 * Sets up the board by placing disks in the starting spots
	 */
	private void setupBoard() {
		for (int i = 0; i<64 ; i++) {
			if (i == 27 || i == 28 || i == 35 || i == 36) {
				continue;
			}
			emptySpaces.add(i);
		}
		occupiedSpaces.put(27, Disk.WHITE);
		occupiedSpaces.put(28, Disk.BLACK);
		occupiedSpaces.put(35, Disk.BLACK);
		occupiedSpaces.put(36, Disk.WHITE);
	}
	
	
	/**
	 * 
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}
	

	/**
	 * @return the occupiedSpaces
	 */
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Disk> getOccupiedSpaces() {
		return (HashMap<Integer, Disk>) occupiedSpaces.clone();
	}
	
	
	/*
	 * @return the current player based off of turn count
	 */
	public Disk getCurrentPlayer () {
		return turn % 2 == 0 ? Disk.WHITE : Disk.BLACK;
	}
	
	
	/*
	 * @return the opposite player based off of the current player
	 */
	private Disk getOppositePlayer() {
		return getCurrentPlayer() == Disk.BLACK ? Disk.WHITE : Disk.BLACK;
	}
	
	/*
	 * TODO i dont know if you still want this one
	 */
	public void testForRow() {
		short valueCounter = 0; //We have 3 enum objects (HORIZONTAL, VERTICAL, DIAGONAL). This value isn't used to find index of an array.
		for (Rows rowsEnum : Rows.values()) { // in the Row class, for each rowsEnum we have (3 times)
			System.out.println("\nNew value: Horizontal/Vertical/Diagonal " + valueCounter++);
			short arrayCounter = 0; //The first index value in the int[][]
			for (var row : rowsEnum.rows) {
				System.out.println("\nNew Array " + arrayCounter++);	//Prints a new line when we move onto the next array in either Horizontal, Vertical, Diagonal
				for (int value : row) {		//For each value in one array, print its value (The 2nd index value in the int[][])
					System.out.print(value + ", ");
				}
			}
		}
	}
	

	/**
	 * Checks if the currentPlayer has at least one disk in the array.
	 * 
	 * @param {int[]} rowArray
	 */
	private boolean arrayContainsCurrentPlayer(int[] rowArray){
		for (int i = 0; i < rowArray.length; i++) {
			if (occupiedSpaces.get(rowArray[i]) == getCurrentPlayer()) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * Checks a row array for the Hashmap location
	 * 
	 * @param {int[]} rowArray
	 * @param {int} loc
	 */
	private boolean arrayContainsLoc(int[] rowArray, int loc) {
		for (int i = 0; i < rowArray.length; i++) {
			if (rowArray[i] == loc) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * Returns the index as an integer of a location inside an array
	 * 
	 * @param {int[]} rowArray
	 * @param {int} loc
	 */
	private int getLocInArray(int[] rowArray, int loc) {
		int locIndex = -1;
		for (int i = 0; i < rowArray.length; i++) {
			if (rowArray[i] == loc) {
				locIndex = i;
				break;
			}
		}
		return locIndex;
	}
	

	/*
	 * Checks the array for empty spaces
	 * 
	 * @param {int[]} rowArray
	 */
	private boolean arrayContainsEmpty(int[] rowArray) {
		for (int i = 0; i < rowArray.length; i++) {
			if (emptySpaces.contains(rowArray[i])) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns an array with all Hashmap locations that are considered a legal move
	 * 
	 * @return {ArrayList<Integer>} legalMoves
	 */
	public ArrayList<Integer> findLegalMove() {
		ArrayList<Integer> legalMoves = new ArrayList<Integer>();
		for (Rows rows : Rows.values()) { // in the Row class, for each value in Rows
			for (var row : rows.rows) { // var picks HORIZONTAL, VERTICAL, DIAGONAL. for each row inside these
//				System.out.println();
//				for (int k = 1; k< row.length; k++) {
//					System.out.print(row[k] + " ");
//				}
				if (arrayContainsEmpty(row) && arrayContainsCurrentPlayer(row)) { //if the current array has at least one empty spot and has at least on currentplayer disk then it is a relevant array
					for (int i = 1; i < row.length-1; i++) {
						if (occupiedSpaces.get(row[i]) == getOppositePlayer() && emptySpaces.contains(row[i-1]) && !legalMoves.contains(row[i-1])) { //Found a nonempty index at i and previos index is empty
							//System.out.println(row[i]);
							for (int j = i+1; j < row.length; j++) { //Iterate forward from current position where if statement is true try to find
//								System.out.println("Checking " + row[j] + " is empty?" + emptySpaces.contains(row[j]));
//								boolean statement = (occupiedSpaces.get(row[j]) == getCurrentPlayer());
//								System.out.println(occupiedSpaces.get(row[j]));
//								System.out.println("Checking " + row[j] + " is " + getCurrentPlayer() + statement);
//								System.out.println(row[j] + " has color " + occupiedSpaces.get(row[j]));
								if (emptySpaces.contains(row[j])) {
									break;
								}
								else if (occupiedSpaces.get(row[j]) == getCurrentPlayer()) {
									legalMoves.add(row[i-1]);
									break;
								}
							}
						}
						if (occupiedSpaces.get(row[i]) == getOppositePlayer() && emptySpaces.contains(row[i+1]) && !legalMoves.contains(row[i+1])) { //Found a nonempty index at i and next index is empty
							for (int j = i-1; j > 0; j--) { //Iterate backwards
								//System.out.println("emptyIndex is " + row[j]);
								if (emptySpaces.contains(row[j])) { 
									break;
								}
								else if (occupiedSpaces.get(row[j]) == getCurrentPlayer()) {
									legalMoves.add(row[i+1]);
									break;
								}
							}
						}
					} // close each index in array for loop
				}
			} // close for each array loop (8, 8, 22 loop)
		} // close for rowEnum loop (3 loop)
		return legalMoves;
	}
	
	
	/*
	 * Determines if a location is a valid location
	 * 
	 * @param {int} loc
	 */
	private boolean isValidMove(int loc) {
		return !isOver() && findLegalMove().contains(loc);
	}
	
	
	/**
	 * Returns true iff able to place the mark of the current player into the
	 * specified grid location.
	 * 
	 * @param loc the specified board location
	 * @return {@code true} iff mark was successfully placed
	 */
	public boolean placeDisk(int loc) {
		return placeDisk(getCurrentPlayer(), loc);
	}
	
	
	/*
	 * Places the current players disk in a specified location
	 * 
	 * @param {Disks} currentPlayer
	 * @param {int} loc
	 */
	private boolean placeDisk(Disk currentPlayer, int loc) {
		if (isValidMove(loc)) {
			occupiedSpaces.put(loc, currentPlayer);			//Place into hashmap our new disk
			emptySpaces.remove(emptySpaces.indexOf(loc));	//Remove from emptySpaces the loc using removeEmptySpace method
			captureDisks(loc);		//Capture the disks
			if (!isOver()) {		
				turn++;
			}
			return true;
		}
		return false;
	}
	
	/*
	 * if the disk has been placed flip the opposite colored disks in the same row.
	 * 
	 * @param loc the specified board location
	 */
	
	public void captureDisks(int loc){
		flipDisks(getCurrentPlayer(), loc);
	}
	
	
	/*
	 * Flips disks that fall along the same row as the most recently placed disk 
	 * if they are inbetween the current players new and existing disks
	 * 
	 * @param loc the specified board location
	 * @param currentPlayer current player as specified by the turn
	 */
	private boolean flipDisks(Disk currentPlayer, int loc) {
		boolean flipped = false;
		System.out.println("New disk at " + loc);
		for (Rows rows : Rows.values()) {
			for (var row : rows.rows) {
				if (arrayContainsLoc(row, loc)) {
					flipped = flipDisksInArray(row, loc, currentPlayer);
				}
			}
		}
		return flipped;
	}
	
	
	/*
	 * Flips Disks captured by a player based on their location in an array
	 * 
	 * @param {int[]} row
	 * @param {int} loc
	 * @param {Disks} currentPlayer
	 */
	private boolean flipDisksInArray(int[] row, int loc, Disk currentPlayer) {
		System.out.println();
		int locIndex = getLocInArray(row, loc);
		boolean flipped = false;
		//System.out.println(willFlipForward(row, locIndex, currentPlayer));
		if (locIndex >= 0 && willFlipForward(row, locIndex, currentPlayer)) {
			System.out.println("Going forward: " + locIndex);
			for (int posCur = locIndex+1; posCur < row.length; posCur++) {
				//System.out.println(occupiedSpaces.get(row[posCur]));
				if (occupiedSpaces.get(row[posCur]) == getOppositePlayer()) {
					occupiedSpaces.replace(row[posCur], getOppositePlayer(), currentPlayer);
					flipped = true;
				} else if (occupiedSpaces.get(row[posCur]) == getCurrentPlayer()) {
					break;
				}
			}
		}
		if (locIndex >= 0 && willFlipBackwards(row, locIndex, currentPlayer)) {
			System.out.println("Going back: " + locIndex);
			for (int i = 0; i<row.length; i++) {
				System.out.println(row[i] + " , ");
			}
			System.out.println();
			for (int negCur = locIndex-1; negCur > 0; negCur--) {
				System.out.println(row[negCur] + " has " + getOppositePlayer() + " ? " + (occupiedSpaces.get(row[negCur]) == getOppositePlayer()));
				if (occupiedSpaces.get(row[negCur]) == getOppositePlayer()) {
					occupiedSpaces.replace(row[negCur], getOppositePlayer(), currentPlayer);
					flipped = true;
				} else if (occupiedSpaces.get(row[negCur]) == getCurrentPlayer()) {
					break;
				}
			}
		}
		return flipped;
	}
	
	
	/*
	 * Determines if disks will be flipped "forward" based on the disk colors surrounding 
	 * the disk at the location it was placed
	 * 
	 * @param {int[]} row
	 * @param {int} locIndex
	 * @param {Disks} currentPlayer
	 */
	private boolean willFlipForward(int[] row, int locIndex, Disk currentPlayer) {
		boolean hasOthers = false;
//		System.out.println();
//		for (int k = 0; k < row.length; k++) {
//			System.out.print(row[k] + ",");
//		}
//		System.out.println();
		for (int i = locIndex; i < row.length; i++) {
			if (occupiedSpaces.get(row[i]) == currentPlayer) {
				for (int j = i+1; j < row.length; j++) { //Iterate forward from current position where if statement is true try to find
//					System.out.print(row[j] + " is " + occupiedSpaces.get(row[j]) + " ");
//					System.out.println("hasOthers is " + hasOthers);
					if (occupiedSpaces.get(row[j]) == getOppositePlayer()) {
						hasOthers = true;
					}
					else if (occupiedSpaces.get(row[j]) == getCurrentPlayer() && hasOthers) {
						//System.out.println("Found true");
						return true;
					}
					else if (occupiedSpaces.get(row[j]) == getCurrentPlayer() && !hasOthers) {
						return false;
					}
				}
			}
		}
		return false;
	}
	
	
	/*
	 * Determines if disks will be flipped "backward" based on the disk colors surrounding 
	 * the disk at the location it was placed
	 * 
	 * @param {int[]} row
	 * @param {int} locIndex
	 * @param {Disks} currentPlayer
	 */
	private boolean willFlipBackwards(int[] row, int locIndex, Disk currentPlayer) {
		boolean hasOthers = false;
		for (int i = locIndex; i > 0; i--) {
			if (occupiedSpaces.get(row[i]) == currentPlayer) {
				for (int j = i-1; j > 0; j--) { //Iterate backward from current position where if statement is true try to find
					//System.out.println(occupiedSpaces.get(row[j]));
					if (occupiedSpaces.get(row[j]) == getOppositePlayer()) {
						hasOthers = true;	//There is the opposite color before
					}
					else if (occupiedSpaces.get(row[j]) == getCurrentPlayer() && hasOthers) {
						return true;		//We found a player color but also found the opposite color before this
					}
					else if (occupiedSpaces.get(row[j]) == getCurrentPlayer() && !hasOthers) {
						return false;		//There is the player's color before
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Returns an integer that represents all instances of black disks based 
	 * on the occupiedSpaces hashmap
	 * 
	 * @return {int} blackCount
	 */
	public int countBlack() {
		int blackCount = 0;
		for (int i = 0; i < 64; i++) {
			if (occupiedSpaces.get(i) == Disk.BLACK) {
				blackCount++;
			}
		}
		return blackCount;
	}
	
	/**
	 * Returns an integer that represents all instances of white disks based 
	 * on the occupiedSpaces hashmap
	 * 
	 * @return {int} whiteCount
	 */
	public int countWhite() {
		int whiteCount = 0;
		for (int i = 0; i < 64; i++) {
			if (occupiedSpaces.get(i) == Disk.WHITE) {
				whiteCount++;
			}
		}
		return whiteCount;
	}
	
	
	/**
	 * Finds the winner of the game if there is one and the game is over.
	 * @return 	null: If black has the same amount of disks occupying the spaces as white.
	 * 			Disk.WHITE: If white has more disks occupying the spaces than black.
	 * 			Disk.BLACK: If black has more disks occupying the spaces than white.
	 */
	public Disk getWinner() {
		if (isOver() && countBlack() == countWhite()) { //This means we have a tie.
			return null;
		}
		return isOver() && (countBlack() < countWhite()) ? Disk.WHITE : Disk.BLACK;
	}
	
	
	/**
	 * The game is over if there are no legal moves left or if the turn is equal to the NUM_SPACES meaning we have filled up the board.
	 * 
	 * @return 	true: no legal moves left (findLegalMove() array is empty) or the number of turns has reached NUM_SPACES.
	 * 			false: there are still legal moves left and the number of turns has not reached the NUM_SPACES.
	 */
	public boolean isOver() {
		return findLegalMove().isEmpty() || turn == NUM_SPACES;
	}
	
}
