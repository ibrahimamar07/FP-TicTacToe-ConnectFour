package ConnectFour;

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

    public static void toggleSound() {
        soundEnabled = !soundEnabled;
    }

    public static boolean isSoundEnabled() {
        return soundEnabled;
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
}