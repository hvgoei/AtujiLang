package main.ast;
import java.util.List;

import main.environment.Environment;


public class BlockStmnt extends ASTList {
    public BlockStmnt(List<ASTree> c) { super(c); }
    public Object eval(Environment env) {
      Object result = 0;
      for (ASTree t: this) {
          if (!(t instanceof NullStmnt))
              result = t.eval(env);
      }
      return result;
  }
}
