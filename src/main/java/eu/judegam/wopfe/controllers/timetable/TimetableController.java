package eu.judegam.wopfe.controllers.timetable;

import eu.judegam.wopfe.models.timetable.Event;
import eu.judegam.wopfe.models.timetable.Timetable;
import eu.judegam.wopfe.services.TimetableService;
import eu.judegam.wopfe.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class TimetableController {

    private final TimetableService ttService;
    private static final Pattern PATTERN = Pattern.compile("(\\D*)(\\d*)");

    public TimetableController(TimetableService ttService) {
        this.ttService = ttService;
    }

    @RequestMapping(value = "/main/timetables", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_PRINCIPAL', 'ROLE_ADMIN')")
    public String getTimetables(Model model) {
        List<Timetable> tts = ttService.getAllTimetables().stream().sorted((t1, t2) -> {
            String s1 = t1.getName();
            String s2 = t2.getName();
            Matcher m1 = PATTERN.matcher(s1);
            Matcher m2 = PATTERN.matcher(s2);

            // The only way find() could fail is at the end of a string
            while (m1.find() && m2.find()) {
                // matcher.group(1) fetches any non-digits captured by the
                // first parentheses in PATTERN.
                int nonDigitCompare = m1.group(1).compareTo(m2.group(1));
                if (0 != nonDigitCompare) {
                    return nonDigitCompare;
                }

                // matcher.group(2) fetches any digits captured by the
                // second parentheses in PATTERN.
                if (m1.group(2).isEmpty()) {
                    return m2.group(2).isEmpty() ? 0 : -1;
                } else if (m2.group(2).isEmpty()) {
                    return +1;
                }

                BigInteger n1 = new BigInteger(m1.group(2));
                BigInteger n2 = new BigInteger(m2.group(2));
                int numberCompare = n1.compareTo(n2);
                if (0 != numberCompare) {
                    return numberCompare;
                }
            }

            // Handle if one string is a prefix of the other.
            // Nothing comes before something.
            return m1.hitEnd() && m2.hitEnd()
                    ? 0
                    : m1.hitEnd()
                        ? -1
                        : +1;

        }).collect(Collectors.toList());
        model.addAttribute("timetables", tts);
        model.addAttribute("timetable", new Timetable());
        model.addAttribute("event", new Event());
        return Utils.addUsrAttrToModel(model, "timetable/timetables");
    }

    @RequestMapping(path = "/main/timetables/addTimetable", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_PRINCIPAL', 'ROLE_ADMIN')")
    public RedirectView saveTimetable(RedirectAttributes redirectAttributes, @ModelAttribute Timetable tt) {
        ttService.saveTimetable(tt);
        final String msg = "Created timetable <b>" + tt.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("", true);
        redirectAttributes.addFlashAttribute("ttMessage", msg);
        return view;
    }

    @GetMapping("/main/timetables/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_PRINCIPAL', 'ROLE_ADMIN')")
    public String showTimetableById(Model model, @PathVariable("id") Long id) {
        Timetable tt = ttService.getTtById(id);
        model.addAttribute("timetable", tt);
        model.addAttribute("event", new Event());
        return Utils.addUsrAttrToModel(model, "timetable/timetable_edit");
    }

    @RequestMapping(path = "/main/timetables/{id}/update", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_PRINCIPAL', 'ROLE_ADMIN')")
    public String updateTimetable(Model model, @PathVariable("id") Long id, @ModelAttribute Timetable timetable) {
        Timetable tt = ttService.updateTimetable(id, timetable);
        model.addAttribute("timetable", tt);
        model.addAttribute("event", new Event());
        return Utils.addUsrAttrToModel(model, "timetable/timetable_edit");
    }

    @RequestMapping(path = "/main/timetables/{id}/delete", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_PRINCIPAL', 'ROLE_ADMIN')")
    public RedirectView deleteTimetable(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Timetable timetable) {
        RedirectView redirectView = new RedirectView("..", true);
        redirectAttributes.addFlashAttribute("ttMessage", ttService.deleteTimetable(id));
        return redirectView;
    }

}
