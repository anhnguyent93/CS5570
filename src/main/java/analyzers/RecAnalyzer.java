package analyzers;

import components.History;
import components.Operation;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DJ Yuhn on 11/1/2018
 */
public class RecAnalyzer {

    public static String RecovChecking(History history) {
        List<Operation> oplist = history.getHistory();
        List<Integer> abortList = new ArrayList<>();
        List<Integer> commitList = new ArrayList<>();

        for(Operation op: oplist) {
            if (op.getOperation() == 'c') {
                commitList.add(op.getTxnID());
            }
            else if (op.getOperation() == 'a') {
                abortList.add(op.getTxnID());
            }
        }

        for (int i = 0; i < history.getHistory().size(); i++) {
            Operation runningOp = history.getHistory().get(i);
            if (runningOp.getOperation() == 'w') {
                if (abortList.contains(runningOp.getTxnID())) {
                    for (int j = i + 1; j < history.getHistory().size(); j++) {
                        Operation checkOp = history.getHistory().get(j);
                        if ((checkOp.getOperation() == 'a') && checkOp.getTxnID().equals(runningOp.getTxnID()))
                            break;
                        else if (checkOp.getOperation() == 'r' && !runningOp.getTxnID().equals(checkOp.getTxnID())
                                && checkOp.getDataItem().equals(runningOp.getDataItem())) {
                            return "History is not Recoverable. T" + checkOp.getTxnID() + " reads data item [" +
                                    checkOp.getDataItem() + "] before T" + runningOp.getTxnID() + " aborted.";
                        }
                    }
                }
                else {
                    for (int j = i + 1; j < history.getHistory().size(); j++) {
                        Operation checkOp = history.getHistory().get(j);
                        if ((checkOp.getOperation() == 'a' || checkOp.getOperation() == 'c') && checkOp.getTxnID().equals(runningOp.getTxnID()))
                            break;
                        else if (checkOp.getOperation() == 'r' && !runningOp.getTxnID().equals(checkOp.getTxnID()) &&
                                !abortList.contains(checkOp.getTxnID()) && checkOp.getDataItem().equals(runningOp.getDataItem())) {
                            for(int k = 0; k < commitList.size(); k++) {
                                if (!(commitList.indexOf(runningOp.getTxnID()) < commitList.indexOf(checkOp.getTxnID()))) {
                                    return "History is not Recoverable. T" + checkOp.getTxnID() + " commits before T" +
                                            runningOp.getTxnID() + " commits.";
                                }
                            }
                        }
                    }
                }
            }
        }

        return "History is Recoverable.";
    }
}
