package eu.judegam.wopfe.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static eu.judegam.wopfe.security.AppUserRole.*;

@Repository("fake")
public class FakeAppUserServiceDao implements AppUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeAppUserServiceDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> selectAppUserByUsername(String username) {
        return getAppUsers().stream().filter(u -> username.equals(u.getUsername())).findFirst();
    }

    private List<AppUser> getAppUsers() {
        return Lists.newArrayList(
                new AppUser(
                    "anton",
                        passwordEncoder.encode("matskevich"),
                        ALL.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                    "ellina",
                        passwordEncoder.encode("gedrojets"),
                        ALL.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                    "julia",
                        passwordEncoder.encode("djomina"),
                        ALL.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                    "annasmith",
                        passwordEncoder.encode("password"),
                        STUDENT.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                    "linda",
                        passwordEncoder.encode("password123"),
                        ADMIN.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                    "tom",
                        passwordEncoder.encode("password123"),
                        ADMIN_TRAINEE.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                )
        );
    }

}
