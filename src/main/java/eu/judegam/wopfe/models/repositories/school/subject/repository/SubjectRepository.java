package eu.judegam.wopfe.models.repositories.school.subject.repository;

import eu.judegam.wopfe.models.school.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Subject repository interface.
 */
public interface  SubjectRepository extends PagingAndSortingRepository<Subject, Long> {
    Subject findByName(String name);
}
