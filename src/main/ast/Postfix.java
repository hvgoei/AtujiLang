package main.ast;

import java.util.List;

import main.environment.Environment;


public abstract class Postfix extends ASTList {
    public Postfix(List<ASTree> c) { super(c); }
    public abstract Object eval(Environment env, Object value);
}
