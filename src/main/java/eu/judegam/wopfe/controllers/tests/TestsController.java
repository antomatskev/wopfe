package eu.judegam.wopfe.controllers.tests;

import eu.judegam.wopfe.models.tests.Test;
import eu.judegam.wopfe.models.repositories.school.tests.service.TestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

    @PostMapping("/addTest")
    public Test addTest(@RequestBody Test test) {
        return service.saveTest(test);
    }


    @PostMapping("/addTests")
    public List<Test> addTests(@RequestBody List<Test> tests) {
        return service.saveTests(tests);
    }

    @GetMapping("/test/{id}")
    public Test getTestById(@PathVariable Long id) {
        return service.getTestById(id);
    }

//    @PostMapping("/test/{name}")
//    public Test getTestByName(@PathVariable String name) {
//        return service.getTestByName(name);
//    }


    @PutMapping("/test/{id}/update")
    public Test updateTest(@RequestBody Test test) {
        return service.updateTest(test);
    }

    @DeleteMapping("/test/{id}/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return service.deleteTest(id);
    }

    @GetMapping("/tests/teacher")
    public String home(Model model) {
        return "/tests/teacher";
    }

    @GetMapping("/tests/student")
    public String login(Model model) {
        return "/tests/student";
    }

}
