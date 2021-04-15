package eu.judegam.wopfe.repositories;

import eu.judegam.wopfe.models.school.School;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SchoolRepo extends PagingAndSortingRepository<School, Long> {
    School findByName(String name);
}

