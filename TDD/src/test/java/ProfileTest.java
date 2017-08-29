import gr.mspets.iloveyouboss.tdd.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {
    private Profile profile;
    private Question questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNotRelocation;

    @Before
    public void createProfile() {
        profile = new Profile();
    }

    @Before
    public void createQuestionAndAnswer() {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNotRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);

    }

    @Test
    public void matchesNothingWhenProfileEmpty() {

        Criterion criterion = new Criterion(new Answer(questionIsThereRelocation, Bool.TRUE), Weight.DontCare);
//
        boolean result = profile.matches(criterion);

        assertFalse(result);

    }

    @Test
    public void matchesWhenProfileContainsMatchingAnswer() {
        profile.add(answerThereIsRelocation);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
//
        boolean result = profile.matches(criterion);

        assertTrue(result);

    }

    @Test
    public void doesNotmatchWhenNoMatchingAnswer() {
        profile.add(answerThereIsNotRelocation);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
//
        boolean result = profile.matches(criterion);

        assertFalse(result);

    }
}
