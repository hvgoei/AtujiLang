package main.ast;
import java.util.List;

import main.environment.Environment;

public class WhileStmnt extends ASTList {
    public static final int FALSE = 0;
    public WhileStmnt(List<ASTree> c) { super(c); }
    public ASTree condition() { return child(0); }
    public ASTree body() { return child(1); }
    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }
    public Object eval(Environment env) {
      Object result = 0;
      while(true) {
        Object c = condition().eval(env);
        if (c instanceof Integer && ((Integer)c).intValue() != FALSE) {
          result = body().eval(env);
        }
        else
          return result;
      }
    }
}
