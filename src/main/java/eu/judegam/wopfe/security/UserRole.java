package eu.judegam.wopfe.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    ALL(Sets.newHashSet(UserPermission.ALL)),
    MANAGER(Sets.newHashSet(UserPermission.MANAGER)),
    ADMIN(Sets.newHashSet(UserPermission.ADMIN)),
    PRINCIPAL(Sets.newHashSet(UserPermission.PRINCIPAL)),
    TEACHER(Sets.newHashSet(UserPermission.TEACHER)),
    STUDENT(Sets.newHashSet(UserPermission.STUDENT));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthority() {
        Set<GrantedAuthority> ret = getPermissions().stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
        ret.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return ret;
    }

    public String getName() {
        return name();
    }

}
