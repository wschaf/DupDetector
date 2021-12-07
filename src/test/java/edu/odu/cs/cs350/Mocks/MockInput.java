package edu.odu.cs.cs350.Mocks;

import java.io.*;
import java.util.*;

import edu.odu.cs.cs350.Interfaces.*;

public class MockInput implements InputInterface {

    private int nSuggestions;
    private List<File> files;
    /**Key: File; Value: tokenCount */
    private Hashtable<File, Integer> tokenCountForFiles;
    private List<TokenInterface> tokens;

    MockInput() {
        this.nSuggestions = 0;
        this.files = new ArrayList<File>();
        this.tokenCountForFiles = new Hashtable<File, Integer>();
        this.tokens = new ArrayList<TokenInterface>();
    }

    MockInput(
        int nSuggestions, 
        List<File> files, 
        Hashtable<File, Integer> tokenCountForFiles, 
        List<TokenInterface> tokens
    ) {
        this.nSuggestions = nSuggestions;
        this.files = files;
        this.tokenCountForFiles = tokenCountForFiles;
        this.tokens = tokens;
    }

    @Override
    public void setTokens() {
        //  Do nothing, tokens should have already been set.
    }

    @Override
    public List<File> getFiles() {
        return this.files;
    }

    @Override
    public List<? extends TokenInterface> getTokens() {
        return this.tokens;
    }

    @Override
    public int getNSuggestions() {
        return this.nSuggestions;
    }

    @Override
    public Dictionary<File, Integer> getTokenCountForFiles() {
        return this.tokenCountForFiles;
    }

    @Override
    public int getTokenCountForFile(File file) {
        return this.tokenCountForFiles.get(file);
    }
    
}
