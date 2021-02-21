package eu.judegam.wopfe.models.user;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * Represents a school.
 */
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String location;
//    private List<Teacher> teachers;
//    private List<Class> classes;
//    private List<Principal> principals;
    private String places;

    public School() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public List<Teacher> getTeachers() {
//        return teachers;
//    }
//
//    public void setTeachers(List<Teacher> teachers) {
//        this.teachers = teachers;
//    }
//
//    public List<Class> getClasses() {
//        return classes;
//    }
//
//    public void setClasses(List<Class> classes) {
//        this.classes = classes;
//    }
//
//    public List<Principal> getPrincipals() {
//        return principals;
//    }
//
//    public void setPrincipals(List<Principal> principals) {
//        this.principals = principals;
//    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }


}
