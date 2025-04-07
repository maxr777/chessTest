package TestLab;

public class Main {
    public static void main(String[] args) {
        int n = 10;
        PieceTypes[][] board = makeBoard(10);

        Point[] rooks = new Point[5];
        rooks[0] = new Point(0, 0);
        rooks[1] = new Point(n - 1, n - 1);
        rooks[2] = new Point(5, 5);
        rooks[3] = new Point(9, 0);
        rooks[4] = new Point(0, 9);

        for (Point rook : rooks) {
            board[rook.x][rook.y] = PieceTypes.Rook;
        }

        board[0][6] = PieceTypes.Piece;
        board[1][0] = PieceTypes.Piece;
        board[9][4] = PieceTypes.Piece;
        board[8][9] = PieceTypes.Piece;
        board[4][0] = PieceTypes.Piece;
        board[6][9] = PieceTypes.Piece;
        board[0][8] = PieceTypes.Piece;
        board[9][1] = PieceTypes.Piece;

        setPossiblePositions(board, rooks);
        displayBoard(board);
    }

    static public PieceTypes[][] makeBoard(int n) {
        PieceTypes[][] board = new PieceTypes[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = PieceTypes.Empty;
            }
        }
        return board;
    }

    static public void displayBoard(PieceTypes[][] board) {
        int n = board.length;

        System.out.print("\t");
        for (int i = 0; i < n; i++) {
            System.out.print(i + "\t");
        }

        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < n; j++) {
                switch (board[i][j]) {
                    case Piece:
                        System.out.print("♙\t");
                        break;
                    case Rook:
                        System.out.print("♜\t");
                        break;
                    case Empty:
                        System.out.print("⬜\t");
                        break;
                    case PossibleMove:
                        System.out.print("⬛\t");
                        break;
                }
            }
            System.out.println();
        }
    }

    static public void setPossiblePositions(PieceTypes[][] board, Point rook) {
        Point[] rooks = new Point[1];
        rooks[0] = rook;

        setPossiblePositions(board, rooks);
    }

    static public void setPossiblePositions(PieceTypes[][] board, Point[] rooks) {
        int n = board.length;
        for (Point rook : rooks) {
            int x = rook.x;
            int y = rook.y;

            // mark possible moves to the right
            if (x != n - 1) {
                x++;
                while (x < n && (board[x][y] != PieceTypes.Rook && board[x][y] != PieceTypes.Piece)) {
                    board[x][y] = PieceTypes.PossibleMove;
                    x++;
                }
                x = rook.x;
            }

            // to the left
            if (x != 0) {
                x--;
                while (x >= 0 && (board[x][y] != PieceTypes.Rook && board[x][y] != PieceTypes.Piece)) {
                    board[x][y] = PieceTypes.PossibleMove;
                    x--;
                }
                x = rook.x;
            }

            // up
            if (y != n - 1) {
                y++;
                while (y < n && (board[x][y] != PieceTypes.Rook && board[x][y] != PieceTypes.Piece)) {
                    board[x][y] = PieceTypes.PossibleMove;
                    y++;
                }
                y = rook.y;
            }

            // down
            if (y != 0) {
                y--;
                while (y >= 0 && (board[x][y] != PieceTypes.Rook && board[x][y] != PieceTypes.Piece)) {
                    board[x][y] = PieceTypes.PossibleMove;
                    y--;
                }
            }
        }
    }

    public enum PieceTypes {
        Rook,
        Piece,
        PossibleMove,
        Empty
    }

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}