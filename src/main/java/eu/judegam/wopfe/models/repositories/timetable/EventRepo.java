package eu.judegam.wopfe.models.repositories.timetable;

import eu.judegam.wopfe.models.timetable.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepo extends PagingAndSortingRepository<Event, Long> {
}
