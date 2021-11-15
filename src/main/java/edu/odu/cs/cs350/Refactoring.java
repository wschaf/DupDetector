package edu.odu.cs.cs350;

import java.util.List;

import edu.odu.cs.cs350.Interfaces.RefactoringInterface;
import edu.odu.cs.cs350.Interfaces.TokenInterface;

/**
 * A refactoring object contains the information needed by
 * Output to print the refactoring suggestion and its associated metadata.
 */
public class Refactoring implements RefactoringInterface {

    public int numberOfTokens;
    public String absolutePath;
    public int lineNumber;
    public int columnNumber;
    public String tokenList;
    public int opportunityValue;

    public Refactoring(List<TokenInterface> current, List<TokenInterface> list) {
        numberOfTokens = current.size();
        lineNumber = Integer.parseInt(current.get(0).getLineNumber());
        columnNumber = Integer.parseInt(current.get(0).getColumnNumber());
        tokenList = current.toString();
    }

    @Override
    public String getNumberOfTokens() {
        return Integer.toString(numberOfTokens);
    }

    @Override
    public String getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public String getLineNumber() {
        return Integer.toString(lineNumber);
    }

    @Override
    public String getColumnNumber() {
        return Integer.toString(columnNumber);
    }

    @Override
    public String getTokenList() {
        return tokenList;
    }
    
}
