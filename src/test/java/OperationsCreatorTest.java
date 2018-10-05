import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by DJ Yuhn on 10/5/2018
 */
public class OperationsCreatorTest {
    @Test
    public void testOperationCreatorCreatesOneOperationOnDataItemAndOutputsProperString() {
        Integer testTxnID = 1;
        Integer testDataItem = 1;
        int testNumOfOps = 1;

        Operation testOperation = OperationsCreator.createRandOpsOnDataItem(testTxnID, testDataItem, testNumOfOps)[0];

        String testStringR = "r1[1]";
        String testStringW = "w1[1]";

        if (testOperation.getOperation() == 'r') {
            assert (testOperation.toString().equals(testStringR));
        }
        else
            assert (testOperation.toString().equals(testStringW));
    }

    @Test
    public void testOperationCreatorCreatesAnAbortOrCommitAndOutputsProperString() {
        Integer testTxnID = 1;

        Operation testOperation = OperationsCreator.createAbortOrCommit(testTxnID);

        String testStringA = "a1";
        String testStringC = "c1";

        if (testOperation.getOperation() == 'a') {
            assert (testOperation.toString().equals(testStringA));
        }
        else
            assert (testOperation.toString().equals(testStringC));
    }

}