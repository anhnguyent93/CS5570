package creators;

import components.History;
import components.Operation;
import components.Transaction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

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
            assert txn.getDataItems().size() == 2;
        }
    }

    @Test
    public void testIfCustomHistoryCreatorCreatesProperHistoryForTwoTransactionsContainingCommits() {
        String testHistoryString = "r1[1],r2[1],w1[1],w2[1],c1[],c2[]";
        Integer testDataItem = 1;
        String[] testTxn1Ops = {"r1[1]", "w1[1]", "c1"};
        String[] testTxn2Ops = {"r2[1]", "w2[1]", "c2"};
        String[] testHistOps = {"r1[1]", "r2[1]", "w1[1]", "w2[1]", "c1", "c2"};

        History testHistory = HistoryCreator.createCustomHistory(testHistoryString);

        ArrayList<Transaction> testHistoryTxn = testHistory.getTxnsList();
        ArrayList<Operation> testHistoryOps = testHistory.getHistory();

        // Transaction 1 from History
        HashSet<Integer> testHistoryTxn1DataItems = testHistoryTxn.get(0).getDataItems();
        ArrayList<Operation> testHistoryTxn1TxnHistory = testHistoryTxn.get(0).getTxnHist();
        // Transaction 2 from History
        HashSet<Integer> testHistoryTxn2DataItems = testHistoryTxn.get(1).getDataItems();
        ArrayList<Operation> testHistoryTxn2TxnHistory = testHistoryTxn.get(1).getTxnHist();


        for(Integer dataItem: testHistoryTxn1DataItems) {
            assert dataItem.equals(testDataItem);
        }
        for(Integer dataItem: testHistoryTxn2DataItems) {
            assert dataItem.equals(testDataItem);
        }

        assert testHistoryTxn1TxnHistory.size() == testTxn1Ops.length;
        for(int i = 0; i < testHistoryTxn1TxnHistory.size(); i++) {
            assert testHistoryTxn1TxnHistory.get(i).toString().equals(testTxn1Ops[i]);
        }

        assert testHistoryTxn2TxnHistory.size() == testTxn2Ops.length;
        for(int i = 0; i < testHistoryTxn1TxnHistory.size(); i++) {
            assert testHistoryTxn2TxnHistory.get(i).toString().equals(testTxn2Ops[i]);
        }

        assert testHistoryOps.size() == testHistOps.length;
        for(int i = 0; i < testHistoryOps.size(); i++) {
            assert testHistoryOps.get(i).toString().equals(testHistOps[i]);
        }

    }

}