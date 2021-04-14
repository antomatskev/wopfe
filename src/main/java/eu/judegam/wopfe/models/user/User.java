package eu.judegam.wopfe.models.user;

import eu.judegam.wopfe.models.school.Class;
import eu.judegam.wopfe.security.AppUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //    private Set<? extends GrantedAuthority> grantedAuthorities;
    private AppUserRole appUserRole;
    private String password;
    private String username;
    private String name;
    private String lastName;
    private List<Class> classes;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public User() {
    }

    public User(String username,
                String password,
//                   Set<? extends GrantedAuthority> grantedAuthorities,
                AppUserRole appUserRole,
                boolean isAccountNonExpired, boolean isAccountNonLocked,
                boolean isCredentialsNonExpired, boolean isEnabled) {
//        this.grantedAuthorities = grantedAuthorities;
        this.appUserRole = appUserRole;
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        classes = new ArrayList<>();
    }

    public User(AppUserRole appUserRole, String password, String username,
                String name, String lastName, List<Class> classes,
                boolean isAccountNonExpired, boolean isAccountNonLocked,
                boolean isCredentialsNonExpired, boolean isEnabled) {
        this.appUserRole = appUserRole;
        this.password = password;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.classes = classes;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return grantedAuthorities;
        return appUserRole.getGrantedAuthority();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public AppUserRole getRole() {
        return appUserRole;
    }

    public void setRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }
}
