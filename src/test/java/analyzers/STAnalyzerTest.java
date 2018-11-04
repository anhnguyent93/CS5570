package analyzers;

import components.History;
import creators.HistoryCreator;
import org.junit.Test;

import static org.junit.Assert.*;

public class STAnalyzerTest {

    @Test
    public void STCheckingTest1() {
        History history = HistoryCreator.createCustomHistory("r1[1],w1[1],w1[2],w2[1],r2[1],c1[],r2[2],w2[2],c2[]");
        System.out.println(STAnalyzer.STChecking(history));
        assert STAnalyzer.STChecking(history).equals("History is not ST because T2 writes to the data item 1 before T1 commit or abort");
    }
}