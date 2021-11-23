package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ReversiBoard implements Serializable{
	private static final long serialVersionUID = 20211103001L;
	static final int NUM_SPACES = 64;
	private int turn;
	private HashMap<Integer, Disks> occupiedSpaces;
	private ArrayList<Integer> emptySpaces;
	
	enum Rows {
		HORIZONTAL (new int[][] {{0, 1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 11, 12, 13, 14, 15}, {16, 17, 18, 19, 20, 21, 22, 23},
					{24, 25, 26, 27, 28, 29, 30, 31}, {32, 33, 34, 35, 36, 37, 38, 39}, {40, 41, 42, 43, 44, 45, 46, 47},
					{ 48, 49, 50, 51, 52, 53, 54, 55}, {56, 57, 58, 59, 60, 61, 62, 63}}),
		
		VERTICAL (new int[][] {{0,8,16,24,32,40,48,56}, {1,9,17,25,33,41,49,57}, {2,10,18,26,34,42,50,58},{3,11,19,27,35,43,51,59},
					{4,12,20,28,36,44,52,61}, {5,13,21,29,37,45,53,61}, {6,14,22,30,38,46,54,62}, {7,15,23,31,39,47,55,63}}),
		
		DIAGONAL (new int[][] {{40,49,58}, {32,41,50,59}, {24,33,42,51,60}, {16,25,34,43,52,61}, {8,17,26,35,44,53,62}, 
				{0,9,18,27,36,45,54,63}, {1,10,19,28,37,46,55}, {2,11,20,29,38,47}, {3,12,21,30,39}, {4,13,22,31}, {5,14,23}, {47,54,61},
				{39,46,53,60}, {31,38,45,52,59}, {23,30,37,44,51,58}, {15,22,29,36,43,50,57}, {7,14,21,28,35,42,49,56},
				{6,13,20,27,34,41,48}, {5,12,19,26,33,40}, {4,11,18,25,32}, {3,10,17,24}, {2,9,16}});
		
		private int[][] rows;

		Rows(int[][] rows) {
			this.rows = rows;
		}
		
	}
	
	enum Disks{
		WHITE, BLACK;
	}
	
	/**
	 * No-arg constructor
	 */
	public ReversiBoard() {
		occupiedSpaces = new HashMap<Integer, Disks>();
		occupiedSpaces.put(27, Disks.BLACK);//I dont know if this the proper way to set up the board 
		occupiedSpaces.put(28, Disks.WHITE);
		occupiedSpaces.put(35, Disks.WHITE);
		occupiedSpaces.put(36, Disks.BLACK);
		turn = 0;
		emptySpaces = new ArrayList<Integer>();
		for (int i = 0; i<64 ; i++) {
			if (i != 27 || i != 28 || i != 36 || i != 37) {
				emptySpaces.add(i);
			}
		}
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
	public HashMap<Integer, Disks> getOccupiedSpaces() {
		return (HashMap<Integer, Disks>) occupiedSpaces.clone();
	}
	
	public Disks getCurrentPlayer () {
		return (isOver()) ? null :turn % 2 == 0 ? Disks.BLACK : Disks.WHITE;
	}
	
	private Disks getOppositePlayer() {
		return getCurrentPlayer() == Disks.BLACK ? Disks.WHITE : Disks.BLACK;
	}

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

	public int[][] findRows(int loc){
		int [][] relevantRows = new int[1][1];
		return relevantRows;
	}

	/**
	 * Checks if the currentPlayer has at least one disk in the array.
	 * @param rowArray
	 * @return
	 */
	public boolean arrayContainsCurrentPlayer(int[] rowArray){
		for (int i = 0; i < rowArray.length; i++) {
			if (occupiedSpaces.get(rowArray[i]) == getCurrentPlayer()) {
				return true;
			}
		}
		return false;
	}

	public boolean arrayContainsLoc(int[] rowArray, int loc) {
		for (int i = 0; i < rowArray.length; i++) {
			if (rowArray[i] == loc) {
				return true;
			}
		}
		return false;
	}

	public boolean arrayContainsEmpty(int[] rowArray) {
		for (int i = 0; i < rowArray.length; i++) {
			if (emptySpaces.contains(rowArray[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is only called after we validate it has an empty index inside it. Then we check each pair if there 
	 * @param rowArray
	 * @return
	 */
	public boolean willFlip(int loc, int[] rowArray) {
		int cur = loc;
		boolean hasOther = false;
		boolean endsMine = false;
		while (cur >= 0 && cur <= 64 && arrayContainsLoc(rowArray, cur)) {
			if (occupiedSpaces.get(cur) == getOppositePlayer() && endsMine == false) {
				hasOther = true;
			}
			if (occupiedSpaces.get(cur) == getCurrentPlayer() && hasOther == true) {
				endsMine = true;
			}
			cur++;
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> findLegalMove() {
		ArrayList<Integer> legalMoves = new ArrayList<Integer>();
		for (Rows rows : Rows.values()) { // in the Row class, for each value in Rows
			for (var row : rows.rows) { // var picks HORIZONTAL, VERTICAL, DIAGONAL. for each row inside these
				if (arrayContainsEmpty(row) && arrayContainsCurrentPlayer(row)) { //if the current array has at least one empty spot and has at least on currentplayer disk then it is a relevant array
					for (int i = 1; i < row.length-1; i++) {
						if (occupiedSpaces.get(row[i]).equals(getOppositePlayer()) && emptySpaces.contains(row[i-1])) { //Found a nonempty index at i and previos index is empty
							for (int j = i+1; j < row.length; j++) { //Iterate forward from current position where if statement is true try to find
								if (emptySpaces.contains(row[j])) {
									break;
								}
								else if (occupiedSpaces.get(j) == getCurrentPlayer()) {
									legalMoves.add(row[i-1]);
									break;
								}
							}
						}
						else if (occupiedSpaces.get(row[i]).equals(getOppositePlayer()) && emptySpaces.contains(row[i+1])) { //Found a nonempty index at i and next index is empty
							for (int j = i-1; j > 0; j--) { //Iterate backwards
								if (emptySpaces.contains(row[j])) { 
									break;
								}
								else if (occupiedSpaces.get(j) == getCurrentPlayer()) {
									legalMoves.add(row[i+1]);
									break;
								}
							}
						}
					} // close each index in array for loop
				}
			} // close for each array loop (8, 8, 22 loop)
		} // close for rowEnum loop (3 loop)
		// TODO: implement how we add indexes to legalMoves. Check each vertical,
		// horizontal, diagonal array if it contains at least one
		// currentPlayer color and then iterate through that array to see if any moves
		// exists in it.
		// A move exists if the there is an opposite color in between the current color
		// and an empty index.
		return legalMoves;
	}
	
//	public void findlegalmoveforloop() {
//		for (int i = 0; i < row.length; i++) {
//			// for each index in an array, check if the value exists in occupiedSpaces and
//			// is not in in legalMoves yet.
//			if (!occupiedSpaces.containsKey(row[i]) && !legalMoves.contains(row[i])) { //if true that means this loc is an empty spot
//				// check if there exists at least one color and a non empty space.
//				for (int a = row[i] + 1; a < row.length - 1; a++) {
//					//boolean willFlip = false;
//					if (occupiedSpaces.get(row[a]).equals(getCurrentPlayer()) || emptySpaces.contains(a)) {
//						break; // We stop checking the rest of the index if we find the index after the empty
//								// is the currentPlayer color or is empty
//					} 
//					else {
//						for (int c = row[i]; c < row.length; c++) {
//							if (occupiedSpaces.get(c) == null) {
//								break;
//							} else if (occupiedSpaces.get(c) == getCurrentPlayer()) {
//								legalMoves.add(row[i]);
//							}
//						}
//					}
//				}
//			}
//			for (int b = row[i]; b > row[1]; b--) {
//				if (occupiedSpaces.get(row[b - 1]) == getCurrentPlayer()
//						|| occupiedSpaces.get(row[b - 1]) == null) {
//					break;
//				} else {
//					for (int e = row[i]; e > row[0]; e--) {
//						if (occupiedSpaces.get(e) == null) {
//							break;
//						} else if (occupiedSpaces.get(e) == getCurrentPlayer()) {
//							legalMoves.add(row[i]);
//						}
//					}
//				}
//			} // close reverse iteration
//		} // close each index in array for loop
//	}
	
	
	
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
	
	private boolean placeDisk(Disks currentPlayer, int loc) {
		boolean placed = false;
		if (currentPlayer != null && isValidMove(loc)) {
			occupiedSpaces.put(loc, currentPlayer);
			emptySpaces.remove(loc); //TODO: delete if not using in future. Used to keep track of empty spaces.
			placed = true;
			captureDisks(loc);
			if (!isOver()) {
				turn++;
			}
		}
		return placed;
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
	public boolean flipDisks(Disks currentPlayer, int loc) {
		boolean flipped = false;
		for (Rows rows : Rows.values()) {
			for (var row : rows.rows) {
				for(int i = 0; i < row.length; i++) {
					if(row[i] == loc) {
						for(int a = loc; a < row.length - 1; a++) {
							if(occupiedSpaces.get(a) == getOppositePlayer()) {
								occupiedSpaces.put(a, currentPlayer);
								}else if(occupiedSpaces.get(a) == getCurrentPlayer()) {
									break;
								}
							
							}
						for(int c = loc; c > row[1]; c--) {
							if(occupiedSpaces.get(c) == getOppositePlayer()) {
								occupiedSpaces.put(c, currentPlayer);
								}else if(occupiedSpaces.get(c) == getCurrentPlayer()) {
									break;
								}
							
							}
						
						}
					
					}
					
				}
					flipped = true;
			}
				return flipped;
		}
	

	/**
	 * 
	 * @return
	 */
	public int countBlack() {
		int blackCount = 0;
		for (int i = 0; i < occupiedSpaces.size(); i++) {
			if (occupiedSpaces.get(i) == Disks.BLACK) {
				blackCount++;
			}
		}
		return blackCount;
	}
	
	/**
	 * 
	 * @return whiteCount: an int value representing the total number of the enum Disk whose property is WHITE.
	 */
	public int countWhite() {
		int whiteCount = 0;
		for (int i = 0; i < occupiedSpaces.size(); i++) {
			if (occupiedSpaces.get(i) == Disks.WHITE) {
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
	public Disks getWinner() {
		if (isOver() && countBlack() == countWhite()) { //This means we have a tie.
			return null;
		}
		return isOver() && (countBlack() < countWhite()) ? Disks.WHITE : Disks.BLACK; //TODO: check if isOver() works with < conditional
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
