package edu.odu.cs.cs350.Mocks;

import edu.odu.cs.cs350.*;

public class MockRefactoring extends Refactoring {

    int numberOfTokens = 6;
    String absolutePath = "/home/wgs/src/cs350/DupDetector/src/test/data/hello.cpp";
    int lineNumber = 32;
    int columnNumber = 64;
    String tokenList = "if this then that";

    
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
