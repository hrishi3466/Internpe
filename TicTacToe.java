import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean gameOn = true;
        char currentPlayer = 'X';

        System.out.println("Welcome to Tic-Tac-Toe!");
        printBoard();

        while (gameOn) {
            if (currentPlayer == 'X') {
                // Player's turn
                System.out.println("Your turn! Enter position (1-9): ");
                int position = scanner.nextInt();
                if (makeMove(position, currentPlayer)) {
                    printBoard();
                    if (isWinner(currentPlayer)) {
                        System.out.println("Congratulations! You win!");
                        gameOn = false;
                    } else if (isDraw()) {
                        System.out.println("It's a draw!");
                        gameOn = false;
                    } else {
                        currentPlayer = 'O';
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                // Computer's turn
                System.out.println("Computer's turn...");
                int position;
                do {
                    position = random.nextInt(9) + 1; // Random position from 1 to 9
                } while (!makeMove(position, currentPlayer));
                printBoard();
                if (isWinner(currentPlayer)) {
                    System.out.println("Computer wins! Better luck next time.");
                    gameOn = false;
                } else if (isDraw()) {
                    System.out.println("It's a draw!");
                    gameOn = false;
                } else {
                    currentPlayer = 'X';
                }
            }
        }
        scanner.close();
    }

    static void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
    }

    static boolean makeMove(int position, char player) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        if (position < 1 || position > 9 || board[row][col] == 'X' || board[row][col] == 'O') {
            return false;
        }

        board[row][col] = player;
        return true;
    }

    static boolean isWinner(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
