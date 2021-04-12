package eu.judegam.wopfe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .authorizeRequests()
                // NB! Order of matchers is important!!!
                .antMatchers("/", "/resources/**", "/css/*", "/js/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/main", true)
                .passwordParameter("password")
                .usernameParameter("username")
                .and()
                .rememberMe() // Defaults session id expiration to 2 weeks.
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) // Extend for 21 days.
                .key("123somethingverysecured!")
                .rememberMeParameter("remember-me")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // Remove this after enabling csrf. LOGOUT MUST BE POST!
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONIND", "remember-me")
                .logoutSuccessUrl("/login");
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
