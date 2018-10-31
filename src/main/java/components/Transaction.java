package components;

import creators.OperationsCreator;

import java.util.*;

/**
 * Created by DJ Yuhn on 10/5/2018
 */
public class Transaction {
    private Integer txnID;
    private HashSet<Integer> dataItems;
    private ArrayList<Operation> txnHist = new ArrayList<>();

    public Transaction(Integer txnID, HashSet<Integer> dataItems) {
        this.txnID = txnID;
        this.dataItems = dataItems;
    }

    public Transaction(Integer txnID, HashSet<Integer> dataItems, ArrayList<Operation> txnHist) {
        if(verifyAcceptableHist(dataItems, txnHist)) {
            this.txnID = txnID;
            this.dataItems = dataItems;
            this.txnHist = txnHist;
        }
    }

    public void createNewRandomHistory() {
        this.txnHist.clear();
        List<Integer> randomDataItems = new ArrayList<>(this.dataItems);
        for (Integer dataItem : randomDataItems) {
            Operation[] dataItemOps = OperationsCreator.createRandOpsOnDataItem(txnID, dataItem, 2);
            this.txnHist.addAll(Arrays.asList(dataItemOps));
        }
        Collections.shuffle(txnHist);
        this.txnHist.add(OperationsCreator.createAbortOrCommit(txnID));
    }

    public ArrayList<Operation> getTxnHist() {
        return txnHist;
    }

    public void setTxnHist(ArrayList<Operation> newHist) {
        this.txnHist = newHist;
    }

    // Getters and Setters

    public Integer getTxnID() {
        return txnID;
    }

    public void setTxnID(Integer txnID) {
        this.txnID = txnID;
    }

    public HashSet<Integer> getDataItems() {
        return dataItems;
    }

    public void setDataItems(HashSet<Integer> dataItems) {
        this.dataItems = dataItems;
    }

    private boolean verifyAcceptableHist(HashSet<Integer> dataItems, ArrayList<Operation> txnHist) {
        boolean hasAbortOrCommit = false;
        for (Operation op:txnHist) {
            if (!dataItems.contains(op.getDataItem()))
                throw new IllegalArgumentException("An operation in the list contains data item " + op.getDataItem() + " not found in the Hash Set");
            else if (op.getOperation() == 'c' || op.getOperation() == 'a') {
                if (hasAbortOrCommit)
                    throw new IllegalArgumentException("There are multiple aborts or commits in the transaction history");
                else
                    hasAbortOrCommit = true;
            }
        }

        // If the transaction has an abort or commit and passed the above, return true;
        return hasAbortOrCommit;
    }

}
