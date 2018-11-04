package analyzers;

import components.History;
import creators.HistoryCreator;
import org.junit.Test;

import static org.junit.Assert.*;

public class ACAAnalyzerTest {

    @Test
    public void ACACheckingTest1() {
        History history = HistoryCreator.createCustomHistory("r1[1],w1[1],w1[2],w2[1],r2[1],c1[],r2[2],w2[2],c2[]");
        System.out.println(ACAAnalyzer.ACAChecking(history));
        assert ACAAnalyzer.ACAChecking(history).equals("History is ACA because all transaction reads those values that are written by a committed transaction");
    }
}