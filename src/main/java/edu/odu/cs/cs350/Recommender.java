package edu.odu.cs.cs350;

import edu.odu.cs.cs350.Interfaces.*;

import java.util.*;
import java.math.BigDecimal;

/**
 * Taking as input a list of tokens which represents a string of
 * tokens taken from all files that the Input processed. It will
 * process these tokens, and produce a list of recommended
 * refactorings, ordered from greatest to least opportunity value.
 * Additionally, after finding a refactoring recommendation, but
 * before adding the recommendation to the list of recommendations,
 * it will compute the opportunity value of that recommendation.
 * @see RecommenderInterface
 * @see TestRecomender
 */
public class Recommender implements RecommenderInterface {

    private List<TokenInterface> tokens;
    private List<RefactoringInterface> refactorings;
    private int minRefactoringSize;
    private int maxRefactoringSize;

    /**
     * Default constructor.
     * tokens and refactorings are set to empty lists;
     * minRefactoringSize and maxRefactoringSize are set to 0.
     */
    Recommender() {
        this.setTokens(new ArrayList<TokenInterface>());
        this.setRefactorings(new ArrayList<RefactoringInterface>());
        this.setMinRefactoringSize(0);
        this.setMaxRefactoringSize(0);
    }

    /**
     * Parameter constructor.
     * tokens is set to inputTokens, recommend runs to set
     * the Refactorings list, 
     * @param inputTokens
     */
    Recommender(List<TokenInterface> inputTokens) {
        this.setTokens(inputTokens);
        this.setMinRefactoringSize();
        this.setMaxRefactoringSize();
        this.recommend();
    }

    Recommender(List<TokenInterface> inputTokens, int min, int max) {
        this.setTokens(inputTokens);
        this.setMinRefactoringSize(min);
        this.setMaxRefactoringSize(max);
        this.recommend();
    }

    /**
     * @return the list of tokens from which the recommendations
     * are generated.
     */
    @Override
    public List<TokenInterface> getTokens() {
        return this.tokens;
    }

    /**
     * Sets the list of tokens from which recommendations are
     * generated to the input list parameter.
     * @param input a list of token objects.
     */
    @Override
    public void setTokens(List<TokenInterface> input) {
        this.tokens = input;
    }

    /**
     * If no refactorings have been created, create them.
     * @return a complete list of all refactoring recommendations.
     */
    @Override
    public List<RefactoringInterface> getRefactorings() {
        if (this.refactorings.size() == 0) this.recommend();
        return this.refactorings;
    }

    /**
     * Sets recommender's list of refactorings to a pre-existing
     * refactoring list.
     * @param input a list of Refactoring recommendations.
     */
    @Override
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
    @Override
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
    @Override
    public void setMinRefactoringSize(int input) {
        this.minRefactoringSize = input;
    }

    /**
     * Recommended refactorings should be greater than
     * minRefactoringSize but less than maxRefactoringSize,
     * inclusive. If no input was provided, sets the min to
     * 0 if Recommender's list of tokens is empty, or to 3%
     * of the size of the token list.
     */
    @Override
    public void setMinRefactoringSize() {
        if (this.getTokens().size() == 0) {
            this.minRefactoringSize = 0;
            return;
        }
        else {
            BigDecimal size = new BigDecimal(this.getTokens().size());
            BigDecimal result = size.multiply(new BigDecimal("0.03"));
            this.minRefactoringSize = result.intValue();
            return;
        }
    }

    /**
     * Recommended refactorings should be greater than
     * minRefactoringSize but less than maxRefactoringSize,
     * inclusive.
     * @return the maximum number of tokens that can make up a
     * refactoring.
     */
    @Override
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
    @Override
    public void setMaxRefactoringSize(int input) {
        this.maxRefactoringSize = input;
    }

    /**
     * Recommended refactorings should be greater than
     * minRefactoringSize but less than maxRefactoringSize,
     * inclusive. If no input was provided, sets the max to
     * 0 if Recommender's list of tokens is empty, or to 75%
     * of the size of the token list.
     */
    @Override
    public void setMaxRefactoringSize() {
        if (this.getTokens().size() == 0) {
            this.maxRefactoringSize = 0;
            return;
        }
        else {
            BigDecimal size = new BigDecimal(this.getTokens().size());
            BigDecimal result = size.multiply(new BigDecimal("0.70"));
            this.maxRefactoringSize = result.intValue();
            return;
        }
    }

    /**
     * Computes the opportunity value for refactoring for candidate,
     * based on its size and number of reoccurrences in the list of
     * tokens that was given to Recommender.
     * @param candidate a list of tokens that is a candidate for
     * refactorization.
     * @param amountOfDuplicates the number of times the candidate
     * reoccurs in tokens.
     * @param 
     * @return an integer between 0 and 100, inclusive, representing
     * the amount of value a refactoring candidate has.
     */
    private int computeOpportunityValue(List<TokenInterface> candidate, int amountOfDuplicates, int amountOfSublists) {
        /**
         * Formula for calculating opportunity value:
         * A = candidate.size() / tokens.size()
         * B = amountOfDuplicates / sublists.size()
         * value = ( A + B ) * 100
         */

         BigDecimal A = new BigDecimal(candidate.size());
         A.divide(new BigDecimal(tokens.size()));

         BigDecimal B = new BigDecimal(amountOfDuplicates);
         B.divide(new BigDecimal(amountOfSublists));

         BigDecimal result = A.add(B);
         result.multiply(new BigDecimal(100));
         return result.intValue();
    }

    /**
     * Using the list of tokens, finds all suggested refactorings of
     * those tokens, calculates the opportunity value for each
     * candidate, and then produces a list of suggested
     * refactorings.
     */
    private void recommend() {
        List<RefactoringInterface> result = new ArrayList<RefactoringInterface>();

        //  sublists stores each of the created candidate list of tokens.
        List<List<TokenInterface>> sublists = new ArrayList<List<TokenInterface>>();

        //  Create lists with varying sizes between minRefactoringSize up to and including maxRefactoringSize.
        for (int i = this.minRefactoringSize; i <= this.maxRefactoringSize; i++) {
            int candidateLength = maxRefactoringSize - minRefactoringSize;

            //  Loop through the entire token list
            for (int j = 0; j < this.tokens.size(); i++) {
                
                //  Create a new sublist from j to j+candidateLength
                sublists.add(tokens.subList(j, candidateLength));
            }
        }

        //  Determine whether each sublist is a recommended refactoring.
        for (List<TokenInterface> list : sublists) {
            int occurrences = countOccurrences(list, sublists);
            if (occurrences > 1) {
                int opportunityValue = computeOpportunityValue(list, occurrences - 1, sublists.size());
                result.add(new Refactoring(list, opportunityValue));
            }
        }
    }

    /**
     * Counts the number of times candidate reoccurs in sublists.
     * @param candidate the list of tokens representing a candidate for refactorization.
     * @param sublists the list of all sublists of tokens.
     * @return the number of times candidate reoccurs in sublists (counting itself).
     */
    private int countOccurrences(List<TokenInterface> candidate, List<List<TokenInterface>> sublists) {
        int result = 0;
        for (List<TokenInterface> list : sublists) {
            if (candidate.equals(list)) result++;
        }
        return result;
    }
}
