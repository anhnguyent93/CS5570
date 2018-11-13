package analyzers;

import components.History;
import creators.HistoryCreator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by DJ Yuhn on 11/7/2018
 */
public class RecAnalyzerTest {
    @Test
    public void testIfHistoryIsRecoverableWithThreeTransactionsAndAllCommitInOrder() {
        History testHistory = HistoryCreator.createCustomHistory("r1[1]w1[1]r2[1]w2[1]r3[1]w3[1]c1c2c3");
        System.out.println(RecAnalyzer.RecovChecking(testHistory));
        assert (RecAnalyzer.RecovChecking(testHistory).contains("History is Recoverable."));
    }

    @Test
    public void testIfHistoryIsRecoverableWithThreeTransactionsAndOneAbortAfterRead() {
        History testHistory = HistoryCreator.createCustomHistory("r1[1]w1[1]r2[1]w2[1]r3[1]a2w3[1]c1c3");
        System.out.println(RecAnalyzer.RecovChecking(testHistory));
        assert (RecAnalyzer.RecovChecking(testHistory).contains("History is not Recoverable."));
    }

    @Test
    public void testIfHistoryIsRecoverableWithThreeTransactionsWithAbortBeforeRead() {
        History testHistory = HistoryCreator.createCustomHistory("r1[1]w1[1]r2[1]w2[1]a2r3[1]w3[1]c1c3");
        System.out.println(RecAnalyzer.RecovChecking(testHistory));
        assert (RecAnalyzer.RecovChecking(testHistory).contains("History is Recoverable."));
    }

    @Test
    public void testIfHistoryIsRecoverableWithThreeTransactionsWithAbortBeforeCommit() {
        History testHistory = HistoryCreator.createCustomHistory("r1[1]w1[1]r2[1]w2[1]r3[1]w3[1]a1c2c3");
        System.out.println(RecAnalyzer.RecovChecking(testHistory));
        assert (RecAnalyzer.RecovChecking(testHistory).contains("History is not Recoverable."));
    }

    @Test
    public void testIfHistoryIsRecoverableWithThreeTransactionsWithCommitsOutOfOrder() {
        History testHistory = HistoryCreator.createCustomHistory("r1[1]w1[1]r2[1]w2[1]r3[1]w3[1]c1c3c2");
        System.out.println(RecAnalyzer.RecovChecking(testHistory));
        assert (RecAnalyzer.RecovChecking(testHistory).contains("History is not Recoverable."));
    }

}