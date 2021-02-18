package eu.judegam.wopfe.models.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents an administrator, which has overview of everything in all schools from own schools list.
 */
@Entity
public class Admin extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // TODO: uncomment when School class will be ready.

//    private List<School> schools;
//
//    public List<School> getSchools() {
//        return schools;
//    }
//
//    public void setSchools(List<School> schools) {
//        this.schools = schools;
//    }
//
//    public Admin() {
//    }
//
//    public Admin(List<School> schools) {
//        this.schools = schools;
//    }
}
