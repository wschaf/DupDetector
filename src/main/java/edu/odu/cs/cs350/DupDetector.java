package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
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
    	 * args[1] is an optional input to load a properties file, if no properties 
    	 * file is loaded, it will set to default properties
    	 * args[i] i will be any number of files supplied in the command line
    	 */
    	if (args.length == 0) {
            System.err.println ("Usage: java -jar build/libs/DupDetector.jar <nSuggestions> "
            		+ "<properties file>[OPTIONAL] <path/of/file1> <path/of/file2>");
            System.exit(-1);
        }
    	int nSuggestions = Integer.parseInt(args[0]);
    	if(args[1].endsWith(".ini")) {
    		for (int i = 2; i < args.length; i++) {	
    			try {
    				String startDir = args[i];
    				RecursiveSearch r = new RecursiveSearch();
    				System.out.println(r.searchWithProperties(startDir, args[1]));
    				r.clear();
    			} catch(FileNotFoundException e) {
    				System.out.println(e.getMessage());
    			}
    		}
		}
    	else {
    		for (int i = 1; i < args.length; i++) {
        		try {
                    String startDir = args[i];
                    RecursiveSearch r = new RecursiveSearch();
                    System.out.println(r.searchDirectory(startDir));
                    r.clear();
                } catch(FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
    		}
    	}
    }
}
