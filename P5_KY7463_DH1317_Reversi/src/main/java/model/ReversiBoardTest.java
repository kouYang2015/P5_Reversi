package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReversiBoardTest {
	
	private ReversiBoard rb = new ReversiBoard();

	@Test
	void testConstruct() {
		ReversiBoard rb2 = new ReversiBoard();
		assertEquals(ReversiBoard.Disks.WHITE, rb.getOccupiedSpaces().get(27));
		assertEquals(ReversiBoard.Disks.BLACK, rb.getOccupiedSpaces().get(28));
		assertEquals(ReversiBoard.Disks.BLACK, rb.getOccupiedSpaces().get(35));
		assertEquals(ReversiBoard.Disks.WHITE, rb.getOccupiedSpaces().get(36));
		assertEquals(1, rb.getTurn());
		
		assertEquals(ReversiBoard.Disks.BLACK, rb.getCurrentPlayer());
		System.out.println(rb.findLegalMove());
		assertTrue(rb.findLegalMove().contains(20));
		assertTrue(rb.findLegalMove().contains(29));
		assertTrue(rb.findLegalMove().contains(34));
		assertTrue(rb.findLegalMove().contains(43));
		 
	}

}
