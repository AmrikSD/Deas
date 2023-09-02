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

        if (!Character.isDigit(charArr[0])){
            return null;
        }

        int i = 0;
        int acc = 0;
        for (char c : charArr){
            if(Character.isDigit(c)){
                acc += c * Math.pow(10,i);
                i++;
            } else{
                break;
            }
        }
        this.cursor += i;
        return new Token(acc);
    }

    private boolean hasMoreTokens(){
        return cursor < string.length();
    }

}
