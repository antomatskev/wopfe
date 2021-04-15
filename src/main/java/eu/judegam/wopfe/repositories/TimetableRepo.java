package eu.judegam.wopfe.repositories;

import eu.judegam.wopfe.models.timetable.Timetable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TimetableRepo extends PagingAndSortingRepository<Timetable, Long> {

    Timetable findByName(String name);

}
