package eu.judegam.wopfe.models.user;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String schools;

    public Principal() {

    }

    public Principal(String schools) {
        this.schools = schools;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchools() {
        return schools;
    }

    public void setSchools(String schools) {
        this.schools = schools;
    }
}