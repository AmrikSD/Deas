package deas.parser;

public class ASTNode<T> {
    public Token<T> token;

    public ASTNode(Token<T> token){
        this.token = token;
    }

    public T getValue(){
        return token.value;
    }
}
