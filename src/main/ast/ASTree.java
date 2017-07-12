package main.ast;

import java.util.Iterator;

public interface ASTree extends Iterable<ASTree> {
  public abstract ASTree child(int i);
  public abstract int numChildren();
  public abstract Iterator<ASTree> children();
  public abstract String location();
  public default Iterator<ASTree> iterator() { return children(); }
}
