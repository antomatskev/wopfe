package eu.judegam.wopfe.models.school.tests;


import eu.judegam.wopfe.models.tests.Question;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String date;
    private int time;
//    @Transient
//    private List<Question> questions;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public List<Question> getQuestions() {
//        return questions;
//    }

//    public void setQuestions(List<Question> questions) {
//        this.questions = questions;
//    }

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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Test() {
    }

    public Test(String name, String date, int time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

}
