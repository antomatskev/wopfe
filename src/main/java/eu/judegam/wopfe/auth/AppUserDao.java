package eu.judegam.wopfe.auth;

import eu.judegam.wopfe.models.user.User;

import java.util.Optional;

public interface AppUserDao {

    Optional<User> selectAppUserByUsername(String username);

}
