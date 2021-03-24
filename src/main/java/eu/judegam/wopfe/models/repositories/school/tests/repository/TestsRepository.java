package eu.judegam.wopfe.models.repositories.school.tests.repository;

import eu.judegam.wopfe.models.tests.Test;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestsRepository extends PagingAndSortingRepository<Test, Long> {
    Test findByName(String name);
}
