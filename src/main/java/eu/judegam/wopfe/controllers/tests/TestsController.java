package eu.judegam.wopfe.controllers.tests;

import eu.judegam.wopfe.models.repositories.school.tests.service.TestsService;
import eu.judegam.wopfe.models.tests.Question;
import eu.judegam.wopfe.models.tests.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TestsController {

    @Autowired
    private TestsService service;

    @Autowired
    private TestsService answerSe; //TODO class answerService

    @RequestMapping(value = "/main/teacher/tests", method = RequestMethod.GET)
    public String getTests(Model model) {
        List<Test> tests = service.getTests();
        model.addAttribute("tests", tests);
        model.addAttribute("test", new Test());
        return "tests/teacher_tests";
    }

    @RequestMapping(value = "/main/teacher/tests/{id}", method = RequestMethod.GET)
    public String showTimetableById(Model model, @PathVariable("id") Long id) {
        Test test = service.getTestById(id);
        model.addAttribute("test", test);
        model.addAttribute("question", new Question());
        return "tests/edit_test";
    }

    @RequestMapping(path = "/main/teacher/tests/{id}/update", method = RequestMethod.POST)
    public String updateTest(Model model, @PathVariable("id") Long id, @ModelAttribute Test test) {
        Test dbTest = service.updateTest(id, test);
        model.addAttribute("test", dbTest);
        model.addAttribute("question", new Question());
        return "tests/edit_test";
    }

    @RequestMapping(path = "/main/teacher/tests/{id}", method = RequestMethod.POST)
    public String updateTestQuestions(Model model, @PathVariable("id") Long id) {
        Test test = service.getTestById(id);
        model.addAttribute("test", test);
        model.addAttribute("questions", test.getQuestions());
        model.addAttribute("question", new Question());
        return "tests/edit_test";
    }

//    TODO: remove for "'/main/teacher/question/' + ${test.id} + '/deleteQuestion/' + ${question.id}"

}
