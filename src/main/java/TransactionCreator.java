import utils.RandomNumberGenerator;

import java.util.*;

/**
 * Created by DJ Yuhn on 10/3/2018
 */
public class TransactionCreator {
    /**
     * Provide the number of transactions and the max possible transaction IDs
     * Return array of transaction IDs
     */
    public static Integer[] transactionIDGenerator(int numTransactions, int maxPossibleTransactionNum) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=1; i<= maxPossibleTransactionNum; i++) {
            arrayList.add(i);
        }
        Collections.shuffle(arrayList);

        List<Integer> list = arrayList.subList(0, numTransactions);

        Integer[] transactionIDs = list.toArray(new Integer[0]);

        return transactionIDs;

    }

    /**
     * Provide the number of max data items per transaction
     * Randomly assign number of data items to the transaction and the data items
     * Return Map of transactionIDs as key and their value as array of data items
     */
    public static Transaction createTransactionWithDataItems(
            Integer txnID, int numDataItemsForTransaction, int maxPossibleDataItemIDs) {

        if (numDataItemsForTransaction > maxPossibleDataItemIDs) {
            throw new IllegalArgumentException("Number of Data Items for Transaction must be equal to " +
                    "or lower than the maximum possible number of data item IDs");
        }

        // Possible data item ids
        ArrayList<Integer> dataItemIDs = new ArrayList<>();
        for (int i=1; i<= maxPossibleDataItemIDs; i++) {
            dataItemIDs.add(i);
        }
        //Randomize the possible data item ids for transaction
        Collections.shuffle(dataItemIDs);

        List<Integer> dataItemsForTrxn = dataItemIDs.subList(0, numDataItemsForTransaction);

        Integer[] txnDataItems = dataItemsForTrxn.toArray(new Integer[numDataItemsForTransaction]);

        return (new Transaction(txnID, txnDataItems));
    }

}
