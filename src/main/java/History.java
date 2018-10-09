import java.util.*;

/**
 * Created by DJ Yuhn on 10/8/2018
 */
public class History {
    private ArrayList<Transaction> txnsList;
    private ArrayList<Operation> history = new ArrayList<>();

    public History(ArrayList<Transaction> txnsList) {
        this.txnsList = txnsList;
    }

    public void createRandomHistory() {
        if (!this.history.isEmpty())
            this.history.clear();

        Map<Integer, Integer> txnIndex = new HashMap<>();
        List<Integer> rndSelection = new ArrayList<>();
        Integer count = 0;
        for (Transaction txn: txnsList) {
            txnIndex.put(txn.getTxnID(), 0);
            rndSelection.add(count);
            count++;
        }

        while (!rndSelection.isEmpty()) {
            Collections.shuffle(rndSelection);
            Integer selectedIndex = rndSelection.get(0);
            Integer selectedTxnID = this.txnsList.get(selectedIndex).getTxnID();
            Integer txnOpIndex = txnIndex.get(selectedTxnID);
            Operation op = this.txnsList.get(selectedIndex).getTxnHist().get(txnOpIndex);
            txnIndex.put(selectedTxnID, txnOpIndex + 1);

            if(txnIndex.get(selectedTxnID) >= txnsList.get(selectedIndex).getTxnHist().size())
                rndSelection.remove(0);

            this.history.add(op);
        }
    }

    public ArrayList<Operation> getHistory() { return history; }

    public ArrayList<Transaction> getTxnsList() { return txnsList; }

}
