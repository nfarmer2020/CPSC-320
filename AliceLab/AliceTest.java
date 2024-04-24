import static org.junit.Assert.fail;

import org.junit.Test;

public class AliceTest {

	@Test
    public void testSolveBoard() {
        AliceController controller = new AliceController();
        Board testBoard = new Board(); 
       
        testBoard.setPiece(7, 0, new Pawn(PieceColor.Black));
       
        String path = controller.solveBoard(testBoard);
        
        if (path == null) {
            fail("solveBoard failed");
        }
       
    }
	@Test
    public void testRecursion() {
        AliceController controller = new AliceController();
        Board testBoard = new Board(); 
       
        testBoard.setPiece(7, 0, new Pawn(PieceColor.Black));
       
        String path = controller.recursion("", testBoard, null, 0, 7, -1, -1);
        
        if (path == null) {
            fail("Recursion failed");
        }
        
    }
	@Test
    public void testGetBoards() {
        AliceController controller = new AliceController();
       
        Board[] boards = controller.getBoards();
        
        if (boards == null) {
            fail("Boards were not got");
        }
     
    }
	
	
}
