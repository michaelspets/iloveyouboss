package gr.mspets.iloveyouboss.biggerdesignpicture;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProfileTest {
    private Profile profile;

    @Before
    public void createProfile() {
        profile = new Profile("");
    }

    int[] ids(Collection<Answer> answers) {
        return answers.stream()
                .mapToInt(a -> a.getQuestion().getId()).toArray();
    }

    @Test
    public void findsAnswersBasedOnPredicate() {
        profile.add(new Answer(new BooleanQuestion(1, "1"), Bool.FALSE));
        profile.add(new Answer(new PercentileQuestion(2, "2", new String[]{}), 0));
        profile.add(new Answer(new PercentileQuestion(3, "3", new String[]{}), 0));

        List<Answer> answers = profile.find(a -> a.getQuestion().getClass() == PercentileQuestion.class);

        assertThat(ids(answers), equalTo(new int[]{2, 3}));
    }
}