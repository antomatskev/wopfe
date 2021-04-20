package eu.judegam.wopfe.auth;

import com.google.common.collect.Lists;
import eu.judegam.wopfe.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static eu.judegam.wopfe.security.UserRole.*;

@Repository("fake")
public class FakeUserServiceDao implements UserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeUserServiceDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return getAppUsers().stream().filter(u -> username.equals(u.getUsername())).findFirst();
    }

    private List<User> getAppUsers() {
        return Lists.newArrayList(
                new User(
                    "anton",
                        passwordEncoder.encode("matskevich"),
                        ALL,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                    "ellina",
                        passwordEncoder.encode("gedrojets"),
                        ALL,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                    "julia",
                        passwordEncoder.encode("djomina"),
                        ALL,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                    "manager",
                        passwordEncoder.encode("password"),
                        MANAGER,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                    "admin",
                        passwordEncoder.encode("password"),
                        ADMIN,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                    "principal",
                        passwordEncoder.encode("password"),
                        PRINCIPAL,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                    "teacher",
                        passwordEncoder.encode("password"),
                        TEACHER,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                    "student",
                        passwordEncoder.encode("password"),
                        STUDENT,
                        true,
                        true,
                        true,
                        true
                )
        );
    }

}
