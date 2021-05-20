package eu.judegam.wopfe.services;

import eu.judegam.wopfe.models.timetable.Event;
import eu.judegam.wopfe.models.timetable.Timetable;
import eu.judegam.wopfe.repositories.EventRepo;
import eu.judegam.wopfe.repositories.TimetableRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    private final TimetableRepo ttRepo;
    private final EventRepo eRepo;

    public EventService(TimetableRepo ttRepo, EventRepo eRepo) {
        this.ttRepo = ttRepo;
        this.eRepo = eRepo;
    }

    public String deleteEvent(Long id) {
        eRepo.deleteById(id);
        return id + " event removed.";
    }

    public Event saveEvent(Event ev, Long ttId) {
        Optional<Timetable> ttOpt = ttRepo.findById(ttId);
        if (ttOpt.isPresent()) {
            final Timetable tt = ttOpt.get();
            ev.setTimetableId(tt.getId());
            ev.setTimetable(tt);
            tt.getEvents().add(ev);
            ttRepo.save(tt);
        }
        return ev;
    }

}
