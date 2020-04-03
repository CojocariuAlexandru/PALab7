package Game;

    /**
     * Manages token attributes: value, availability
     */

public class Token {
    private boolean isTaken;
    private int value;

    public Token(){
        isTaken = false;
        value = 0;
    }
    public Token(int tokenValue){
        isTaken = false;
        value = tokenValue;
    }

    public boolean getAvailability(){
        if(isTaken == true){
            return false; //token is not available if taken
        }
        else{
            return true;
        }
    }

    public void setChosen(){
        isTaken = true;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int newValue){
        value = newValue;
    }
}
