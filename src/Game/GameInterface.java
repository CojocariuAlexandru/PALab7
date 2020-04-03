package Game;

import java.util.List;

public interface GameInterface {
    /**
     * Everything a game has to implement
     */
    String getCurrentTurnPlayerName(String previousPlayer);
    String getCurrentPlayer();
    Player getWinner();

    List<Token> getAvailableTokens();

    void changeCurrentPlayer();
    void startGameLoop();
    void stopGame();
    boolean checkIfGameEnded();
    boolean checkGameRunning();
}
