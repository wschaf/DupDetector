package edu.odu.cs.cs350;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * DupDetector is the main class for the system. It will call
 * each subsystem of the program, passing input/output between
 * them.
 */
public class DupDetector {
    
    public static void main(String[] args) throws Exception {
        System.out.println("Successfully ran DupDetector.");
        if (args.length > 0) {
            try {
 
                File f = new File(args[0]);
                Scanner s = new Scanner(f);
                String source = " ";
                while (s.hasNext()) {
                    source += s.nextLine() + "\n";
                }
                s.close();
                Lexer l = new Lexer(source);
                l.printTokens();
            } catch(FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Command args needed: java DupDetector <SourceFile>");
        }
        return;
    }
}
