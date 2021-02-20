package eu.judegam.wopfe.models.user;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Represents a school subject.
 */
@Entity
public class Subject {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
//    private int id;
//    private String name;
//    private String specialty;
//    private List<Classes> classes;
//    private String school;
//    private String teacher;
//    private String period;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSpecialty() {
//        return specialty;
//    }
//
//    public void setSpecialty(String specialty) {
//        this.specialty = specialty;
//    }
//
//    public List<Classes> getClasses() {
//        return classes;
//    }
//
//    public void setClasses(List<Classes> classes) {
//        this.classes = classes;
//    }
//
//    public String getSchool() {
//        return school;
//    }
//
//    public void setSchool(String school) {
//        this.school = school;
//    }
//
//    public String getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(String teacher) {
//        this.teacher = teacher;
//    }
//
//    public String getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(String period) {
//        this.period = period;
//    }
}
