package eu.judegam.wopfe.classs.repository;

import eu.judegam.wopfe.models.school.Class;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClassRepository extends PagingAndSortingRepository<Class, Long> {
    Class findByName(String name);
}
