package Game;
import java.util.*;

    /**
    * Implement random choosing of tokens
    */

public class RandomPlayer extends Player{
    


    public RandomPlayer(String name, GameInterface GamePlayed){
        this.GamePlayed = GamePlayed;
        this.name = name;
        this.tokensTaken = new ArrayList<>();
    }


    @Override
    protected synchronized void takeToken(List<Token> availableTokens, int count){
        int randomNumberChoice;
        if(GamePlayed.checkIfGameEnded() == true){
            stopChoosingTokens();
            GamePlayed.changeCurrentPlayer();
            lock.notifyAll();
        }
        else {
            randomNumberChoice = (int) (Math.random() * count);
            chooseToken(availableTokens.get(randomNumberChoice));
            System.out.println("Player " + name + " chose token with value " + availableTokens.get(randomNumberChoice).getValue());
            GamePlayed.changeCurrentPlayer();
            lock.notifyAll();
        }
    }

}
