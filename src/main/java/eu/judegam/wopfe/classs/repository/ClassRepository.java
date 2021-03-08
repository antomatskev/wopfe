package eu.judegam.wopfe.classs.repository;

import eu.judegam.wopfe.models.school.Class;
import org.springframework.data.repository.PagingAndSortingRepository;

/***
 * Class repository interface.
 */
public interface ClassRepository extends PagingAndSortingRepository<Class, Long> {
    Class findByName(String name);
}
