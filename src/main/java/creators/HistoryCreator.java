package creators;

import components.History;
import components.Operation;
import components.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static History createCustomHistory(String userHistory) {
        HashMap<Integer, ArrayList<Operation>> txnsMap = new HashMap<>();
        ArrayList<Operation> opsList = new ArrayList<>();

        // Acceptable operation examples: r1[1], w23[2], c1[], a2[]
        String[] separatedOperations = userHistory.split(",");
        for (String operation:separatedOperations) {
            try {
                String txnID = "";
                String dataItem = "";
                char op = operation.charAt(0);
                int firstOpenBracket = operation.indexOf("[");
                int firstClosedBracket = operation.indexOf("]");
                if (firstOpenBracket != -1 && firstClosedBracket != -1) {
                    txnID = operation.substring(1, firstOpenBracket);
                    dataItem = operation.substring(firstOpenBracket + 1, firstClosedBracket);
                } else {
                    throw new IllegalArgumentException("The string does not contain any brackets to indicate the data item.");
                }

                if (!dataItem.equals("")) {
                    if (validOperation(op) && validTxnID(txnID) && validDataItem(dataItem)) {
                        if (txnsMap.containsKey(Integer.valueOf(txnID))) {
                            txnsMap.get(Integer.valueOf(txnID)).add(new Operation(Integer.valueOf(txnID), op, Integer.valueOf(dataItem)));
                        }
                        else {
                            txnsMap.put(Integer.valueOf(txnID), new ArrayList<>());
                            txnsMap.get(Integer.valueOf(txnID)).add(new Operation(Integer.valueOf(txnID), op, Integer.valueOf(dataItem)));
                        }
                        opsList.add(new Operation(Integer.valueOf(txnID), op, Integer.valueOf(dataItem)));
                    }
                } else {
                    if (validOperation(op) && validTxnID(txnID)) {
                        if (txnsMap.containsKey(Integer.valueOf(txnID))) {
                            txnsMap.get(Integer.valueOf(txnID)).add(new Operation(Integer.valueOf(txnID), op));
                        }
                        else {
                            txnsMap.put(Integer.valueOf(txnID), new ArrayList<>());
                            txnsMap.get(Integer.valueOf(txnID)).add(new Operation(Integer.valueOf(txnID), op));
                        }
                        opsList.add(new Operation(Integer.valueOf(txnID), op));
                    }
                }
            }
            catch (IndexOutOfBoundsException ex) {
                System.out.println("The history entered was not in an acceptable format. Be sure to include proper placement of brackets.");
            }
        }

        ArrayList<Transaction> txnsList = new ArrayList<>();
        for (HashMap.Entry<Integer, ArrayList<Operation>> entry : txnsMap.entrySet()) {
            Transaction txn = new Transaction(entry.getKey(), entry.getValue());
            txnsList.add(txn);
        }

        return (new History(txnsList, opsList));
    }

    private static Boolean validOperation(char op) {
        char[] opList = {'r', 'w', 'a', 'c'};
        for (char opL:opList) {
            if (opL == op)
                return true;
        }
        return false;
    }

    private static Boolean validTxnID(String txnID) {
        try{
            int validTxnID = Integer.parseInt(txnID);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("Invalid format for txnID entered as: " + txnID + ". Must enter an integer value");
        }
        return false;
    }

    private static Boolean validDataItem(String dataItem) {
        try{
            int validDataItem = Integer.parseInt(dataItem);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("Invalid format for data item entered as: " + dataItem + ". Must enter an integer value");
        }
        return false;
    }

}
