import java.util.Random;
import java.util.Scanner;

public class Connect4 {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER = 'X';
    private static final char COMPUTER = 'O';

    public static void main(String[] args) {
        char[][] board = new char[ROWS][COLUMNS];
        initializeBoard(board);
        playGame(board);
    }

    private static void initializeBoard(char[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard(char[][] board) {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        for (int j = 0; j < COLUMNS; j++) {
            System.out.print((j + 1) + " ");
        }
        System.out.println();
    }

    private static void playGame(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean isPlayerTurn = true;

        while (true) {
            printBoard(board);
            if (isPlayerTurn) {
                System.out.println("Your turn (Player X). Choose a column (1-7): ");
                int column;
                while (true) {
                    column = scanner.nextInt() - 1;
                    if (column >= 0 && column < COLUMNS && board[0][column] == EMPTY) {
                        break;
                    }
                    System.out.println("Invalid column. Try again.");
                }
                int row = dropPiece(board, column, PLAYER);
                if (checkWin(board, row, column, PLAYER)) {
                    printBoard(board);
                    System.out.println("Congratulations! You win!");
                    break;
                }
            } else {
                System.out.println("Computer's turn (O). Choosing a column...");
                int column;
                while (true) {
                    column = random.nextInt(COLUMNS);
                    if (board[0][column] == EMPTY) {
                        break;
                    }
                }
                int row = dropPiece(board, column, COMPUTER);
                System.out.println("Computer chose column " + (column + 1));
                if (checkWin(board, row, column, COMPUTER)) {
                    printBoard(board);
                    System.out.println("Computer wins! Better luck next time.");
                    break;
                }
            }

            if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a draw!");
                break;
            }

            isPlayerTurn = !isPlayerTurn;
        }

        scanner.close();
    }

    private static int dropPiece(char[][] board, int column, char player) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == EMPTY) {
                board[i][column] = player;
                return i;
            }
        }
        return -1; // Should never happen since we check for valid moves
    }

    private static boolean checkWin(char[][] board, int row, int column, char player) {
        return checkDirection(board, row, column, player, 1, 0) || // Horizontal
               checkDirection(board, row, column, player, 0, 1) || // Vertical
               checkDirection(board, row, column, player, 1, 1) || // Diagonal 
               checkDirection(board, row, column, player, 1, -1);  // Diagonal /
    }

    private static boolean checkDirection(char[][] board, int row, int column, char player, int rowDelta, int colDelta) {
        int count = 1;
        count += countInDirection(board, row, column, player, rowDelta, colDelta);
        count += countInDirection(board, row, column, player, -rowDelta, -colDelta);
        return count >= 4;
    }

    private static int countInDirection(char[][] board, int row, int column, char player, int rowDelta, int colDelta) {
        int count = 0;
        int currentRow = row + rowDelta;
        int currentColumn = column + colDelta;

        while (currentRow >= 0 && currentRow < ROWS && currentColumn >= 0 && currentColumn < COLUMNS && board[currentRow][currentColumn] == player) {
            count++;
            currentRow += rowDelta;
            currentColumn += colDelta;
        }

        return count;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int j = 0; j < COLUMNS; j++) {
            if (board[0][j] == EMPTY) {
                return false;
            }
        }
        return true;
    }
}