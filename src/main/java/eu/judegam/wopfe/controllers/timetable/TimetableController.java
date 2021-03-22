package eu.judegam.wopfe.controllers.timetable;

import eu.judegam.wopfe.models.repositories.timetable.EventService;
import eu.judegam.wopfe.models.repositories.timetable.TimetableService;
import eu.judegam.wopfe.models.timetable.Event;
import eu.judegam.wopfe.models.timetable.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TimetableController {

    @Autowired
    private TimetableService ttService;
    @Autowired
    private EventService evService;

    @RequestMapping(value = "/main/timetables", method = RequestMethod.GET)
    public String getTimetables(Model model) {
        List<Timetable> tts = ttService.getAllTimetables();
        model.addAttribute("timetables", tts);
        model.addAttribute("timetable", new Timetable());
        model.addAttribute("event", new Event());
        return "timetable/timetables";
    }

    @RequestMapping(path = "/main/timetables/addTimetable", method = RequestMethod.POST)
    public RedirectView saveTimetable(RedirectAttributes redirectAttributes, @ModelAttribute Timetable tt) {
        ttService.saveTimetable(tt);
        final String msg = "Created timetable <b>" + tt.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("", true);
        redirectAttributes.addFlashAttribute("ttMessage", msg);
        return view;
    }

    @GetMapping("/main/timetables/{id}")
    public String showTimetableById(Model model, @PathVariable("id") Long id) {
        Timetable tt = ttService.getTtById(id);
        model.addAttribute("timetable", tt);
        model.addAttribute("event", new Event());
        return "timetable/timetable_edit";
    }

    @RequestMapping(path = "/main/timetables/{id}/update", method = RequestMethod.POST)
    public String updateTimetable(Model model, @PathVariable("id") Long id, @ModelAttribute Timetable timetable) {
        ttService.updateTimetable(id, timetable);
        model.addAttribute("timetable", timetable);
        return "timetable/timetable_edit";
    }

    @RequestMapping(path = "/main/timetables/{id}/delete", method = RequestMethod.POST)
    public RedirectView deleteTimetable(RedirectAttributes redirectAttributes, @PathVariable("id") Long id, @ModelAttribute Timetable timetable) {
        RedirectView redirectView = new RedirectView("..", true);
        redirectAttributes.addFlashAttribute("ttMessage", ttService.deleteTimetable(id));
        return redirectView;
//        return "redirect:timetable/timetables";
    }

}
