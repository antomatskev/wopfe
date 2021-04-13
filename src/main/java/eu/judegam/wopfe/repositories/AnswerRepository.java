package eu.judegam.wopfe.repositories;

import eu.judegam.wopfe.models.tests.Answer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long> {
}
