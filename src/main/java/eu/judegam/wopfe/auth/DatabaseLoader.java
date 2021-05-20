package eu.judegam.wopfe.auth;

import eu.judegam.wopfe.models.User;
import eu.judegam.wopfe.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static eu.judegam.wopfe.security.UserRole.*;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseLoader(UserRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... strings) throws Exception {
        List<String> usernames = ((List<User>) repo.findAll()).stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        if (!usernames.contains("anton")) {
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
            repo.save(anton);
        }
        if (!usernames.contains("ellina")) {
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
            repo.save(ellina);
        }
        if (!usernames.contains("julia")) {
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
            repo.save(julia);
        }
        if (!usernames.contains("manager")) {
            repo.save(new User(
                    1720L,
                    "manager",
                    passwordEncoder.encode("password"),
                    MANAGER,
                    true,
                    true,
                    true,
                    true
            ));
        }
        if (!usernames.contains("admin")) {
            repo.save(new User(
                    1721L,
                    "admin",
                    passwordEncoder.encode("password"),
                    ADMIN,
                    true,
                    true,
                    true,
                    true
            ));
        }
        if (!usernames.contains("teacher")) {
            User teacher = new User(
                    1723L,
                    "teacher",
                    passwordEncoder.encode("password"),
                    TEACHER,
                    true,
                    true,
                    true,
                    true
            );
            teacher.setClazz("0a");
            repo.save(teacher);
        }
        if (!usernames.contains("student")) {
            User student = new User(
                    1724L,
                    "student",
                    passwordEncoder.encode("password"),
                    STUDENT,
                    true,
                    true,
                    true,
                    true
            );
            student.setClazz("0a");
            repo.save(student);
        }
        SecurityContextHolder.clearContext();
    }

}
