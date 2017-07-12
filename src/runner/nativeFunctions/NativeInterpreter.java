package runner.nativeFunctions;

import main.basic.Natives;
import main.basic.ParseException;
import main.environment.NestedEnv;
import main.parser.ClosureParser;
import runner.basicEval.BasicInterpreter;

public class NativeInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(),
            new Natives().environment(new NestedEnv()));
    }
}
