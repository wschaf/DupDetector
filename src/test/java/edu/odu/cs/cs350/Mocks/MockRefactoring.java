package edu.odu.cs.cs350.Mocks;

import edu.odu.cs.cs350.Interfaces.*;

import java.util.*;

public class MockRefactoring implements RefactoringInterface {

    public int numberOfTokens;
    public String absolutePath;
    public int lineNumber;
    public int columnNumber;
    public List<TokenInterface> tokenList;
    public int opportunityValue;

    public MockRefactoring() {
        this.numberOfTokens = 0;
        this.absolutePath = "";
        this.lineNumber = 0;
        this.columnNumber = 0;
        tokenList = new ArrayList<TokenInterface>();
    }

    public MockRefactoring(
        int numberOfTokens,
        String absolutePath,
        int lineNumber,
        int columnNumber,
        List<? extends TokenInterface> inputTokenList,
        int opportunityValue
    ) {
        this.numberOfTokens = numberOfTokens;
        this.absolutePath = absolutePath;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.tokenList = new ArrayList<TokenInterface>();
        for (var t : inputTokenList) this.tokenList.add(t);
        this.opportunityValue = opportunityValue;
    }
    
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
        String result = "";
        for (var t : this.tokenList) result += t.toString();
        return result;
    }

    @Override
    public int getOpportunityValue() {
        return this.opportunityValue;
    }
}
