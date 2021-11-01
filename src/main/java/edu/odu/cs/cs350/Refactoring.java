package edu.odu.cs.cs350;

import java.util.StringTokenizer;

public class Refactoring {
	
<<<<<<< HEAD
	
=======
	private int numRefTokens; //added new members
    private int numOpportunities;
    
    /**
     * set the number of tokens that were Refactored
     * @param r: the number of Refactored tokens
     */
    public void setNumRefTokens(int r) {
    	this.numRefTokens = r;
    }
    
    /**
     * get the number of Refactored Tokens
     * 
     * @return: get the number of Refactored Tokens
     */
    public int getNumRefTokens() {
    	return this.numRefTokens;
    }
    
    /**
     * set the number of nSuggestion Opportunities
     * @param o: the number of Opportunities
     */
    public void setOpportunity(int o) {
    	this.numOpportunities = o;
    }
    
    /**
     * get the number of Opportunities
     * 
     * @return: get the number of Opportunities
     */
    public int getOpportunities() {
    	return this.numOpportunities;
    }
    
    public void countTokens() {
        //this is a placeholder that represents opportunities
        String fakeData = "Count c = 0; If (x = y); c++";

        // breaks a string into tokens, but does not distinguish identifiers, numbers, 
        // quoted strings, nor recognize and skip comments
        StringTokenizer stringTokens = new StringTokenizer(fakeData);

        // count the number of tokens
        numOpportunities = stringTokens.countTokens();
    }
>>>>>>> 626fe0e603636e42d5c4391ecdb6f4ee3c550836
    
}
