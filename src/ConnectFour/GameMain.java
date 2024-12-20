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
        import java.awt.event.*;
        import javax.swing.*;

public class GameMain extends JPanel {
    private static final long serialVersionUID = 1L;

    public static final String TITLE = "Connect Four";
    public static final Color COLOR_BG = Color.WHITE;
    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    public static final Color COLOR_CROSS = new Color(239, 105, 80);
    public static final Color COLOR_NOUGHT = new Color(64, 154, 225);
    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    private Board board;
    private State currentState;
    private Seed currentPlayer;
    private JLabel statusBar;
    private boolean isAIGame;
    private AIPlayer aiPlayer;
    private Tournament tournament;
    private GameMenu gameMenu;
    private boolean isTournamentMode;
    public GameMain() {
        // Initialize game menu
        gameMenu = new GameMenu(this);

        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentState == State.PLAYING) {
                    int mouseX = e.getX();
                    int col = mouseX / Cell.SIZE;

                    if (col >= 0 && col < Board.COLS) {
                        // Find empty row in the selected column
                        int row = findEmptyRow(col);
                        if (row != -1) {
                            makeMove(row, col);

                            // Check for game end and handle tournament mode
                            if (currentState != State.PLAYING) {
                                handleGameEnd();
                            } else if (isAIGame && !isTournamentMode) {
                                makeAIMove();
                            }
                        }
                    }
                } else {
                    if (!isTournamentMode || (tournament != null && tournament.isComplete())) {
                        newGame();
                    } else {
                        tournament.startNewMatch();
                        newGame();
                    }
                }
            }
        });

        // Initialize UI components
        setupUI();

        // Initial game setup
        initGame(0); // Default to AI mode
    }

    private void setupUI() {
        statusBar = new JLabel();
//        statusBar.setFont(GameSettings.getTextColor());
        statusBar.setBackground(GameSettings.getBackgroundColor());
        statusBar.setOpaque(true);
        statusBar.setPreferredSize(new Dimension(300, 30));
        statusBar.setHorizontalAlignment(JLabel.LEFT);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));

        setLayout(new BorderLayout());
        add(gameMenu, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 60));
        setBorder(BorderFactory.createLineBorder(GameSettings.getBackgroundColor(), 2, false));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(GameSettings.getBackgroundColor()); // Use the theme color
        board.paint(g);
        updateStatusBar();
    }

        private int findEmptyRow(int col) {
            for (int row = Board.ROWS - 1; row >= 0; row--) {
                if (board.cells[row][col].content == Seed.NO_SEED) {
                    return row;
                }
            }
            return -1;
        }

        private void makeMove(int row, int col) {
            board.cells[row][col].content = currentPlayer;
            currentState = board.stepGame(currentPlayer, row, col);

            if (GameSettings.isSoundEnabled()) {
                AudioPlayer.playSound("klik.wav");
            }

            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
            repaint();
        }

        private void makeAIMove() {
            if (currentPlayer == Seed.NOUGHT && currentState == State.PLAYING) {
                int aiCol = aiPlayer.getBestMove(board);
                int aiRow = findEmptyRow(aiCol);

                if (aiRow != -1) {
                    makeMove(aiRow, aiCol);
                }
            }
        }

        private void handleGameEnd() {
            if (isTournamentMode && tournament != null) {
                boolean playerWon = (currentState == State.CROSS_WON);
                tournament.recordWin(playerWon);
                GameSettings.updateScore(playerWon);

                if (tournament.isComplete()) {
                    showTournamentResults();
                }
            } else if (currentState != State.DRAW) {
                boolean playerWon = (currentState == State.CROSS_WON);
                GameSettings.updateScore(playerWon);
            }
        }

        private void showTournamentResults() {
            String results = tournament.getResults();
            JOptionPane.showMessageDialog(this, results, "Tournament Results",
                    JOptionPane.INFORMATION_MESSAGE);
            tournament = null;
            isTournamentMode = false;
        }

        public void initGame(int gameMode) {
            board = new Board();

            switch (gameMode) {
                case 0: // vs AI
                    isAIGame = true;
                    isTournamentMode = false;
                    aiPlayer = new AIPlayer(Seed.NOUGHT);
                    aiPlayer.setDifficulty(GameSettings.getDifficulty());
                    break;
                case 1: // vs Player
                    isAIGame = false;
                    isTournamentMode = false;
                    aiPlayer = null;
                    break;
                case 2: // Tournament Mode
                    isAIGame = true;
                    isTournamentMode = true;
                    tournament = new Tournament();
                    aiPlayer = new AIPlayer(Seed.NOUGHT);
                    aiPlayer.setDifficulty(3);
                    break;
            }

            newGame();
        }

        public void newGame() {
            board.newGame();
            currentPlayer = Seed.CROSS;
            currentState = State.PLAYING;
            repaint();
        }

        private void updateStatusBar() {
            if (currentState == State.PLAYING) {
                String currentTurn = (currentPlayer == Seed.CROSS) ? "X" : "O";
                String modeInfo = isAIGame ? (isTournamentMode ? " (Tournament Mode)" : " (vs AI)") : " (vs Player)";
                statusBar.setForeground(Color.BLACK);
                statusBar.setText(currentTurn + "'s Turn" + modeInfo);
            } else {
                statusBar.setForeground(Color.RED);
                if (currentState == State.DRAW) {
                    statusBar.setText("It's a Draw! Click to play again.");
                } else {
                    String winner = (currentState == State.CROSS_WON) ? "X" : "O";
                    statusBar.setText("'" + winner + "' Won! Click to play again.");
                    if (GameSettings.isSoundEnabled()) {
                        AudioPlayer.playSound("win.wav");
                    }
                }
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame(TITLE);
                frame.setContentPane(new GameMain());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        }
    }
