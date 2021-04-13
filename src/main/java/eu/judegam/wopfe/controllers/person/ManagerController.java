package eu.judegam.wopfe.controllers.person;

import eu.judegam.wopfe.services.AdminService;
import eu.judegam.wopfe.services.SchoolService;
import eu.judegam.wopfe.models.school.School;
import eu.judegam.wopfe.models.user.Admin;
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
public class ManagerController {

    private final AdminService adminService;
    private final SchoolService schoolService;

    public ManagerController(AdminService adminService, SchoolService schoolService) {
        this.adminService = adminService;
        this.schoolService = schoolService;
    }

    @GetMapping("/main/manager")
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String managerHome(Model model) {
        return "manager/manager_main";
    }

    @RequestMapping(path = "/main/manager/admins", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String getAdmins(Model model) {
        List<Admin> admins = adminService.getAdmins();
        model.addAttribute("admins", admins);
        model.addAttribute("admin", new Admin());
        return "manager/manager_admins";
    }

    @RequestMapping(path = "/main/manager/admins/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String getAdmin(Model model, @PathVariable("id") Long id) {
        Admin admin = adminService.getAdminById(id);
        model.addAttribute("admin", admin);
        return "manager/manager_admin_edit";
    }

    @RequestMapping(path = "/main/manager/admins", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView createAdmin(RedirectAttributes redirectAttributes, @ModelAttribute Admin admin) {
        adminService.saveAdmin(admin);
        final String msg = "Created admin <b>" + String.format("%s %s", admin.getName(), admin.getLastName()) + "</b> .";
        RedirectView view = new RedirectView("admins", true);
        redirectAttributes.addFlashAttribute("adminMessage", msg);
        return view;
    }

    @RequestMapping(path = "/main/manager/admins/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView updateAdmin(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Admin admin) {
        if (admin.isActive()) {
            adminService.updateAdmin(id, admin);
        } else {
            adminService.deleteAdmin(id);
        }
        String message = (admin.isActive() ? "Updated " : "Deleted ") + " admin <b>" + admin.getName() + " " + admin.getLastName() + "</b> .";
        RedirectView redirectView = new RedirectView("/main/manager/admins", true);
        redirectAttributes.addFlashAttribute("adminMessage", message);
        return redirectView;
    }

    @RequestMapping(path = "/main/manager/schools", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String getSchools(Model model) {
        List<School> schools = schoolService.getSchools();
        model.addAttribute("schools", schools);
        model.addAttribute("school", new School());
        return "manager/manager_schools";
    }

    @RequestMapping(path = "/main/manager/schools/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public String getSchool(Model model, @PathVariable("id") Long id) {
        School school = schoolService.getSchoolById(id);
        model.addAttribute("school", school);
        return "manager/manager_school_edit";
    }

    @RequestMapping(path = "/main/manager/schools", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView createSchool(RedirectAttributes redirectAttributes, @ModelAttribute School school) {
        schoolService.saveSchool(school);
        final String msg = "Created school <b>" + school.getName() + "</b> .";
        RedirectView view = new RedirectView("schools", true);
        redirectAttributes.addFlashAttribute("schoolMessage", msg);
        return view;
    }

    @RequestMapping(path = "/main/manager/schools/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL')")
    public RedirectView updateSchool(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute School school) {
        if (school.isActive()) {
            schoolService.updateSchool(id, school);
        } else {
            schoolService.deleteSchool(id);
        }
        String message = (school.isActive() ? "Updated " : "Deleted ") + " school <b>" + school.getName() + "</b> .";
        RedirectView redirectView = new RedirectView("/main/manager/schools", true);
        redirectAttributes.addFlashAttribute("schoolMessage", message);
        return redirectView;
    }

}
