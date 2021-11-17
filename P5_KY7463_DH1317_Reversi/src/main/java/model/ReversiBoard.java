package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ReversiBoard implements Serializable{
	private static final long serialVersionUID = 20211103001L;
	static final int NUM_SPACES = 64;
	private int turn = 0;
	private HashMap<Integer, Disks> occupiedSpaces;
	
	enum Rows {
		HORIZONTAL (new int[][] {{0, 1, 2, 3, 4, 5, 6, 7}, {8, 9, 10, 11, 12, 13, 14, 15}, {16, 17, 18, 19, 20, 21, 22, 23},
					{24, 25, 26, 27, 28, 29, 30, 31},{32, 33, 34, 35, 36, 37, 38, 39}, {40, 41, 42, 43, 44, 45, 46, 47},
					{ 48, 49, 50, 51, 52, 53, 54, 55},{56, 57, 58, 59, 60, 61, 62, 63}});
		
	}
	
	enum Disks{
		WHITE, BLACK;
	}
	
	public ReversiBoard() {
		occupiedSpaces = new HashMap<Integer, Disks>();
		turn = 0;
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
	
	public ArrayList<Integer> findLegalMove(){
		ArrayList<Integer> legalMoves = new ArrayList<Integer>();
		//TODO: implement how we add indexes to legalMoves. Check each vertical, horizontal, diagonal array if it contains at least one
		//currentPlayer color and then iterate through that array to see if any moves exists in it.
		return legalMoves;
	}
	

	public Disks findWinner() {
		return blackCount < whiteCount ? Disks.WHITE : Disks.BLACK;
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
