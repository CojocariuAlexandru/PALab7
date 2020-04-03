package Game;
import java.util.*;

 /**
 * Keeps all tokens in a game, and does different operations with this list and
 */

public class Board {
   
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

    /**
    * Is an usual getter for tokens, the ones that have the 'isAvailable'=true meaning that can still be chosen in the game
    */
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
