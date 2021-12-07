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
}
