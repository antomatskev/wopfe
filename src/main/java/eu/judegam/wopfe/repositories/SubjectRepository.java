package eu.judegam.wopfe.repositories;

import eu.judegam.wopfe.models.school.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Subject repository interface.
 */
public interface  SubjectRepository extends PagingAndSortingRepository<Subject, Long> {
    Subject findByName(String name);
}
