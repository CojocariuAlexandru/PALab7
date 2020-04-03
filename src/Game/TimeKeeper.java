package Game;

import java.awt.event.WindowEvent;

/**
* Keeps track of current playing time
*/

public class TimeKeeper implements Runnable {
    
    private GameInterface apartainingGame;
    private double startTime;
    private double currentTime;
    private final double maximumTime;

    public TimeKeeper(GameInterface apartainingGame, double maximumTime){
        startTime = System.nanoTime();
        this.apartainingGame = apartainingGame;
        this.maximumTime = maximumTime * 1000; //*1000 because i'm working with nano
    }

    /**
     * The thread starts here: if the time elapsed is bigger than maximumTime then stopGame() method is called
     */
    @Override
    public void run() {
        while(apartainingGame.checkGameRunning() == true){
            currentTime = System.nanoTime();
            if(currentTime - startTime >= maximumTime){
                apartainingGame.stopGame();
            }
        }
    }
}
