package Game;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import ArithmeticProgressionLogic.Solver;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

/**
* Implements the logic for the game of arithmetic progression and stars player's threads
*/

public class GameArithmeticProgression implements GameInterface {
    private int maximumSizeProgression;
    private Solver problemSolver;
    private Board gameBoard;
    private List<Player> playersInGame;
    private TimeKeeper timeKeeper;
    private String currentPlayerTurn;
    boolean gameRunning;

    /**
    * Takes the number of tokens, token's values and the size of the progression that needs to be achieved
    */
    public GameArithmeticProgression(int maximumSizeProgression, int numberOfTokens, int... tokenValues){
        this.maximumSizeProgression = maximumSizeProgression;
        gameBoard = new Board(numberOfTokens);
        problemSolver = new Solver();
        playersInGame = new ArrayList<>();
        gameRunning = true;

        for(int tokenValue : tokenValues){
            gameBoard.addToken(tokenValue);
        }
    }

    /**
    * Shifts to the next player in queue
    */
    public void changeCurrentPlayer(){
        currentPlayerTurn = getCurrentTurnPlayerName(currentPlayerTurn);
    }

    public String getCurrentPlayer(){
        return currentPlayerTurn;
    }
    public void stopGame(){ gameRunning = false; }
    public boolean checkGameRunning(){ return gameRunning; }

    public List<Token> getAvailableTokens(){
        List<Token> availableTokens = new ArrayList<>();
        availableTokens = gameBoard.getAllAvailableTokens();
        return availableTokens;
    }

    public List<Player> getPlayers(){
        return playersInGame;
    }

    public void addPlayer(Player player){
        playersInGame.add(player);
        if(currentPlayerTurn == null){
            currentPlayerTurn = player.name;
        }
    }

    /**
    * Checks if the game has to be stopped, meaning that either no tokens are available or a player managed to achieve the goal
    */
    public boolean checkIfGameEnded(){
        int maximumPlayerSequence;
        for(Player player : playersInGame){
            maximumPlayerSequence = problemSolver.getMaximumProgression(player.getTokensChosen());
            if(maximumPlayerSequence >= maximumSizeProgression){
                return true;
            }
        }
        if(gameBoard.getAllAvailableTokens().size() == 0){
            return true;
        }
        return false;
    }

    /**
    * In case the game ended, returns the player that managed to achieve the goal
    */
    public Player getWinner(){
        int maximumPlayerSequence;
        if(checkIfGameEnded() == false){
            return null;
        }
        for(Player player : playersInGame){
            maximumPlayerSequence = problemSolver.getMaximumProgression(player.getTokensChosen());
            if(maximumPlayerSequence >= maximumSizeProgression){
                return player;
            }
        }
        return null;
    }
    
    /**
    * Starts the threads: players and time keeper and prints the winner
    */

    public void startGameLoop(){
        timeKeeper = new TimeKeeper(this, /*maximum time in seconds for a game*/100);
        for(Player player : playersInGame){
            new Thread(player).start();
        }
        while(gameRunning == true){
            try{
            Thread.sleep(1000);
            }
            catch (Exception InterruptedException){

            }
        }
        System.out.println(" ");
        System.out.println("Game ended");
        System.out.println("Winner: " + getWinner().name);
        getWinner().displayChosenTokens();
    }

    /**
    *  Gets the name of a player and returns the next player in the list (his name)
    */
    public String getCurrentTurnPlayerName(String previousPlayer){
        boolean isItNext = false;
        //if the previuos player is the last in the list then the next player will be the first in the list (cyclic order)
        if(playersInGame.get(playersInGame.size()-1).name.compareTo(previousPlayer) == 0){
            return playersInGame.get(0).name;
        }
        for(Player player : playersInGame){
            if(isItNext == true){
                return player.name;
            }
            if(player.name.compareTo(previousPlayer) == 0){
                isItNext = true;
            }
        }
        return null;
    }
}
