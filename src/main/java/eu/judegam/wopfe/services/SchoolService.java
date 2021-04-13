package eu.judegam.wopfe.services;

import eu.judegam.wopfe.models.school.School;
import eu.judegam.wopfe.repositories.SchoolRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepo repo;

    public SchoolService(SchoolRepo repository) {
        this.repo = repository;
    }

    public School saveSchool(School school) {
        return repo.save(school);
    }

    public List<School> saveSchools(List<School> schools) {
        return (List<School>) repo.saveAll(schools);
    }

    public List<School> getSchools() {
        return (List<School>) repo.findAll();
    }

    public School getSchoolById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public School getSchoolByName(String name) {
        return repo.findByName(name);
    }

    public String deleteSchool(Long id) {
        repo.deleteById(id);
        return "School was deleted.";
    }

    public School updateSchool(School school) {
        return updateSchool(Long.MAX_VALUE, school);
    }

    public School updateSchool(Long id, School school) {
        School existingProduct = repo.findById(id).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(school.getName());
        existingProduct.setLocation(school.getLocation());
        existingProduct.setClasses(school.getClasses());
        existingProduct.setPrincipals(school.getPrincipals());
        existingProduct.setTeachers(school.getTeachers());
        return repo.save(existingProduct);
    }

}
