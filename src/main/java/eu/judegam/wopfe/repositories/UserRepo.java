package eu.judegam.wopfe.repositories;

import eu.judegam.wopfe.models.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {

    User findByName(String name);

    @Override
    User save(@Param("user") User user);

    @Override
    void deleteById(@Param("id") Long id);

    @Override
    void delete(@Param("user") User user);
}
