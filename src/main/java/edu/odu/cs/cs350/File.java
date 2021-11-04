package edu.odu.cs.cs350;

import edu.odu.cs.cs350.Interfaces.FileInterface;

public class File implements FileInterface {

    private String absolutePath;
    private int numberOfTokens;
    private String data;

    @Override
    public String getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public void setAbsolutePath(String input) {
        this.absolutePath = input;
    }

    @Override
    public String getNumberOfTokens() {
        return Integer.toString(numberOfTokens);
    }

    @Override
    public void setNumberOfTokens(int input) {
        this.numberOfTokens = input;
    }

    @Override
    public String getData() {
        return this.data;
    }

    @Override
    public void setData(String input) {
        this.data = input;
    }
    
}
