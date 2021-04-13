package eu.judegam.wopfe.repositories;

import eu.judegam.wopfe.models.school.Class;
import org.springframework.data.repository.PagingAndSortingRepository;

/***
 * Class repository interface.
 */
public interface ClassRepository extends PagingAndSortingRepository<Class, Long> {
    Class findByName(String name);
}
