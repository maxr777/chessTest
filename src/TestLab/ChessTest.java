package TestLab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChessTest {
    @DisplayName("Bounds check - rook in the middle")
    @Test
    void possiblePositionsEdgeCase() {
        int n = 10;
        Main.PieceTypes[][] board = Main.makeBoard(n);
        Main.Point rook = new Main.Point(4, 4);

        Main.setPossiblePositions(board, rook);

        assertEquals(Main.PieceTypes.PossibleMove, board[4][0]);
        assertEquals(Main.PieceTypes.PossibleMove, board[4][9]);
        assertEquals(Main.PieceTypes.PossibleMove, board[0][4]);
        assertEquals(Main.PieceTypes.PossibleMove, board[9][4]);
    }

    @DisplayName("Bounds check - rook in the corner")
    @Test
    void possiblePositionsEdgeCase2() {
        int n = 10;
        Main.PieceTypes[][] board = Main.makeBoard(n);
        Main.Point rook = new Main.Point(0, 0);

        Main.setPossiblePositions(board, rook);

        assertEquals(Main.PieceTypes.PossibleMove, board[9][0]);
        assertEquals(Main.PieceTypes.PossibleMove, board[0][9]);
    }

    @DisplayName("2 rooks in the same line - possible moves should overlap")
    @Test
    void rooksInTheSameLine() {
        int n = 10;
        Main.PieceTypes[][] board = Main.makeBoard(n);

        Main.Point[] rooks = new Main.Point[2];
        rooks[0] = new Main.Point(5, 0);
        rooks[1] = new Main.Point(5, 5);

        Main.setPossiblePositions(board, rooks);

        assertEquals(Main.PieceTypes.PossibleMove, board[5][3]);
    }

    @DisplayName("Pieces should act as obstacles")
    @Test
    void pieceAsObstacle() {
        int n = 10;
        Main.PieceTypes[][] board = Main.makeBoard(n);
        Main.Point rook = new Main.Point(4, 4);
        board[6][4] = Main.PieceTypes.Piece;

        Main.setPossiblePositions(board, rook);

        assertEquals(Main.PieceTypes.PossibleMove, board[5][4]);
        assertEquals(Main.PieceTypes.Empty, board[7][4]);
    }
}