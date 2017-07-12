package main.parser;
import static main.parser.Parser.rule;

import main.ast.ArrayLiteral;
import main.ast.ArrayRef;

public class ArrayParser extends ClassParser {
    Parser elements = rule(ArrayLiteral.class)
                          .ast(expr).repeat(rule().sep(",").ast(expr));
    public ArrayParser() {
        reserved.add("]");
        primary.insertChoice(rule().sep("[").maybe(elements).sep("]"));
        postfix.insertChoice(rule(ArrayRef.class).sep("[").ast(expr).sep("]"));
    }
}
