package ArithmeticProgressionLogic;
import java.util.*;
import Game.*;
/**
* Implements the algorithm of finding the longest arithmetic subsequence
*/
public class Solver {
    

    /**
    *Gets the length of the maximamum arithmetic prograssion, receiving a list of tokens as parameter
    */
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

    /**
    * Given two numbers, find how many other numbers can continue that sequence
    * For example: start = 5, progression = 3, the method will search for 8, 11, 14, ...
    */
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

    /**
    * Checks if a token value exists in an array of tokens
    */
    private boolean checkIfNumberExists(List<Token> tokensToCheck, int numberSearched){
        for(Token token : tokensToCheck){
            if(token.getValue() == numberSearched){
                return true;
            }
        }
        return false;
    }
}
