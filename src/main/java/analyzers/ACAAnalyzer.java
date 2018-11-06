package analyzers;

import components.History;
import components.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DJ Yuhn on 10/10/2018
 * Modified by Anh Nguyen on 10/23/2018
 */
public class ACAAnalyzer {

    public static String ACAChecking(History history) {
        Map<Integer, List<Integer>> writeSet = new HashMap<>();
        List<Integer> commitSet = new ArrayList<>();
        boolean flag = false;

        if (history.getTxnsList().size() == 1)
            return ("History is not ACA because it only has 1 transaction");

        for (Operation op : history.getHistory()) {
            if (op.getOperation() == 'w') {
                if (writeSet.containsKey(op.getDataItem())) {
                    List<Integer> listWrittenToDataItem = writeSet.get(op.getDataItem());
                    listWrittenToDataItem.add(op.getTxnID());
                } else {
                    List<Integer> listWrittenToDataItem = new ArrayList<>();
                    listWrittenToDataItem.add(op.getTxnID());
                    writeSet.put(op.getDataItem(), listWrittenToDataItem);
                }
            }

            if (op.getOperation() == 'r') {
                if (writeSet.containsKey(op.getDataItem())) {
                    List<Integer> listWrittenToDataItem = writeSet.get(op.getDataItem());
                    if (!listWrittenToDataItem.isEmpty()) {
                        int lastTxnWrittenToDataItem = listWrittenToDataItem.get(listWrittenToDataItem.size() - 1);
                        if (lastTxnWrittenToDataItem != op.getTxnID()) {
                            if (!commitSet.contains(lastTxnWrittenToDataItem)) {
                                return ("History is not ACA because T" + op.getTxnID() + " reads the data item "
                                        + op.getDataItem() + " from T" + lastTxnWrittenToDataItem
                                        + " before T" + lastTxnWrittenToDataItem + " commit");
                            } else {
                                flag = true;
                            }
                        }
                    }
                }
            }

            if (op.getOperation() == 'c') {
                commitSet.add(op.getTxnID());
            }

            if (op.getOperation() == 'a') {
                for (Map.Entry<Integer, List<Integer>> entry : writeSet.entrySet()) {
                    List<Integer> listWrittenToDataItem = entry.getValue();
                    if (!listWrittenToDataItem.isEmpty()) {
                        listWrittenToDataItem.remove(op.getTxnID());
                    }
                }
            }
        }

        if (flag)
            return ("History is ACA because every transaction reads those values that are written by a committed transaction.");
        else
            return ("History is ACA because no transaction reads a data item operated on by another transaction.");
    }
}
