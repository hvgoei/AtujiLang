package main.parser;
import static main.parser.Parser.rule;

import main.ast.ClassBody;
import main.ast.ClassStmnt;
import main.ast.Dot;
import main.basic.Token;

public class ClassParser extends ClosureParser {
    Parser member = rule().or(function, simple);
    Parser class_body = rule(ClassBody.class).sep("{").option(member)
                            .repeat(rule().sep(";", Token.EOL).option(member))
                            .sep("}");
    Parser defclass = rule(ClassStmnt.class).sep("class").identifier(reserved)
                          .option(rule().sep("extends").identifier(reserved))
                          .ast(class_body);
    public ClassParser() {
        postfix.insertChoice(rule(Dot.class).sep(".").identifier(reserved));
        program.insertChoice(defclass);
    }
}
