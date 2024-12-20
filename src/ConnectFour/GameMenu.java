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

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JPanel {
    private JButton newGameBtn;
    private JButton settingsBtn;
    private JButton statsBtn;
    private JButton themeBtn;
    private GameMain gameMain;

    public GameMenu(GameMain gameMain) {
        this.gameMain = gameMain;
        setLayout(new FlowLayout());

        newGameBtn = new JButton("New Game");
        settingsBtn = new JButton("Settings");
        statsBtn = new JButton("Statistics");
        themeBtn = new JButton("Theme");

        // Styling buttons
        styleButton(newGameBtn);
        styleButton(settingsBtn);
        styleButton(statsBtn);
        styleButton(themeBtn);

        // Add action listeners
        newGameBtn.addActionListener(e -> showNewGameDialog());
        settingsBtn.addActionListener(e -> showSettingsDialog());
        statsBtn.addActionListener(e -> showStatsDialog());
        themeBtn.addActionListener(e -> showThemeDialog());

        // Add buttons to panel
        add(newGameBtn);
        add(settingsBtn);
        add(statsBtn);
        add(themeBtn);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(GameSettings.getButtonColor()); // Button color based on theme
//        button.setForeground(GameSettings.getTextColor()); // Text color based on theme
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(100, 30));
    }

    private void showNewGameDialog() {
        Object[] options = {"vs AI", "vs Player", "Tournament Mode"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Choose Game Mode:",
                "New Game",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice >= 0) {
            GameSettings.resetScores();
            gameMain.initGame(choice);
        }
    }

    private void showSettingsDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        // Difficulty setting
        String[] difficulties = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyBox = new JComboBox<>(difficulties);
        difficultyBox.setSelectedIndex(GameSettings.getDifficulty() - 1);

        // Sound toggle
        JCheckBox soundToggle = new JCheckBox("Enable Sound", GameSettings.isSoundEnabled());

        panel.add(new JLabel("Difficulty:"));
        panel.add(difficultyBox);
        panel.add(soundToggle);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Settings",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            GameSettings.setDifficulty(difficultyBox.getSelectedIndex() + 1);
            if (soundToggle.isSelected() != GameSettings.isSoundEnabled()) {
                GameSettings.toggleSound();
            }
        }
    }

    private void showStatsDialog() {
        String stats = String.format(
                "Game Statistics:\n\n" +
                        "X score: %d\n" +
                        "O ScoreScore: %d\n" +
                        "Win Rate: %.1f%%",
                GameSettings.getPlayerScore(),
                GameSettings.getAIScore(),
                calculateWinRate()
        );

        JOptionPane.showMessageDialog(
                this,
                stats,
                "Statistics",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private double calculateWinRate() {
        int totalGames = GameSettings.getPlayerScore() + GameSettings.getAIScore();
        if (totalGames == 0) return 0.0;
        return (GameSettings.getPlayerScore() * 100.0) / totalGames;
    }

    private void showThemeDialog() {
        Object[] themes = {"Classic", "Space", "Ocean"};
        Object selected = JOptionPane.showInputDialog(
                this,
                "Choose Theme:",
                "Theme Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                themes,
                GameSettings.getTheme()
        );

        if (selected != null) {
            GameSettings.setTheme((String) selected);
            // Apply changes immediately after theme selection
            SwingUtilities.updateComponentTreeUI(gameMain);  // Refresh the UI with new theme
        }
    }
}

