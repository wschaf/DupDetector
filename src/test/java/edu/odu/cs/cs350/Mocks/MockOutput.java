/*
Complete DupDetector Output as follows:

Files scanned:
	<FileAbsolutePath>, <NumberofTokens>
	<FileAbsolutePath>, <NumberofTokens>
	<FileAbsolutePath>, <NumberofTokens>

Opportunity <Opportunity#>, <NumberofTokensInOpportunity> tokens
<RefactoringAbsolutePath>:<RefactoringLineNumber>:<RefactoringColumnNumber>
<ListofTokens>

*/

package edu.odu.cs.cs350.Mocks;

import edu.odu.cs.cs350.*;

import java.util.*;

public class MockOutput extends Output {
    
	private int refactoringsToPrint;
	private List<MockFile> files;
	private List<MockRefactoring> refactorings;

	public MockOutput() {
		refactoringsToPrint = 0;
		files = new ArrayList<MockFile>();
		refactorings = new ArrayList<MockRefactoring>();
	}

	public MockOutput(int refactoringsToPrint, List<MockFile> files, List<MockRefactoring> refactorings) {
		this.refactoringsToPrint = refactoringsToPrint;
		this.files = files;
		this.refactorings = refactorings;
	}

	public int getRefactoringsToPrint() {
		return refactoringsToPrint;
	}

	public void setRefactoringsToPrint(int refactoringsToPrint) {
		this.refactoringsToPrint = refactoringsToPrint;
	}

	public List<MockFile> getFiles() {
		return files;
	}

	public void setFiles(List<MockFile> files) {
		this.files = files;
	}

	public List<MockRefactoring> getRefactorings() {
		return refactorings;
	}

	public void setRefactorings(List<MockRefactoring> refactorings) {
		this.refactorings = refactorings;
	}

	public String getSectionOne() {
		String sectionOne = new String();
		sectionOne.concat("Files Scanned:\n");
		for (var file : files) {
			sectionOne.concat(file.getAbsolutePath());
			sectionOne.concat(", ");
			sectionOne.concat(file.getNumberOfTokens());
			sectionOne.concat("\n");
		}
		return sectionOne;
	}

	public String getSectionTwo() {
		String sectionTwo = new String();
		for (int i = 0; i < refactoringsToPrint; i++) {
			sectionTwo.concat("Opportunity #");
			sectionTwo.concat(Integer.toString(i));
			sectionTwo.concat(", ");
			sectionTwo.concat(refactorings.get(i).getNumberOfTokens());
			sectionTwo.concat(" tokens\n");
			sectionTwo.concat(refactorings.get(i).getAbsolutePath());
			sectionTwo.concat(":");
			sectionTwo.concat(refactorings.get(i).getLineNumber());
			sectionTwo.concat(":");
			sectionTwo.concat(refactorings.get(i).getColumnNumber());
			sectionTwo.concat("\n");
			sectionTwo.concat(refactorings.get(i).getTokenList());
			sectionTwo.concat("\n");
		}
		return sectionTwo;
	}

	public String getCompleteOutput() {
		String completeOutput = new String();

		completeOutput.concat(getSectionOne());
		completeOutput.concat("\n");
		completeOutput.concat(getSectionTwo());

		return completeOutput;
	}
}
