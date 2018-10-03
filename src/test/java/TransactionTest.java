import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by DJ Yuhn on 10/3/2018
 */
public class TransactionTest {
    @Test
    public void testTransactionIDGeneratorForRetrievingFourUniqueIDsFromAPossibleMaxOf100() {
        Transaction transaction = new Transaction();
        int numOfTransactions = 4;
        int maxNumOfPossibleTransactions = 100;

        for(int i=0; i<1000; i++) {
            Integer[] transactionIDs = transaction.transactionIDGenerator(numOfTransactions, maxNumOfPossibleTransactions);

            Set<Integer> mySet = new HashSet<>(Arrays.asList(transactionIDs));
            assert(mySet.size() == numOfTransactions); // The number of unique transactions is equal to the request
            for (int transactionID : transactionIDs) {
                assert (transactionID >= 1 && transactionID <= 100); // The transactionIDs fall between 1-100 inclusive
            }
        }
    }

}