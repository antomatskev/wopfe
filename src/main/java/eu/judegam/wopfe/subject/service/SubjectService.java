package eu.judegam.wopfe.subject.service;

import eu.judegam.wopfe.models.school.Subject;
import eu.judegam.wopfe.subject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Subject class.
 */
@Service
public class SubjectService {
    @Autowired
    private SubjectRepository repository;

    public Subject saveSubject(Subject subject) {
        return repository.save(subject);
    }

    public List<Subject> saveSubjects(List<Subject> subjects) {
        return (List<Subject>) repository.saveAll(subjects);
    }

    public List<Subject> getSubject() {
        return (List<Subject>) repository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Subject getSubjectByName(String name) {
        return repository.findByName(name);
    }

    public String deleteSubject(Long id) {
        repository.deleteById(id);
        return "Subject was deleted.";
    }

    public Subject updateSubject(Subject subject) {
        Subject existingProduct = repository.findById(subject.getId()).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(subject.getName());
        existingProduct.setSchool(subject.getSchool());
        existingProduct.setPeriod(subject.getPeriod());
        existingProduct.setSpecialty(subject.getSpecialty());
        existingProduct.setClasses(subject.getClasses());
        existingProduct.setTeacher(subject.getTeacher());
        return repository.save(existingProduct);

    }
}
