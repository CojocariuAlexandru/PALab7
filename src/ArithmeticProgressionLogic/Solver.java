package ArithmeticProgressionLogic;
import java.util.*;
import Game.*;

public class Solver {
    /**
     * Implements the algorithm of finding the longest arithmetic subsequence
     */

    //Implemented naively in O(n^4) complexity however O(n^2) is achievable
    public int getMaximumProgression(List<Token> tokensToCheck){
        int i, j;
        int progression;
        int counter;
        int counterMax = -1;
        for(i=0; i<tokensToCheck.size(); i++){
            for(j=i+1; j<tokensToCheck.size(); j++){
                //For every 2 numbers consider they are the first in the progression
                progression = tokensToCheck.get(i).getValue() - tokensToCheck.get(j).getValue();
                if(progression < 0){
                    progression = -progression;
                }
                if(tokensToCheck.get(i).getValue() > tokensToCheck.get(j).getValue()){
                    counter = searchForOtherNumbers(tokensToCheck, tokensToCheck.get(i).getValue(), progression);
                }
                else{
                    counter = searchForOtherNumbers(tokensToCheck, tokensToCheck.get(j).getValue(), progression);
                }
                if(counter > counterMax){
                    counterMax = counter;
                }
            }
        }
        return counterMax;
    }

    private int searchForOtherNumbers(List<Token> tokensToCheck, int start, int progression){
        int counter = 2;
        int nextNumberInSequence;
        boolean numberExists;
        for(nextNumberInSequence = start+progression;;nextNumberInSequence+=progression){
            numberExists = checkIfNumberExists(tokensToCheck, nextNumberInSequence);
            if(numberExists == true){
                counter = counter + 1;
            }
            else
                break;
        }
        return counter;
    }

    private boolean checkIfNumberExists(List<Token> tokensToCheck, int numberSearched){
        for(Token token : tokensToCheck){
            if(token.getValue() == numberSearched){
                return true;
            }
        }
        return false;
    }
}
