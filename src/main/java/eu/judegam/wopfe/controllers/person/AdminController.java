package eu.judegam.wopfe.controllers.person;

import eu.judegam.wopfe.auth.UserService;
import eu.judegam.wopfe.models.user.User;
import eu.judegam.wopfe.security.UserRole;
import eu.judegam.wopfe.utils.Utils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private final UserService usrService;

    public AdminController(UserService usrService) {
        this.usrService = usrService;
    }

    @RequestMapping(path = "/main/students", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public String getStudents(Model model) {
        List<User> students = usrService.getUsersWithRole(UserRole.STUDENT);
        model.addAttribute("students", students);
        model.addAttribute("student", new User());
        return Utils.addUsrAttrToModel(model, "school/students");
    }

    @RequestMapping(path = "/main/students/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public String getStudent(Model model, @PathVariable("id") Long id) {
        User student = usrService.getUserById(id);
        model.addAttribute("user", student);
        return "school/student_info";
    }

    @RequestMapping(path = "/main/students", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public RedirectView createStudent(RedirectAttributes redirectAttributes,
                                      @ModelAttribute User student) {
        usrService.saveUser(student, UserRole.STUDENT);
        final String msg = String.format("Created student <b>%s</b>.",
                student.getFullName());
        RedirectView view = new RedirectView("students", true);
        redirectAttributes.addFlashAttribute("studentMessage", msg);
        return view;
    }

    @RequestMapping(path = "/main/students/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public String updateStudent(Model model, @PathVariable("id") Long id,
                                @ModelAttribute User student) {
        User updStudent = usrService.updateUser(id, student);
        model.addAttribute("user", updStudent);
        return "school/student_info";
    }

    @RequestMapping(path = "/main/students/{id}/delete", method =
            RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public RedirectView deleteStudent(RedirectAttributes redirectAttributes,
                                      @PathVariable("id") Long id,
                                      @ModelAttribute User student) {
        RedirectView redirectView = new RedirectView("..", true);
        redirectAttributes.addFlashAttribute("studentMessage",
                usrService.deleteUser(id));
        return redirectView;
    }

    @RequestMapping(path = "/main/teachers", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public String getTeachers(Model model) {
        List<User> teachers = usrService.getUsersWithRole(UserRole.TEACHER);
        model.addAttribute("teachers", teachers);
        model.addAttribute("teacher", new User());
        return "school/teachers";
    }

    @RequestMapping(path = "/main/teachers/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public String getTeacher(Model model, @PathVariable("id") Long id) {
        User teacher = usrService.getUserById(id);
        model.addAttribute("user", teacher);
        return "school/teacher_info";
    }

    @RequestMapping(path = "/main/teachers", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public RedirectView createTeacher(RedirectAttributes redirectAttributes,
                                      @ModelAttribute User teacher) {
        usrService.saveUser(teacher, UserRole.TEACHER);
        final String msg = String.format("Created teacher <b>%s</b>.",
                teacher.getFullName());
        RedirectView view = new RedirectView("teachers", true);
        redirectAttributes.addFlashAttribute("teacherMessage", msg);
        return view;
    }

    @RequestMapping(path = "/main/teachers/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public String updateTeacher(Model model, @PathVariable("id") Long id,
                                @ModelAttribute User teacher) {
        User updTeacher = usrService.updateUser(id, teacher);
        model.addAttribute("user", updTeacher);
        return "school/teacher_info";
    }

    @RequestMapping(path = "/main/teachers/{id}/delete", method =
            RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public RedirectView deleteTeacher(RedirectAttributes redirectAttributes,
                                      @PathVariable("id") Long id,
                                      @ModelAttribute User teacher) {
        RedirectView redirectView = new RedirectView("..", true);
        redirectAttributes.addFlashAttribute("teacherMessage",
                usrService.deleteUser(id));
        return redirectView;
    }

    @RequestMapping(path = "/main/classes", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public String getClasses(Model model) {
        Set<String> classes = usrService.getAllUsers().stream()
                .map(User::getClazz)
                .collect(Collectors.toSet());
        model.addAttribute("classes", classes);
        return "school/classes";
    }

    @RequestMapping(path = "/main/classes/{name}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public String getClass(Model model, @PathVariable("name") String name) {
        List<User> users = usrService.getAllUsers().stream()
                .filter(u -> Objects.equals(u.getClazz(), name))
                .collect(Collectors.toList());
        model.addAttribute("class", name);
        model.addAttribute("users", users);
        return "school/class_info";
    }

}
