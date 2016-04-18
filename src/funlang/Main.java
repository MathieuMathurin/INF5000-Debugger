
package funlang;

import java.io.*;

import funlang.syntax.analysis.ReversedDepthFirstAdapter;
import funlang.syntax.lexer.*;
import funlang.syntax.node.*;
import funlang.syntax.parser.*;

import lib.Observer;

public class Main {
    static Observer observer;
    static Boolean isInDebugMode = false;

    public static void startDebugMode(String[] args, Observer o){
        observer = o;
        isInDebugMode = true;
        main(args);
    }

    public static void main(
            String[] args) {

        try {

            if(args.length < 1) {
                System.out.println("Pas de fichiers en arguments.");
                System.exit(1);
            }

            Reader in = new FileReader(args[0]);
            Lexer lexer = new Lexer(new PushbackReader(in, 1024));
            Parser parser = new Parser(lexer);
            Start tree = parser.parse();

            // Verify semantics
            Semantics semantics = SemanticVerifier.verify(tree);

            if(isInDebugMode){

            }

            Interpreter.interpret(tree, semantics, observer);

            observer.sendProgramHasEnded();
        }
        catch (LexerException e) {
            String errorMessage = e.getMessage();
            if(isInDebugMode){
                observer.updateDebuggerConsole(errorMessage);
            }else{
                System.err.println(errorMessage);
                System.exit(1);
            }
        }
        catch (ParserException e) {
            String errorMessage = e.getMessage();
            if(isInDebugMode){
                observer.updateDebuggerConsole(errorMessage);
            }else{
                System.err.println(errorMessage);
                System.exit(1);
            }
        }
        catch (SemanticException e) {
            String errorMessage = e.getMessage();
            if(isInDebugMode){
                observer.updateDebuggerConsole(errorMessage);
            }else{
                System.err.println(errorMessage);
                System.exit(1);
            }
        }
        catch (InterpreterException e) {
            String errorMessage = e.getMessage();
            if(isInDebugMode){
                observer.updateDebuggerConsole(errorMessage);
            }else{
                System.err.println(errorMessage);
                System.exit(1);
            }
        }
        catch (IOException e) {
            String errorMessage = e.toString();
            if(isInDebugMode){
                observer.updateDebuggerConsole(errorMessage);
            }else{
                System.err.println(errorMessage);
                System.exit(1);
            }
        }
    }
}
