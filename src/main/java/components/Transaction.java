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
        createNewRandomHistory();
    }

    public Transaction(Integer txnID, ArrayList<Operation> txnHist) {
        HashSet<Integer> dataItems = new HashSet<>();
        if(verifyAcceptableHist(txnID, txnHist, dataItems)) {
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

    private boolean verifyAcceptableHist(Integer txnID, ArrayList<Operation> txnHist, HashSet<Integer> dataItems) {
        boolean hasAbortOrCommit = false;
        for (Operation op:txnHist) {
            if (!hasAbortOrCommit) {
                if (op.getOperation() == 'c' || op.getOperation() == 'a') {
                    hasAbortOrCommit = true;
                }
                if (op.getDataItem() != null)
                    dataItems.add(op.getDataItem());
            }
            else if (hasAbortOrCommit)
                throw new IllegalArgumentException("Transaction with ID = " + txnID + " has operations occuring after a commit or abort.");

        }
        if (!hasAbortOrCommit)
            throw new IllegalArgumentException("Transaction does not have a commit or abort.");

        // If the transaction has an abort or commit and passed the above, return true;
        return hasAbortOrCommit;
    }

}
