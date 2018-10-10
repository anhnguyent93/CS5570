package components;

import org.junit.Test;

import java.util.ArrayList;

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

        System.out.println("components.TransactionTest:: Below are the components.Transaction Operations:");

        for (Operation op: testTxnArrListOp) {
            System.out.print(op.toString());
        }
    }
}