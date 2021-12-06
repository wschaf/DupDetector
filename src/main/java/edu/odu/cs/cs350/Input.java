package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

/**
 * Input file will be invoked using command line arguments.
 * args[0] is reserved for the number of suggestions the user wants
 * to print.
 * args[1] is an optional input to load a properties file, if no
 * properties file is loaded, it will set to default properties.
 * args[i] i will be any number of files supplied in the command
 * line.
 */
public class Input {

    private int nSuggestions;
    private List<File> files;
    private List<Token> tokens;
    
    Input() {
        this.nSuggestions = 0;
        files = new ArrayList<File>();
        tokens = new ArrayList<Token>();
    }

    Input(String args[]) throws Exception {
        if (args.length == 0) {
            System.err.println("Usage: java -jar build/libs/DupDetector.jar <nSuggestions> "
            		+ "<properties file>[OPTIONAL] <path/of/file1> <path/of/file2>");
            System.exit(-1);
        }

        this.nSuggestions = Integer.parseInt(args[0]);

        if(args[1].endsWith(".ini")) {
    		for (int i = 2; i < args.length; i++) {	
    			try {
    				String startDir = args[i];
    				RecursiveSearch r = new RecursiveSearch();
					this.files = r.searchWithProperties(startDir, args[1]);
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
					this.files = r.searchDirectory(startDir);
                } catch(FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
    		}
    	}

        this.setTokens();
    }

    public void setTokens() {
        this.tokens = new ArrayList<Token>();
        for (File file : this.getFiles()) {
            TokenAnalyzer t = new TokenAnalyzer(file);
            this.tokens.add(t.getTokens());
        }
    }

    public List<File> getFiles() {
        return this.files;
    }

    public List<Token> getTokens() {
        return this.tokens;
    }

    public int getNSuggestions() {
        return this.nSuggestions;
    }
}
