package eu.judegam.wopfe.models.school;

import eu.judegam.wopfe.models.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * Class of a student where he/she is studying(
 */
@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String timetable;

    public Class(String name, String timetable) {
        this.name = name;
        this.timetable = timetable;
    }

    public Class(String name, List<User> users, String timetable) {
        this.name = name;
        this.timetable = timetable;
    }

    public Class() {
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

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }
}
