package eu.judegam.wopfe.controllers;

import eu.judegam.wopfe.models.repositories.school.classs.service.ClassService;
import eu.judegam.wopfe.models.school.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for class Class.
 */
@RestController
public class ClassController {

    @Autowired
    private ClassService service;

    @PostMapping("/addClass")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Class addClass(@RequestBody Class classs) {
        return service.saveClass(classs);
    }

    @PostMapping("/addClasses")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public List<Class> addClasses(@RequestBody List<Class> classes) {
        return service.saveClasses(classes);
    }

    @PostMapping("/classes")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public List<Class> findAllClasses() {
        return service.getClasss();
    }

    @PostMapping("/class/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Class getClassById(@PathVariable Long id) {
        return service.getClassById(id);
    }

    @PostMapping("/class/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Class getClassByName(@PathVariable String name) {
        return service.getClassByName(name);
    }

    @PutMapping("/class/{id}/update")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Class updateClass(@RequestBody Class classs) {
        return service.updateClass(classs);
    }

    @DeleteMapping("/class/{id}/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String deleteClass(@PathVariable Long id) {
        return service.deleteClass(id);
    }

}
