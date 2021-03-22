package eu.judegam.wopfe.models.repositories.timetable;

import eu.judegam.wopfe.models.timetable.Event;
import eu.judegam.wopfe.models.timetable.Timetable;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final TimetableRepo ttRepo;
    private final EventRepo eRepo;

    public EventService(TimetableRepo ttRepo, EventRepo eRepo) {
        this.ttRepo = ttRepo;
        this.eRepo = eRepo;
    }

    public Event saveEvent(Event ev) {
        Timetable tt = ttRepo.findById(ev.getTimetableId()).get();
        ev.setTimetable(tt);
        return eRepo.save(ev);
    }

    public Event saveEvent(Event ev, Long ttId) {
        Timetable tt = ttRepo.findById(ttId).get();
        ev.setTimetableId(tt.getId());
        ev.setTimetable(tt);
        tt.addEvent(ev);
        ttRepo.save(tt);
        return eRepo.save(ev);
    }

}
