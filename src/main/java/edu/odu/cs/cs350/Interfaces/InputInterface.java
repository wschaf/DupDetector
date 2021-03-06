package edu.odu.cs.cs350.Interfaces;

import java.io.*;
import java.util.*;

public interface InputInterface {

    /**
     * Generates the token list.
     */
    public void setTokens();

    /**
     * @return the list of all files that input received as
     * arguments.
     */
    public List<File> getFiles();

    /**
     * @return the list of tokens produced using the argument files.
     */
    public List<? extends TokenInterface> getTokens();

    /**
     * @return the number of suggestions for Output to print back
     * to the user.
     */
    public int getNSuggestions();

    /**
     * Key: a File object
     * Value: an Integer object reprsenting the total tokens in 
     * that File.
     * @return a dictionary containing each file and the count of
     * tokens in that file.
     */
    public Dictionary<File, Integer> getTokenCountForFiles();

    /**
     * @param file any of a list of files that was passed to input
     * as an argument.
     * @return the number of tokens present in that file.
     */
    public int getTokenCountForFile(File file);

    /**
     * @return a list of strings representing the file extensions
     * to be analyzed by the program.
     */
    public List<String> getfileExtensions();

    /**
     * Sets file extension to default.
     * Default: [".h,.cpp"]
     */
    public void setFileExtensions();

    /**
     * Sets the file extension to those listed in properties.ini.
     */
    public void setFileExtensions(File propertiesFile) throws Exception;

    /**
     * @return Minimum sequence length of token to be refactored.
     */
    public int getMinSequenceLength();

    /**
     * @return Max number of lexeme substitutions defined in the
     * properties.ini file.
     */
    public int getMaxSubstitutions();

    /**
     * Sets properties value to default defined in the SRD.
     */
    public void setMinSequenceMaxSubs();

    /**
     * Sets the properties value for minimum sequence length
     * and maxs substitution defined in the properties file.
     * @param propertiesFile the properties file from the CLI.
     */
    public void setMinSequenceMaxSubs(File propertiesFile) throws Exception;
}
