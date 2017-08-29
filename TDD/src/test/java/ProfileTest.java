import gr.mspets.iloveyouboss.tdd.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {
    private Profile profile;
    private Question question;
    private Answer answer;

    @Before
    public void createProfile() {
        profile = new Profile();
    }

    @Before
    public void createQuestionAndAnswer() {
        question = new BooleanQuestion(1, "Relocation package?");
        answer = new Answer(question, Bool.TRUE);

    }

    @Test
    public void matchesNothingWhenProfileEmpty() {

        Criterion criterion = new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare);
//
        boolean result = profile.matches(criterion);

        assertFalse(result);

    }

    @Test
    public void matchesWhenProfileContainsMatchingAnswer() {
        profile.add(answer);
        Criterion criterion = new Criterion(answer, Weight.Important);
//
        boolean result = profile.matches(criterion);

        assertTrue(result);

    }
}
