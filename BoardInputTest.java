import junit.framework.TestCase;


public class BoardInputTest extends TestCase {
	
	public void testBoardInput() {
		int[][] b= {{0,0,0,0,0,0,0},
	    		 {0,0,0,0,0,0,0},
	    		 {0,0,0,0,0,0,0},
	    		 {0,0,0,0,0,0,0},
	    		 {0,0,0,0,0,0,0},
	    		 {6,0,0,0,0,0,7},
	    	 	 {6,5,3,5,10,4,7}};
		BoardInput.updateBoard(b);
	}
	
	public void testGetBoard() {
		int[][] b=BoardInput.createBoard();
		Board board=new Board(b);
		board.draw();
	}
}
