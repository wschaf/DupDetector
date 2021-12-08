package edu.odu.cs.cs350;

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
    private int MinSequenceLength;
    private int MaxSubstitutions;
    private List<File> files;
    private File propertiesFile;
    private List<String> fileExtensions;
    /**Key: File; Value: tokenCount */
    private Hashtable<File, Integer> tokenCountForFiles;
    private List<TokenInterface> tokens;
    
    Input() {
        this.nSuggestions = 0;
        files = new ArrayList<File>();
        tokens = new ArrayList<TokenInterface>();
        tokenCountForFiles = new Hashtable<File, Integer>();
        propertiesFile = new File("");
        this.setFileExtensions();
        this.setMinSequenceMaxSubs();
    }

    Input(String args[]) throws Exception {
        if (args == null || args.length == 0) {
            System.err.println("Usage: java -jar build/libs/DupDetector.jar <nSuggestions> "
            		+ "<properties file>[OPTIONAL] <path/of/file1> <path/of/file2>");
            System.exit(-1);
        }
        this.files = new ArrayList<File>();
        List<String> argList = new ArrayList<String>();
        for (String string : args) argList.add(string);
        this.nSuggestions = Integer.parseInt(argList.get(0));
        argList.remove(0);

        if (argList.get(0).endsWith(".ini")) {
            this.propertiesFile = new File(argList.get(0));
            argList.remove(0);
            this.setFileExtensions(propertiesFile);
            this.setMinSequenceMaxSubs(propertiesFile);
            for (var path : argList) {
                try {
                    RecursiveSearch r = new RecursiveSearch();
                    this.files.addAll(r.findFiles(path, this.getfileExtensions()));
                } catch(FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        else {
            this.setFileExtensions();
            this.setMinSequenceMaxSubs();
            for (var path : argList) {
                try {
                    RecursiveSearch r = new RecursiveSearch();
                    this.files.addAll(r.findFiles(path));
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

    /**
     * @return a list of strings representing the file extensions
     * to be analyzed by the program.
     */
    public List<String> getfileExtensions() {
        return this.fileExtensions;
    }

    /**
     * @return Minimum sequence length of token to be refactored.
     */
    public int getMinSequenceLength() {
        return this.MinSequenceLength;
    }

    /**
     * @return Max number of lexeme substitutions defined in the
     * properties.ini file.
     */
    public int getMaxSubstitutions() {
        return this.MaxSubstitutions;
    }

    /**
     * Sets file extension to default.
     * Default: [".h,.cpp"]
     */
    public void setFileExtensions() {
        this.fileExtensions = new ArrayList<String>();
        this.fileExtensions.add(".h");
        this.fileExtensions.add(".cpp");
    }

    /**
     * Sets the file extension to those listed in properties.ini.
     */
    public void setFileExtensions(File propertiesFile) throws Exception {
        this.fileExtensions = new ArrayList<String>();
        Properties props = new Properties();
        try(Reader reader = new FileReader(propertiesFile)){
            props.load(reader);
        }
        // after loading the .ini file, string are split by "," and stored in an array of extension
        fileExtensions = Arrays.asList(props.getProperty("CppExtensions").split(", "));
    }

    /**
     * Sets properties value to default defined in the SRD.
     */
    public void setMinSequenceMaxSubs() {
        this.MinSequenceLength = 10;
        this.MaxSubstitutions = 8;
    }

    /**
     * Sets the properties value for minimum sequence length
     * and maxs substitution defined in the properties file.
     * @param propertiesFile the properties file from the CLI.
     */
    public void setMinSequenceMaxSubs(File propertiesFile) throws Exception {
        Properties props = new Properties();
        try(Reader reader = new FileReader(propertiesFile)) {
            props.load(reader);
        }
        MinSequenceLength = Integer.valueOf(props.getProperty("MinSequenceLength"));
        MaxSubstitutions = Integer.valueOf(props.getProperty("MaxSubstitutions"));
    }
}
