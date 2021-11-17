package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ReversiBoard implements Serializable{
	private static final long serialVersionUID = 20211103001L;
	static final int NUM_SPACES = 64;
	private int turn = 0;
	private HashMap<Integer, Disks> occupiedSpaces;
	
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
