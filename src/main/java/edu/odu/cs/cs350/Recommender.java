package edu.odu.cs.cs350;

import edu.odu.cs.cs350.Interfaces.*;

import java.util.*;

/**
 * Performs the functions of producing refactoring recommendations based on input files.
 * tokens contains a list of all tokens provided as input from the user.
 */
public class Recommender {

    private List<TokenInterface> tokens;
    private List<RefactoringInterface> refactorings;
    int minSublistSize;
    int maxSublistSize;
    
    /**
     * Default constructor for Recommender.
     */
    Recommender() {
        this.setTokens(new ArrayList<TokenInterface>());
        setMinSublistSize(0);
        setMaxSublistSize(0);
    }

    /**
     * Constructor.
     * @param tokens a list of C++ tokens from all files that were provided as input from the user.
     */
    Recommender(List<TokenInterface> tokens, int minSublistSize, int maxSublistSize) {
        this.setTokens(tokens);
        setMinSublistSize(minSublistSize);
        setMaxSublistSize(maxSublistSize);
    }

    /**
     * Sets the list of tokens to create recommended refactorings from.
     * @param tokens a list of C++ tokens from all files that were provided as input from the user.
     */
    public void setTokens(List<TokenInterface> tokens) {
        List<TokenInterface> tokenlist = new ArrayList<TokenInterface>();
		for (var t : tokens) tokenlist.add(t);
		this.tokens = tokenlist;
    }

    /**
     * Sets minSublistSize equal to min.
     * @param min the minimum size of token sublists to analyze.
     */
    public void setMinSublistSize(int min)
    {
        this.minSublistSize = min;
    }

    /**
     * @return the minimum size of token sublists to analyze.
     */
    public int getMinSublistSize()
    {
        return this.minSublistSize;
    }

    /**
     * Sets maxSublistSize equal to max.
     * @param max the minimum size of token sublists to analyze.
     */
    public void setMaxSublistSize(int max)
    {
        this.maxSublistSize = max;
    }

    /**
     * @return the maximum size of token sublists to analyze.
     */
    public int getMaxSublistSize()
    {
        return this.maxSublistSize;
    }

    /**
     * @return the list of tokens that recommended refactorings are created from.
     */
    public List<TokenInterface> getTokens() {
        return this.tokens;
    }

    /**
     * Computes the list of all recommended refactorings, ordered by
     * opportunity value from most valuable to least.
     * @return the list of all recommended refactorings.
     */
    public List<RefactoringInterface> getRecommendations()
    {
        List<RefactoringInterface> result = new ArrayList<RefactoringInterface>();
        List<List<TokenInterface>> sublists = new ArrayList<List<TokenInterface>>();

        /**
         * Create a 2-D list of lists of tokens.
         * - The lists should vary in size from 5 tokens up to and including 70% of the size of the largest input file.
         * - Each list of the same size should overlap by 1 token.
         */
        for (int i = getMinSublistSize(); i <= getMaxSublistSize(); i++) {
            sublists.add(createSublists(i, this.tokens));
        }

        /**
         * Compare each sublist with all the other sublists of the same size.
         * - If two sublists have the exact same sequence of tokens, it is a recommended refactoring.
         * - Assign the candidate to a list of candidates.
         */
        List<List<TokenInterface>> candidateTokens = new ArrayList<List<TokenInterface>>();
        for (List<TokenInterface> list : sublists) {
            if (isCandidate(list)) candidateTokens.add(list);
        }

        /**
         * Instantiate a new refactoring and add it to the result list.
         */
        for (List<TokenInterface> candidate : candidateTokens) {
            int opportunityValue = computeOpportunity(candidate, sublists);
            result.add(new Refactoring(candidate, opportunityValue));
        }

        this.refactorings = result;

        /**
         * Sort the list from greatest to least oppotunity value.
         */
        Sort();

        return this.refactorings;
    }

    /**
     * Sorts the list from greatest to least oppotunity value.
     */
    private void Sort() {
        // todo
        return;
    }

    /**
     * Compute a value reoccurences, which is the number of times the sequence of tokens re-occurs in the 2-D list.
     * Compute a value length, which is the length of the candidate sequence of tokens.
     * Compute and return opportunityValue = ( ( reoccurences * length ) % 100 )
     * 
     * @param candidate the list of tokens to compute the opportunityValue for.
     * @param sublists the total list of token lists to check for a count of reocurrences.
     * @return opportunityValue, an integer between 0 and 100 inclusive, representing how valuable a recommended refactoring is.
     */
    private int computeOpportunity(List<TokenInterface> candidate, List<List<TokenInterface>> sublists) {
        //todo
        return 0;
    }

    /**
     * @param list a list of tokens to check to see if it is a candidate for refactorization.
     * @return true iff list is a candidate for refactorization.
     */
    private boolean isCandidate(List<TokenInterface> list) {
        //todo
        return false;
    }

    /**
     * Create a 2-D list of lists of tokens.
     * The lists should vary in size from 5 tokens up to and including 70% of the size of the largest input file.
     * Each list of the same size should overlap by 1 token.
     * @param i
     * @param tokens
     * @return
     */
    private List<TokenInterface> createSublists(int i, List<TokenInterface> tokens) {
        //todo
        return null;
    }
}
