package gr.mspets.iloveyouboss.tdd;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {
    private Profile profile;
    private Criteria criteria;
    private Question questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNotRelocation;
    private BooleanQuestion questionReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;

    @Before
    public void createProfile() {
        profile = new Profile();
    }

    @Before
    public void createCriteria() {
        criteria = new Criteria();
    }

    @Before
    public void createQuestionAndAnswer() {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNotRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);

        questionReimbursesTuition = new BooleanQuestion(1, "Reimburses tuition?");
        answerDoesNotReimburseTuition =
                new Answer(questionReimbursesTuition, Bool.FALSE);

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
    public void matchesWhenContainsMultipleAnswers() {
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimburseTuition);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
//
        boolean result = profile.matches(criterion);

        assertTrue(result);

    }

    @Test
    public void doesNotMatchWhenNonOfMultipleCriteriaMatch() {
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerThereIsNotRelocation, Weight.Important));
//
        boolean result = profile.matches(criteria);

        assertFalse(result);

    }

    @Test
    public void doesNotMatchWhenAnyMustMeetCriteriaNotMet() {
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.MustMatch));
        criteria.add(new Criterion(answerDoesNotReimburseTuition, Weight.Important));
//
        boolean result = profile.matches(criteria);

        assertFalse(result);

    }

    @Test
    public void matchWhenAnyOfMultipleCriteriaMatch() {
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerDoesNotReimburseTuition, Weight.Important));
//
        boolean result = profile.matches(criteria);

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
