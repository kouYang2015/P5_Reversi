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
		System.out.println(rb.getLegalMoves());
		
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
		System.out.print(rb.getCountBlack());
		System.out.print(rb.getCountWhite());
	}
}
