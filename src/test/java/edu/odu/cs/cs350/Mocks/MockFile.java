package edu.odu.cs.cs350.Mocks;

import edu.odu.cs.cs350.*;

public class MockFile extends File {

    String absolutePath = "/home/wgs/src/cs350/DupDetector/src/test/data/hello.cpp";
    int numberOfTokens = 19;
    String data = "#include <iostream>\nusing namespace std;\nint main(int argc, char** argv) {\n    cout << \"Hello World!\n\";\nreturn 0;\n}\"";

    
    public String getAbsolutePath() {
        return absolutePath;
    }

    public String getNumberOfTokens() {
        return Integer.toString(numberOfTokens);
    }
    
}
