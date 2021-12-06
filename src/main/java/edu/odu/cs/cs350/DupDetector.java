package edu.odu.cs.cs350;

/**
 * DupDetector is the main class for the system. It will call
 * each subsystem of the program, passing input/output between
 * them.
 */
public class DupDetector {
    
	public static void main(String args[]) throws Exception {
		Input input = new Input(args);
		Recommender recommender = new Recommender(input.getTokens());
		Output output = new Output(
			input.getNSuggestions(),
			input.getFiles(),
			recommender.getRefactorings()
		);
		System.out.println(output.getCompleteOutput());
    }
}
