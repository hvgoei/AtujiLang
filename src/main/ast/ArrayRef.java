package main.ast;
import java.util.List;

import main.basic.AtujiException;
import main.environment.Environment;

public class ArrayRef extends Postfix {
    public ArrayRef(List<ASTree> c) { super(c); }
    public ASTree index() { return child(0); }
    public String toString() { return "[" + index() + "]"; }
    @Override
    public Object eval(Environment env, Object value) {
      if (value instanceof Object[]) {
          Object index = ((ASTree)index()).eval(env);
          if (index instanceof Integer)
              return ((Object[])value)[(Integer)index];
      }

      throw new AtujiException("bad array access", this);
  }
}
