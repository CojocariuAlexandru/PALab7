package Game;
import java.util.*;

public class Board {
    /**
     * Keeps all tokens in a game
     */
    private List<Token> tokensOnBoard;
    private int numberOfTokensOnBoard;

    public Board(int numberOfTokensOnBoard){
        tokensOnBoard = new ArrayList<>();
        this.numberOfTokensOnBoard = numberOfTokensOnBoard;
    }

    public void addToken(int tokenValue){
        tokensOnBoard.add(new Token(tokenValue));
    }

    public List<Token> getAllTokens(){
        return tokensOnBoard;
    }

    public List<Token> getAllAvailableTokens(){
        List<Token> availableTokens = new ArrayList<>();
        for(Token token : tokensOnBoard){
            if(token.getAvailability() == true){
                availableTokens.add(token);
            }
        }
        return availableTokens;
    }

}
