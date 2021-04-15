package eu.judegam.wopfe.controllers;

import eu.judegam.wopfe.models.Subject;
import eu.judegam.wopfe.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for Subject class.
 */
@RestController
public class SubjectController {
    @Autowired
    private SubjectService service;

    @PostMapping("/addSubject")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Subject addSubject(@RequestBody Subject subject) {
        return service.saveSubject(subject);
    }

    @PostMapping("/addSubjects")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public List<Subject> addSubjects(@RequestBody List<Subject> subjects) {
        return service.saveSubjects(subjects);
    }

    @PostMapping("/subjects")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public List<Subject> findAllSubjects() {
        return service.getSubject();
    }

    @PostMapping("/subject/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Subject getClassById(@PathVariable Long id) {
        return service.getSubjectById(id);
    }

    @PostMapping("/subject/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Subject getSubjectByName(@PathVariable String name) {
        return service.getSubjectByName(name);
    }

//    @PutMapping("/subject/{id}/update")
//    public Subject updateSubject(@RequestBody Subject subject) {
//        return service.updateSubject(subject);
//    }

    @DeleteMapping("/subject/{id}/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String deleteSubject(@PathVariable Long id) {
        return service.deleteSubject(id);
    }

}
