package Game;

    /**
     * Creates instances: a game, players and starts the game
     */

public class Main {
    public static void main(String[] args) {

        GameArithmeticProgression game1 = new GameArithmeticProgression(5, 20,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        RandomPlayer player1 = new RandomPlayer("alex", game1);
        SmartPlayer player2 = new SmartPlayer("ramo", game1);
        game1.addPlayer(player1);
        game1.addPlayer(player2);
        game1.startGameLoop();
    }
}
