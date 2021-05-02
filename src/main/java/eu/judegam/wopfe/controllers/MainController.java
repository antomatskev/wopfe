package eu.judegam.wopfe.controllers;

import eu.judegam.wopfe.utils.Utils;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/profile")
    public String profile(Model model) {
        return Utils.addUsrAttrToModel(model, "/profile");
    }

    @GetMapping("/main")
    public String main(Model model) {
        return Utils.addUsrAttrToModel(model, "/mains/main");
    }

    @GetMapping("/main/teacher")
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String teacher(Model model) {
        return Utils.addUsrAttrToModel(model, "mains/teacher_main");
    }

    @GetMapping("/main/student")
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_STUDENT')")
    public String student(Model model) {
        return Utils.addUsrAttrToModel(model, "mains/student_main");
    }

}
