package Game;
import java.util.*;
import ArithmeticProgressionLogic.Solver;
import java.io.*;

    /**
    *Implements the strategy that greedily takes the best option
    */

public class SmartPlayer extends Player{

    private Solver solver;

    public SmartPlayer(String name, GameInterface GamePlayed){
        this.GamePlayed = GamePlayed;
        this.name = name;
        this.tokensTaken = new ArrayList<>();
        solver = new Solver();
    }

    @Override
    protected synchronized void takeToken(List<Token> availableTokens, int count){
        int numberChoice;
        if(GamePlayed.checkIfGameEnded() == true){
            stopChoosingTokens();
            GamePlayed.changeCurrentPlayer();
            lock.notifyAll();
        }
        else {
            numberChoice = getSmartPlayerChoice(availableTokens, count);
            chooseToken(availableTokens.get(numberChoice-1));
            System.out.println("Player " + name + " chose token with value " + availableTokens.get(numberChoice-1).getValue());
            GamePlayed.changeCurrentPlayer();
            lock.notifyAll();
        }
    }
    /**
    * Implements the logic of a 'smart' choosing of tokens - tries all posibilities and choses the best one
    */
    private int getSmartPlayerChoice(List<Token> availableTokens, int count){
        int tokenIndex = 1;
        int indexToBeReturned = 0;
        int maximumProgression = -1;
        int currentProgression = 0;

        for(Token token : availableTokens){
            tokensTaken.add(token);
            currentProgression = solver.getMaximumProgression(availableTokens);
            if(currentProgression > maximumProgression){
                maximumProgression = currentProgression;
                indexToBeReturned = tokenIndex;
            }
            tokensTaken.remove(tokensTaken.size()-1);
            tokenIndex = tokenIndex + 1;
        }
        return indexToBeReturned;
    }
}
