package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReversiBoardTest {
	
	private ReversiBoard rb = new ReversiBoard();

	@Test
	void testConstruct() {
		assertEquals(ReversiBoard.Disks.BLACK, rb.getOccupiedSpaces().get(27));
		 
		 
	}

}
