package edu.odu.cs.cs350;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * DupDetector is the main class for the system. It will call
 * each subsystem of the program, passing input/output between
 * them.
 */
public class DupDetector {
    
	public static void main(String args[]) throws Exception {
    	/**
    	 * input file will be invoked using command line arguments
    	 * args[0] is reserved for the number of suggestions the user wants to print
    	 * args[i] i will be any number of files supplied in the command line
    	 */
    	if (args.length == 0) {
            System.err.println ("Usage: java <nSuggestions> <path/of/file1> <path/of/file2>");
            System.exit(-1);
        }
    	/**
    	 * nSuggestions currently not in used but is assigned to reserved the first
    	 * argument to the number of suggestions without affecting the file name 
    	 * supplied for subsequent arguments
    	 */
    	int nSuggestions = Integer.parseInt(args[0]);
    	
    	for (int i = 1; i < args.length; i++) {
    		
    		try {
       		 
                File f = new File(args[i]);
                if(f.isFile()) {
                	Scanner s = new Scanner(f);
                	String source = " ";
                    while (s.hasNext()) {
                        source += s.nextLine() + "\n";
                    }
                    s.close();
                    
                    Reader input = new StringReader(source);
                    TokenAnalyzer t = new TokenAnalyzer(input);
                    System.out.println(t);
                }
                else if(f.isDirectory()){
                	
                	String startDir = args[i];
                	RecursiveSearch r = new RecursiveSearch();
                	r.searchDirectory(startDir);
                	
                }
                
            } catch(FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
    	}
    }
   
}
