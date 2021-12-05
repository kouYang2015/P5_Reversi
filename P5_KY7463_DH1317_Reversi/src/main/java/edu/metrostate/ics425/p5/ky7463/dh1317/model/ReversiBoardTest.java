package edu.metrostate.ics425.p5.ky7463.dh1317.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReversiBoardTest {
	
	private ReversiBoard rb = new ReversiBoard();

	@Test
	void testConstruct() {
		// Tests our setupBoard() helper method successfully populated occupiedSpaces with the correct Disk enum.
		assertEquals(Disk.WHITE, rb.getOccupiedSpaces().get(27));
		assertEquals(Disk.BLACK, rb.getOccupiedSpaces().get(28));
		assertEquals(Disk.BLACK, rb.getOccupiedSpaces().get(35));
		assertEquals(Disk.WHITE, rb.getOccupiedSpaces().get(36));
		assertEquals(1, rb.getTurn());
		
		//Black should be first to move.
		assertEquals(Disk.BLACK, rb.getCurrentPlayer());
		
		// Initial possible moves for Black.
		assertTrue(rb.getLegalMoves().contains(19));
		assertTrue(rb.getLegalMoves().contains(26));
		assertTrue(rb.getLegalMoves().contains(37));
		assertTrue(rb.getLegalMoves().contains(44));
	}
		
	
	@Test
	void testPlace() {
		// Check the first 4 legal moves.
		assertEquals(4, rb.getLegalMoves().size());
		assertTrue(rb.getLegalMoves().contains(19));
		assertTrue(rb.getLegalMoves().contains(26));
		assertTrue(rb.getLegalMoves().contains(37));
		assertTrue(rb.getLegalMoves().contains(44));
		
		// testing placeDisk and flipDisks
		rb.placeDisk(19);
		assertEquals(2, rb.getTurn());
		assertEquals(Disk.BLACK, rb.getOccupiedSpaces().get(19));
		assertEquals(Disk.BLACK, rb.getOccupiedSpaces().get(27));
		assertEquals(Disk.BLACK, rb.getOccupiedSpaces().get(28));
		assertEquals(Disk.BLACK, rb.getOccupiedSpaces().get(35));
		assertEquals(Disk.WHITE, rb.getOccupiedSpaces().get(36));
		
		rb.placeDisk(18);
		assertEquals(Disk.WHITE, rb.getOccupiedSpaces().get(27));
		assertEquals(Disk.WHITE, rb.getOccupiedSpaces().get(18));
		assertEquals(Disk.WHITE, rb.getOccupiedSpaces().get(36));
		
		// testing that legalMove is updating.
		assertEquals(4, rb.getLegalMoves().size());
		assertTrue(rb.getLegalMoves().contains(17));
		assertTrue(rb.getLegalMoves().contains(26));
		assertTrue(rb.getLegalMoves().contains(44));
		assertTrue(rb.getLegalMoves().contains(37));
		
		// emptySpaces should no longer contain the first 2 loc 19 and 18.
		assertFalse(rb.getEmptySpaces().contains(18));
		assertFalse(rb.getEmptySpaces().contains(19));
	}
	
	@Test
	void testCount() {
		// Tests the getCountBlack and getCountWhite before and after a placement of a new disk.
		assertEquals(2, rb.getCountBlack());
		assertEquals(2, rb.getCountWhite());
		rb.placeDisk(44);
		assertEquals(4, rb.getCountBlack());
		assertEquals(1, rb.getCountWhite());
		
	}
	
	@Test 
	void testIsOver() {
		rb.placeDisk(44);
		rb.placeDisk(29);
		rb.placeDisk(18);
		rb.placeDisk(26);
		rb.placeDisk(22);
		
		// Tests that the game is not over. Null winner should be return from getWinner.
		assertFalse(rb.isOver());
		assertNull(rb.getWinner());
	}
	
	@Test 
	void testCurrentPlayer() {
		// Tests the currentplayer is switching after making a move.
		assertEquals(Disk.BLACK, rb.getCurrentPlayer());
		rb.placeDisk(44);
		assertEquals(Disk.WHITE, rb.getCurrentPlayer());
		rb.placeDisk(29);
		assertEquals(Disk.BLACK, rb.getCurrentPlayer());
		rb.placeDisk(18);
		assertEquals(Disk.WHITE, rb.getCurrentPlayer());
		
	}
	
}
