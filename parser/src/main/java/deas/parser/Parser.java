package deas.parser;

public class Parser {

    private Tokenizer tokenizer;
    private Token lookAhead;
    private String string;

    public Parser(){
        this.tokenizer = new Tokenizer();
    }

    public ASTNode parse(String s){
        this.string = s;
        tokenizer.setString(s);
        this.lookAhead = tokenizer.getNextToken();

        return this.deasFile();
    }

    /**
     * Deas File representation
     *  : NumericalLiteral
     *  ;
     */
    ASTNode deasFile(){
        return this.numericalLiteral();
    }

    /**
     * NumericalLiteral
     *  : NUMBER
     *  ;
     */
    ASTNode numericalLiteral(){
        try {
            Token<Integer> token = eat(Integer.class);
            return new ASTNode(token);
        } catch (Exception e) {
            System.out.println(e);
            return new ASTNode(null);
        }

    }


    private <T> Token<T> eat(Class<T> clazz) throws Exception{

        Token token = lookAhead;

        if (token == null){
            throw new Exception("Unexpected end of input");
        }
        if (token.value.getClass() != clazz){
            throw new Exception("Unexpected type of input, expected " + clazz.toString() + " but got " + token.value.getClass());
        }

        this.lookAhead = tokenizer.getNextToken();
        return token;

    }

}
