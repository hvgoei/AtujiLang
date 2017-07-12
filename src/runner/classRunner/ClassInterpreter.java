package runner.classRunner;

import main.basic.Natives;
import main.basic.ParseException;
import main.environment.NestedEnv;
import main.parser.ClassParser;
import runner.basicEval.BasicInterpreter;

public class ClassInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClassParser(), new Natives().environment(new NestedEnv())); 
    }
}
