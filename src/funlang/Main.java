
package funlang;

import java.io.*;

import funlang.syntax.lexer.*;
import funlang.syntax.node.*;
import funlang.syntax.parser.*;

import lib.Observer;

public class Main {
    static Observer observer;

    public static void startDebugMode(String[] args, Observer o){
        observer = o;
        main(args);
    }

    public static void main(
            String[] args) {

        try {

            if(args.length < 1) {
                System.out.println("Pas de fichiers en arguments.");
                System.exit(1);
            }

            // COMPILATOR
            Reader in = new FileReader(args[0]);
            Lexer lexer = new Lexer(new PushbackReader(in, 1024));
            Parser parser = new Parser(lexer);
            Start tree = parser.parse();

            // Verify semantics
            Semantics semantics = SemanticVerifier.verify(tree);
            // COMPILATOR



            // On travaillea partir d'ici
            // Interpret
            // On ne touche pas au compilator, nos sleeps seront dans Interpretor
            Interpreter.interpret(tree, semantics, observer);
        }
        catch (LexerException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (ParserException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (SemanticException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (InterpreterException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}
