package edu.odu.cs.cs350.Interfaces;

/**
 * A File object contains the data contained in C++ source files
 * and its associated metadata.
 */
public abstract interface FileInterface {

    public String getAbsolutePath();

    public void setAbsolutePath(String input);

    public String getNumberOfTokens();

    public void setNumberOfTokens(int input);

    public String getData();

    public void setData(String input);
    
}
