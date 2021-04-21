package eu.judegam.wopfe.models.tests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents Question, that can be open(students can write their answers manually) or questions for tests.
 */
@Entity
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String questionText;
    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    @JsonIgnore
    private Test test;
    @Transient
    @JsonIgnore
    private Long testId;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;
    private String answer;

    public Question(String name, String type, String questionText) {
        this.name = name;
        this.type = type;
        this.questionText = questionText;
    }

    public Question(String name, Test test) {
        this.name = name;
        this.test = test;
    }

    public Question(String type, String questionText, Long testId) {
        this.type = type;
        this.questionText = questionText;
        this.testId = testId;
    }

    public Question(Long id, String type, String questionText, Test test) {
        this.id = id;
        this.type = type;
        this.questionText = questionText;
        this.test = test;
    }

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Answer> getAnswers() {
            return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getAnswer() {
        // TODO: make selection in task.html for answers.
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
