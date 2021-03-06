package components;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by DJ Yuhn on 10/8/2018
 */
public class TransactionTest {
    @Test
    public void testIfTransactionProducesProperTxnHistory() {
        Integer testTxnID = 1;
        HashSet<Integer> testDataItems = new HashSet<>();
        Collections.addAll(testDataItems, 1);
        Transaction testTxn = new Transaction(testTxnID, testDataItems);
        testTxn.createNewRandomHistory();
        ArrayList<Operation> testTxnArrListOp = testTxn.getTxnHist();

        System.out.println("components.TransactionTest:: Below are the components.Transaction Operations:");

        for (Operation op: testTxnArrListOp) {
            System.out.print(op.toString());
        }
    }
}