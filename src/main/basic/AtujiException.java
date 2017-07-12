package main.basic;

import main.ast.ASTree;

public class AtujiException extends RuntimeException {
  public AtujiException(String msg) {
    super(msg);
  }
  public AtujiException(String msg, ASTree t) {
    super(msg + "  " + t.location());
  }
}
