package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

import edu.odu.cs.cs350.Interfaces.TokenInterface;

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
	 * @param f: The file given from the input, provided by the user.
	 * @return Character stream of the file that will be read
	 * in token analyzer
	 */
	public Reader readFiles(File f) {
		try {
			Scanner s = new Scanner(f);
			String source = "";
			while(s.hasNext()) {
				source += s.nextLine() + "\n";
			}
			s.close();
			Reader input = new StringReader(source);
			return input;
		}
		catch(FileNotFoundException e) {

		}
		return null;
	}

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

    public List<File> getFiles() {
        return this.files;
    }

    public List<? extends TokenInterface> getTokens() {
        return this.tokens;
    }

    public int getNSuggestions() {
        return this.nSuggestions;
    }

    public Dictionary<File, Integer> getTokenCountForFiles() {
        return this.tokenCountForFiles;
    }

    public int getTokenCountForFile(File file) {
        return this.tokenCountForFiles.get(file);
    }
}
