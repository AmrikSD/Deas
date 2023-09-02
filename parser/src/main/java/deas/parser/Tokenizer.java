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

        // Number
        char[] charArr = string.substring(cursor).toCharArray();
        boolean isDigit = Character.isDigit(charArr[0]);
        boolean isNegative = charArr[0] == '-';
        boolean isQuotes =  charArr[0] == '"' || charArr[0] == '\'';

        if (isQuotes){
            return getStringLiteral(charArr);
        }

        if (isDigit || isNegative) {
            return getNumberLiteral(charArr, isNegative);
        }

        return null;
    }

    private Token<String> getStringLiteral(char[] charArray){
        char quote = charArray[0];
        StringBuilder sb = new StringBuilder();
        do {
           sb.append(charArray[cursor++]);
        } while (!isEOF() && charArray[cursor] != quote);
        sb.append(quote);
        cursor++;

        return new Token<String>(sb.toString());

    }

    private Token<Integer> getNumberLiteral(char[] charArray, boolean isNegative){

        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < charArray.length; i++){
            if(i == 0 && isNegative){
                continue;
            }
            if(Character.isDigit(charArray[i])){
                stack.push(Character.getNumericValue(charArray[i]));
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
    private boolean isEOF(){
        return cursor == string.length();
    }

}
