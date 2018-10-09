import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by DJ Yuhn on 10/9/2018
 */
public class HistoryCreatorTest {
    @Test
    public void testIfHistoryCreatorCreatesHistoryWithTwoTransactionsWithTwoDataItems() {
        int testTxnCount = 2;
        int testDataItemsPerTxn = 2;
        int maxTxnID = 2;
        int maxDataItemID = 2;
        History testHistory = HistoryCreator.createRandomHistory(
                testTxnCount, testDataItemsPerTxn, maxTxnID, maxDataItemID);

        ArrayList<Operation> testHistOps = testHistory.getHistory();
        ArrayList<Transaction> testHistTxns = testHistory.getTxnsList();

        assert testHistTxns.size() == 2;

        for (Transaction txn: testHistTxns) {
            assert txn.getDataItems().length == 2;
        }
    }

}