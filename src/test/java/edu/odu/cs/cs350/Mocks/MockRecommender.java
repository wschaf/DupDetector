package edu.odu.cs.cs350.Mocks;

import java.util.List;

import edu.odu.cs.cs350.Interfaces.RecommenderInterface;
import edu.odu.cs.cs350.Interfaces.RefactoringInterface;
import edu.odu.cs.cs350.Interfaces.TokenInterface;

public class MockRecommender implements RecommenderInterface {

    @Override
    public List<? extends TokenInterface> getTokens() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTokens(List<? extends TokenInterface> input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<? extends RefactoringInterface> getRefactorings() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setRefactorings(List<? extends RefactoringInterface> input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getMinRefactoringSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setMinRefactoringSize(int input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setMinRefactoringSize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getMaxRefactoringSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setMaxRefactoringSize(int input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setMaxRefactoringSize() {
        // TODO Auto-generated method stub
        
    }
    
}
