package runner.function;

import main.ast.ASTree;
import main.ast.NullStmnt;
import main.basic.CodeDialog;
import main.basic.ParseException;
import main.basic.Token;
import main.environment.Environment;
import main.environment.NestedEnv;
import main.parser.BasicParser;
import main.parser.ClosureParser;
import main.parser.Lexer;

public class ClosureInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(), new NestedEnv());
    }
    public static void run(BasicParser bp, Environment env)
        throws ParseException
    {
        Lexer lexer = new Lexer(new CodeDialog());
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                Object r = t.eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}
