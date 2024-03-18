// Nur Ahmed
// CSE 123 AO
// C0: Abstract Strategy Games
// 4.12.2023
// A class to represent a game of Connect Four that implements the 
// AbstractStrategyGame interface.
import java.util.*;

public class ConnectFour implements AbstractStrategyGame {
    private char[][] board;
    private char currPlayer;
    private boolean isRedTurn;
    

    // Constructs a new ConnectFour game.
    public ConnectFour() {
        board = new char[][]{{'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'},
                             {'-', '-', '-', '-', '-', '-', '-'}};
        isRedTurn = true;
        currPlayer = 'R';
    }

    // Returns whether or not the game is over.
    public boolean isGameOver() {
        return getWinner() >= 0;
    }

    // Checks all possible patterns of a win (horizontally, vertically, and diagonally)
    // It returns the index of the winner. 1 if player 1 (R), 2 if player 2 (Y),
    // 0 if a tie occurred, and -1 if the game is not over.
    public int getWinner() {
        // Checks horizontal wins
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == currPlayer &&
                board[row][col+1] == currPlayer &&
                board[row][col+2] == currPlayer &&
                board[row][col+3] == currPlayer) {
                return currPlayer == 'R' ? 1 : 2;
                }
            }
        }
        // Checks vertical wins
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == currPlayer &&
                board[row + 1][col] == currPlayer &&
                board[row + 2][col] == currPlayer &&
                board[row + 3][col] == currPlayer) {
                return currPlayer == 'R' ? 1 : 2;
                }
            }
        }
        // Checks diagonal wins
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row ][col] == currPlayer &&
                board[row + 1][col + 1] == currPlayer &&
                board[row + 2][col + 2] == currPlayer &&
                board[row + 3][col + 3] == currPlayer) {
                return currPlayer == 'R' ? 1 : 2;
                }
            }
        }

        // Checks diagonal wins
        for (int row = 0; row < 3; row++) {
            for (int col = 3; col < 7; col++) {
                if (board[row][col] == currPlayer &&
                board[row + 1][col - 1] == currPlayer &&
                board[row + 2][col - 2] == currPlayer &&
                board[row + 3][col - 3] == currPlayer) {
                return currPlayer == 'R' ? 1 : 2;
                }
            }
        }

        // Checks if there are still playable spaces and keeps the game going
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == '-') {
                    return -1;
                }
            }
        }

        // it's a tie!
        return 0;
    }

    // Returns the index of which player's turn it is.
    // 1 if player 1 (R), 2 if player 2 (Y).
    public int getNextPlayer() {
        return isRedTurn ? 1 : 2;
    }

    // Given the input, places an R or an Y where
    // the player specifies.
    public void makeMove(Scanner input) {
        currPlayer = isRedTurn ? 'R' : 'Y';
        System.out.print("Column? ");
        int col = input.nextInt();
        makeMove(col, currPlayer);
        isRedTurn = !isRedTurn;
    }

    // Private helper method for makeMove.
    // Given a col, as well as player index,
    // places an R or a Y in that col.
    // Throws an IllegalArgumentException if the position is
    // invalid, whether that be out of bounds or already occupied.
    // Board bounds are [0, 6] for cols.
    private void makeMove(int col, char player) {
        if (col < 0 || col > board[0].length) {
                throw new IllegalArgumentException("Invalid board position: " + col);
        }
        int row = board.length - 1;
        while (row >= 0 && board[row][col] != '-') {
            row--;
        }

        if (row < 0) {
            throw new IllegalArgumentException("Space already occupied: " + col);
        }
        
        board[row][col] = player;
    }

    // Returns a String containing instructions to play the game.
    public String instructions() {
        String result = "";
        result += "Player 1 is Red and goes first. Choose where to play by entering a column\n";
        result += "number between 0 and 6 (inclusive). Spaces show as a '-' are empty. The game\n";
        result += "ends when one player marks four spaces in a row horizontally, vertically, or\n";
        result += "diagonally in which case that player wins, or when the board is full\n";
        result += "which ends the game in a tie.";
        return result;
    }

    // Returns a String representation of the current state of the board.
    public String toString() {
        String result = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <= board.length; j++) {
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
}