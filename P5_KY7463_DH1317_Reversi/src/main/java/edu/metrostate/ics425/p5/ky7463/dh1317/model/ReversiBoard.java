package edu.metrostate.ics425.p5.ky7463.dh1317.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Kou Yang & Dominic Hannah
 * ICS 425 - Ralph Foy
 * Assignment P5 - Reversi
 * Date: 11/28/2021
 * This class represents the ReversiBoard Bean used to implment all functionalities of the logic in the game for placing a disk,
 * capturing/flipping disks, finding all the possible moves for the current player, counting disks, and getting the winner
 * when the game is over.
 *
 */

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
	 * No-arg constructor for ReversiBoard. Initializes occupiedSpaces, emptySpaces, and turn. Also calls setupBoard helper method.
	 */
	public ReversiBoard() {
		occupiedSpaces = new HashMap<Integer, Disk>();
		emptySpaces = new ArrayList<Integer>();
		turn = 1;
		setupBoard();
	}
	

	/*
	 * Helper method to set up the initial board by putting disks in the starting spots and populating emptySpaces.
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
	 * Getter for turn.
	 * @return int: the turn count.
	 */
	public int getTurn() {
		return turn;
	}
	

	/**
	 * Getter for occupiedSpaces property. Returns a clone of occupiedSpaces.
	 * @return Hashmap<Integer, Disk> : a clone of occupiedSpaces.
	 */
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Disk> getOccupiedSpaces() {
		return (HashMap<Integer, Disk>) occupiedSpaces.clone();
	}
	
	
	/**
	 * Getter for currentPlayer. Returns the corresponding Disk enum based on the turn count.
	 * @return 	Disk.BLACK: Returns BLACK when turn is odd.
	 * 			Disk.WHITE: Returns WHITE when turn is even.
	 */
	public Disk getCurrentPlayer () {
		return turn % 2 == 0 ? Disk.WHITE : Disk.BLACK;
	}
	
	
	/**
	 * Getter for oppositePlayer. Returns the corresponding Disk enum based on the currentPlayer.
	 * @return 	Disk.BLACK: Returns BLACK if currentPlayer is WHITE.
	 * 			Disk.WHITE: Returns WHITE if currentPlayer is BLACK.
	 */
	private Disk getOppositePlayer() {
		return getCurrentPlayer() == Disk.BLACK ? Disk.WHITE : Disk.BLACK;
	}

	/**
	 * Checks if the currentPlayer has at least one Disk in the occupiedSpaces based on the key = elements from int[] rowArray.
	 * @param int[] rowArray: the row containing the indexes of the reversiBoard defined by the Rows enum class.
	 * @return	true: if we iterate through rowArray and find a Disk enum matching the currentPlayer in rowArray.
	 * 			false: if we iterate through rowArray and cannot find a Disk enum matching the currentPlayer in rowArray.
	 */
	private synchronized boolean arrayContainsPlayer(int[] rowArray, Disk player){
		for (int i = 0; i < rowArray.length; i++) {
			if (occupiedSpaces.get(rowArray[i]) == player) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Checks an int[] from Rows to see if it contains an element = loc.
	 * @param int[] rowArray: the row containing the indexes of the reversiBoard defined by the Rows enum class.
	 * @param int loc: the location index from the ReversiBoard that we want to check for in the rowArray.
	 * @return	true: if we iterate through rowArray and find loc in rowArray.
	 * 			false: if we iterate through rowArray and cannot find loc in rowArray.
	 */
	private synchronized boolean arrayContainsLoc(int[] rowArray, int loc) {
		for (int i = 0; i < rowArray.length; i++) {
			if (rowArray[i] == loc) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns the index that refers to the element inside an int[] rowArray where the element = loc.
	 * @param int[]rowArray: the row containing the indexes of the reversiBoard defined by the Rows enum class.
	 * @param int loc: the location index from the ReversiBoard that we want to check for in the rowArray.
	 * @return int locIndex: the integer whose index loc is at inside rowArray. A default value of -1 is initialized.
	 */
	private synchronized int getLocInArray(int[] rowArray, int loc) {
		int locIndex = -1;
		for (int i = 0; i < rowArray.length; i++) {
			if (rowArray[i] == loc) {
				locIndex = i;
				break;
			}
		}
		return locIndex;
	}
	

	/**
	 * Checks the int[] rowArray if it has an element that is inside the ArrayList<Integer> emptySpaces.
	 * @param int[] rowArray: the row containing the indexes of the reversiBoard defined by the Rows enum class.
	 * @return	true: if we iterate through rowArray and find an element that is inside emptySpaces.
	 * 			false: if we iterate through rowArray and cannot find an element that is inside emptySpaces.
	 */
	private synchronized boolean arrayContainsEmpty(int[] rowArray) {
		for (int i = 0; i < rowArray.length; i++) {
			if (emptySpaces.contains(rowArray[i])) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns a list of all the locations that the currentPlayer can place a new disk on.
	 * @return ArrayList<Integer> legalMoves: The list of all the locations a new disk can be placed on.
	 */
	public synchronized ArrayList<Integer> getLegalMoves() {
		ArrayList<Integer> legalMoves = new ArrayList<Integer>();
		for (Rows rows : Rows.values()) { // in the Row class, for each value in Rows
			for (var row : rows.rows) { // var picks HORIZONTAL, VERTICAL, DIAGONAL for each array.
				if (arrayContainsPlayer(row, getCurrentPlayer()) && arrayContainsEmpty(row)) {
					legalMoves = rowHasLegalMoves(row, legalMoves);
				}
			}
		}
		return legalMoves;
	}
	
	/**
	 * Helper method. Use each element in int[] rowArray as a key to see if has the Disk of the opposite player. If true, check
	 * if the next/previous element is empty and not already in legalMoves. Then iterate forward/backward until we find an 
	 * element in emptySpaces or the currentPlayer's disk. 
	 * @param int[] rowArray: int[] rowArray: the row containing at least one currentPlayer disk and at least one empty space.
	 * @param ArrayList<Integer> legalMoves: The list of all the locations a new disk can be placed on. 
	 * @return ArrayList<Integer> legalMoves: an updated list of all new locations a new disk can be placed on. 
	 */
	private synchronized ArrayList<Integer> rowHasLegalMoves(int[] rowArray, ArrayList<Integer> legalMoves) {
		for (int i = 1; i < rowArray.length-1; i++) {
			if (occupiedSpaces.get(rowArray[i]) == getOppositePlayer() && emptySpaces.contains(rowArray[i-1]) 
					&& !legalMoves.contains(rowArray[i-1])) { //Found a nonempty index at i and previous index is empty
				//Iterate forward to see if a currentPlayer Disk exists in occupiedSpaces given key = row[j]
				for (int j = i+1; j < rowArray.length; j++) {
					if (emptySpaces.contains(rowArray[j])) {
						break;
					}
					else if (occupiedSpaces.get(rowArray[j]) == getCurrentPlayer()) {
						legalMoves.add(rowArray[i-1]);
						break;
					}
				}
			}
			if (occupiedSpaces.get(rowArray[i]) == getOppositePlayer() && emptySpaces.contains(rowArray[i+1]) 
					&& !legalMoves.contains(rowArray[i+1])) { //Found a nonempty index at i and next index is empty
				//Iterate backwards to see if a currentPlayer Disk exists in occupiedSpaces given key = row[j]
				for (int j = i-1; j > 0; j--) {
					if (emptySpaces.contains(rowArray[j])) { 
						break;
					}
					else if (occupiedSpaces.get(rowArray[j]) == getCurrentPlayer()) {
						legalMoves.add(rowArray[i+1]);
						break;
					}
				}
			}
		}
		return legalMoves;
	}
	
	/**
	 * Determines if a location on the reversiBoard is a valid location.
	 * @param int loc: the location index from the ReversiBoard that we want to check for in list returned from findLegalMove().
	 * @return	true: if the game is not over and the list return from findLegalMove() has loc as an element.
	 * 			false: if the game is over or the list return from findLegalMove() does not have loc as an element.
	 */
	private synchronized boolean isValidMove(int loc) {
		return !isOver() && getLegalMoves().contains(loc);
	}
	
	/**
	 * Checks if the current player has any moves to make. If not, return true to make a pass.
	 * @return 	true : iff the current player cannot make any moves. Signals they will pass their turn.
	 * 			false: iff the current player has moves to make.
	 */
	private synchronized boolean makePass() {
		return getLegalMoves().isEmpty() ? true : false;
	}
	
	/**
	 * Places a new Disk onto the board and if successful, captures applicable Disks and increments the turn count by 1 if the
	 * occupiedSpaces size has not reached the NUM_SPACES meaning the board is not filled. Increments the turn count by 1 again if
	 * a pass is needed to continue the game.
	 * @param int loc: the location index from the ReversiBoard that we want to place the currentPlayer's Disk on.
	 */
	public synchronized void placeDisk(int loc) {
		if (placeDisk(getCurrentPlayer(), loc)) {
			captureDisks(loc);		//Capture the disks
			if (occupiedSpaces.size() != NUM_SPACES) {		
				turn++;
			}
			if (makePass()) {
				System.out.println("Made a pass");
				turn++;
			}
		}
	}
	
	
	/**
	 * Helper method. Places the currentPlayer Disk with key = loc into the occupiedSpaces. Then removes from emptySpaces the same element
	 * that is equal to loc.
	 * @param Disk currentPlayer: the Disk enum of the currentPlayer as specified by the turn.
	 * @param int loc: the location index from the ReversiBoard that we want to pair with the currentPlayer's Disk.
	 */
	private synchronized boolean placeDisk(Disk currentPlayer, int loc) {
		if (isValidMove(loc)) {
			occupiedSpaces.put(loc, currentPlayer);
			emptySpaces.remove(emptySpaces.indexOf(loc));
			return true;
		}
		return false;
	}
	
	/**
	 * Method used to capture existing disks after placing a new disk down at loc. Calls a helper method flipDisk().
	 * @param int loc: the location index from the ReversiBoard that the new disc was placed on.
	 */
	public synchronized void captureDisks(int loc){
		flipDisks(getCurrentPlayer(), loc);
	}
	
	/**
	 * Helper method. For each int array defined in the Rows enum class, check if the array has the element = loc. If it does,
	 * call the flipDisksInArray() helper method.
	 * @param int loc: the location index from the ReversiBoard that the new disc was placed on.
	 * @param Disk currentPlayer: the Disk enum of the currentPlayer as specified by the turn.
	 */
	private synchronized void flipDisks(Disk currentPlayer, int loc) {
		for (Rows rows : Rows.values()) {
			for (var row : rows.rows) {
				if (arrayContainsLoc(row, loc)) {
					flipDisksInArray(row, loc, currentPlayer);
				}
			}
		}
	}
	
	
	/**
	 * Helper method. Finds the index location of the loc in the int[] row, and then checks if we can flip any disk going forward,
	 * and backwards starting at the index location of loc. Flip any disks that is the oppositePlayer Disk enum until we find the
	 * currentPlayer's Disk or an element that is in emptySpaces.
	 * @param int[] rowArray: the row containing the indexes of the reversiBoard defined by the Rows enum class.
	 * @param int loc: the location index from the ReversiBoard that the new disc was placed on.
	 * @param Disk currentPlayer: the Disk enum of the currentPlayer as specified by the turn.
	 */
	private synchronized void flipDisksInArray(int[] row, int loc, Disk currentPlayer) {
		int locIndex = getLocInArray(row, loc);
		if (locIndex >= 0 && willFlipForward(row, locIndex, currentPlayer)) {
			//Iterate forward starting +1 index from our loc element index.
			for (int posCur = locIndex+1; posCur < row.length; posCur++) { 
				if (occupiedSpaces.get(row[posCur]) == currentPlayer || emptySpaces.contains(row[posCur])) {
					break;
				}
				else if (occupiedSpaces.get(row[posCur]) == getOppositePlayer()) {
					occupiedSpaces.replace(row[posCur], getOppositePlayer(), currentPlayer);
				} 
			}
		}
		if (locIndex >= 0 && willFlipBackwards(row, locIndex, currentPlayer)) {
			//Iterate backward starting at -1 from our loc element index.
			for (int negCur = locIndex-1; negCur > 0; negCur--) { 
				if (occupiedSpaces.get(row[negCur]) == currentPlayer || emptySpaces.contains(row[negCur])) {
					break;
				}
				else if (occupiedSpaces.get(row[negCur]) == getOppositePlayer()) {
					occupiedSpaces.replace(row[negCur], getOppositePlayer(), currentPlayer);
				}
			}
		}
	}
	
	
	/**
	 * Helper method. Checks if there contains at least one opposite player Disk between the currentPlayer's newly placed Disk and 
	 * the next currentPlayer's Disk while iterating forward in the rowArray starting at locIndex.
	 * @param int[] rowArray: the row containing the indexes of the reversiBoard defined by the Rows enum class.
	 * @param int locIndex: the location index inside rowArray that refers to the spot of the newly placed Disk.
	 * @param Disk currentPlayer: the Disk enum of the currentPlayer as specified by the turn.
	 * @return	true: if there exists at least one opposite player Disk between the currentPlayer's newly placed Disk and 
	 * 			the next currentPlayer's Disk. 
	 * 			false: if encounter an emptySpace or the currentPlayer's Disk when looking at indices in rowArray after the newly 
	 * 			placed Disk.
	 */
	private synchronized boolean willFlipForward(int[] row, int locIndex, Disk currentPlayer) {
		boolean hasOthers = false;
		for (int i = locIndex; i < row.length; i++) {
			if (occupiedSpaces.get(row[i]) == currentPlayer) {
				for (int j = i+1; j < row.length; j++) { //Iterate forward from current position where if statement is true try to find
					if (occupiedSpaces.get(row[j]) == getOppositePlayer()) {
						hasOthers = true;	//Found an oppositePlayer disk before finding empty or currentPlayer disk.
					}
					else if (occupiedSpaces.get(row[j]) == currentPlayer && hasOthers) {
						return true;		//Found the currentPlayer and hasOthers is true. We can flip at least one time.
					}
					else if (occupiedSpaces.get(row[j]) == currentPlayer && !hasOthers) {
						return false;		//The value at key = row[j] is the currentPlayer. Cannot flip.
					}
					else if (emptySpaces.contains(row[j])) {
						return false;		//The element is an empty space. Cannot flip.
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Helper method. Checks if there contains at least one opposite player Disk between the currentPlayer's newly placed Disk and 
	 * the next currentPlayer's Disk while iterating backwards in the rowArray starting at locIndex.
	 * @param int[] rowArray: the row containing the indexes of the reversiBoard defined by the Rows enum class.
	 * @param int locIndex: the location index inside rowArray that refers to the spot of the newly placed Disk.
	 * @param Disk currentPlayer: the Disk enum of the currentPlayer as specified by the turn.
	 * @return	true: if there exists at least one opposite player Disk between the currentPlayer's newly placed Disk and 
	 * 			the next currentPlayer's Disk. 
	 * 			false: if encounter an emptySpace or the currentPlayer's Disk when looking at indices in rowArray before the newly 
	 * 			placed Disk.
	 */
	private synchronized boolean willFlipBackwards(int[] row, int locIndex, Disk currentPlayer) {
		boolean hasOthers = false;
		for (int i = locIndex; i > 0; i--) {
			if (occupiedSpaces.get(row[i]) == currentPlayer) {
				for (int j = i-1; j > 0; j--) { //Iterate backward from current position where if statement is true try to find
					if (occupiedSpaces.get(row[j]) == getOppositePlayer()) {
						hasOthers = true;	//Found an oppositePlayer disk before finding empty or currentPlayer disk.
					}
					else if (occupiedSpaces.get(row[j]) == currentPlayer && hasOthers) {
						return true;		//Found the currentPlayer and hasOthers is true. We can flip at least one time.
					}
					else if (occupiedSpaces.get(row[j]) == currentPlayer && !hasOthers) {
						return false;		//The value at key = row[j] is the currentPlayer. Cannot flip.
					}
					else if (emptySpaces.contains(row[j])) {
						return false;		//The element is an empty space. Cannot flip.
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Counts the occurrences of Disk.BLACK in occupiedSpaces.
	 * @return int blackCount: The count of how many Disk.BLACK enum exists in occupiedSpaces.
	 */
	public int getCountBlack() {
		int blackCount = 0;
		for (int i = 0; i < occupiedSpaces.size(); i++) {
			int key = (int) occupiedSpaces.keySet().toArray()[i];
			if (occupiedSpaces.get(key) == Disk.BLACK) {
				blackCount++;
			}
		}
		return blackCount;
	}
	
	/**
	 * Counts the occurrences of Disk.WHITE in occupiedSpaces.
	 * @return int whiteCount: The count of how many Disk.WHITE enum exists in occupiedSpaces.
	 */
	public int getCountWhite() {
		int whiteCount = 0;
		for (int i = 0; i < occupiedSpaces.size(); i++) {
			int key = (int) occupiedSpaces.keySet().toArray()[i];
			if (occupiedSpaces.get(key) == Disk.WHITE) {
				whiteCount++;
			}
		}
		return whiteCount;
	}
	
	
	/**
	 * Finds the winner of the game if there is one when the game is over.
	 * @return 	null: If black has the same amount of disks occupying the spaces as white.
	 * 			Disk.WHITE: If white has more Disk in occupiedSpaces than black.
	 * 			Disk.BLACK: If black has more Disk in occupiedSpaces than white.
	 */
	public Disk getWinner() {
		if (isOver() && getCountBlack() == getCountWhite()) {
			return null;
		}
		return (isOver() ? ((getCountBlack() < getCountWhite()) ? Disk.WHITE : Disk.BLACK ): null);
	}
	
	
	/**
	 * The game is over if there are no legal moves left or if the turn is equal to the NUM_SPACES meaning we have filled up the 
	 * board.
	 * @return 	true: no legal moves left (findLegalMove() array is empty) or the number of turns has reached NUM_SPACES.
	 * 			false: there are still legal moves left for the currentPlayer and the number of turns has not reached NUM_SPACES.
	 */
	public boolean isOver() {
		return getLegalMoves().isEmpty() || occupiedSpaces.size() == NUM_SPACES;
	}
	
}
