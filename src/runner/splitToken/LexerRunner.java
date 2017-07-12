package runner.splitToken;

import main.basic.CodeDialog;
import main.basic.ParseException;
import main.basic.Token;
import main.parser.Lexer;

public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        for (Token t; (t = l.read()) != Token.EOF; )
            System.out.println("=> " + t.getText());
    }
}
