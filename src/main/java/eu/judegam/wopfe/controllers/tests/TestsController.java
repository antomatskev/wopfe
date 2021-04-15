package eu.judegam.wopfe.controllers.tests;

import eu.judegam.wopfe.services.TestsService;
import eu.judegam.wopfe.models.tests.Question;
import eu.judegam.wopfe.models.tests.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class TestsController {

    @Autowired
    private TestsService service;

    @Autowired
    private TestsService answerSe; //TODO class answerService

    @RequestMapping(value = "/main/teacher/tests", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String getTests(Model model) {
        List<Test> tests = service.getTests();
        model.addAttribute("tests", tests);
        model.addAttribute("test", new Test());
        return "tests/teacher_tests";
    }

    @GetMapping(value = "/main/teacher/tests/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String showTestById(Model model, @PathVariable("id") Long id) {
        Test test = service.getTestById(id);
        model.addAttribute("test", test);
        model.addAttribute("question", new Question());
        return "tests/edit_test";
    }

    @RequestMapping(path = "/main/teacher/tests/{id}/update", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String updateTest(Model model, @PathVariable("id") Long id, @ModelAttribute Test test) {
        Test dbTest = service.updateTest(id, test);
        model.addAttribute("test", dbTest);
        model.addAttribute("question", new Question());
        return "tests/edit_test";
    }

    @RequestMapping(path = "/main/teacher/tests/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String updateTestQuestions(Model model, @PathVariable("id") Long id) {
        Test test = service.getTestById(id);
        model.addAttribute("test", test);
        model.addAttribute("questions", test.getQuestions());
        return "tests/edit_test";
    }

    @RequestMapping(path = "/main/teacher/addTest", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public RedirectView saveTest(RedirectAttributes redirectAttributes, @ModelAttribute Test test) {
        service.saveTest(test);
        final String msg = "Created test <b>" + test.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("tests", true);
        redirectAttributes.addFlashAttribute("ttMessage", msg);
        return view;
    }


    @RequestMapping(path = "/main/teacher/test/{id}/delete", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public RedirectView deleteTest(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Test test) {
        RedirectView redirectView = new RedirectView("/main/teacher/tests", true);
        redirectAttributes.addFlashAttribute("ttMessage", service.deleteTest(id));
        return redirectView;
    }

}
