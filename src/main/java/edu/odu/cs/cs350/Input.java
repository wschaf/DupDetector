package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

import edu.odu.cs.cs350.Interfaces.*;

/**
 * Input file will be invoked using command line arguments.
 * args[0] is reserved for the number of suggestions the user wants
 * to print.
 * args[1] is an optional input to load a properties file, if no
 * properties file is loaded, it will set to default properties.
 * args[i] i will be any number of files supplied in the command
 * line.
 */
public class Input implements InputInterface {

    private int nSuggestions;
    private List<File> files;
    /**Key: File; Value: tokenCount */
    private Hashtable<File, Integer> tokenCountForFiles;
    private List<TokenInterface> tokens;
    
    Input() {
        this.nSuggestions = 0;
        files = new ArrayList<File>();
        tokens = new ArrayList<TokenInterface>();
        tokenCountForFiles = new Hashtable<File, Integer>();
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

    /**
     * Generates the token list.
     */
    @Override
    public void setTokens() {
        this.tokens = new ArrayList<TokenInterface>();
        this.tokenCountForFiles = new Hashtable<File, Integer>();
        for (File file : this.getFiles()) {
            TokenAnalyzer t = new TokenAnalyzer(file);
            t.processSourceCode();
            tokenCountForFiles.put(file, t.getFileTokenCount());
            for (var token : t.getTokens()) {
                this.tokens.add(token);
            }
        }
    }

    /**
     * @return the list of all files that input received as
     * arguments.
     */
    @Override
    public List<File> getFiles() {
        return this.files;
    }

    /**
     * @return the list of tokens produced using the argument files.
     */
    @Override
    public List<? extends TokenInterface> getTokens() {
        return this.tokens;
    }

    /**
     * @return the number of suggestions for Output to print back
     * to the user.
     */
    @Override
    public int getNSuggestions() {
        return this.nSuggestions;
    }

    /**
     * Key: a File object
     * Value: an Integer object reprsenting the total tokens in 
     * that File.
     * @return a dictionary containing each file and the count of
     * tokens in that file.
     */
    @Override
    public Dictionary<File, Integer> getTokenCountForFiles() {
        return this.tokenCountForFiles;
    }

    /**
     * @param file any of a list of files that was passed to input
     * as an argument.
     * @return the number of tokens present in that file.
     */
    @Override
    public int getTokenCountForFile(File file) {
        return this.tokenCountForFiles.get(file);
    }
}
