package eu.judegam.wopfe.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum AppUserRole {
    ALL(Sets.newHashSet(AppUserPermission.ALL)),
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(AppUserPermission.COURSE_READ, AppUserPermission.COURSE_WRITE, AppUserPermission.STUDENT_READ,
            AppUserPermission.STUDENT_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(AppUserPermission.COURSE_READ, AppUserPermission.STUDENT_READ));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthority() {
        Set<GrantedAuthority> ret = getPermissions().stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
        ret.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return ret;
    }

}
