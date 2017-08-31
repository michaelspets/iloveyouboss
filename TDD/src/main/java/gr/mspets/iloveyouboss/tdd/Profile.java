package gr.mspets.iloveyouboss.tdd;

import java.util.HashMap;
import java.util.Map;

public class Profile {
    private Map<String, Answer> answers = new HashMap<>();

    private Answer getMatchingProfileAnswer(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public boolean matches(Criterion criterion) {
        Answer answer = getMatchingProfileAnswer(criterion);
        return criterion.getAnswer().match(answer);
    }

    public boolean matches(Criteria criteria) {
        boolean matches = false;
        for (Criterion c : criteria) {
            if (matches(c)) {
                matches = true;
            } else if (c.getWeight().equals(Weight.MustMatch)) {
                return false;
            }
        }
        return matches;
    }

    public void add(Answer answer) {
        this.answers.put(answer.getQuestionText(), answer);
    }
}
