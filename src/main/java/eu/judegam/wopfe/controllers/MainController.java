package eu.judegam.wopfe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/main")
    public String main(Model model) {
        return "mains/main";
    }
    @GetMapping("/main/teacher")
    public String teachermain(Model model){
        return "mains/teacher_main";
    }

    @GetMapping("/main/student")
    public String student(Model model) {
        return "mains/student_main";
    }

}
