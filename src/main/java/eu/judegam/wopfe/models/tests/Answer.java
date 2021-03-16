package eu.judegam.wopfe.models.tests;

import ch.qos.logback.core.status.StatusListenerAsList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

/**
 * Represents Answers.
 */
@Entity
public class Answer {
    // answers for question, where there isn't any solid answer,
    // we may have a text field for writing bigger amount of text
    private String openEndedAnswers;
    // answers for questions where you can choose "yes" or "no" (or any other choices)
    private Boolean theDichotomousAnswer;
    @Transient
    // answers for question, where can be several answers,
    // we will check, if all answers are chosen and if yes, then answer is correct, if no, then student will get 0 points
//    private List<String> multipleChoiceAnswers;
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public Answer(List<String> multipleChoiceAnswers) {
//        this.multipleChoiceAnswers = multipleChoiceAnswers;

    }

    public Answer(Boolean theDichotomousAnswer) {
        this.theDichotomousAnswer = theDichotomousAnswer;
    }

    public Answer(String openEndedAnswers) {
        this.openEndedAnswers = openEndedAnswers;
        String answer;  // field, where the student will write his answers
    }

    public Answer() {

    }

    public String getOpenEndedAnswers() {
        return openEndedAnswers;
    }

    public void setOpenEndedAnswers(String openEndedAnswers) {
        this.openEndedAnswers = openEndedAnswers;
    }

    public Boolean getTheDichotomousAnswer() {
        return theDichotomousAnswer;
    }

    public void setTheDichotomousAnswer(Boolean theDichotomousAnswer) {
        this.theDichotomousAnswer = theDichotomousAnswer;
    }

//    public List<String> getMultipleChoiceAnswers() {
//        return multipleChoiceAnswers;
//    }

    public void setMultipleChoiceAnswers(List<String> multipleChoiceAnswers) {
//        this.multipleChoiceAnswers = multipleChoiceAnswers;
    }

}
