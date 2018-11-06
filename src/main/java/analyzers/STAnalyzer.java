package analyzers;

import components.History;
import components.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class STAnalyzer {

    public static String STChecking(History history) {
        Map<Integer, List<Integer>> writeSet = new HashMap<>();
        List<Integer> commitAndAbortSet = new ArrayList<>();
        boolean flag = false;

        if (history.getTxnsList().size() == 1)
            return ("History is not ST because it only has 1 transaction");

        for (Operation op : history.getHistory()) {
            if (op.getOperation() == 'w') {
                if (writeSet.containsKey(op.getDataItem())) {
                    List<Integer> listWrittenToDataItem = writeSet.get(op.getDataItem());
                    if (!listWrittenToDataItem.isEmpty()) {
                        int lastTxnWrittenToDataItem = listWrittenToDataItem.get(listWrittenToDataItem.size() - 1);
                        if (lastTxnWrittenToDataItem != op.getTxnID()) {
                            if (!commitAndAbortSet.contains(lastTxnWrittenToDataItem)) {
                                return ("History is not ST because T" + op.getTxnID() + " writes to the data item "
                                        + op.getDataItem() + " before T" + lastTxnWrittenToDataItem + " commit or abort");
                            } else {
                                flag = true;
                            }
                        }
                    }
                    listWrittenToDataItem.add(op.getTxnID());
                } else {
                    List<Integer> listAccessedDataItem = new ArrayList<>();
                    listAccessedDataItem.add(op.getTxnID());
                    writeSet.put(op.getDataItem(), listAccessedDataItem);
                }
            }

            if (op.getOperation() == 'r') {
                if (writeSet.containsKey(op.getDataItem())) {
                    List<Integer> listWrittenToDataItem = writeSet.get(op.getDataItem());
                    if (!listWrittenToDataItem.isEmpty()) {
                        int lastTxnWrittenToDataItem = listWrittenToDataItem.get(listWrittenToDataItem.size() - 1);
                        if (lastTxnWrittenToDataItem != op.getTxnID()) {
                            if (!commitAndAbortSet.contains(lastTxnWrittenToDataItem)) {
                                return ("History is not ST because T" + op.getTxnID() + " reads the data item "
                                        + op.getDataItem() + " before T" + lastTxnWrittenToDataItem + " commit or abort");
                            } else {
                                flag = true;
                            }
                        }
                    }
                }
            }

            if (op.getOperation() == 'c' || op.getOperation() == 'a') {
                commitAndAbortSet.add(op.getTxnID());
            }
        }

        if (flag)
            return ("History is ST because all transactions neither read or write a data item X " +
                    "until the last transaction that wrote X has committed or aborted");
        else
            return ("History is ST because no transaction reads or writes a data item operated on by another transaction.");
    }
}
