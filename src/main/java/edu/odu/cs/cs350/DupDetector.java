package edu.odu.cs.cs350;

import edu.odu.cs.cs350.Interfaces.*;

import java.util.List;

/**
 * DupDetector is the main class for the system. It will call
 * each subsystem of the program, passing input/output between
 * them.
 */
public class DupDetector {

	private static Input input;
	private static Recommender recommender;
	private static Output output;
    
	public static void main(String args[]) throws Exception {
		setInput(args);
		setRecommender(input.getTokens(), input.getMinSequenceLength());
		setOutput(input, recommender);
		System.out.println(output.getCompleteOutput());
    }

	/**
	 * Uses the Recommender constructor produce the refactoring
	 * recommendations.
	 * @param tokens a list of C++ tokens.
	 */
	private static void setRecommender(List<? extends TokenInterface> tokens, int min) {
		recommender = new Recommender(tokens, min);
	}

	/**
	 * @return the current Input oject.
	 */
	public static Input getInput() {
		return input;
	}

	/**
	 * Sets input to an arbitrary Input object.
	 * @param i the Input object to set input to.
	 */
	public static void setInput(Input i) {
		input = i;
	}

	/**
	 * Uses the input constructor to produce the data from args.
	 * @param args command line arguments for the program.
	 * @throws Exception
	 */
	public static void setInput(String args[]) throws Exception {
		input = new Input(args);
	}

	/**
	 * @return the current Recommender object.
	 */
	public static Recommender getRecommender() {
		return recommender;
	}

	/**
	 * Sets recommender to an arbitrary Recommender object.
	 * @param r the Recommender object to set recommender to.
	 */
	public static void setRecommender(Recommender r) {
		recommender = r;
	}

	/**
	 * @return the current Output object.
	 */
	public static Output getOutput() {
		return output;
	}

	/**
	 * Sets output to an arbitrary Output object.
	 * @param o the Output object to set output to.
	 */
	public static void setOutput(Output o) {
		output = o;
	}

	/**
	 * Sets output to an arbitrary Output object based on input and recommender.
	 * @param o the Output object to set output to.
	 */
	public static void setOutput(Input input, Recommender recommender) {
		output = new Output(input, recommender);
	}
}
