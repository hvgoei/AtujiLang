package main.ast;

import main.basic.Token;
import main.environment.Environment;

public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t) { super(t); }
    public int value() { return token().getNumber(); }
    public Object eval(Environment e) { return value(); }
}
