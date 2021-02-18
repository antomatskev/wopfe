package eu.judegam.wopfe.models.user;

import javax.persistence.Entity;

/**
 * Represents an administrator, which has overview of everything in all schools from own schools list.
 */
@Entity
public class Admin extends Person {

    private List<School> schools;

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public Admin() {
    }

    public Admin(List<School> schools) {
        this.schools = schools;
    }
}
