import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by DJ Yuhn on 10/3/2018
 */
public class TransactionCreatorTest {
    @Test
    public void testTransactionIDGeneratorForRetrievingFourUniqueIDsFromAPossibleMaxOf100() {
        int numOfTransactions = 4;
        int maxNumOfPossibleTransactions = 100;

        for(int i=0; i<1000; i++) {
            Integer[] transactionIDs = TransactionCreator.transactionIDGenerator(numOfTransactions, maxNumOfPossibleTransactions);

            Set<Integer> mySet = new HashSet<>(Arrays.asList(transactionIDs));
            assert(mySet.size() == numOfTransactions); // The number of unique transactions is equal to the request
            for (int transactionID : transactionIDs) {
                assert (transactionID >= 1 && transactionID <= 100); // The transactionIDs fall between 1-100 inclusive
            }
        }
    }

    @Test
    public void testCreateTransactionDataItemsWithMaxDataItemPerTransactionOf4And1000PossibleDataItems() {
        int maxDataItemsForTransaction = 4;
        int maxPossibleDataItems = 1000;
        Integer[] transactionIDs = TransactionCreator.transactionIDGenerator(4, 100);
        Map<Integer, Integer[]> transactionsDataItems = TransactionCreator.createTransactionsAndDataItems(
                transactionIDs, maxDataItemsForTransaction, maxPossibleDataItems);

        for(Integer transactionID: transactionsDataItems.keySet()) {
            Integer[] dataItems = transactionsDataItems.get(transactionID);
            assert (dataItems.length >= 1 && dataItems.length <= maxDataItemsForTransaction);
        }
    }

}