package eu.judegam.wopfe.models.school;

import eu.judegam.wopfe.models.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
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
//    @OneToMany(mappedBy = "clazz")
//    private List<User> users;
    private String timetable;

    public Class(String name, String timetable) {
        this.name = name;
        this.timetable = timetable;
//        this.users = new ArrayList<>();
    }

    public Class(String name, List<User> users, String timetable) {
        this.name = name;
//        this.users = users;
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

//    public List<User> getUsers() {
//        return users;
//    }

//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }
}
