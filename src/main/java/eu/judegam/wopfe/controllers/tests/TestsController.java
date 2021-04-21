package eu.judegam.wopfe.controllers.tests;

import eu.judegam.wopfe.models.User;
import eu.judegam.wopfe.security.UserRole;
import eu.judegam.wopfe.services.TestsService;
import eu.judegam.wopfe.models.tests.Question;
import eu.judegam.wopfe.models.tests.Test;
import eu.judegam.wopfe.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class TestsController {

    private final TestsService service;

    public TestsController(TestsService service) {
        this.service = service;
    }

    @RequestMapping(value = "/main/tests", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String getTests(Model model) {
        List<Test> tests = service.getTests();
        model.addAttribute("tests", tests);
        model.addAttribute("test", new Test());
        return Utils.addUsrAttrToModel(model, "tests/teacher_tests");
    }

    @GetMapping(value = "/main/tests/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String showTestById(Model model, @PathVariable("id") Long id) {
        Test test = service.getTestById(id);
        model.addAttribute("test", test);
        model.addAttribute("question", new Question());
        return Utils.addUsrAttrToModel(model, "tests/edit_test");
    }

    @RequestMapping(path = "/main/tests/{id}/update", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String updateTest(Model model, @PathVariable("id") Long id, @ModelAttribute Test test) {
        Test dbTest = service.updateTest(id, test);
        model.addAttribute("test", dbTest);
        model.addAttribute("question", new Question());
        return Utils.addUsrAttrToModel(model, "tests/edit_test");
    }

    @RequestMapping(path = "/main/tests/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String updateTestQuestions(Model model, @PathVariable("id") Long id) {
        Test test = service.getTestById(id);
        model.addAttribute("test", test);
        model.addAttribute("questions", test.getQuestions());
        return Utils.addUsrAttrToModel(model, "tests/edit_test");
    }

    @RequestMapping(path = "/main/addTest", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public RedirectView saveTest(RedirectAttributes redirectAttributes, @ModelAttribute Test test) {
        service.saveTest(test);
        final String msg = "Created test <b>" + test.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("tests", true);
        redirectAttributes.addFlashAttribute("ttMessage", msg);
        return view;
    }

    @RequestMapping(path = "/main/test/{id}/delete", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public RedirectView deleteTest(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Test test) {
        RedirectView redirectView = new RedirectView("/main/tests", true);
        redirectAttributes.addFlashAttribute("ttMessage", service.deleteTest(id));
        return redirectView;
    }

    @RequestMapping(value = "/main/tasks", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_STUDENT')")
    public String getTasks(Model model) {
        final String ret;
        final List<Test> tests = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            Object user = auth.getPrincipal();
            if (user instanceof User) {
                UserRole userRole = ((User) user).getUserRole();
                model.addAttribute("role", userRole);
                model.addAttribute("username", ((User) user).getUsername());
                tests.addAll(service.getUserTests(((User) user).getClazz()));
                ret = "tests/student_tasks";
            } else {
                ret = "error";
            }
        } else {
            ret = "error";
        }
        model.addAttribute("tests", tests);
        model.addAttribute("test", new Test());
        return ret;
    }

    @GetMapping(value = "/main/tasks/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_STUDENT')")
    public String getTask(Model model, @PathVariable("id") Long id) {
        final String ret;
        Test test = service.getTestById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            Object user = auth.getPrincipal();
            if (user instanceof User) {
                UserRole userRole = ((User) user).getUserRole();
                model.addAttribute("role", userRole);
                model.addAttribute("username", ((User) user).getUsername());
                if (!Objects.equals(test.getClazz(), ((User) user).getClazz())) {
                    ret = "error";
                } else {
                    model.addAttribute("test", test);
                    model.addAttribute("question", new Question());
                    ret = "tests/task";
                }
            } else {
                ret = "error";
            }
        } else {
            ret = "error";
        }
        return ret;
    }

    @RequestMapping(path = "/main/task/{id}/check", method =
            RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_STUDENT')")
    public RedirectView checkTask(RedirectAttributes redirectAttributes,
                          @PathVariable("id") Long id, @ModelAttribute Test test) {
        RedirectView redirectView = new RedirectView("/main/tasks/", true);
        Test task = service.getTestById(id);
        redirectAttributes.addFlashAttribute("taskCheckMessage",
                task);
        return redirectView;
    }

}
