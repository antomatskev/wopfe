package eu.judegam.wopfe.models.user;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Represents a school.
 */
@Entity
public class School {
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
//    private String location;
//    private List<Teacher> teachers;
//    private List<Class> classes;
//    private List<Principals> principals;
//    private String places;
//
//    public School(int id, String name, String location, List<Teacher> teachers, List<Class> classes, List<Principals> principals, String places) {
//        this.id = id;
//        this.name = name;
//        this.location = location;
//        this.teachers = teachers;
//        this.classes = classes;
//        this.principals = principals;
//        this.places = places;
//    }
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
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
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
//    public List<Principals> getPrincipals() {
//        return principals;
//    }
//
//    public void setPrincipals(List<Principals> principals) {
//        this.principals = principals;
//    }
//
//    public String getPlaces() {
//        return places;
//    }
//
//    public void setPlaces(String places) {
//        this.places = places;
//    }


}
