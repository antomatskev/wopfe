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
        User anton = new User(
                1717L,
                "anton",
                passwordEncoder.encode("matskevich"),
                ALL,
                true,
                true,
                true,
                true
        );
        User ellina = new User(
                1718L,
                "ellina",
                passwordEncoder.encode("gedrojets"),
                ALL,
                true,
                true,
                true,
                true
        );
        User julia = new User(
                1719L,
                "julia",
                passwordEncoder.encode("djomina"),
                ALL,
                true,
                true,
                true,
                true
        );
        return Lists.newArrayList(
                anton,
                ellina,
                julia,
                new User(
                        1720L,
                        "manager",
                        passwordEncoder.encode("password"),
                        MANAGER,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                        1721L,
                        "admin",
                        passwordEncoder.encode("password"),
                        ADMIN,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                        1722L,
                        "principal",
                        passwordEncoder.encode("password"),
                        PRINCIPAL,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                        1723L,
                        "teacher",
                        passwordEncoder.encode("password"),
                        TEACHER,
                        true,
                        true,
                        true,
                        true
                ),
                new User(
                        1724L,
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
