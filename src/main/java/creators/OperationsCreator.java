package creators;

import components.Operation;
import org.jetbrains.annotations.NotNull;
import utils.RandomNumberGenerator;

/**
 * Created by DJ Yuhn on 10/5/2018
 */
public class OperationsCreator {

    /**
     * Creates operations on the Data Item for the transaction
     * @param numOfOps a value that must be 1 or 2
     * @throws IllegalArgumentException if numOps <= 0 or numOps > 2
     */
    public static Operation[] createRandOpsOnDataItem(Integer txnID, Integer dataItem, int numOfOps) {
        if (numOfOps <= 0 || numOfOps > 2) {
            throw new IllegalArgumentException("number of operations on a single data item must be 1 or 2");
        }

        // Can perform a max of two operations on a single data item
        Operation[] dataItemOps = new Operation[numOfOps];

        if (numOfOps == 1) {
            char op = randomReadOrWrite();
            dataItemOps[0] = new Operation(txnID, op, dataItem);
        }
        else if (numOfOps == 2) {
            char op = randomReadOrWrite();
            if (op == 'r') {
                dataItemOps[0] = new Operation(txnID, op, dataItem);
                dataItemOps[1] = new Operation(txnID, 'w', dataItem);
            }
            else if (op == 'w') {
                dataItemOps[0] = new Operation(txnID, op, dataItem);
                dataItemOps[1] = new Operation(txnID, 'r', dataItem);
            }
        }

        return dataItemOps;
    }

    @NotNull
    public static Operation createAbortOrCommit(Integer txnID) {
        char op = randomAbortOrCommit();
        return new Operation(txnID, op);
    }

    private static char randomReadOrWrite() {
        // 0 = read (r), 1 = write (w)
        if (RandomNumberGenerator.randomNumberInRangeInclusive(0, 1) == 0)
            return 'r';
        else
            return 'w';
    }

    private static char randomAbortOrCommit() {
        // 0 = abort (a), 1 = commit (c)
        if (RandomNumberGenerator.randomNumberInRangeInclusive(0, 1) == 0)
            return 'a';
        else
            return 'c';
    }
}
