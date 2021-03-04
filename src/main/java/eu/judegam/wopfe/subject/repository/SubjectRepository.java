package eu.judegam.wopfe.subject.repository;

import eu.judegam.wopfe.models.school.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubjectRepository extends PagingAndSortingRepository<Subject, Long> {
    Subject findByName(String name);
}
