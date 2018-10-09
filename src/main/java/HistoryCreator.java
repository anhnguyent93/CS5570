import java.util.ArrayList;

/**
 * Created by DJ Yuhn on 10/9/2018
 */
public class HistoryCreator {
    public static History createRandomHistory(int txnCount, int txnDataItemCount, int maxTxnIDs, int maxDataItemIDs) {
        if (txnCount < maxTxnIDs)
            throw new IllegalArgumentException("The number of transactions must be equal to or lower than" +
                    "the maximum possible transaction IDs");
        if (txnDataItemCount < maxDataItemIDs)
            throw new IllegalArgumentException("The number of data items a transaction may have must be equal to " +
                    "or lower than the maximum possible data item IDs");

        Integer[] txnIDs = TransactionCreator.transactionIDGenerator(txnCount, maxTxnIDs);
        ArrayList<Transaction> txns = new ArrayList<>();

        for (Integer txnID:txnIDs) {
            Transaction txn = TransactionCreator.createTransactionWithDataItems(txnID, txnDataItemCount, maxDataItemIDs);
            txn.createNewRandomHistory();
            txns.add(txn);
        }

        History history = new History(txns);
        history.createRandomHistory();

        return history;
    }
}
