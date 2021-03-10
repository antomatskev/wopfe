package eu.judegam.wopfe.models.tests;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Represents Answers.
 */
@Entity
public class Answer {
    private String answerForOpenQuestion;
    private Boolean answerForTest;

    // Answers for tests
    public Answer(Boolean answerForTest, Long id) {
        this.answerForTest = answerForTest;
        this.id = id;
    }

    // Answers for open questions
    public Answer(String answerForOpenQuestion, Long id) {
        this.answerForOpenQuestion = answerForOpenQuestion;
        this.id = id;
    }

    public Answer() {

    }

    public String getAnswerForOpenQuestion() {
        return answerForOpenQuestion;
    }

    public void setAnswerForOpenQuestion(String answerForOpenQuestion) {
        this.answerForOpenQuestion = answerForOpenQuestion;
    }

    public Boolean getAnswerForTest() {
        return answerForTest;
    }

    public void setAnswerForTest(Boolean answerForTest) {
        this.answerForTest = answerForTest;
    }

    private Long id;


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

}
