
package System;

public class Reader {
    private static Player player1 = new Player();
    private static Player player2 = new Player();
    private static String gameResult = "";
    private static int gameMode = 0;

    public static Player getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Player player1) {
        Reader.player1 = player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static void setPlayer2(Player player2) {
        Reader.player2 = player2;
    }
    
    public static int getGameMode() {
        return gameMode;
    }

    public static void setGameMode(int gameMode) {
        Reader.gameMode = gameMode;
    }
    
    public static String getGameResult() {
        return gameResult;
    }

    public static void setGameResult(String gameResult) {
        Reader.gameResult = gameResult;
    }
    
}
