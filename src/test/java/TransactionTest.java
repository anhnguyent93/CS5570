import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by DJ Yuhn on 10/8/2018
 */
public class TransactionTest {
    @Test
    public void testIfTransactionProducesProperTxnHistory() {
        Integer testTxnID = 1;
        Integer[] testDataItems = {1};
        Transaction testTxn = new Transaction(testTxnID, testDataItems);
        testTxn.createNewRandomHistory();
        ArrayList<Operation> testTxnArrListOp = testTxn.getTxnHist();

        for (Operation op: testTxnArrListOp) {
            System.out.println(op.toString());
        }
    }
}