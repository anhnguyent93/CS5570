import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by DJ Yuhn on 10/5/2018
 */
public class Transaction {
    private Integer txnID;
    private Integer[] dataItems = null;
    private ArrayList<Operation> txnHist;

    public void createRandomHistory() {
        List<Integer> randomDataItems = Arrays.asList(dataItems);
        Collections.shuffle(randomDataItems);
        for (Integer dataItem : randomDataItems) {

        }
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
