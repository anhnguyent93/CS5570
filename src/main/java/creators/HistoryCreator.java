package creators;

import components.History;
import components.Operation;
import components.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DJ Yuhn on 10/9/2018
 */
public class HistoryCreator {
    public static History createRandomHistory(int txnCount, int txnDataItemCount, int maxTxnIDs, int maxDataItemIDs) {
        if (txnCount > maxTxnIDs)
            throw new IllegalArgumentException("The number of transactions must be equal to or lower than" +
                    "the maximum possible transaction IDs");
        if (txnDataItemCount > maxDataItemIDs)
            throw new IllegalArgumentException("The number of data items a transaction may have must be equal to " +
                    "or lower than the maximum possible data item IDs");

        Integer[] txnIDs = TransactionCreator.transactionIDGenerator(txnCount, maxTxnIDs);
        ArrayList<Transaction> txns = new ArrayList<>();

        for (Integer txnID:txnIDs) {
            Transaction txn = TransactionCreator.createTransactionWithDataItems(txnID, txnDataItemCount, maxDataItemIDs);
            txns.add(txn);
        }

        History history = new History(txns);
        history.createRandomHistory();

        return history;
    }

    public static History createCustomHistory(String userHistory) {
        HashMap<Integer, ArrayList<Operation>> txnsMap = new HashMap<>();
        ArrayList<Operation> opsList = new ArrayList<>();

        String regex = "(?<RWOperationType>(r|w))(?<TxnID>\\d+)(\\[|\\(|\\{)(?<DataItem>\\d+)(\\]|\\)|\\})" +
                "|(?<CAOperationType>(c|a))(?<TxnID1>\\d+)";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(userHistory);

        while (matcher.find()) {
            //String group = matcher.group();
            //System.out.println(group);
            if (matcher.group().contains("r") || matcher.group().contains("w")) {
                char op = matcher.group("RWOperationType").charAt(0);
                Integer txnID = Integer.valueOf(matcher.group("TxnID"));
                Integer dataItem = Integer.valueOf(matcher.group("DataItem"));

                if (txnsMap.containsKey(txnID)) {
                    txnsMap.get(txnID).add(new Operation(txnID, op, dataItem));
                }
                else {
                    txnsMap.put(txnID, new ArrayList<>());
                    txnsMap.get(txnID).add(new Operation(txnID, op, dataItem));
                }
                opsList.add(new Operation(txnID, op, dataItem));
            } else {
                char op = matcher.group("CAOperationType").charAt(0);
                Integer txnID = Integer.valueOf(matcher.group("TxnID1"));

                if (txnsMap.containsKey(txnID)) {
                    txnsMap.get(txnID).add(new Operation(txnID, op));
                }
                else {
                    txnsMap.put(txnID, new ArrayList<>());
                    txnsMap.get(txnID).add(new Operation(txnID, op));
                }
                opsList.add(new Operation(txnID, op));
            }
        }

        ArrayList<Transaction> txnsList = new ArrayList<>();
        if (txnsMap.isEmpty())
            throw new IllegalArgumentException("Could not find valid input from your history.");

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
