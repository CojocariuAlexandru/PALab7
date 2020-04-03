package Game;

import java.util.List;

    /**
    * Everything a game has to implement
    */

public interface GameInterface {

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
