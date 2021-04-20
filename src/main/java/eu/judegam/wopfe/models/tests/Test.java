package eu.judegam.wopfe.models.tests;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Entity
public class Test implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String time;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;

    public Test(String name) {
        this.name = name;
    }

    public Test(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public Test(Long id, String name, String date, String time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public Test(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

}
