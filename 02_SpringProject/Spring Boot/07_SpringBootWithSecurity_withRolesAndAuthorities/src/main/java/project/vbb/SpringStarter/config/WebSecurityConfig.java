package project.vbb.SpringStarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import project.vbb.SpringStarter.util.constants.Privillages;
import project.vbb.SpringStarter.util.constants.Roles;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {

    private static final String[] WHITELIST = {
        "/",
        "/login",
        "/register",
        "/db-console/**",
        "/css/**",
        "/fonts/**",
        "/images/**",
        "/js/**"
    };

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(WHITELIST).permitAll()
            .requestMatchers("/profile/**").authenticated()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/editor/**").hasAnyRole("EDITOR", "ADMIN")
            .requestMatchers("/admin/**").hasAuthority(Privillages.ACCESS_ADMIN_PANEL.getPrivillage())
            )
            .formLogin(form -> form
                .loginPage("/login") // specify your custom login page if any
                .permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error").permitAll()

            )
            .logout(logout -> logout
            .logoutUrl("/logout")    
            .logoutSuccessUrl("/login")
            .permitAll()
            );

        // Disable CSRF and frame options only for H2 console access
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}
