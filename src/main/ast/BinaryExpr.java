package main.ast;
import java.util.List;

import main.basic.AtujiException;
import main.basic.AtujiObject;
import main.basic.AtujiObject.AccessException;
import main.environment.Environment;

public class BinaryExpr extends ASTList {
    public static final int TRUE = 1;
    public static final int FALSE = 0;
  
    public BinaryExpr(List<ASTree> c) { super(c); }
    public ASTree left() { return child(0); }
    public String operator() {
        return ((ASTLeaf)child(1)).token().getText();
    }
    public ASTree right() { return child(2); }
    public Object eval(Environment env) {
      String op = operator();
      if ("=".equals(op)) {
        Object right = right().eval(env);
        return computeAssign(env, right);
      }
      else {
        Object left = left().eval(env);
        Object right = right().eval(env);
        return computeOp(left, op, right);
      }
    }
    //如果是赋值
    protected Object computeAssign0(Environment env, Object rvalue) {
      ASTree left = left();
      if (left instanceof Name ) {
        env.put(((Name) left).name(), rvalue);
        return rvalue;
      }
      else
        throw new AtujiException("bad assignment", this);
    }
    
    protected Object computeOp(Object left, String op, Object right) {
      if (left instanceof Integer && right instanceof Integer) {
        return computeNumber((Integer)left, op, (Integer)right);
      }
      else {
        if (op.equals("+"))
          return String.valueOf(left) + String.valueOf(right);
        else if (op.equals("==")) {
          if (left == null)
            return right == null ? TRUE: FALSE;
          else 
            return left.equals(right) ? TRUE: FALSE;
        }
        else
          throw new AtujiException("bad type", this);
      }
    }
    
    protected Object computeNumber(Integer left, String op, Integer right) {
      int a = left.intValue();
      int b = right.intValue();
      if (op.equals("+"))
        return a + b;
      else if (op.equals("-"))
        return a - b;
      else if (op.equals("*"))
        return a * b;
      else if (op.equals("/"))
        return a / b;
      else if (op.equals("%"))
        return a % b;
      else if (op.equals("=="))
        return a == b ? TRUE : FALSE;
      else if (op.equals(">"))
        return a > b ? TRUE : FALSE;
      else if (op.equals("<"))
        return a < b ? TRUE : FALSE;
      else
        throw new AtujiException("bad operator", this);
    }
    
    //类
    protected Object computeAssign1(Environment env, Object rvalue) {
      ASTree le = left();
      if (le instanceof PrimaryExpr) {
        PrimaryExpr p = (PrimaryExpr)le;
          if (p.hasPostfix(0) && p.postfix(0) instanceof Dot) {
              Object t = ((PrimaryExpr)le).evalSubExpr(env, 1);
              if (t instanceof AtujiObject)
                  return setField((AtujiObject)t, (Dot)p.postfix(0),
                                  rvalue);
          }
      }
      return computeAssign0(env, rvalue);
  }
  protected Object setField(AtujiObject obj, Dot expr, Object rvalue) {
      String name = expr.name();
      try {
          obj.write(name, rvalue);
          return rvalue;
      } catch (AccessException e) {
          throw new AtujiException("bad member access " + location()
                                   + ": " + name);
      }
  }
  
  //数组
  protected Object computeAssign(Environment env, Object rvalue) {
    ASTree le = left();
    if (le instanceof PrimaryExpr) {
      PrimaryExpr p = (PrimaryExpr)le;
        if (p.hasPostfix(0) && p.postfix(0) instanceof ArrayRef) {
            Object a = ((PrimaryExpr)le).evalSubExpr(env, 1);
            if (a instanceof Object[]) {
                ArrayRef aref = (ArrayRef)p.postfix(0);
                Object index = ((ASTree)aref.index()).eval(env);
                if (index instanceof Integer) {
                    ((Object[])a)[(Integer)index] = rvalue;
                    return rvalue;
                }
            }
            throw new AtujiException("bad array access", this);
        }
    }
    return computeAssign1(env, rvalue);
}
}
