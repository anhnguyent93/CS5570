package components;

import creators.OperationsCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by DJ Yuhn on 10/5/2018
 */
public class Transaction {
    private Integer txnID;
    private Integer[] dataItems;
    private ArrayList<Operation> txnHist = new ArrayList<>();

    public Transaction(Integer txnID, Integer[] dataItems) {
        this.txnID = txnID;
        this.dataItems = dataItems;
    }

    public void createNewRandomHistory() {
        this.txnHist.clear();
        List<Integer> randomDataItems = Arrays.asList(dataItems);
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

    public Integer[] getDataItems() {
        return dataItems;
    }

    public void setDataItems(Integer[] dataItems) {
        this.dataItems = dataItems;
    }

}
