package eu.judegam.wopfe.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.util.Objects;

/**
 * Represents an administrator, which has overview of everything in all schools from own schools list.
 */
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String schools;
//    private @ManyToOne Manager manager;
    private String manager;
    private boolean isActive = true;

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

    public String getLastName() {
        return lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSchools() {
        return schools;
    }

    public void setSchools(String schools) {
        this.schools = schools;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Admin() {
    }

    public Admin(String name, String lastName, String schools, String manager) {
        this.name = name;
        this.lastName = lastName;
        this.schools = schools;
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id)
                && Objects.equals(name, admin.name)
                && Objects.equals(lastName, admin.lastName)
                && Objects.equals(schools, admin.schools)
                && Objects.equals(manager, admin.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, schools, manager);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", schools='" + schools + '\'' +
                ", manager=" + manager +
                '}';
    }
}
