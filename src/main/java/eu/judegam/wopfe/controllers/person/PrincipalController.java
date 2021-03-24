package eu.judegam.wopfe.controllers.person;

import eu.judegam.wopfe.models.repositories.person.principal.service.PrincipalService;
import eu.judegam.wopfe.models.repositories.school.classs.service.ClassService;
import eu.judegam.wopfe.models.repositories.school.subject.service.SubjectService;
import eu.judegam.wopfe.models.school.Class;
import eu.judegam.wopfe.models.school.Subject;
import eu.judegam.wopfe.models.user.Principal;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Principal addPrincipal(@RequestBody Principal principal) {
        return service.savePrincipal(principal);
    }

    /**
     * Method for getting all classes from database.
     */
    @RequestMapping(path = "/main/principal/addClasses", method = RequestMethod.GET)
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
    public RedirectView createClass(RedirectAttributes redirectAttributes, @RequestParam String name, @RequestParam String school,
                                    @RequestParam String students, @RequestParam String timetable, Model model) {
        Class classs = new Class(name, school, students, timetable);
        classService.saveClass(classs);
        final String msg = "Created class <b>" + String.format("%s", classs.getName()) + "</b>";
        RedirectView view = new RedirectView("addClasses", true);
        redirectAttributes.addFlashAttribute("classMessage", msg);
        return view;
    }

    /**
     * Method for getting all subjects from database.
     */
    @RequestMapping(path = "/main/principal/addSubject", method = RequestMethod.GET)
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
    public RedirectView createSubject2(RedirectAttributes redirectAttributes, @ModelAttribute Subject subject) {
        subjectService.saveSubject(subject);
        final String msg = "Created class <b>" + String.format("%s", subject.getName()) + "</b>";
        RedirectView view = new RedirectView("addSubject", true);
        redirectAttributes.addFlashAttribute("subjectMessage", msg);
        return view;
    }

    /**
     * Mapping for deleting and editing a subject.
     */
    @GetMapping("/main/principal/deleteSubject")
    public String deleteEditPage(Model model) {
        List<Subject> subjects = subjectService.getSubject();
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", new Subject());
        return "principal/principal_delete_edit_subjects";
    }

    /**
     * Mapping for deleting and editing a class.
     */
    @GetMapping("/main/principal/deleteClasses")
    public String deleteEditClass(Model model) {
        List<Class> classes = classService.getClasss();
        model.addAttribute("classes", classes);
        model.addAttribute("class", new Class());
        return "principal/principal_delete_edit_class";
    }

    /**
     * Method for viewing a subject with current ID.
     */
    @RequestMapping(path = "/main/principal/subject/{id}", method = RequestMethod.GET)
    public String getSubject(Model model, @PathVariable("id") Long id) {
        Subject subject = subjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        return "principal/principal_delete_edit_subject_id";
    }

    /**
     * Method for viewing a class with current ID.
     */
    @RequestMapping(path = "/main/principal/class/{id}", method = RequestMethod.GET)
    public String getClass(Model model, @PathVariable("id") Long id) {
        Class classs = classService.getClassById(id);
        model.addAttribute("class", classs);
        return "principal/principal_delete_edit_class_id";
    }

    /**
     * Method for editing and updating a subject
     */
    @RequestMapping(path = "/main/principal/subject/{id}/delete", method = RequestMethod.POST)
    public RedirectView deleteSubject(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Subject subject) {
        subjectService.deleteSubject(id);
//        String message = (subject.isActive() ? "Updated " : "Deleted ") + " subject <b>" + subject.getName() + "</b>";
        RedirectView redirectView = new RedirectView("/main/principal/deleteSubject", true);
//        redirectAttributes.addFlashAttribute("subjectMessage", message);
        return redirectView;
    }

    @RequestMapping(path = "/main/principal/subject/{id}/update", method = RequestMethod.POST)
    public RedirectView updateSubject(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Subject subject) {
        subjectService.updateSubject(id, subject);
//        String message = (subject.isActive() ? "Updated " : "Deleted ") + " subject <b>" + subject.getName() + "</b>";
        RedirectView redirectView = new RedirectView("/main/principal/deleteSubject", true);
//        redirectAttributes.addFlashAttribute("subjectMessage", message);
        return redirectView;
    }

    /**
     * Method for editing and updating a class
     */
    @RequestMapping(path = "/main/principal/class/{id}/delete", method = RequestMethod.POST)
    public RedirectView deleteClass(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Class classs) {
        classService.deleteClass(id);
        // String message = (classs.isActive() ? "Updated " : "Deleted ") + " subject <b>" + classs.getName() + "</b>";
        RedirectView redirectView = new RedirectView("/main/principal/deleteClasses", true);
        // redirectAttributes.addFlashAttribute("classMessage", message);
        return redirectView;
    }

    @RequestMapping(path = "/main/principal/class/{id}/update", method = RequestMethod.POST)
    public RedirectView updateClass(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Class classs) {
        classService.updateClass(classs);
        // String message = (classs.isActive() ? "Updated " : "Deleted ") + " subject <b>" + classs.getName() + "</b>";
        RedirectView redirectView = new RedirectView("/main/principal/deleteClasses", true);
        // redirectAttributes.addFlashAttribute("classMessage", message);
        return redirectView;
    }

    @PostMapping("/addPrincipals")
    public List<Principal> addTests(@RequestBody List<Principal> principals) {
        return service.savePrincipals(principals);
    }

    @PostMapping("/principals")
    public List<Principal> findAllPrincipals() {
        return service.getPrincipal();
    }

    @PostMapping("/principal/{id}")
    public Principal getPrincipalById(@PathVariable Long id) {
        return service.getPrincipalById(id);
    }

    @PostMapping("/principal/{name}")
    public Principal getPrincipalByName(@PathVariable String name) {
        return service.getPrincipalByName(name);
    }

    @PutMapping("/principal/{id}/update")
    public Principal updatePrincipal(@RequestBody Principal principal) {
        return service.updatePrincipal(principal);
    }

    @DeleteMapping("/principal/{id}/delete/{id}")
    public String deletePrincipal(@PathVariable Long id) {
        return service.deletePrincipal(id);
    }

    @GetMapping("/main/principal")
    public String principal(Model model) {
        return "mains/principal_main";
    }

}
