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

    @BeforeEach
    public void setup() {
        //  Empty list of tokens.
        noTokens = new ArrayList<Token>();

        //  One C++ statement, no refactorings to recommend.
        basicTokens = Arrays.asList (
            (new Token(TokenType.INT, 2, 5)),
            (new Token(TokenType.IDENTIFIER, 2, 9)),
            (new Token(TokenType.ASSIGN_OP, 2, 11)),
            (new Token(TokenType.CONSTANT_NUMBERS, 2, 13)),
            (new Token(TokenType.SEMI_COLON, 2, 14))
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
        assertThat(subject.getTokens().get(0).toString(), equalTo(new Token(TokenType.INT, 2, 5).toString()));
        assertThat(subject.getTokens().get(4).toString(), equalTo(new Token(TokenType.SEMI_COLON, 2, 14).toString()));
    }

    @Test
    public void testSetTokens() {
        Recommender subject = new Recommender();
        subject.setTokens(basicTokens);

        assertThat(subject.getTokens().size(), is(5));
        assertThat(subject.getTokens().get(0).toString(), equalTo(new Token(TokenType.INT, 2, 5).toString()));
        assertThat(subject.getTokens().get(4).toString(), equalTo(new Token(TokenType.SEMI_COLON, 2, 14).toString()));
    }

    @Test
    public void testGetRefactorings() {
        //  TODO
    }

    @Test
    public void testSetRefactorings() {
        Recommender subject = new Recommender();
        List<Refactoring> refactorings = new ArrayList<Refactoring>();
        refactorings.add(new Refactoring(basicTokens, 5));
        subject.setRefactorings(refactorings);

        assertThat(subject.getRefactorings().size(), is(1));

    }

    @Test
    public void testGetMinRefactoringSize() {

    }

    @Test
    public void testSetMinRefactoringSize() {

    }

    @Test
    public void testSetMinRefactoringSizeParameter() {

    }

    @Test
    public void testGetMaxRefactoringSize() {

    }

    @Test
    public void testSetMaxRefactoringSize() {

    }

    @Test
    public void testSetMaxRefactoringSizeParameter() {

    }
}
