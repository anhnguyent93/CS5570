package components;

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
        else
            throw new IllegalArgumentException("This operation must be an 'a' or a 'c'.");
        this.dataItem = null;
    }

    public Operation(Integer txnID, char op, Integer dataItem) {
        this.txnID = txnID;
        if (op == 'r' || op == 'w')
            this.operation = op;
        else
            throw new IllegalArgumentException("This operation must be a 'r' or a 'w'.");

        this.dataItem = dataItem;
    }

    @Override
    public String toString() {
        if (dataItem != null)
            return (Character.toString(operation) + txnID.toString() + "[" + dataItem.toString() + "]");
        else
            return (Character.toString(operation) + txnID.toString());
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

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        if (validAbortCommit(operation)) {
            this.operation = operation;
            this.dataItem = null;
        }
    }

    public void setOperation(char operation, Integer dataItem) {
        if (validReadWrite(operation)) {
            this.operation = operation;
            this.dataItem = dataItem;
        }
    }

    private static Boolean validReadWrite(char op) {
        char[] opList = {'r', 'w'};
        for (char opL:opList) {
            if (opL == op)
                return true;
        }
        return false;
    }

    private static Boolean validAbortCommit(char op) {
        char[] opList = {'a', 'c'};
        for (char opL:opList) {
            if (opL == op)
                return true;
        }
        return false;
    }
}
