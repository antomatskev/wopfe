package eu.judegam.wopfe.models.repositories.person;

import eu.judegam.wopfe.models.user.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_MANAGER')")
public interface AdminRepo extends PagingAndSortingRepository<Admin, Long> {
    @Override
    @PreAuthorize("#admin?.manager == null or #admin?.manager?.name == authentication?.name")
    Admin save(@Param("admin") Admin admin);

    @Override
    @PreAuthorize("@adminRepo.findById(#id)?.manager?.name == authentication?.name")
    void deleteById(@Param("id") Long id);

    @Override
    @PreAuthorize("#admin?.manager?.name == authentication?.name")
    void delete(@Param("admin") Admin admin);
}
