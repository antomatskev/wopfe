package eu.judegam.wopfe.repositories;

import eu.judegam.wopfe.models.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {

}
