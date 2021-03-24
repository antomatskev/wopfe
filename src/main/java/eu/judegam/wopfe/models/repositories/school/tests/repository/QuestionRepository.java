package eu.judegam.wopfe.models.repositories.school.tests.repository;

import eu.judegam.wopfe.models.tests.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
