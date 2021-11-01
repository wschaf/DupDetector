package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


public class testOutput {
    
    @Test
    public void testPrintSectionOne() {
        assertThat("Trueness", true, is(true));
    }
}
