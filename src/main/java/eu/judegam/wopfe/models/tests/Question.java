package eu.judegam.wopfe.models.tests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.judegam.wopfe.models.school.tests.Test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Represents Question, that can be open(students can write their answers manually) or questions for tests.
 */
@Entity
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String questionText;
    @ManyToOne
    @JoinColumn(name="test_id", nullable=false)
    @JsonIgnore
    private Test test;
    @Transient
    @JsonIgnore
    private Long testId;

    public Question(Long id, String type, String questionText, Test test) {
        this.id = id;
        this.type = type;
        this.questionText = questionText;
        this.test = test;
    }

    public Question() {
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
