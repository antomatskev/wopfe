package eu.judegam.wopfe.controllers.timetable;

import eu.judegam.wopfe.services.EventService;
import eu.judegam.wopfe.models.timetable.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class EventController {

    @Autowired
    private EventService service;

    @RequestMapping(path = "/main/timetables/{id}/addEvent", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_PRINCIPAL', 'ROLE_ADMIN')")
    public RedirectView saveEvent(RedirectAttributes redirectAttributes, @ModelAttribute Event event,
                                  @PathVariable("id") Long ttId) {
        service.saveEvent(event, ttId);
        final String msg = "Created event <b>" + event.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("", true);
        redirectAttributes.addFlashAttribute("evMessage", msg);
        return view;
    }

    @RequestMapping(path = "/main/timetables/{id}/deleteEvent/{eId}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ALL', 'ROLE_PRINCIPAL', 'ROLE_ADMIN')")
    public RedirectView deleteEvent(RedirectAttributes redirectAttributes, @ModelAttribute Event event,
                                  @PathVariable("id") Long ttId, @PathVariable("eId") Long eId) {
        service.deleteEvent(eId);
        final String msg = "Created event <b>" + event.getName() + "</b> ✨.";
        RedirectView view = new RedirectView("/main/timetables/{id}", true);
        redirectAttributes.addFlashAttribute("evMessage", msg);
        return view;
    }

}
