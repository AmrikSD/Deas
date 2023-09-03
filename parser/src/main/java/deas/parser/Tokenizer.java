package deas.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        this.cursor = 0;
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
        Pattern pattern = Pattern.compile("^([\"'])(\\w\\s?)*?\\1$");
        Matcher matcher = pattern.matcher(string);
        boolean matchFound = matcher.find();

        if(!matchFound){
            System.err.println("not found");
            return new Token<String>(null);
        }

        String match = matcher.group();
        cursor += matcher.group().length();
        
        return new Token<String>(match);
    }

    private Token<Integer> getNumberLiteral(char[] charArray, boolean isNegative){

        Pattern pattern = Pattern.compile("^-?\\d+$");
        Matcher matcher = pattern.matcher(string);
        boolean matchFound = matcher.find();

        if(!matchFound){
            System.err.println("not found");
            return new Token<Integer>(null);
        }

        int match = Integer.parseInt(matcher.group());
        cursor += matcher.group().length();
        
        return new Token<Integer>(match);

    }

    private boolean hasMoreTokens(){
        return cursor < string.length();
    }
    private boolean isEOF(){
        return cursor == string.length();
    }

}
