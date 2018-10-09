import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by DJ Yuhn on 10/8/2018
 */
public class HistoryTest {
    @Test
    public void testIfHistoryHasSameTotalOperationsToTheTransactionsOperationsSum() {
        ArrayList<Transaction> testTxns = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            testTxns.add(TransactionCreator.createTransactionWithDataItems(i, 4, 10));
            testTxns.get(i).createNewRandomHistory();
        }
        History testHistory = new History(testTxns);

        int testSumOfOpsInTxns = 0;
        for (Transaction txn:testTxns) {
            testSumOfOpsInTxns += txn.getTxnHist().size();
        }

        testHistory.createRandomHistory();
        StringBuilder testOutput = new StringBuilder();
        for (Operation op: testHistory.getHistory()) {
            testOutput.append(op.toString());
        }
        System.out.println("HistoryTest:: Below is the history:");
        System.out.println(testOutput.toString());

        assert (testHistory.getHistory().size() == testSumOfOpsInTxns);
    }

}