//package ConnectFour;
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
///**
// * Tic-Tac-Toe: Two-player Graphic version with better OO design.
// * The Board and Cell classes are separated in their own classes.
// */
//public class GameMain extends JPanel {
//    private static final long serialVersionUID = 1L; // to prevent serializable warning
//
//    // Define named constants for the drawing graphics
//    public static final String TITLE = "Tic Tac Toe";
//    public static final Color COLOR_BG = Color.WHITE;
//    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
//    public static final Color COLOR_CROSS = new Color(239, 105, 80);  // Red #EF6950
//    public static final Color COLOR_NOUGHT = new Color(64, 154, 225); // Blue #409AE1
//    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);
//
//    // Define game objects
//    private Board board;         // the game board
//    private State currentState;  // the current state of the game
//    private Seed currentPlayer;  // the current player
//    private JLabel statusBar;    // for displaying status message
//
//    /** Constructor to setup the UI and game components */
//    public GameMain() {
//
//        // This JPanel fires MouseEvent
//        super.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {  // mouse-clicked handler
//                int mouseX = e.getX();
//                int mouseY = e.getY();
//                // Get the row and column clicked
//                int row = mouseY / Cell.SIZE;
//                int col = mouseX / Cell.SIZE;
//
//                if (currentState == State.PLAYING) {
////                    if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
////                            && board.cells[row][col].content == Seed.NO_SEED) {
////                        // Update cells[][] and return the new game state after the move
////                        currentState = board.stepGame(currentPlayer, row, col);
////                        // Switch player
////                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
////                    }
//                    if (col >= 0 && col < Board.COLS) {
//                        // Look for an empty cell from the bottom-most row
//                        for (int rows = Board.ROWS - 1; rows >= 0; rows--) {
//                            if (board.cells[rows][col].content == Seed.NO_SEED) {
//                                board.cells[rows][col].content = currentPlayer; // Make a move
//                                currentState = board.stepGame(currentPlayer, rows, col); // Update game state
//                                currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS; // Switch player
//                                break;
//                            }
//                        }
//                    }
//
//                } else {        // game over
//                    newGame();  // restart the game
//                }
//                // Refresh the drawing canvas
//                repaint();  // Callback paintComponent().
//            }
//        });
//
//        // Setup the status bar (JLabel) to display status message
//        statusBar = new JLabel();
//        statusBar.setFont(FONT_STATUS);
//        statusBar.setBackground(COLOR_BG_STATUS);
//        statusBar.setOpaque(true);
//        statusBar.setPreferredSize(new Dimension(300, 30));
//        statusBar.setHorizontalAlignment(JLabel.LEFT);
//        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));
//
//        super.setLayout(new BorderLayout());
//        super.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
//        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 30));
//        // account for statusBar in height
//        super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));
//
//        // Set up Game
//        initGame();
//        newGame();
//    }
//
//    /** Initialize the game (run once) */
//    public void initGame() {
//        board = new Board();  // allocate the game-board
//    }
//
//    /** Reset the game-board contents and the current-state, ready for new game */
//    public void newGame() {
//        for (int row = 0; row < Board.ROWS; ++row) {
//            for (int col = 0; col < Board.COLS; ++col) {
//                board.cells[row][col].content = Seed.NO_SEED; // all cells empty
//            }
//        }
//        currentPlayer = Seed.CROSS;    // cross plays first
//        currentState = State.PLAYING;  // ready to play
//    }
//
//
//    /** Custom painting codes on this JPanel */
//    @Override
//    public void paintComponent(Graphics g) {  // Callback via repaint()
//        super.paintComponent(g);
//        setBackground(COLOR_BG); // set its background color
//
//        board.paint(g);  // ask the game board to paint itself
//
//        // Print status-bar message
//        if (currentState == State.PLAYING) {
//            statusBar.setForeground(Color.BLACK);
//            statusBar.setText((currentPlayer == Seed.CROSS) ? "X's Turn" : "O's Turn");
//        } else if (currentState == State.DRAW) {
//            statusBar.setForeground(Color.RED);
//            statusBar.setText("It's a Draw! Click to play again.");
//        } else if (currentState == State.CROSS_WON) {
//            AudioPlayer.playSound("win.wav");
//            statusBar.setForeground(Color.RED);
//            statusBar.setText("'X' Won! Click to play again.");
//        } else if (currentState == State.NOUGHT_WON) {
//     AudioPlayer.playSound("win.wav");
//            statusBar.setForeground(Color.RED);
//            statusBar.setText("'O' Won! Click to play again.");
//        }
//    }
//
//    /** The entry "main" method */
//    public static void main(String[] args) {
//        // Run GUI construction codes in Event-Dispatching thread for thread safety
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                JFrame frame = new JFrame(TITLE);
//                // Set the content-pane of the JFrame to an instance of main JPanel
//                frame.setContentPane(new GameMain());
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.pack();
//                frame.setLocationRelativeTo(null); // center the application window
//                frame.setVisible(true);            // show it
//            }
//        });
//    }
//}
























//package ConnectFour;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.util.Scanner;
//import javax.swing.*;
//
//public class GameMain extends JPanel {
//    private static final long serialVersionUID = 1L;
//
//    public static final String TITLE = "Connect Four";
//    public static final Color COLOR_BG = Color.WHITE;
//    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
//    public static final Color COLOR_CROSS = new Color(239, 105, 80);
//    public static final Color COLOR_NOUGHT = new Color(64, 154, 225);
//    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);
//
//    private Board board;
//    private State currentState;
//    private Seed currentPlayer;
//    private JLabel statusBar;
//
//    private AIPlayer aiPlayer;  // AI player object
//
//    public GameMain() {
//        super.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int mouseX = e.getX();
//                int mouseY = e.getY();
//                int col = mouseX / Cell.SIZE;
//
//                if (currentState == State.PLAYING && col >= 0 && col < Board.COLS) {
//                    for (int row = Board.ROWS - 1; row >= 0; row--) {
//                        if (board.cells[row][col].content == Seed.NO_SEED) {
//                            board.cells[row][col].content = currentPlayer;
//                            currentState = board.stepGame(currentPlayer, row, col);
//                            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
//                            break;
//                        }
//                    }
//                    repaint();
//
//                    // Only trigger AI move if it's AI's turn
//                    if (currentPlayer == Seed.NOUGHT && currentState == State.PLAYING) {
//                        // AI chooses the best column
//                        int aiCol = aiPlayer.getBestMove(board);
//
//                        // Find the row to place the AI's move
//                        int aiRow = -1;
//                        for (int row = Board.ROWS - 1; row >= 0; row--) {
//                            if (board.cells[row][aiCol].content == Seed.NO_SEED) {
//                                aiRow = row;
//                                break;
//                            }
//                        }
//
//                        // Make the AI move
//                        if (aiRow != -1) {
//                            board.cells[aiRow][aiCol].content = Seed.NOUGHT;
//                            currentState = board.stepGame(Seed.NOUGHT, aiRow, aiCol);
//                            currentPlayer = Seed.CROSS;  // Switch back to human player
//                            repaint();
//                        }
//                    }
//                } else {
//                    newGame();
//                }
//            }
//        });
//
//        statusBar = new JLabel();
//        statusBar.setFont(FONT_STATUS);
//        statusBar.setBackground(COLOR_BG_STATUS);
//        statusBar.setOpaque(true);
//        statusBar.setPreferredSize(new Dimension(300, 30));
//        statusBar.setHorizontalAlignment(JLabel.LEFT);
//        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));
//
//        super.setLayout(new BorderLayout());
//        super.add(statusBar, BorderLayout.PAGE_END);
//        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 30));
//        super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));
//
//        initGame();
//        newGame();
//    }
//
//    public void initGame() {
//        Scanner a =new Scanner(System.in);
//        int input=a.nextInt();
//        if(input==1) {
//            board = new Board();
//            aiPlayer = new AIPlayer(Seed.NOUGHT);  // AI will be 'O'
//        }
//        else{
//            board = new Board();
//        }
//    }
//
//    public void newGame() {
//        board.newGame();
//        currentPlayer = Seed.CROSS;  // Player X starts first
//        currentState = State.PLAYING;
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        setBackground(COLOR_BG);
//
//        board.paint(g);
//
//        if (currentState == State.PLAYING) {
//            statusBar.setForeground(Color.BLACK);
//            statusBar.setText((currentPlayer == Seed.CROSS) ? "X's Turn" : "O's Turn");
//        } else if (currentState == State.DRAW) {
//            statusBar.setForeground(Color.RED);
//            statusBar.setText("It's a Draw! Click to play again.");
//        } else if (currentState == State.CROSS_WON) {
//            AudioPlayer.playSound("win.wav");
//            statusBar.setForeground(Color.RED);
//            statusBar.setText("'X' Won! Click to play again.");
//        } else if (currentState == State.NOUGHT_WON) {
//            AudioPlayer.playSound("win.wav");
//            statusBar.setForeground(Color.RED);
//            statusBar.setText("'O' Won! Click to play again.");
//        }
//    }
//
//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame(TITLE);
//            frame.setContentPane(new GameMain());
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });
//    }
//}






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

    private AIPlayer aiPlayer;  // AI player object

    public GameMain() {
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int col = mouseX / Cell.SIZE;

                if (currentState == State.PLAYING && col >= 0 && col < Board.COLS) {
                    for (int row = Board.ROWS - 1; row >= 0; row--) {
                        if (board.cells[row][col].content == Seed.NO_SEED) {
                            board.cells[row][col].content = currentPlayer;
                            currentState = board.stepGame(currentPlayer, row, col);
                            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                            break;
                        }
                    }
                    repaint();

                    // Only trigger AI move if it's AI's turn
                    if (currentPlayer == Seed.NOUGHT && currentState == State.PLAYING) {
                        // AI chooses the best column
                        int aiCol = aiPlayer.getBestMove(board);

                        // Find the row to place the AI's move
                        int aiRow = -1;
                        for (int row = Board.ROWS - 1; row >= 0; row--) {
                            if (board.cells[row][aiCol].content == Seed.NO_SEED) {
                                aiRow = row;
                                break;
                            }
                        }

                        // Make the AI move
                        if (aiRow != -1) {
                            board.cells[aiRow][aiCol].content = Seed.NOUGHT;
                            currentState = board.stepGame(Seed.NOUGHT, aiRow, aiCol);
                            currentPlayer = Seed.CROSS;  // Switch back to human player
                            repaint();
                        }
                    }
                } else {
                    newGame();
                }
            }
        });

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

        initGame();
        newGame();
    }

    // Method to initialize the game based on user choice (AI or human opponent)
    public void initGame() {
        String[] options = {"AI", "Human"};
        int input = JOptionPane.showOptionDialog(
                null,
                "Choose your opponent:",
                "Select Opponent",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (input == 0) { // AI vs Player
            board = new Board();
            aiPlayer = new AIPlayer(Seed.NOUGHT);  // AI will be 'O'
        } else { // Human vs Human
            board = new Board();
        }
    }

    public void newGame() {
        board.newGame();
        currentPlayer = Seed.CROSS;  // Player X starts first
        currentState = State.PLAYING;
    }

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
            AudioPlayer.playSound("win.wav");
            statusBar.setForeground(Color.RED);
            statusBar.setText("'X' Won! Click to play again.");
        } else if (currentState == State.NOUGHT_WON) {
            AudioPlayer.playSound("win.wav");
            statusBar.setForeground(Color.RED);
            statusBar.setText("'O' Won! Click to play again.");
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(TITLE);
            frame.setContentPane(new GameMain());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}


//package ConnectFour;
//
//import java.awt.*;
//        import java.awt.event.*;
//        import javax.swing.*;
//
//public class GameMain extends JPanel {
//    private static final long serialVersionUID = 1L;
//
//    public static final String TITLE = "Connect Four";
//    public static final Color COLOR_BG = Color.WHITE;
//    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
//    public static final Color COLOR_CROSS = new Color(239, 105, 80);
//    public static final Color COLOR_NOUGHT = new Color(64, 154, 225);
//    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);
//
//    private Board board;
//    private State currentState;
//    private Seed currentPlayer;
//    private JLabel statusBar;
//
//    private AIPlayer aiPlayer;  // AI player object
//
//    public GameMain() {
//        super.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int mouseX = e.getX();
//                int mouseY = e.getY();
//                int col = mouseX / Cell.SIZE;
//
//                if (currentState == State.PLAYING && col >= 0 && col < Board.COLS) {
//                    for (int row = Board.ROWS - 1; row >= 0; row--) {
//                        if (board.cells[row][col].content == Seed.NO_SEED) {
//                            board.cells[row][col].content = currentPlayer;
//                            currentState = board.stepGame(currentPlayer, row, col);
//                            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
//                            break;
//                        }
//                    }
//                    repaint();
//
//                    // Only trigger AI move if it's AI's turn
//                    if (currentPlayer == Seed.NOUGHT && currentState == State.PLAYING) {
//                        // AI chooses the best column
//                        int aiCol = aiPlayer.getBestMove(board);
//
//                        // Find the row to place the AI's move
//                        int aiRow = -1;
//                        for (int row = Board.ROWS - 1; row >= 0; row--) {
//                            if (board.cells[row][aiCol].content == Seed.NO_SEED) {
//                                aiRow = row;
//                                break;
//                            }
//                        }
//
//                        // Make the AI move
//                        if (aiRow != -1) {
//                            board.cells[aiRow][aiCol].content = Seed.NOUGHT;
//                            currentState = board.stepGame(Seed.NOUGHT, aiRow, aiCol);
//                            currentPlayer = Seed.CROSS;  // Switch back to human player
//                            repaint();
//                        }
//                    }
//                } else {
//                    newGame();
//                }
//            }
//        });
//
//        statusBar = new JLabel();
//        statusBar.setFont(FONT_STATUS);
//        statusBar.setBackground(COLOR_BG_STATUS);
//        statusBar.setOpaque(true);
//        statusBar.setPreferredSize(new Dimension(300, 30));
//        statusBar.setHorizontalAlignment(JLabel.LEFT);
//        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));
//
//        super.setLayout(new BorderLayout());
//        super.add(statusBar, BorderLayout.PAGE_END);
//        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 30));
//        super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));
//
//        initGame();
//        newGame();
//    }
//
//    // Method to initialize the game based on user choice (AI or human opponent)
//    public void initGame() {
//        String[] options = {"AI", "Human"};
//        int input = JOptionPane.showOptionDialog(
//                null,
//                "Choose your opponent:",
//                "Select Opponent",
//                JOptionPane.DEFAULT_OPTION,
//                JOptionPane.INFORMATION_MESSAGE,
//                null,
//                options,
//                options[0]
//        );
//
//        if (input == 0) { // AI vs Player
//            board = new Board();
//            aiPlayer = new AIPlayer(Seed.NOUGHT);  // AI will be 'O'
//        } else { // Human vs Human
//            board = new Board();
//        }
//    }
//
//    // Method to initialize menu
//    public void initMenu(JFrame frame) {
//        JMenuBar menuBar = new JMenuBar();
//
//        // Create "Game" menu
//        JMenu gameMenu = new JMenu("Game");
//
//        // Create "New Game" item
//        JMenuItem newGameMenuItem = new JMenuItem("New Game");
//        newGameMenuItem.addActionListener(e -> newGame());
//        gameMenu.add(newGameMenuItem);
//
//        // Create "Restart Game" item
//        JMenuItem restartGameMenuItem = new JMenuItem("Restart Game");
//        restartGameMenuItem.addActionListener(e -> restartGame());
//        gameMenu.add(restartGameMenuItem);
//
//        // Add "Game" menu to menu bar
//        menuBar.add(gameMenu);
//
//        // Set the menu bar
//        frame.setJMenuBar(menuBar);
//    }
//
//    // Method to start a new game
//    public void newGame() {
//        initGame();
//        board.newGame();
//        currentPlayer = Seed.CROSS;  // Player X starts first
//        currentState = State.PLAYING;
//        repaint();
//    }
//
//    // Method to restart the current game
//    public void restartGame() {
//        board.newGame();
//        currentPlayer = Seed.CROSS;  // Player X starts first
//        currentState = State.PLAYING;
//        repaint();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        setBackground(COLOR_BG);
//
//        board.paint(g);
//
//        if (currentState == State.PLAYING) {
//            statusBar.setForeground(Color.BLACK);
//            statusBar.setText((currentPlayer == Seed.CROSS) ? "X's Turn" : "O's Turn");
//        } else if (currentState == State.DRAW) {
//            statusBar.setForeground(Color.RED);
//            statusBar.setText("It's a Draw! Click to play again.");
//        } else if (currentState == State.CROSS_WON) {
//            AudioPlayer.playSound("win.wav");
//            statusBar.setForeground(Color.RED);
//            statusBar.setText("'X' Won! Click to play again.");
//        } else if (currentState == State.NOUGHT_WON) {
//            AudioPlayer.playSound("win.wav");
//            statusBar.setForeground(Color.RED);
//            statusBar.setText("'O' Won! Click to play again.");
//        }
//    }
//
//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame(TITLE);
//            frame.setContentPane(new GameMain());
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//
//            // Initialize menu after the JFrame has been created
//            ((GameMain) frame.getContentPane()).initMenu(frame);
//        });
//    }
//}
