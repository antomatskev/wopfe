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

    public User saveUser(User admin) {
        return repo.save(admin);
    }

    public List<User> saveAdmins(List<User> admins) {
        return (List<User>) repo.saveAll(admins);
    }

    public List<User> getUsers(UserRole role) {
        return ((List<User>) repo.findAll()).stream()
                .filter(u -> u.getUserRole() == role)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public User getAdminByName(String name) {
        // TODO: here will be first and last names in the play.
        return repo.findByName(name);
    }

    public String deleteUser(Long id) {
        repo.deleteById(id);
        return "User was deleted.";
    }

    public User updateUser(Long id, User admin) {
        User existingProduct = repo.findById(id).orElse(null);
        assert existingProduct != null;
        existingProduct.setName(admin.getName());
        existingProduct.setLastName(admin.getLastName());
        return repo.save(existingProduct);
    }

}
