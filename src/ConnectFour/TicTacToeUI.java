package ConnectFour;

import javax.swing.*;
import java.awt.*;

public class TicTacToeUI extends JFrame {
    private JButton[][] buttons;
    private JLabel statusLabel;

    public TicTacToeUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 500);
        setLayout(new BorderLayout());

        // Panel untuk papan permainan
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(6, 7, 6, 7)); // 5x5 grid dengan margin
        boardPanel.setBackground(Color.LIGHT_GRAY);

        buttons = new JButton[6][7];
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 24));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setBackground(Color.WHITE);
                boardPanel.add(buttons[row][col]);
            }
        }

        // Status pemain
        statusLabel = new JLabel("X's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tambahkan panel ke frame
        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeUI::new);
    }
}

