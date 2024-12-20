/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group 15
 * 1 - 5026231195- ibrahim amar alfanani
 * 2 - 5026231219- ghifari rabbani A
 * 3 - 5026231180 - favian astama
 */




package ConnectFour;

import java.awt.*;
/**
 * The Board class models the ROWS-by-COLS game board.
 */
public class Board {
    // Define named constants
    public static final int ROWS = 6;  // ROWS x COLS cells
    public static final int COLS = 7;
    // Define named constants for drawing
    public static final int CANVAS_WIDTH = Cell.SIZE * COLS;  // the drawing canvas
    public static final int CANVAS_HEIGHT = Cell.SIZE * ROWS;
    public static final int GRID_WIDTH = 8;  // Grid-line's width
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2; // Grid-line's half-width
    public static final Color COLOR_GRID = Color.LIGHT_GRAY;  // grid lines
    public static final int Y_OFFSET = 1;  // Fine tune for better display

    // Define properties (package-visible)
    /** Composes of 2D array of ROWS-by-COLS Cell instances */
    Cell[][] cells;

    /** Constructor to initialize the game board */
    public Board() {
        initGame();
    }

    /** Initialize the game objects (run once) */
    public void initGame() {
        cells = new Cell[ROWS][COLS]; // allocate the array
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                // Allocate element of the array
                cells[row][col] = new Cell(row, col);
                // Cells are initialized in the constructor
            }
        }
    }

    /** Reset the game board, ready for new game */
    public void newGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].newGame(); // clear the cell content
            }
        }
    }

    /**
     *  The given player makes a move on (selectedRow, selectedCol).
     *  Update cells[selectedRow][selectedCol]. Compute and return the
     *  new game state (PLAYING, DRAW, CROSS_WON, NOUGHT_WON).
     */
    /**
     * Check if the given player has won after placing a seed at (rowSelected, colSelected).
     */
    public boolean hasWon(Seed theSeed, int rowSelected, int colSelected) {
        // Check for 4-in-a-line on the row
        int count = 0;
        for (int col = 0; col < COLS; ++col) {
            if (cells[rowSelected][col].content == theSeed) {
                ++count;
                if (count == 4) return true;  // found 4 in a row
            } else {
                count = 0; // reset counter
            }
        }

        // Check for 4-in-a-line on the column
        count = 0;
        for (int row = 0; row < ROWS; ++row) {
            if (cells[row][colSelected].content == theSeed) {
                ++count;
                if (count == 4) return true;  // found 4 in a column
            } else {
                count = 0; // reset counter
            }
        }

        // Check for 4-in-a-line on diagonal (top-left to bottom-right)
        count = 0;
        int startRow = Math.max(rowSelected - colSelected, 0);
        int startCol = Math.max(colSelected - rowSelected, 0);
        while (startRow < ROWS && startCol < COLS) {
            if (cells[startRow][startCol].content == theSeed) {
                ++count;
                if (count == 4) return true;  // found 4 in a diagonal
            } else {
                count = 0; // reset counter
            }
            ++startRow;
            ++startCol;
        }

        // Check for 4-in-a-line on opposite diagonal (top-right to bottom-left)
        count = 0;
        startRow = Math.min(rowSelected + colSelected, ROWS - 1);
        startCol = Math.max(colSelected - (ROWS - 1 - rowSelected), 0);
        while (startRow >= 0 && startCol < COLS) {
            if (cells[startRow][startCol].content == theSeed) {
                ++count;
                if (count == 4) return true;  // found 4 in an opposite diagonal
            } else {
                count = 0; // reset counter
            }
            --startRow;
            ++startCol;
        }

        // No 4-in-a-line found
        return false;
    }





    //...
//    public State stepGame(Seed player, int selectedRow, int selectedCol) {
//        // Update game board
//        cells[selectedRow][selectedCol].content = player;
//
//        // Compute and return the new game state
//        if (cells[selectedRow][0].content == player  // 3-in-the-row
//                && cells[selectedRow][1].content == player
//                && cells[selectedRow][2].content == player
//                || cells[0][selectedCol].content == player // 3-in-the-column
//                && cells[1][selectedCol].content == player
//                && cells[2][selectedCol].content == player
//                || selectedRow == selectedCol     // 3-in-the-diagonal
//                && cells[0][0].content == player
//                && cells[1][1].content == player
//                && cells[2][2].content == player
//                || selectedRow + selectedCol == 2 // 3-in-the-opposite-diagonal
//                && cells[0][2].content == player
//                && cells[1][1].content == player
//                && cells[2][0].content == player) {
//            return (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
//        } else {
//            // Nobody win. Check for DRAW (all cells occupied) or PLAYING.
//            for (int row = 0; row < ROWS; ++row) {
//                for (int col = 0; col < COLS; ++col) {
//                    if (cells[row][col].content == Seed.NO_SEED) {
//                        return State.PLAYING; // still have empty cells
//                    }
//                }
//            }
//            return State.DRAW; // no empty cell, it's a draw
//        }
    //}
    public State stepGame(Seed player, int selectedRow, int selectedCol) {
        // Update game board
        cells[selectedRow][selectedCol].content = player;

        // Check if the player has won
        if (hasWon(player, selectedRow, selectedCol)) {
            return (player == Seed.CROSS) ? State.CROSS_WON : State.NOUGHT_WON;
        }

        // Check for DRAW or continue PLAYING
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (cells[row][col].content == Seed.NO_SEED) {
                    return State.PLAYING; // still have empty cells
                }
            }
        }
        return State.DRAW; // no empty cell, it's a draw
    }


    /** Paint itself on the graphics canvas, given the Graphics context */
    public void paint(Graphics g) {
        // Draw the grid-lines
        g.setColor(COLOR_GRID);
        for (int row = 1; row < ROWS; ++row) {
            g.fillRoundRect(0, Cell.SIZE * row - GRID_WIDTH_HALF,
                    CANVAS_WIDTH - 1, GRID_WIDTH,
                    GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < COLS; ++col) {
            g.fillRoundRect(Cell.SIZE * col - GRID_WIDTH_HALF, 0 + Y_OFFSET,
                    GRID_WIDTH, CANVAS_HEIGHT - 1,
                    GRID_WIDTH, GRID_WIDTH);
        }

        // Draw all the cells
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint(g);  // ask the cell to paint itself
            }
        }
    }
    public boolean isGameOver() {
        // Check for a win
        if (isWin(Seed.CROSS) || isWin(Seed.NOUGHT)) {
            return true;
        }

        // Check if the board is full (no empty cells)
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (cells[row][col].content == Seed.NO_SEED) {
                    return false;  // Board is not full, game is not over
                }
            }
        }

        // If the board is full and there's no winner, it's a draw
        return true;
    }

    // Method to check if a player has won the game
    public boolean isWin(Seed seed) {
        // Check horizontal, vertical, and diagonal lines for a win
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (cells[row][col].content == seed) {
                    if (checkDirection(row, col, 1, 0, seed) ||  // Horizontal
                            checkDirection(row, col, 0, 1, seed) ||  // Vertical
                            checkDirection(row, col, 1, 1, seed) ||  // Diagonal /
                            checkDirection(row, col, 1, -1, seed)) { // Diagonal \
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Method to check a specific direction for a winning line (4 in a row)
    private boolean checkDirection(int row, int col, int rowDir, int colDir, Seed seed) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int r = row + i * rowDir;
            int c = col + i * colDir;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS && cells[r][c].content == seed) {
                count++;
            } else {
                break;
            }
        }
        return count == 4;
    }

}
