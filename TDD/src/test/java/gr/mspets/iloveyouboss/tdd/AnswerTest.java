package gr.mspets.iloveyouboss.tdd;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class AnswerTest {

    @Test
    public void matchAgainstNullAnswerReturnFalse() {
        assertFalse(new Answer(new BooleanQuestion(0,""), Bool.TRUE).match(null));
    }
}
