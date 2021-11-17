package model;

import java.io.Serializable;
import java.util.HashMap;

public class ReversiBoard implements Serializable{
	private static final long serialVersionUID = 20211103001L;
	static final int NUM_SPACES = 64;
	private int turn = 0;
	private HashMap<Integer, Disks> occupiedSpaces;
	
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
