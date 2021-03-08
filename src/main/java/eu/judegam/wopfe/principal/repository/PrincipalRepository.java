package eu.judegam.wopfe.principal.repository;

import eu.judegam.wopfe.models.user.Principal;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Principal repository interface.
 */
public interface PrincipalRepository extends PagingAndSortingRepository<Principal, Long> {
    Principal findByName(String name);
}
