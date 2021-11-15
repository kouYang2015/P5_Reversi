package model;

import java.io.Serializable;
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
	
	
	
}
