package main.environment;
import java.util.HashMap;

public class BasicEnv implements Environment {
    protected HashMap<String,Object> values;
    public BasicEnv() { values = new HashMap<String,Object>(); }
    public void put(String name, Object value) { values.put(name, value); }
    public Object get(String name) { return values.get(name); }
    
    /**
     * basicEnv不会实现这三个方法
     * 支持函数的environment实现见 {@link main.environment.NestedEnv}
     */
    @Override
    public void putNew(String name, Object value) {
      //ignore this
    }
    @Override
    public Environment where(String name) {
      //ignore this
      return null;
    }
    @Override
    public void setOuter(Environment e) {
      //ignore this      
    }
}
