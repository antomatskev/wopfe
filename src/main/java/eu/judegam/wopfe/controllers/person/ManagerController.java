package eu.judegam.wopfe.controllers.person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

    @GetMapping("/main/manager")
    public String managerHome(Model model) {
        return "manager/manager_main";
    }

    @GetMapping("/main/manager/admins")
    public String managerAdmins(Model model) {
        return "manager/manager_admins";
    }

    @GetMapping("/main/manager/schools")
    public String managerSchools(Model model) {
        return "manager/manager_schools";
    }

}
