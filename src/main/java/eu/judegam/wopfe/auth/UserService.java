package eu.judegam.wopfe.auth;

import com.google.common.base.Objects;
import eu.judegam.wopfe.models.User;
import eu.judegam.wopfe.repositories.UserRepo;
import eu.judegam.wopfe.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(@Qualifier("fake") UserDao userDao, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.repo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("Username %s wasn't found!", username)));
    }

    public User saveUser(User user, UserRole role) {
        final String username = generateUsername(user);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(username));
        user.setUserRole(role);
        user.enableAll();
        return repo.save(user);
    }

    public User saveUser(User user) {
        return saveUser(user, UserRole.STUDENT);
    }

    public List<User> getUsersWithRole(UserRole role) {
        return getAllUsers().stream()
                .filter(u -> u.getUserRole() == role)
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return (List<User>) repo.findAll();
    }

    public Optional<User> getUserByUsername(String username) {
        final Optional<User> user = userDao.selectUserByUsername(username);
        return user.isPresent()
                ? user
                : ((List<User>) repo.findAll())
                .stream().filter(u -> Objects.equal(username, u.getUsername()))
                .findFirst();
    }

    public List<User> getAllUsersByClass(String clazz) {
        return getAllUsers().stream().filter(u -> Objects.equal(u.getClazz(),
                clazz)).collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public String deleteUser(Long id) {
        repo.deleteById(id);
        return "User was deleted.";
    }

    public User updateUser(Long id, User user) {
        User oldUser = repo.findById(id).orElse(null);
        assert oldUser != null;
        if (user.getFullName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getClazz() != null) {
            oldUser.setClazz(user.getClazz());
        }
        if (user.getUserRole() != null) {
            oldUser.setUserRole(user.getUserRole());
        }
        return repo.save(oldUser);
    }

    private String generateUsername(User user) {
        String lastName = user.getLastName();
        return user.getFirstName().toLowerCase().charAt(0) +
                (lastName.length() >= 7
                        ? lastName.toLowerCase().substring(0, 6)
                        : lastName.toLowerCase() + generateRandomNum(7 - lastName.length()))
                + generateRandomNum(3);
    }

    private String generateRandomNum(int amount) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            sb.append(new Random().nextInt(10));
        }
        return sb.toString();
    }

}
