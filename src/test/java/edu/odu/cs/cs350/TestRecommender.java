package edu.odu.cs.cs350;

import java.util.*;

import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.*;

import edu.odu.cs.cs350.Interfaces.RefactoringInterface;
import edu.odu.cs.cs350.Interfaces.TokenInterface;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @see Recommender
 * @see RecommenderInterface
 */
public class TestRecommender {

    private List<Token> noTokens;
    private List<Token> basicTokens;
    private List<Token> oneRefactoring;

    @BeforeEach
    public void setup() {
        /** Empty list of tokens. */
        noTokens = new ArrayList<Token>();

        /** One C++ statement, no refactorings to recommend.
         * int x = 5;
        */
        basicTokens = Arrays.asList (
            (new Token(TokenType.INT, 2, 1)),
            (new Token(TokenType.IDENTIFIER, 2, 5)),
            (new Token(TokenType.ASSIGN_OP, 2, 7)),
            (new Token(TokenType.CONSTANT_NUMBERS, 2, 9)),
            (new Token(TokenType.SEMI_COLON, 2, 10))
        );

        /** The same C++ statement twice, should produce one candidate refacoring.
         * int x = 5;
         * int y = 6;
        */
        oneRefactoring = Arrays.asList (
            (new Token(TokenType.INT, 2, 1)),
            (new Token(TokenType.IDENTIFIER, 2, 5)),
            (new Token(TokenType.ASSIGN_OP, 2, 7)),
            (new Token(TokenType.CONSTANT_NUMBERS, 2, 9)),
            (new Token(TokenType.SEMI_COLON, 2, 10)),

            (new Token(TokenType.INT, 3, 1)),
            (new Token(TokenType.IDENTIFIER, 3, 5)),
            (new Token(TokenType.ASSIGN_OP, 3, 7)),
            (new Token(TokenType.CONSTANT_NUMBERS, 3, 9)),
            (new Token(TokenType.SEMI_COLON, 3, 10))
        );
    }

    @Test
    public void testRecommenderConstructor() {
        Recommender subject = new Recommender();

        assertThat(subject.getTokens().size(), is(0));
        assertThat(subject.getRefactorings().size(), is(0));
        assertThat(subject.getMinRefactoringSize(), is(subject.getMaxRefactoringSize()));
        assertThat(subject.getMinRefactoringSize(), is(0));
        assertThat(subject.getMaxRefactoringSize(), is(0));
    }
    
    @Test
    public void testRecommenderTokenParameterConstructor() {
        Recommender subject = new Recommender(noTokens);

        assertThat(subject.getTokens().size(), is(0));
        assertThat(subject.getRefactorings().size(), is(0));
        assertThat(subject.getMinRefactoringSize(), is(subject.getMaxRefactoringSize()));
        assertThat(subject.getMinRefactoringSize(), is(0));
        assertThat(subject.getMaxRefactoringSize(), is(0));
    }

    @Test
    public void testRecommenderMinMaxParameterConstructor() {
        Recommender subject = new Recommender(noTokens, 5, 10);

        assertThat(subject.getTokens().size(), is(0));
        assertThat(subject.getRefactorings().size(), is(0));
        assertThat(subject.getMinRefactoringSize(), not(subject.getMaxRefactoringSize()));
        assertThat(subject.getMinRefactoringSize(), is(5));
        assertThat(subject.getMaxRefactoringSize(), is(10));
    }
    
    @Test
    public void testGetTokens() {
        Recommender subject = new Recommender(basicTokens);

        assertThat(subject.getTokens().size(), is(5));
        assertThat(subject.getTokens().get(0).toString(), equalTo(new Token(TokenType.INT, 2, 1).toString()));
        assertThat(subject.getTokens().get(4).toString(), equalTo(new Token(TokenType.SEMI_COLON, 2, 10).toString()));
    }

    @Test
    public void testSetTokens() {
        Recommender subject = new Recommender();
        subject.setTokens(basicTokens);

        assertThat(subject.getTokens().size(), is(5));
        assertThat(subject.getTokens().get(0).toString(), equalTo(new Token(TokenType.INT, 2, 1).toString()));
        assertThat(subject.getTokens().get(4).toString(), equalTo(new Token(TokenType.SEMI_COLON, 2, 10).toString()));
    }

    @Test
    public void testGetRefactorings() {
        /**
         * Three scenarios to test:
         * 1. A recommender with no tokens returns an empty list.
         * 2. A recommender with non-refactorable tokens returns an empty list.
         * 3. A recommender with refactorable tokens returns at least one refactoring.
         */

        Recommender subject1 = new Recommender();
        assertThat(subject1.getRefactorings().size(), is(0));
        
        Recommender subject2 = new Recommender(basicTokens, 0, 5);
        assertThat(subject2.getRefactorings().size(), is(0));
    }

    @Test
    public void testSetRefactorings() {
        Recommender subject = new Recommender();
        List<Refactoring> refactorings = new ArrayList<Refactoring>();
        refactorings.add(new Refactoring(basicTokens, 5));
        subject.setRefactorings(refactorings);

        assertThat(subject.getRefactorings().size(), not(0));
        assertThat(subject.getRefactorings(), is(refactorings));
    }

    @Test
    public void testGetMinRefactoringSize() {
        Recommender subject = new Recommender();
        assertThat(subject.getMinRefactoringSize(), is(0));

        subject.setMinRefactoringSize(5);
        assertThat(subject.getMinRefactoringSize(), is(5));
    }

    @Test
    public void testSetMinRefactoringSizeDefault() {
        List<TokenInterface> t = new ArrayList<TokenInterface>();
        for (int i = 0; i < 100; i++) t.add(new Token(TokenType.INT, 2, 5));
        Recommender subject = new Recommender(t);

        assertThat(subject.getMinRefactoringSize(), is(3));
    }

    @Test
    public void testSetMinRefactoringSizeParameter() {
        Recommender subject = new Recommender();
        subject.setMinRefactoringSize(5);

        assertThat(subject.getMinRefactoringSize(), is(5));
    }

    @Test
    public void testGetMaxRefactoringSize() {
        Recommender subject = new Recommender();
        assertThat(subject.getMinRefactoringSize(), is(0));

        subject.setMinRefactoringSize(50);
        assertThat(subject.getMinRefactoringSize(), is(50));
    }

    @Test
    public void testSetMaxRefactoringSize() {
        List<TokenInterface> t = new ArrayList<TokenInterface>();
        for (int i = 0; i < 100; i++) t.add(new Token(TokenType.INT, 2, 5));
        Recommender subject = new Recommender(t);

        assertThat(subject.getMaxRefactoringSize(), is(70));
    }

    @Test
    public void testSetMaxRefactoringSizeParameter() {
        Recommender subject = new Recommender();
        subject.setMaxRefactoringSize(50);

        assertThat(subject.getMaxRefactoringSize(), is(50));
    }
}
