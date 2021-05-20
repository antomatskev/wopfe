package eu.judegam.wopfe.controllers.person;

import eu.judegam.wopfe.auth.UserService;
import eu.judegam.wopfe.models.User;
import eu.judegam.wopfe.security.UserRole;
import eu.judegam.wopfe.utils.Utils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ManagerController {

    private final UserService service;

    public ManagerController(UserService service) {
        this.service = service;
    }

    @RequestMapping(path = "/main/admins", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_MANAGER')")
    public String getAdmins(Model model) {
        List<User> admins = service.getUsersWithRole(UserRole.ADMIN).stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        model.addAttribute("admins", admins);
        model.addAttribute("admin", new User());
        return Utils.addUsrAttrToModel(model, "manager/manager_admins");
    }

    @RequestMapping(path = "/main/admins/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_MANAGER')")
    public String getAdmin(Model model, @PathVariable("id") Long id) {
        User admin = service.getUserById(id);
        model.addAttribute("admin", admin);
        return Utils.addUsrAttrToModel(model, "manager/manager_admin_edit");
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
        return Utils.addUsrAttrToModel(model, "manager/manager_admins");
    }

    @RequestMapping(path = "/main/users/{id}/delete", method =
            RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_MANAGER')")
    public RedirectView deleteUser(RedirectAttributes redirectAttributes,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute User user) {
        RedirectView redirectView = new RedirectView("..", true);
        redirectAttributes.addFlashAttribute("usrMessage",
                service.deleteUser(id));
        return redirectView;
    }

    @RequestMapping(path = "/main/users", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_MANAGER')")
    public String getAllUsers(Model model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return Utils.addUsrAttrToModel(model, "mains/users");
    }

}
