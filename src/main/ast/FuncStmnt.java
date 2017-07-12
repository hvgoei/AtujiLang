package main.ast;
import java.util.List;

import main.environment.Environment;


public class FuncStmnt extends ASTList {
    public FuncStmnt(List<ASTree> c) { super(c); }
    public String name() { return ((ASTLeaf)child(0)).token().getText(); }
    public ParameterList parameters() { return (ParameterList)child(1); }
    public BlockStmnt body() { return (BlockStmnt)child(2); } 
    public String toString() {
        return "(function " + name() + " " + parameters() + " " + body() + ")";
    }
    public Object eval(Environment env) {
      env.putNew(name(), new Function(parameters(), body(), env));
      return name();
  }
}
