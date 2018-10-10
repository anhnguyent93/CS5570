package components;

import org.junit.Test;

/**
 * Created by DJ Yuhn on 10/5/2018
 */
public class OperationTest {
    @Test
    public void testIfOperationReturnsAProperlyFormatedString() {
        char testRead = 'r';
        Integer testTxnID = 1;
        Integer testDataItem = 1;
        Operation testOperation = new Operation(testTxnID, testRead, testDataItem);
        String testOutput = testOperation.toString();
        String expectedOutput = "r1[1]";

        assert (testOutput.equals(expectedOutput));
    }

}