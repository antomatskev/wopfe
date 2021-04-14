package eu.judegam.wopfe.auth;

import eu.judegam.wopfe.models.school.Class;
import eu.judegam.wopfe.models.user.User;
import eu.judegam.wopfe.repositories.UserRepo;
import eu.judegam.wopfe.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final UserRepo repo;

    @Autowired
    public UserService(@Qualifier("fake") UserDao userDao, UserRepo userRepo) {
        this.userDao = userDao;
        this.repo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.selectUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s wasn't found!", username)));
    }

    public User saveUser(User user, UserRole role) {
        user.setUserRole(role);
        return repo.save(user);
    }

    public User saveUser(User user) {
        return saveUser(user, UserRole.STUDENT);
    }

    public List<User> getUsers(UserRole role) {
        return ((List<User>) repo.findAll()).stream()
                .filter(u -> u.getUserRole() == role)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public String deleteUser(Long id) {
        repo.deleteById(id);
        return "User was deleted.";
    }

    public User updateUser(Long id, User user) {
        User existingProduct = repo.findById(id).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(user.getName());
        existingProduct.setLastName(user.getLastName());
        return repo.save(existingProduct);
    }

}
