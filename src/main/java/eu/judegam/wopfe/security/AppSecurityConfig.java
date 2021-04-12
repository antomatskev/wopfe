package eu.judegam.wopfe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/resources/**", "/css/*", "/js/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/main/teacher/**").hasAuthority(AppUserPermission.STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/main/teacher/**").hasAuthority(AppUserPermission.STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/main/teacher/**").hasAuthority(AppUserPermission.STUDENT_WRITE.getPermission())
                .antMatchers("/main/teacher/**").hasAnyRole(AppUserRole.ADMIN.name(), AppUserRole.ADMIN_TRAINEE.name())
                .antMatchers(HttpMethod.GET, "/main/timetables/**").hasRole(AppUserRole.STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmith = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
//                .roles(AppUserRole.STUDENT.name())
                .authorities(AppUserRole.STUDENT.getGrantedAuthority())
                .build();
        UserDetails linda = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
//                .roles(AppUserRole.ADMIN.name())
                .authorities(AppUserRole.ADMIN.getGrantedAuthority())
                .build();
        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password123"))
//                .roles(AppUserRole.ADMIN_TRAINEE.name())
                .authorities(AppUserRole.ADMIN_TRAINEE.getGrantedAuthority())
                .build();
        return new InMemoryUserDetailsManager(
                annaSmith,
                linda,
                tom
        );
    }
}
