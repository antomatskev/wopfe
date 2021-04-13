package eu.judegam.wopfe.controllers.person;

import eu.judegam.wopfe.services.SchoolService;
import eu.judegam.wopfe.models.school.School;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminController {

    private final SchoolService schoolService;

    public AdminController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping(path = "/main/admin/schools", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public String getSchools(Model model) {
        List<School> schools = schoolService.getSchools();
        model.addAttribute("schools", schools);
        model.addAttribute("school", new School());
        return "mains/admin_main";
    }

    @RequestMapping(path = "/main/admin/schools/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_ADMIN')")
    public String getSchool(Model model, @PathVariable("id") Long id) {
        School school = schoolService.getSchoolById(id);
        model.addAttribute("school", school);
        return "school/school_info";
    }

}
