package main.ast;

import main.basic.AtujiException;
import main.basic.Token;
import main.environment.Environment;

public class Name extends ASTLeaf {
    public Name(Token t) { super(t); }
    public String name() { return token().getText(); }
    public Object eval(Environment e) {
      Object value = e.get(name());
      if (null == value)
        throw new AtujiException("Undefined name: ", this);
      else
        return value;
    }
}
