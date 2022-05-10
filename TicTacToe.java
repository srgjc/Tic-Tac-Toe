import java.util.Random;

public class TicTacToe {

    Marker[][] board = new Marker[3][3];

    public boolean markField(int row, int col, Marker marker) {
        if (board[row][col] == null) {
            board[row][col] = marker;
            return true;
        }
        return false;
    }

    public Marker whoWins() {
        if (isInDia1()) return board[0][0];
        if (isInDia2()) return board[board.length - 1][0];

        for (int i = 0; i < board.length; i++) {
            if (isInRow(i)) {
                return board[i][0];
            } else if (isInCol(i)) return board[0][i];
        } return null;
    }

    private boolean isInDia1() {
        if (board[0][0] == null) return false;
        for (int row = 1, col = 1; row < board.length; row++, col++) {
            if (board[row - 1][col - 1] != board[row][col]) {
                return false;
            }
        }return true;
    }

    private boolean isInDia2() {
        if(board[board.length-1][0] == null) return false;
        for (int row = board.length - 1, col = 1; col < board.length; row--, col++) {
            if (board[row][col - 1] != board[row - 1][col]) {
                return false;
            }
        } return true;
    }

    private boolean isInCol(int i) {
        if (board[0][i] == null) return false;
        for (int row = 1; row < board.length; row++) {
            if (board[0][i] != board[row][i]) {
                return false;
            }
        } return true;
    }

    private boolean isInRow(int i) {
        if (board[i][0] == null) return false;
        for (int col = 1; col < board.length; col++) {
            if (board[i][0] != board[i][col]) {
                return false;
            }
        } return true;
    }

    public boolean isGameOver() {
        return ((whoWins() != null) || isFilled());
    }

    public boolean isFilled() {
        for (Marker[] m : board) {
            for (Marker n : m) {
                if (n == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public int size() {
        return board.length;
    }

    public void printBoard() {
        for (Marker[] markers : board) {
            System.out.printf("%n|");
            for (int j = 0; j < board.length; j++) {
                System.out.printf("%s|", markers[j] == null ? " " : markers[j]);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe();
        Random rand = new Random();
        Marker one, two;

        if (Math.random() < 0.5) {
            one = Marker.CROSS;
            two = Marker.CIRCLE;
        } else {
            one = Marker.CIRCLE;
            two = Marker.CROSS;
        }

        while (!ttt.isGameOver()) {

            int nextRow = rand.nextInt(ttt.size());
            int nextCol = rand.nextInt(ttt.size());

            while (!ttt.markField(nextRow, nextCol, one)) {
                nextRow = rand.nextInt(ttt.size());
                nextCol = rand.nextInt(ttt.size());
            }

            ttt.printBoard();

            Marker tmp = one;
            one = two;
            two = tmp;
        }

        Marker winner = ttt.whoWins();
        System.out.println();
        if (winner != null) {
            System.out.printf("%s WINS!", winner);
        } else {
            System.out.print("GAME DRAW");
        }
    }
}

enum Marker {
    CROSS("X"), CIRCLE("O");
    private final String symbol;

    Marker(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
