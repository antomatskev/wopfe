package eu.judegam.wopfe.controllers;

import eu.judegam.wopfe.models.school.Subject;
import eu.judegam.wopfe.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubjectController {
    @Autowired
    private SubjectService service;

    @PostMapping("/addSubject")
    public Subject addClass(@RequestBody Subject subject) {
        return service.saveSubject(subject);
    }

    @PostMapping("/addSubjects")
    public List<Subject> addClasses(@RequestBody List<Subject> subjects) {
        return service.saveSubjects(subjects);
    }

    @PostMapping("/subjects")
    public List<Subject> findAllClasses() {
        return service.getSubject();
    }

    @PostMapping("/subject/{id}")
    public Subject getClassById(@PathVariable Long id) {
        return service.getSubjectById(id);
    }

    @PostMapping("/subject/{name}")
    public Subject getSubjectByName(@PathVariable String name) {
        return service.getSubjectByName(name);
    }

    @PutMapping("/update")
    public Subject updateSubject(@RequestBody Subject subject) {
        return service.updateSubject(subject);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        return service.deleteSubject(id);
    }

}
