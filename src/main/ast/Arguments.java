package main.ast;
import java.util.List;

import main.basic.AtujiException;
import main.environment.Environment;


public class Arguments extends Postfix {
    public Arguments(List<ASTree> c) { super(c); }
    public int size() { return numChildren(); }
    
    /**
     * @param callerEnv 函数调用语句所在的环境
     * @param newEnv    调用函数时创建的环境
     */
    public Object eval(Environment callerEnv, Object value) {
      if (!(value instanceof Function))
          throw new AtujiException("bad function", this);
      Function func = (Function)value;
      ParameterList params = func.parameters();
      if (size() != params.size())
          throw new AtujiException("bad number of arguments", this);
      Environment newEnv = func.makeEnv();
      //树的节点index
      int num = 0;
      for (ASTree a: this)
          params.eval(newEnv, num++,a.eval(callerEnv));
      return (func.body()).eval(newEnv);
  }
}
