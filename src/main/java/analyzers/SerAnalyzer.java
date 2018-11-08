package analyzers;

import components.History;
import components.Operation;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DJ Yuhn on 11/1/2018
 */
public class SerAnalyzer {

    public static String SerialChecking(History history) {
        DirectedGraph<Integer, DefaultEdge> serialGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        if (history.getTxnsList().size() == 1)
            return ("History is serializable as it only contains one transaction.");

        for (Operation op: history.getHistory()) {
            serialGraph.addVertex(op.getTxnID());
        }

        for (int i = 0; i < history.getHistory().size(); i++) {
            Operation runningOp = history.getHistory().get(i);

            if (runningOp.getOperation() == 'r') {
                for (int j = i + 1; j < history.getHistory().size(); j++) {
                    Operation checkOp = history.getHistory().get(j);
                    if (checkOp.getOperation() == 'w' && !runningOp.getTxnID().equals(checkOp.getTxnID()) && checkOp.getDataItem().equals(runningOp.getDataItem())) {
                        serialGraph.addEdge(runningOp.getTxnID(), checkOp.getTxnID());
                    }
                    else if ((checkOp.getOperation() == 'c' || checkOp.getOperation() == 'a') && checkOp.getTxnID().equals(runningOp.getTxnID()))
                        break;
                }
            }
            else if (runningOp.getOperation() == 'w') {
                for (int j = i + 1; j < history.getHistory().size(); j++) {
                    Operation checkOp = history.getHistory().get(j);
                    if (runningOp.getDataItem().equals(checkOp.getDataItem())) {
                        serialGraph.addEdge(runningOp.getTxnID(), checkOp.getTxnID());
                    }
                    else if ((checkOp.getOperation() == 'c' || checkOp.getOperation() == 'a') && checkOp.getTxnID().equals(runningOp.getTxnID()))
                        break;
                }
            }
        }

        CycleDetector detectCycle = new CycleDetector(serialGraph);
        System.out.println(detectCycle.findCycles());

        if (detectCycle.detectCycles()) {
            return "This history is not serializable as the precedence graph is cyclic, therefore all conflicting " +
                    "operations of Ti do not come before all conflicting operations of Tj, where Ti -> Tj -> Ti";
        }

        return "This history is serializable as its precendence graph is acyclic, therefore all conflicting " +
                "operations of Ti comes before all conflicting operations of Tj where Ti -> Tj";

    }
}
