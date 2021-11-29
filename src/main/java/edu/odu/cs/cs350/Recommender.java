package edu.odu.cs.cs350;

import edu.odu.cs.cs350.Interfaces.*;

import java.util.*;

/**
 * Taking as input a list of tokens which represents a string of
 * tokens taken from all files that the Input processed. It will
 * process these tokens, and produce a list of recommended
 * refactorings, ordered from greatest to least opportunity value.
 * Additionally, after finding a refactoring recommendation, but
 * before adding the recommendation to the list of recommendations,
 * it will compute the opportunity value of that recommendation.
 * @see Recommender
 * @see TestRecomender
 */
public class Recommender implements RecommenderInterface {

    private List<TokenInterface> tokens;
    private List<RefactoringInterface> refactorings;
    private int minRefactoringSize;
    private int maxRefactoringSize;

    /**
     * @return the list of tokens from which the recommendations
     * are generated.
     */
    public List<TokenInterface> getTokens() {
        return this.tokens;
    }

    /**
     * Sets the list of tokens from which recommendations are
     * generated to the input list parameter.
     * @param input a list of token objects.
     */
    public void setTokens(List<TokenInterface> input) {
        this.tokens = input;
    }

    /**
     * If no refactorings have been created, create them.
     * @return a complete list of all refactoring recommendations.
     */
    public List<RefactoringInterface> getRefactorings() {
        if (this.refactorings.size() == 0) this.recommend();
        return this.refactorings;
    }

    /**
     * Sets recommender's list of refactorings to a pre-existing
     * refactoring list.
     * @param input a list of Refactoring recommendations.
     */
    public void setRefactorings(List<RefactoringInterface> input) {
        this.refactorings = input;
    }

    /**
     * Recommended refactorings should be greater than
     * minRefactoringSize but less than maxRefactoringSize,
     * inclusive.
     * @return the minimum number of tokens that can make up a
     * refactoring.
     */
    public int getMinRefactoringSize() {
        return this.minRefactoringSize;
    }

    /**
     * Recommended refactorings should be greater than
     * minRefactoringSize but less than maxRefactoringSize,
     * inclusive.
     * @param input the minimum number of tokens that can make up a
     * refactoring.
     */
    public void setMinRefactoringSize(int input) {
        this.minRefactoringSize = input;
    }

    /**
     * Recommended refactorings should be greater than
     * minRefactoringSize but less than maxRefactoringSize,
     * inclusive.
     * @return the maximum number of tokens that can make up a
     * refactoring.
     */
    public int getMaxRefactoringSize() {
        return this.maxRefactoringSize;
    }

    /**
     * Recommended refactorings should be greater than
     * minRefactoringSize but less than maxRefactoringSize,
     * inclusive.
     * @param input the maximum number of tokens that can make up a
     * refactoring.
     */
    public void setMaxRefactoringSize(int input) {
        this.maxRefactoringSize = input;
    }
}
