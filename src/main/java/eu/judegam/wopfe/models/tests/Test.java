package eu.judegam.wopfe.models.tests;

import eu.judegam.wopfe.models.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="tests")
public class Test implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date date;
    private String time;
    private String clazz;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;
    @ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.PERSIST,
            mappedBy = "assignedTests")
    private List<User> users = new ArrayList<>();
    @ElementCollection
    private List<Long> correctlyAnswered = new ArrayList<>();

    public Test() {

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User u) {
        users.add(u);
    }

    public void addUsers(List<User> usrs) {
        users.addAll(usrs);
    }

    public void updateQuestion(Question q) {
        questions.remove(q);
        questions.add(q);
    }

    public List<Long> getCorrectlyAnswered() {
        return correctlyAnswered;
    }

    public void setCorrectlyAnswered(List<Long> correctlyAnswered) {
        this.correctlyAnswered = correctlyAnswered;
    }
}
