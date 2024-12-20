//package  ConnectFour;
//
//public class AIPlayer {
//    private Seed aiSeed;
//    private Seed opponentSeed;
//    private static final int MAX_DEPTH = 5;  // Adjust for difficulty level
//
//    public AIPlayer(Seed aiSeed) {
//        this.aiSeed = aiSeed;
//        this.opponentSeed = (aiSeed == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
//    }
//
//    // AI chooses the best move using Minimax Algorithm with Alpha-Beta Pruning
//    public int getBestMove(Board board) {
//        int bestCol = -1;
//        int bestScore = Integer.MIN_VALUE;
//
//        for (int col = 0; col < Board.COLS; col++) {
//            // Check if the column is valid for dropping a piece
//            for (int row = Board.ROWS - 1; row >= 0; row--) {
//                if (board.cells[row][col].content == Seed.NO_SEED) {
//                    // Simulate the move
//                    board.cells[row][col].content = aiSeed;
//
//                    // Get the score for this move
//                    int score = minimax(board, MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
//
//                    // Undo the move
//                    board.cells[row][col].content = Seed.NO_SEED;
//
//                    // Choose the move with the best score
//                    if (score > bestScore) {
//                        bestScore = score;
//                        bestCol = col;
//                    }
//                    break;
//                }
//            }
//        }
//
//        return bestCol;
//    }







package ConnectFour;

public class AIPlayer {
    private Seed aiSeed;
    private Seed opponentSeed;
    private int difficulty; // 1=Easy, 2=Medium, 3=Hard
    private static final int MAX_DEPTH = 5;

    public AIPlayer(Seed aiSeed) {
        this.aiSeed = aiSeed;
        this.opponentSeed = (aiSeed == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        this.difficulty = 1; // Default to easy
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getBestMove(Board board) {
        // Adjust search depth based on difficulty
        int searchDepth = switch(difficulty) {
            case 1 -> 2; // Easy: shallow search
            case 2 -> 4; // Medium: moderate search
            case 3 -> MAX_DEPTH; // Hard: deep search
            default -> MAX_DEPTH;
        };

        int bestCol = -1;
        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        // Add randomness for easier difficulties
        if (difficulty < 3) {
            if (Math.random() < 0.3) { // 30% chance of random move
                return (int) (Math.random() * Board.COLS);
            }
        }

        for (int col = 0; col < Board.COLS; col++) {
            for (int row = Board.ROWS - 1; row >= 0; row--) {
                if (board.cells[row][col].content == Seed.NO_SEED) {
                    board.cells[row][col].content = aiSeed;
                    int score = minimax(board, searchDepth, alpha, beta, false);
                    board.cells[row][col].content = Seed.NO_SEED;

                    if (score > bestScore) {
                        bestScore = score;
                        bestCol = col;
                    }
                    break;
                }
            }
        }

        return bestCol;
    }

    // Minimax algorithm with Alpha-Beta pruning
    private int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        if (depth == 0 || board.isGameOver()) {
            return evaluateBoard(board);
        }

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int col = 0; col < Board.COLS; col++) {
                for (int row = Board.ROWS - 1; row >= 0; row--) {
                    if (board.cells[row][col].content == Seed.NO_SEED) {
                        board.cells[row][col].content = aiSeed;
                        int eval = minimax(board, depth - 1, alpha, beta, false);
                        board.cells[row][col].content = Seed.NO_SEED;
                        maxEval = Math.max(maxEval, eval);
                        alpha = Math.max(alpha, eval);
                        if (beta <= alpha) break;
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int col = 0; col < Board.COLS; col++) {
                for (int row = Board.ROWS - 1; row >= 0; row--) {
                    if (board.cells[row][col].content == Seed.NO_SEED) {
                        board.cells[row][col].content = opponentSeed;
                        int eval = minimax(board, depth - 1, alpha, beta, true);
                        board.cells[row][col].content = Seed.NO_SEED;
                        minEval = Math.min(minEval, eval);
                        beta = Math.min(beta, eval);
                        if (beta <= alpha) break;
                    }
                }
            }
            return minEval;
        }
    }

    // Evaluates the board and returns a score
    private int evaluateBoard(Board board) {
        if (board.isWin(aiSeed)) {
            return 1000;  // AI wins
        } else if (board.isWin(opponentSeed)) {
            return -1000; // Opponent wins
        } else {
            return evaluateBoardHeuristics(board); // Evaluate based on heuristics
        }
    }

    // A simple heuristic evaluation based on the number of connected pieces
    private int evaluateBoardHeuristics(Board board) {
        int score = 0;

        // Horizontal, vertical, and diagonal checks (you can enhance this)
        score += evaluateLines(board, aiSeed);
        score -= evaluateLines(board, opponentSeed);

        return score;
    }

    // Evaluates the lines for a given seed (AI or opponent)
    private int evaluateLines(Board board, Seed seed) {
        int score = 0;
        // Evaluate horizontal, vertical, and diagonal lines for the seed
        // You can implement specific logic for counting connected pieces (e.g., 2 in a row, 3 in a row, etc.)
        // Example (very simplified):
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS - 3; col++) {
                if (board.cells[row][col].content == seed &&
                        board.cells[row][col+1].content == seed &&
                        board.cells[row][col+2].content == seed &&
                        board.cells[row][col+3].content == seed) {
                    score += 10;  // Award points for a line of 4 connected pieces
                }
            }
        }
        return score;
    }
}
