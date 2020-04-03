package Game;
import java.util.*;
import java.io.*;

public class ManualPlayer extends Player{
    /**
    Implements user input as taking strategy
     */
    private Scanner scanner;

    public ManualPlayer(String name, GameInterface GamePlayed){
        this.GamePlayed = GamePlayed;
        this.name = name;
        this.tokensTaken = new ArrayList<>();
        scanner = new Scanner(System.in);
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
            System.out.print("Available tokens: ");
            for(Token token : availableTokens){
                System.out.print(token.getValue() + " ");
            }
            System.out.println(" ");

            numberChoice = scanner.nextInt();
            chooseToken(availableTokens.get(numberChoice-1));
            System.out.println("Player " + name + " chose token with value " + availableTokens.get(numberChoice-1).getValue());
            GamePlayed.changeCurrentPlayer();
            lock.notifyAll();
        }
    }
}
