package eu.judegam.wopfe.auth;

import eu.judegam.wopfe.models.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> selectUserByUsername(String username);
}
