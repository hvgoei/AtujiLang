package main.parser;

import static main.parser.Parser.rule;
import main.ast.Fun;

public class ClosureParser extends FuncParser {
  public ClosureParser() {
    primary.insertChoice(rule(Fun.class).sep("fun")
        .ast(paramList).ast(block));
  }
}
