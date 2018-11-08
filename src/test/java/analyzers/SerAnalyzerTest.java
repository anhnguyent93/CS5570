package analyzers;

import components.History;
import creators.HistoryCreator;
import org.junit.Test;

/**
 * Created by DJ Yuhn on 11/7/2018
 */
public class SerAnalyzerTest {
    @Test
    public void testIfHistoryisSerializable() {
        History testHistory = HistoryCreator.createCustomHistory("r1[1]w1[1]r2[1]w2[1]c1c2");
        System.out.println(SerAnalyzer.SerialChecking(testHistory));
        assert SerAnalyzer.SerialChecking(testHistory).equals("This history is serializable as its precendence graph is acyclic, therefore all conflicting " +
                "operations of Ti comes before all conflicting operations of Tj where Ti -> Tj");
    }

    @Test
    public void testIfHistoryisNotSerializable() {
        History testHistory = HistoryCreator.createCustomHistory("r1[1]w1[1]r2[1]w2[1]w2[2]r1[2]w2[3]r3[3]w3[4]r1[4]c1c2c3");
        System.out.println(SerAnalyzer.SerialChecking(testHistory));
        assert SerAnalyzer.SerialChecking(testHistory).equals("This history is not serializable as the precedence graph is cyclic, therefore all conflicting " +
                "operations of Ti do not come before all conflicting operations of Tj, where Ti -> Tj -> Ti");
    }
}