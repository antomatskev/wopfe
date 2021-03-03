package eu.judegam.wopfe.tests.repository;

import eu.judegam.wopfe.models.tests.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestsRepository extends JpaRepository<Test, Integer> {
    Test findByName(String name);

}
