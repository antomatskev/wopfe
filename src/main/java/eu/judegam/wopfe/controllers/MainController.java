package eu.judegam.wopfe.controllers;

import eu.judegam.wopfe.models.user.User;
import eu.judegam.wopfe.security.UserRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            Object user = auth.getPrincipal();
            if (user instanceof User) {
                UserRole userRole = ((User) user).getUserRole();
                model.addAttribute("role", userRole);
                model.addAttribute("username", ((User) user).getUsername());
            } else {
                return "error";
            }
        } else {
            return "error";
        }
        return "mains/main";
    }
    @GetMapping("/main/teacher")
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_TEACHER')")
    public String teacher(Model model){
        return "mains/teacher_main";
    }

    @GetMapping("/main/student")
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_STUDENT')")
    public String student(Model model) {
        return "mains/student_main";
    }

}
