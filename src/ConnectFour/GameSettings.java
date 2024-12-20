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

public class GameSettings {
    private static int difficulty = 1; // 1=Easy, 2=Medium, 3=Hard
    private static String theme = "Classic"; // Classic, Space, Ocean
    private static boolean soundEnabled = true;
    private static int playerScore = 0;
    private static int aiScore = 0;

    public static void setDifficulty(int level) {
        difficulty = level;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setTheme(String newTheme) {
        theme = newTheme;
    }

    public static String getTheme() {
        return theme;
    }

    public static boolean isSoundEnabled() {
        return soundEnabled;
    }

    public static void toggleSound() {
        soundEnabled = !soundEnabled;
    }

    public static void updateScore(boolean playerWon) {
        if (playerWon) {
            playerScore++;
        } else {
            aiScore++;
        }
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static int getAIScore() {
        return aiScore;
    }

    public static void resetScores() {
        playerScore = 0;
        aiScore = 0;
    }

    // Theme-specific colors
    public static Color getBackgroundColor() {
        switch (theme) {
            case "Space":
                return new Color(0, 0, 50); // Dark blue for space
            case "Ocean":
                return new Color(0, 105, 148); // Ocean blue
            default:
                return Color.WHITE; // Classic theme (white background)
        }
    }

    public static Color getButtonColor() {
        switch (theme) {
            case "Space":
                return new Color(80, 80, 150); // Darker buttons for Space
            case "Ocean":
                return new Color(0, 150, 205); // Light blue buttons for Ocean
            default:
                return new Color(70, 130, 180); // Default Classic button color
        }
    }

//    public static Font getTextColor() {
//        switch (theme) {
//            case "Space":
//                return Color.WHITE; // White text for Space
//            case "Ocean":
//                return Color.WHITE; // White text for Ocean
//            default:
//                return Color.BLACK; // Classic theme black text
//        }
//    }
}
