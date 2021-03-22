package eu.judegam.wopfe.models.repositories.timetable;

import eu.judegam.wopfe.models.timetable.Timetable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TimetableService {

    private final TimetableRepo ttRepo;
    private final EventRepo eRepo;

    public TimetableService(TimetableRepo ttRepo, EventRepo eRepo) {
        this.ttRepo = ttRepo;
        this.eRepo = eRepo;
    }

    public List<Timetable> getAllTimetables() {
        return (List<Timetable>) ttRepo.findAll();
    }

    public Timetable saveTimetable(Timetable tt) {
        return ttRepo.save(tt);
    }

    public Timetable getTtById(Long id) {
        return ttRepo.findById(id).get();
    }

}
