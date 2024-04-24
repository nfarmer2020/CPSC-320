import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;



public class AliceController {
	private Board[] boardList;

    public static void main(String[] args) {
        AliceController controller = new AliceController();
        controller.go();
    }

    private void go() {
        this.boardList = getBoards();
        if (this.boardList != null) {
            for (int i = 0; i < boardList.length; i++) {
                String path = solveBoard(boardList[i]);
                System.out.println("Board " + (i + 1) + ": " + path);
            }
        } else {
            System.out.println("No boards found.");
        }
    }

    String solveBoard(Board board) {
        String path = "";
        Piece currentPlayer = null;
        int currentPieceRow = 7;
        int currentPieceColumn = 0;
      
        for (int i = 0; i < 8; i++) {
            if (board.getPiece(7, i) != null) {
                currentPlayer = board.getPiece(7, i);
                currentPlayer.setColor(PieceColor.White);
                currentPieceColumn = i;
                break;
            }
        }
        path = recursion("", board, currentPlayer, currentPieceColumn, currentPieceRow, -1, -1);
        if (path != null) {
            path = path.replaceAll("2", "");
            path = path.replaceAll("1", "");
        } else {
            path = "Alice is stuck!";
        }
        return path;
    }

    String recursion(String path, Board board, Piece currentPlayer, int currentPieceColumn,
            int currentPieceRow, int previousPieceColumn, int previousPieceRow) {
       
        path += board.getPiece(currentPieceRow, currentPieceColumn).getSymbol();

   
        board.setPiece(currentPieceRow, currentPieceColumn, null);

        
        if (board.countPieces(PieceColor.Black) == 0) {
            return path;
        }

     
        boolean isStuck = true; 
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (board.isValidCoords(row, column) && board.getPiece(row, column) != null) {
                    Piece nextPiece = board.getPiece(row, column);
                    if (currentPlayer.isValidMove(currentPieceRow, currentPieceColumn, row, column)) {
                    
                        String possibleMove = recursion(path, board, nextPiece, column, row, currentPieceColumn,
                                currentPieceRow);
                        if (possibleMove != null) {
                            isStuck = false; 
                            return possibleMove; 
                        }
                    }
                }
            }
        }

       
        board.setPiece(currentPieceRow, currentPieceColumn, currentPlayer);
        if (isStuck) {
            return null; 
        }
        return null;
    }

    Board[] getBoards() {
        File file = getFileFromChooser();
        if (file != null) {
            try {
                Scanner scan = new Scanner(file);
                int numberOfBoards = scan.nextInt();
                Board[] boardsArray = new Board[numberOfBoards];
                for (int boardNumber = 0; boardNumber < numberOfBoards; boardNumber++) {
                    Board board = new Board();
                    for (int row = 0; row < 8; row++) {
                        for (int col = 0; col < 8; col++) {
                            String scannedVariable = scan.next();
                            switch (scannedVariable) {
                            case "P":
                                board.setPiece(row, col, new Pawn(PieceColor.Black));
                                break;
                            case "B":
                                board.setPiece(row, col, new Bishop(PieceColor.Black));
                                break;
                            case "R":
                                board.setPiece(row, col, new Rook(PieceColor.Black));
                                break;
                            case "N":
                                board.setPiece(row, col, new Knight(PieceColor.Black));
                                break;
                            case "Q":
                                board.setPiece(row, col, new Queen(PieceColor.Black));
                                break;
                            case "K":
                                board.setPiece(row, col, new King(PieceColor.Black));
                                break;
                            }
                        }
                    }
                    boardsArray[boardNumber] = board;
                }
                return boardsArray;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    File getFileFromChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}