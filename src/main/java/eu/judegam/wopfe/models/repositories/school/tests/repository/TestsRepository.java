package eu.judegam.wopfe.models.repositories.school.tests.repository;

import eu.judegam.wopfe.models.school.tests.Test;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestsRepository extends PagingAndSortingRepository<Test, Long> {
    Test findByName(String name);
}
