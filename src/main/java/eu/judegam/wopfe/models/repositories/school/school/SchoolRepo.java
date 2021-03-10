package eu.judegam.wopfe.models.repositories.school.school;

import eu.judegam.wopfe.models.school.School;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SchoolRepo extends PagingAndSortingRepository<School, Long> {
    School findByName(String name);
}

