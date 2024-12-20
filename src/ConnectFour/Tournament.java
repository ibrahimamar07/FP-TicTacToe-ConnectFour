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

public class Tournament {
    private static final int MATCHES = 5;
    private int currentMatch = 0;
    private int playerWins = 0;
    private int aiWins = 0;

    public void startNewMatch() {
        currentMatch++;
    }

    public void recordWin(boolean playerWon) {
        if (playerWon) {
            playerWins++;
        } else {
            aiWins++;
        }
    }

    public boolean isComplete() {
        return currentMatch >= MATCHES;
    }

    public String getResults() {
        return String.format(
                "Tournament Results:\n" +
                        "Matches Played: %d/%d\n" +
                        "X Score: %d\n" +
                        "O score: %d\n" +
                        "Result: %s",
                currentMatch, MATCHES,
                playerWins,
                aiWins,
                getDetermineWinner()
        );
    }

    private String getDetermineWinner() {
        if (playerWins > aiWins) {
            return "Player Wins Tournament! 🏆";
        } else if (aiWins > playerWins) {
            return "AI Wins Tournament!";
        } else {
            return "Tournament Draw!";
        }
    }
}







