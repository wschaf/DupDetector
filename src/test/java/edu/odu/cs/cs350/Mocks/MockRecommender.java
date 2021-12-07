package edu.odu.cs.cs350.Mocks;

import java.util.ArrayList;
import java.util.List;

import edu.odu.cs.cs350.Interfaces.RecommenderInterface;
import edu.odu.cs.cs350.Interfaces.RefactoringInterface;
import edu.odu.cs.cs350.Interfaces.TokenInterface;

public class MockRecommender implements RecommenderInterface {

    private List<TokenInterface> tokens;
    private List<RefactoringInterface> refactorings;
    private int minRefactoringSize;
    private int maxRefactoringSize;

    public MockRecommender() {
        this.tokens = new ArrayList<TokenInterface>();
        this.refactorings = new ArrayList<RefactoringInterface>();
        this.minRefactoringSize = 0;
        this.maxRefactoringSize = 0;
    }

    public MockRecommender(
        List<TokenInterface> tokens,
        List<RefactoringInterface> refactorings,
        int minRefactoringSize,
        int maxRefactoringSize
    ) {
        this.tokens = tokens;
        this.refactorings = refactorings;
        this.minRefactoringSize = minRefactoringSize;
        this.maxRefactoringSize = maxRefactoringSize;
    }

    @Override
    public List<? extends TokenInterface> getTokens() {
        return this.tokens;
    }

    @Override
    public void setTokens(List<? extends TokenInterface> input) {
        this.tokens = new ArrayList<TokenInterface>();
        for (var t : input) this.tokens.add(t);
    }

    @Override
    public List<? extends RefactoringInterface> getRefactorings() {
        return this.refactorings;
    }

    @Override
    public void setRefactorings(List<? extends RefactoringInterface> input) {
        this.refactorings = new ArrayList<RefactoringInterface>();
        for (var r : input) this.refactorings.add(r);
    }

    @Override
    public int getMinRefactoringSize() {
        return this.minRefactoringSize;
    }

    @Override
    public void setMinRefactoringSize(int input) {
        this.minRefactoringSize = input;
        
    }

    @Override
    public void setMinRefactoringSize() {
        this.minRefactoringSize = 0;
        
    }

    @Override
    public int getMaxRefactoringSize() {
        return this.maxRefactoringSize;
    }

    @Override
    public void setMaxRefactoringSize(int input) {
        this.maxRefactoringSize = input;
        
    }

    @Override
    public void setMaxRefactoringSize() {
        this.maxRefactoringSize = 0;
    }
    
}
