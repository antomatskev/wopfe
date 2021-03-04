package eu.judegam.wopfe.models.tests;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Test {
    private String name;
    private String date;
    private int time;
    private Long id;
    private String content;
    private String answer;


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

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
