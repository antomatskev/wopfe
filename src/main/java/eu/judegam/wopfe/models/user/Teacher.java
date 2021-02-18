package eu.judegam.wopfe.models.user;
import javax.persistence.Entity;
import java.util.List;

/**
 * Represents a teacher, which has overview of classes, schools, specialty, timetable.
 */
@Entity
public class Teacher extends Person{
    private List<School> schools;
    private List<Classes> classes;
    private String specialty;
    private String timetable;

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }
}
