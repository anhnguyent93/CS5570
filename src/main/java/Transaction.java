import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by DJ Yuhn on 10/3/2018
 */
public class Transaction {

    /**
     * Provide the number of transactions and the max possible transaction IDs
     */
    public Integer[] transactionIDGenerator(int numTransactions, int maxPossibleTransactionNum) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=1; i<= maxPossibleTransactionNum; i++) {
            arrayList.add(i);
        }
        Collections.shuffle(arrayList);

        List<Integer> list = arrayList.subList(0, numTransactions);

        Integer[] transactionIDs = list.toArray(new Integer[0]);

        return transactionIDs;

    }
}
