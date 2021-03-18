package eu.judegam.wopfe.models.repositories.school.tests.repository;

import eu.judegam.wopfe.models.school.tests.Test;
import eu.judegam.wopfe.models.tests.Question;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
