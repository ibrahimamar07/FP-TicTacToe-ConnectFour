package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    // Konstanta untuk pemain dan status permainan
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;
    public static final int PLAYING = 0;
    public static final int DRAW = 1;
    public static final int CROSS_WON = 2;
    public static final int NOUGHT_WON = 3;

    // Ukuran papan permainan
    public static final int ROWS = 3, COLS = 3;
    public static int[][] board = new int[ROWS][COLS];
    public static int currentState;
    public static int currentPlayer;
    public static int currentRow, currentCol;
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        // Inisialisasi permainan
        initGame();
        do {
            // Pemain melakukan gerakan
            playerMove(currentPlayer);
            // Perbarui status permainan
            updateGame(currentPlayer, currentRow, currentCol);
            // Tampilkan papan permainan
            printBoard();
            // Ubah giliran pemain
            if (currentState == PLAYING) {
                currentPlayer = (currentPlayer == CROSS) ? NOUGHT : CROSS;
            }
        } while (currentState == PLAYING);
        // Tampilkan hasil akhir
        if (currentState == CROSS_WON) {
            System.out.println("'X' menang! Selamat!");
        } else if (currentState == NOUGHT_WON) {
            System.out.println("'O' menang! Selamat!");
        } else if (currentState == DRAW) {
            System.out.println("Seri! Tidak ada pemenang.");
        }
    }

    // Inisialisasi papan dan status permainan
    public static void initGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col] = EMPTY;
            }
        }
        currentState = PLAYING;
        currentPlayer = CROSS;
    }

    // Proses input gerakan pemain
    public static void playerMove(int theSeed) {
        boolean validInput = false;
        do {
            if (theSeed == CROSS) {
                System.out.print("Pemain 'X', masukkan baris (1-3) dan kolom (1-3): ");
            } else {
                System.out.print("Pemain 'O', masukkan baris (1-3) dan kolom (1-3): ");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY) {
                currentRow = row;
                currentCol = col;
                board[currentRow][currentCol] = theSeed;
                validInput = true;
            } else {
                System.out.println("Gerakan tidak valid! Coba lagi.");
            }
        } while (!validInput);
    }

    // Perbarui status permainan setelah gerakan
    public static void updateGame(int theSeed, int currentRow, int currentCol) {
        if (hasWon(theSeed, currentRow, currentCol)) {
            currentState = (theSeed == CROSS) ? CROSS_WON : NOUGHT_WON;
        } else if (isDraw()) {
            currentState = DRAW;
        }
    }

    // Periksa apakah permainan seri
    public static boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (board[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Periksa apakah pemain saat ini menang
    public static boolean hasWon(int theSeed, int currentRow, int currentCol) {
        return (board[currentRow][0] == theSeed && board[currentRow][1] == theSeed && board[currentRow][2] == theSeed)
            || (board[0][currentCol] == theSeed && board[1][currentCol] == theSeed && board[2][currentCol] == theSeed)
            || (currentRow == currentCol && board[0][0] == theSeed && board[1][1] == theSeed && board[2][2] == theSeed)
            || (currentRow + currentCol == 2 && board[0][2] == theSeed && board[1][1] == theSeed && board[2][0] == theSeed);
    }

    // Tampilkan papan permainan
    public static void printBoard() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                printCell(board[row][col]);
                if (col != COLS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row != ROWS - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    // Tampilkan sel papan
    public static void printCell(int content) {
        switch (content) {
            case EMPTY:  System.out.print("   "); break;
            case NOUGHT: System.out.print(" O "); break;
            case CROSS:  System.out.print(" X "); break;
        }
    }
}
