package eu.judegam.wopfe.models.repositories.school.classs.service;

import eu.judegam.wopfe.models.repositories.school.classs.repository.ClassRepository;
import eu.judegam.wopfe.models.school.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Class class.
 */
@Service
public class ClassService {

    private ClassRepository repository;

    @Autowired
    public ClassService(ClassRepository repo) {
        this.repository = repo;
    }

    public Class saveClass(Class classs) {
        return repository.save(classs);
    }

    public List<Class> saveClasses(List<Class> classes) {
        return (List<Class>) repository.saveAll(classes);
    }

    public List<Class> getClasss() {
        return (List<Class>) repository.findAll();
    }

    public Class getClassById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Class getClassByName(String name) {
        return repository.findByName(name);
    }

    public String deleteClass(Long id) {
        repository.deleteById(id);
        return "Class was deleted.";
    }

    public Class updateClass(Class classs) {
        Class existingProduct = repository.findById(classs.getId()).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(classs.getName());
        existingProduct.setSchool(classs.getSchool());
        existingProduct.setStudents(classs.getStudents());
        existingProduct.setTimetable(classs.getTimetable());
        return repository.save(existingProduct);

    }
}
