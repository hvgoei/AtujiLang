package main.ast;
import java.util.List;

import main.basic.AtujiException;
import main.basic.AtujiObject;
import main.basic.ClassInfo;
import main.environment.Environment;
import main.environment.NestedEnv;

public class Dot extends Postfix {
    public Dot(List<ASTree> c) { super(c); }
    public String name() { return ((ASTLeaf)child(0)).token().getText(); }
    public String toString() { return "." + name(); }
    @Override
    public Object eval(Environment env, Object value) {
      String member = name();
      if (value instanceof ClassInfo) {
        if ("new".equals(member)) {
          ClassInfo ci = (ClassInfo) value;
          NestedEnv e = new NestedEnv(ci.environment());
          AtujiObject ao = new AtujiObject(e);
          e.putNew("this", ao);
          initObject(ci, e);
          return ao;
        }
      }
      else if (value instanceof AtujiObject) {
        try {
          return ((AtujiObject)value).read(member);
        } catch (Exception e) {
          // ignore this
        }
      }
      throw new AtujiException("bad member access: " + member, this);
    }
      protected void initObject(ClassInfo ci, Environment env) {
        if (ci.superClass() != null)
            initObject(ci.superClass(), env);
        ci.body().eval(env);
    } 
}
