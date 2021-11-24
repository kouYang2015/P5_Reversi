package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReversiBoardTest {
	
	private ReversiBoard rb = new ReversiBoard();

	@Test
	void testConstruct() {
		assertEquals(ReversiBoard.Disks.WHITE, rb.getOccupiedSpaces().get(27));
		assertEquals(ReversiBoard.Disks.BLACK, rb.getOccupiedSpaces().get(28));
		assertEquals(ReversiBoard.Disks.BLACK, rb.getOccupiedSpaces().get(35));
		assertEquals(ReversiBoard.Disks.WHITE, rb.getOccupiedSpaces().get(36));
		assertEquals(1, rb.getTurn());
		
		assertEquals(ReversiBoard.Disks.BLACK, rb.getCurrentPlayer());
		System.out.println(rb.findLegalMove());
		
		assertTrue(rb.findLegalMove().contains(19));
		assertTrue(rb.findLegalMove().contains(26));
		assertTrue(rb.findLegalMove().contains(37));
		assertTrue(rb.findLegalMove().contains(44));
		
	}
		
	
	@Test
	void testPlace() {
		
		//testing placeDisk and flipDisks
		rb.placeDisk(19);
		assertEquals(2, rb.getTurn());
		assertEquals(ReversiBoard.Disks.BLACK, rb.getOccupiedSpaces().get(19));
		assertEquals(ReversiBoard.Disks.BLACK, rb.getOccupiedSpaces().get(27));
		assertEquals(ReversiBoard.Disks.BLACK, rb.getOccupiedSpaces().get(28));
		assertEquals(ReversiBoard.Disks.BLACK, rb.getOccupiedSpaces().get(35));
		assertEquals(ReversiBoard.Disks.WHITE, rb.getOccupiedSpaces().get(36));
		
		rb.placeDisk(18);
		assertEquals(ReversiBoard.Disks.WHITE, rb.getOccupiedSpaces().get(27));
		assertEquals(ReversiBoard.Disks.WHITE, rb.getOccupiedSpaces().get(18));
		assertEquals(ReversiBoard.Disks.WHITE, rb.getOccupiedSpaces().get(36));
		
		//testing that legalMove is updating
		assertTrue(rb.findLegalMove().contains(17));
		assertTrue(rb.findLegalMove().contains(26));
		assertTrue(rb.findLegalMove().contains(44));
		assertTrue(rb.findLegalMove().contains(37));
		
		assertFalse(rb.findLegalMove().contains(10));
		assertFalse(rb.findLegalMove().contains(20));
		assertFalse(rb.findLegalMove().contains(29));
		assertFalse(rb.findLegalMove().contains(49));
	}
	
	
		
	
	
	

}
