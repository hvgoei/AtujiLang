package main.environment;

public interface Environment {
    void put(String name, Object value);
    Object get(String name);
    //新建内部域
    void putNew(String name, Object value);
    //递归查找变量
    Environment where(String name);
    //新建外部域
    void setOuter(Environment e);
}
