package deas.parser;

public class Parser {

    private String string;

    public Parser(){}

    ASTNode parse(String s){
        this.string = s;
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
        return new ASTNode(Integer.parseInt(this.string));
    }

}
