package TicTacToe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameMain extends JPanel {
    private static final long serialVersionUID = 1L;

    public static final String TITLE = "Tic Tac Toe";
    public static final Color COLOR_BG = Color.WHITE;
    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    private Board board;
    private State currentState;
    private Seed currentPlayer;
    private JLabel statusBar;

    private AIPlayer aiPlayer; // AI player instance
    private boolean playAgainstAI = false; // Flag for AI mode

    /** Constructor */
    public GameMain() {
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int row = mouseY / Cell.SIZE;
                int col = mouseX / Cell.SIZE;

                if (currentState == State.PLAYING) {
                    if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                            && board.cells[row][col].content == Seed.NO_SEED) {
                        currentState = board.stepGame(currentPlayer, row, col);
                        SoundEffect.EAT_FOOD.play(); // Mainkan suara langkah
                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                    }
                } else if (currentState == State.CROSS_WON || currentState == State.NOUGHT_WON) {
                    SoundEffect.EXPLODE.play(); // Mainkan suara kemenangan
                } else if (currentState == State.DRAW) {
                    SoundEffect.DIE.play(); // Mainkan suara seri
                } else {
                    newGame();               
                }
                repaint();
            }
        });

        // Status bar
        statusBar = new JLabel();
        statusBar.setFont(FONT_STATUS);
        statusBar.setBackground(COLOR_BG_STATUS);
        statusBar.setOpaque(true);
        statusBar.setPreferredSize(new Dimension(300, 30));
        statusBar.setHorizontalAlignment(JLabel.LEFT);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));

        super.setLayout(new BorderLayout());
        super.add(statusBar, BorderLayout.PAGE_END);
        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 30));
        super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));

        // Initialize game
        initGame();
        newGame();
    }

    /** Initialize the game */
    public void initGame() {
        board = new Board();
    }

    /** Start a new game */
    public void newGame() {
        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                board.cells[row][col].content = Seed.NO_SEED;
            }
        }
        currentPlayer = Seed.CROSS;
        currentState = State.PLAYING;

        if (playAgainstAI) {
            aiPlayer = new AIPlayerMinimax(board);
            aiPlayer.setSeed(Seed.NOUGHT);
        }
    }

    /** Custom painting */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(COLOR_BG);

        board.paint(g);

        if (currentState == State.PLAYING) {
            statusBar.setForeground(Color.BLACK);
            statusBar.setText((currentPlayer == Seed.CROSS) ? "X's Turn" : "O's Turn");
        } else if (currentState == State.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("It's a Draw! Click to play again.");
        } else if (currentState == State.CROSS_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'X' Won! Click to play again.");
        } else if (currentState == State.NOUGHT_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'O' Won! Click to play again.");
        }
    }

    /** Add menu */
    private void setupMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        JMenuItem playWithAI = new JMenuItem("Play Against AI");
        playWithAI.addActionListener(e -> {
            playAgainstAI = true;
            newGame();
        });

        JMenuItem playWithHuman = new JMenuItem("Play Against Human");
        playWithHuman.addActionListener(e -> {
            playAgainstAI = false;
            newGame();
        });

        gameMenu.add(playWithAI);
        gameMenu.add(playWithHuman);
        menuBar.add(gameMenu);
        frame.setJMenuBar(menuBar);
    }

    /** Main method */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(TITLE);
            GameMain game = new GameMain();
            game.setupMenu(frame);
            frame.setContentPane(game);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
