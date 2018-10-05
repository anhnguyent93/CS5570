/**
 * Created by DJ Yuhn on 10/5/2018
 */
public class Operation {
    private Integer txnID;
    private Integer dataItem;
    private char operation;

    public Operation(Integer txnID, char op) {
        this.txnID = txnID;
        if (op == 'c' || op == 'a')
            this.operation = op;
        this.dataItem = null;
    }

    public Operation(Integer txnID, char op, Integer dataItem) {
        this.txnID = txnID;
        if (op == 'r' || op == 'w')
            this.operation = op;
        this.dataItem = dataItem;
    }

    @Override
    public String toString() {
        return (Character.toString(operation) + txnID.toString() + "[" + dataItem.toString() + "]");
    }

    public Integer getTxnID() {
        return txnID;
    }

    public void setTxnID(Integer txnID) {
        this.txnID = txnID;
    }

    public Integer getDataItem() {
        return dataItem;
    }

    public void setDataItem(Integer dataItem) {
        this.dataItem = dataItem;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }
}
