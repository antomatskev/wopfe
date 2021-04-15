package eu.judegam.wopfe.controllers.person;

import eu.judegam.wopfe.auth.UserService;
import eu.judegam.wopfe.models.user.User;
import eu.judegam.wopfe.security.UserRole;
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

@Controller
public class ManagerController {

    private final UserService service;

    public ManagerController(UserService service) {
        this.service = service;
    }

    @RequestMapping(path = "/main/admins", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_MANAGER')")
    public String getAdmins(Model model) {
        List<User> admins = service.getUsersWithRole(UserRole.ADMIN);
        model.addAttribute("admins", admins);
        model.addAttribute("admin", new User());
        return "manager/manager_admins";
    }

    @RequestMapping(path = "/main/admins/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_MANAGER')")
    public String getAdmin(Model model, @PathVariable("id") Long id) {
        User admin = service.getUserById(id);
        model.addAttribute("admin", admin);
        return "manager/manager_admin_edit";
    }

    @RequestMapping(path = "/main/admins", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_MANAGER')")
    public RedirectView createAdmin(RedirectAttributes redirectAttributes,
                                    @ModelAttribute User admin) {
        service.saveUser(admin, UserRole.ADMIN);
        final String msg = "Created admin <b>" + String.format("%s %s", admin.getFirstName(), admin.getLastName()) + "</b> .";
        RedirectView view = new RedirectView("admins", true);
        redirectAttributes.addFlashAttribute("adminMessage", msg);
        return view;
    }

    @RequestMapping(path = "/main/admins/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_MANAGER')")
    public String updateAdmin(Model model, @PathVariable("id") Long id,
                              @ModelAttribute User admin) {
        User updatedAdmin = service.updateUser(id, admin);
        model.addAttribute("admin", updatedAdmin);
        return "manager/manager_admins";
    }

    @RequestMapping(path = "/main/admins/{id}/delete", method =
            RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_MANAGER')")
    public RedirectView deleteAdmin(RedirectAttributes redirectAttributes,
                                    @PathVariable("id") Long id,
                                    @ModelAttribute User admin) {
        RedirectView redirectView = new RedirectView("..", true);
        redirectAttributes.addFlashAttribute("adminMessage",
                service.deleteUser(id));
        return redirectView;
    }

}
