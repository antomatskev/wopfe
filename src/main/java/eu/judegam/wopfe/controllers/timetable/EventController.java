package eu.judegam.wopfe.controllers.timetable;

import eu.judegam.wopfe.models.repositories.timetable.EventService;
import eu.judegam.wopfe.models.timetable.Event;
import eu.judegam.wopfe.models.timetable.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class EventController {

    @Autowired
    private EventService service;

    @RequestMapping(path = "/main/teacher/addEvent", method = RequestMethod.POST)
    public RedirectView saveEvent(RedirectAttributes redirectAttributes, @ModelAttribute Event event,
                                  @RequestParam(name = "ttName") String ttName) {
        service.saveEvent(event, ttName);
        final String msg = "Created event <b>" + event.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("timetables", true);
        redirectAttributes.addFlashAttribute("evMessage", msg);
        return view;
    }

}
