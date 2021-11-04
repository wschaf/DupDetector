package edu.odu.cs.cs350.Mocks;

import edu.odu.cs.cs350.Interfaces.*;

public class MockFile implements FileInterface {

    private String absolutePath = "/home/wgs/src/cs350/DupDetector/src/test/data/hello.cpp";
    private int numberOfTokens = 19;
    private String data = "#include <iostream>\nusing namespace std;\nint main(int argc, char** argv) {\n    cout << \"Hello World!\n\";\nreturn 0;\n}\"";

    @Override
    public String getAbsolutePath() {
        return absolutePath;
    }

    @Override
	public void setAbsolutePath(String input) {
		this.absolutePath = input;
	}

    @Override
    public String getNumberOfTokens() {
        return Integer.toString(numberOfTokens);
    }

	@Override
	public void setNumberOfTokens(int input) {
		this.numberOfTokens = input;
	}

	@Override
	public String getData() {
		return this.data;
	}

	@Override
	public void setData(String input) {
		this.data = input;
	}
    
}
