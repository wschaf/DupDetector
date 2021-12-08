package edu.odu.cs.cs350;

import java.util.List;

import edu.odu.cs.cs350.Interfaces.RefactoringInterface;
import edu.odu.cs.cs350.Interfaces.TokenInterface;

/**
 * A refactoring object contains the information needed by
 * Output to print the refactoring suggestion and its associated metadata.
 */
public class Refactoring implements RefactoringInterface, Comparable<Refactoring> {

    public int numberOfTokens;
    public String absolutePath;
    public int lineNumber;
    public int columnNumber;
    public String tokenList;
    public int opportunityValue;

    public Refactoring(List<TokenInterface> current, List<TokenInterface> list) {
        numberOfTokens = current.size();
        absolutePath = current.get(0).getAbsolutePath();
        lineNumber = Integer.parseInt(current.get(0).getLineNumber());
        columnNumber = Integer.parseInt(current.get(0).getColumnNumber());
        tokenList = current.toString();
    }

    public Refactoring(List<? extends TokenInterface> candidate, int opportunityValue) {
        this.numberOfTokens = candidate.size();
        this.absolutePath = candidate.get(0).getAbsolutePath();
        this.lineNumber = Integer.parseInt(candidate.get(0).getLineNumber());
        this.columnNumber = Integer.parseInt(candidate.get(0).getColumnNumber());
        this.tokenList = "";
        for(var s : candidate) this.tokenList += s.getLexeme() + " ";
        // this.tokenList = candidate.toString();
        this.opportunityValue = opportunityValue;
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
        return Integer.toString(columnNumber-1);
    }

    @Override
    public String getTokenList() {
        return tokenList;
    }

    @Override
    public int compareTo(Refactoring o) {
        return o.opportunityValue - this.opportunityValue;
    }

    @Override
    public String toString() {
        String result = "";

        result += numberOfTokens + " ";
        result += "ABSPATH" + " ";
        result += lineNumber + " ";
        result += columnNumber + " ";
        result += tokenList + " ";
        result += opportunityValue;

        return result;
    }

    @Override
    public int getOpportunityValue() {
        return this.opportunityValue;
    }
}
