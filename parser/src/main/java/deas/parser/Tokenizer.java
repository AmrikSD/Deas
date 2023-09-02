package deas.parser;

import java.util.*;

/**
 * Tokenizer
 * 
 * Lazily grabs a token from a stream.
 */
public class Tokenizer {
    

    private String string;
    private int cursor;

    public Tokenizer(String string){
        this.string = string;
        this.cursor = 0;
    }

    public Tokenizer(){}

    public void setString(String string){
        this.string = string;
    }

    public void setCursor(int cursor){
        this.cursor = cursor;
    }

    public Token getNextToken(){

        if (!hasMoreTokens()){
            return null;
        }

        char[] charArr = string.substring(cursor).toCharArray();
        boolean isNegative = charArr[0] == '-';

        if (!Character.isDigit(charArr[0]) && !isNegative){
            return null;
        }

        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i< charArr.length; i++){
            if(i == 0 && isNegative){
                continue;
            }
            if(Character.isDigit(charArr[i])){
                stack.push(Character.getNumericValue(charArr[i]));
            } else{
                break;
            }
        }

        int i = 0;
        int acc = 0;
        for (Integer integer : stack) {
            acc += integer * Math.pow(10,i++);
        }

        if (isNegative){
            acc = acc * -1;
        }

        return new Token<Integer>(acc);
    }

    private boolean hasMoreTokens(){
        return cursor < string.length();
    }

}
