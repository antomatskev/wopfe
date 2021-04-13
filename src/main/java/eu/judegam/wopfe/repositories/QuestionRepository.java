package eu.judegam.wopfe.repositories;

import eu.judegam.wopfe.models.tests.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
