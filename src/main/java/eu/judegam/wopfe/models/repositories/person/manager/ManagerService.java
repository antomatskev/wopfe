package eu.judegam.wopfe.models.repositories.person.manager;

import eu.judegam.wopfe.models.user.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ManagerService {// implements UserDetailsService {

//    private final ManagerRepo repository;
//
//    @Autowired
//    public ManagerService(ManagerRepo repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        Manager manager = this.repository.findByName(name);
//        return new User(manager.getName(), manager.getPassword(),
//                AuthorityUtils.createAuthorityList(manager.getRoles()));
//    }

}
