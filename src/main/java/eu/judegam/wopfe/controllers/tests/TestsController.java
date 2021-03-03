package eu.judegam.wopfe.controllers.tests;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestsController {

    @GetMapping("/teacher_tests_main")
    public String home(Model model) {
        return "/teacher_tests_main";
    }

    @GetMapping("/student_tests_main")
    public String login(Model model) {
        return "/student_tests_main";
    }

}
