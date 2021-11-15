package edu.odu.cs.cs350;

import edu.odu.cs.cs350.Interfaces.*;

import java.util.*;

public class Recommender {

    private List<TokenInterface> tokens;
    
    Recommender() {

    }

    Recommender(List<TokenInterface> tokens) {
        this.setTokens(tokens);
    }

    public void setTokens(List<TokenInterface> tokens) {
        List<TokenInterface> tokenlist = new ArrayList<TokenInterface>();
		for (var t : tokens) tokenlist.add(t);
		this.tokens = tokenlist;
    }

    public List<TokenInterface> getTokens() {
        return this.tokens;
    }

    public List<RefactoringInterface> getRecommendations()
    {
        List<RefactoringInterface> result = new ArrayList<RefactoringInterface>();
        for (int i = tokens.size() - 5; i > 0; i++) {
            List<List<TokenInterface>> sublists = createSublists(i, tokens);
            List<RefactoringInterface> r = analyzeSublists(sublists);
            if (r.size() > 0) result.addAll(r);
        }
        return result;
    }

    private List<List<TokenInterface>> createSublists(int i, List<TokenInterface> tokens) {
        List<List<TokenInterface>> result = new ArrayList<List<TokenInterface>>();
        for (int k = 0; k < i; k++) {
            List<TokenInterface> n = new ArrayList<TokenInterface>();
            for (int j = k; j < i; j++) {
                n.add(tokens.get(j));
            }
            result.add(n);
        }
        return result;
    }

    private List<RefactoringInterface> analyzeSublists(List<List<TokenInterface>> sublists) {
        List<RefactoringInterface> result = new ArrayList<RefactoringInterface>();
        for (var current : sublists) {
            for (int i = 0; i < sublists.size(); i++) {
                if (match(current, sublists.get(i))) {
                    result.add(new Refactoring(current, sublists.get(i)));
                }
            }
        }
        return result;
    }

    private boolean match(List<TokenInterface> current, List<TokenInterface> list) {
        return false;
    }
}
