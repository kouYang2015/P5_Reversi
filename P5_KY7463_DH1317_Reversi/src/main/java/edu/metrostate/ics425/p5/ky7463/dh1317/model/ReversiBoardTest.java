package edu.metrostate.ics425.p5.ky7463.dh1317.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReversiBoardTest {
	
	private ReversiBoard rb = new ReversiBoard();

	@Test
	void testConstruct() {
		assertEquals(Disk.WHITE, rb.getOccupiedSpaces().get(27));
		assertEquals(Disk.BLACK, rb.getOccupiedSpaces().get(28));
		assertEquals(Disk.BLACK, rb.getOccupiedSpaces().get(35));
		assertEquals(Disk.WHITE, rb.getOccupiedSpaces().get(36));
		assertEquals(1, rb.getTurn());
		
		assertEquals(Disk.BLACK, rb.getCurrentPlayer());

		assertTrue(rb.getLegalMoves().contains(19));
		assertTrue(rb.getLegalMoves().contains(26));
		assertTrue(rb.getLegalMoves().contains(37));
		assertTrue(rb.getLegalMoves().contains(44));
		
	}
		
	
	@Test
	void testPlace() {
		
		//testing placeDisk and flipDisks
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
		
		//testing that legalMove is updating
		assertTrue(rb.getLegalMoves().contains(17));
		assertTrue(rb.getLegalMoves().contains(26));
		assertTrue(rb.getLegalMoves().contains(44));
		assertTrue(rb.getLegalMoves().contains(37));
		

		assertFalse(rb.getLegalMoves().contains(10));
		assertFalse(rb.getLegalMoves().contains(20));
		assertFalse(rb.getLegalMoves().contains(29));
		assertFalse(rb.getLegalMoves().contains(49));
		

	}
	
	@Test
	void testCount() {
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
		assertFalse(rb.isOver());
		//testing if there is a winner
		assertNull(rb.getWinner());
	}
	
	@Test 
	void testCurrentPlayer() {
		assertEquals(Disk.BLACK, rb.getCurrentPlayer());
		rb.placeDisk(44);
		assertEquals(Disk.WHITE, rb.getCurrentPlayer());
		rb.placeDisk(29);
		assertEquals(Disk.BLACK, rb.getCurrentPlayer());
		rb.placeDisk(18);
		assertEquals(Disk.WHITE, rb.getCurrentPlayer());
		
	}
	
}
