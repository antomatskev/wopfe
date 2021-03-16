package eu.judegam.wopfe.models.tests;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Represents Question, that can be open(students can write their answers manually) or questions for tests.
 */
@Entity
public class Question {
    private String questionText;
    private Boolean isOpen;
    @Transient
    private Answer answer;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Question(String question, Answer answer, Boolean isOpen) {
        this.questionText = question;
        this.answer = answer;
        this.isOpen = isOpen;
    }

    public Question() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String question) {
        this.questionText = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

}
