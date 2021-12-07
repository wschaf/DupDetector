package edu.odu.cs.cs350.Interfaces;

/**
 * A refactoring object contains the information needed by
 * Output to print the refactoring suggestion and its associated metadata.
 */
public abstract interface RefactoringInterface {

    public String getNumberOfTokens();

    public String getAbsolutePath();

    public String getLineNumber();

    public String getColumnNumber();

    public String getTokenList();

    public int getOpportunityValue();
    
}
