package edu.odu.cs.cs350.Mocks;

import edu.odu.cs.cs350.Interfaces.*;

public class MockRefactoring implements RefactoringInterface {

    public int numberOfTokens = 6;
    public String absolutePath = "/home/wgs/src/cs350/DupDetector/src/test/data/hello.cpp";
    public int lineNumber = 32;
    public int columnNumber = 64;
    public String tokenList = "if this then that";
    public int opportunityValue;
    
    public String getNumberOfTokens() {
        return Integer.toString(numberOfTokens);
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public String getLineNumber() {
        return Integer.toString(lineNumber);
    }

    public String getColumnNumber() {
        return Integer.toString(columnNumber);
    }

    public String getTokenList() {
        return tokenList;
    }
    
}
