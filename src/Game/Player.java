package Game;
import java.util.*;

public abstract class Player implements Runnable{
    /**
     * Has all implementation of a player except his playing strategy
     */
    protected String name;
    protected GameInterface GamePlayed;
    protected List<Token> tokensTaken;
    protected boolean running = true;
    protected static final Object lock = new Object();



    public void setName(String newName){
        name = newName;
    }
    public String getName(){
        return name;
    }

    protected void chooseToken(Token chosenToken){
        tokensTaken.add(chosenToken);
        chosenToken.setChosen();
    }

    public List<Token> getTokensChosen(){
        return tokensTaken;
    }

    public void displayChosenTokens(){
        for(Token token : tokensTaken){
            System.out.print(token.getValue() + " ");
        }
        System.out.println(" ");
    }

    public void stopChoosingTokens(){
        running = false;
        GamePlayed.stopGame();
    }

    //The strategy of choosing tookens - random from available tokens
    @Override
    public void run() {
        List<Token> availableTokens = new ArrayList<>();
        int countAvailableTokens;

        while(running == true){
            //Take available tokens
            availableTokens = GamePlayed.getAvailableTokens();
            countAvailableTokens = availableTokens.size()-1;

            //From these, take one at random
            synchronized (lock){
                if(this.name.compareTo(GamePlayed.getCurrentPlayer()) != 0){
                    waitForYourTurn();
                }
                else{
                    takeToken(availableTokens, countAvailableTokens);
                }
            }
        }
    }

    //By default take randomly
    synchronized protected void takeToken(List<Token> availableTokens, int count){
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

    synchronized private void waitForYourTurn(){
        while(this.name.compareTo(GamePlayed.getCurrentPlayer()) != 0){
            try {
                lock.wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
            }
        }
    }


}
