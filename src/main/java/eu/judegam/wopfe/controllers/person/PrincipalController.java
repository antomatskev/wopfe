package eu.judegam.wopfe.controllers.person;

import eu.judegam.wopfe.models.repositories.person.principal.service.PrincipalService;
import eu.judegam.wopfe.models.repositories.school.classs.service.ClassService;
import eu.judegam.wopfe.models.repositories.school.subject.service.SubjectService;
import eu.judegam.wopfe.models.school.Class;
import eu.judegam.wopfe.models.school.Subject;
import eu.judegam.wopfe.models.user.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Controller for Principal class.
 */
@Controller
public class PrincipalController {
    private final ClassService classService;
    private final SubjectService subjectService;

    @Autowired
    private PrincipalService service;

    public PrincipalController(ClassService classService, SubjectService subjectService) {
        this.classService = classService;
        this.subjectService = subjectService;
    }

    @PostMapping("/addPrincipal")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Principal addPrincipal(@RequestBody Principal principal) {
        return service.savePrincipal(principal);
    }

    /**
     * Method for getting all classes from database.
     */
    @RequestMapping(path = "/main/principal/addClasses", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String getClass(Model model) {
        List<Class> classes = classService.getClasss();
        model.addAttribute("classes", classes);
        model.addAttribute("class", new Class());
        return "principal/principal_add_class";
    }

    /**
     * Method for successful class adding to database ang viewing a message after adding.
     *
     * @return redirect to the same page.
     */
    @PostMapping(path = "/main/principal/addClasses")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView createClass(RedirectAttributes redirectAttributes, @RequestParam String name, @RequestParam String school,
                                    @RequestParam String students, @RequestParam String timetable, Model model) {
        Class classs = new Class(name, school, students, timetable);
        classService.saveClass(classs);
        final String msg = "Created class <b>" + String.format("%s", classs.getName()) + "</b> .";
        RedirectView view = new RedirectView("addClasses", true);
        redirectAttributes.addFlashAttribute("classMessage", msg);
        return view;
    }

    /**
     * Method for getting all subjects from database.
     */
    @RequestMapping(path = "/main/principal/addSubject", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String getSubject(Model model) {
        List<Subject> subjects = subjectService.getSubject();
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", new Subject());
        return "principal/principal_add_subjects";
    }


    /**
     * Method for successful subject adding to database ang viewing a message after adding.
     *
     * @return redirect to the same page.
     */
    @PostMapping(path = "/main/principal/addSubject")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView createSubject2(RedirectAttributes redirectAttributes, @ModelAttribute Subject subject) {
        subjectService.saveSubject(subject);
        final String msg = "Created class <b>" + String.format("%s", subject.getName()) + "</b> .";
        RedirectView view = new RedirectView("addSubject", true);
        redirectAttributes.addFlashAttribute("subjectMessage", msg);
        return view;
    }

    /**
     * Method for viewing a subject with current ID.
     */
    @RequestMapping(path = "/main/principal/subject/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String getSubject(Model model, @PathVariable("id") Long id) {
        Subject subject = subjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        return "principal/principal_delete_edit_subject_id";
    }

    /**
     * Method for viewing a class with current ID.
     */
    @RequestMapping(path = "/main/principal/class/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String getClass(Model model, @PathVariable("id") Long id) {
        Class classs = classService.getClassById(id);
        model.addAttribute("class", classs);
        return "principal/principal_delete_edit_class_id";
    }

    /**
     * Method for deleting a subject.
     */
    @RequestMapping(path = "/main/principal/subject/{id}/delete")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView deleteSubject(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Subject subject) {
        String name = subject.getName();
        subjectService.deleteSubject(id);
        final String msg = "Deleted subject <b>" + String.format("%s", name) + "</b> .";
        RedirectView redirectView = new RedirectView("/main/principal/addSubject", true);
//        redirectAttributes.addFlashAttribute("subjectMessage", msg);
        return redirectView;
    }

    /**
     * Method for editing a subject.
     */
    @RequestMapping(path = "/main/principal/subject/{id}/update", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView updateSubject(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Subject subject) {
        subjectService.updateSubject(id, subject);
        final String msg = "Updated subject <b>" + String.format("%s", subject.getName()) + "</b> .";
        RedirectView redirectView = new RedirectView("/main/principal/addSubject", true);
        redirectAttributes.addFlashAttribute("subjectMessage", msg);
        return redirectView;
    }

    /**
     * Method for deleting a class.
     */
    @RequestMapping(path = "/main/principal/class/{id}/delete")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView deleteClass(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Class classs) {
        classService.deleteClass(id);
        final String msg = "Deleted class <b>" + String.format("%s", classs.getName()) + "</b> .";
        RedirectView redirectView = new RedirectView("/main/principal/addClasses", true);
//        redirectAttributes.addFlashAttribute("classMessage", msg);
        return redirectView;
    }

    /**
     * Method for editing a class.
     */
    @RequestMapping(path = "/main/principal/class/{id}/update", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView updateClass(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Class classs) {
        classService.updateClass(classs);
        final String msg = "Updated class <b>" + String.format("%s", classs.getName()) + "</b> .";
        RedirectView redirectView = new RedirectView("/main/principal/addClasses", true);
        redirectAttributes.addFlashAttribute("classMessage", msg);
        return redirectView;
    }

    @PostMapping("/addPrincipals")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public List<Principal> addTests(@RequestBody List<Principal> principals) {
        return service.savePrincipals(principals);
    }

    @PostMapping("/principals")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public List<Principal> findAllPrincipals() {
        return service.getPrincipal();
    }

    @PostMapping("/principal/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Principal getPrincipalById(@PathVariable Long id) {
        return service.getPrincipalById(id);
    }

    @PostMapping("/principal/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Principal getPrincipalByName(@PathVariable String name) {
        return service.getPrincipalByName(name);
    }

    @PutMapping("/principal/{id}/update")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public Principal updatePrincipal(@RequestBody Principal principal) {
        return service.updatePrincipal(principal);
    }

    @DeleteMapping("/principal/{id}/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String deletePrincipal(@PathVariable Long id) {
        return service.deletePrincipal(id);
    }

    @GetMapping("/main/principal")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String principal(Model model) {
        return "mains/principal_main";
    }

}
