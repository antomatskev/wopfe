package eu.judegam.wopfe.models.repositories.timetable;

import eu.judegam.wopfe.models.timetable.Timetable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Timetable updateTimetable(Long id, Timetable tt) {
        Timetable existingProduct = ttRepo.findById(id).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(tt.getName());
        existingProduct.getEvents().addAll(tt.getEvents());
        return ttRepo.save(existingProduct);
    }

    public String deleteTimetable(Long id) {
        ttRepo.deleteById(id);
        return id + " timetable has been removed";
    }

}
