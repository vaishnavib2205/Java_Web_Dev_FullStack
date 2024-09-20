package project.vbb.SpringStarter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.vbb.SpringStarter.models.Account;
import project.vbb.SpringStarter.models.Authority;
import project.vbb.SpringStarter.repositories.AccountRepository;
import project.vbb.SpringStarter.util.constants.Roles;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Save a new account with encoded password and default role.
     *
     * @param account Account to be saved
     * @return saved Account entity
     */
    public Account save(Account account) {
        // Encode the password
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        
        // Set the default role if not already set (e.g., USER)
        if (account.getRole() == null) {
            account.setRole(Roles.USER);  // Changed to enum usage
        }

        return accountRepository.save(account);
    }

    /**
     * Load user by username (email in this case) for Spring Security authentication.
     *
     * @param email The email (username) to look for
     * @return UserDetails containing user's credentials and roles/authorities
     * @throws UsernameNotFoundException if the user is not found by email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findOneByEmailIgnoreCase(email);

        if (optionalAccount.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Account account = optionalAccount.get();

        // Convert account's authorities and roles to GrantedAuthority for Spring Security
        List<GrantedAuthority> grantedAuthorities = getAuthorities(account);

        // Return Spring Security's User with email, password, and authorities
        return new User(account.getEmail(), account.getPassword(), grantedAuthorities);
    }

    /**
     * Converts the account's role and authorities into a list of GrantedAuthority.
     *
     * @param account Account entity containing role and authorities
     * @return List of GrantedAuthority for Spring Security
     */
    private List<GrantedAuthority> getAuthorities(Account account) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // Add the role as a GrantedAuthority
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole().getRole()));

        // Add additional authorities (privileges) as GrantedAuthority
        Set<Authority> authorities = account.getAuthorities();
        grantedAuthorities.addAll(authorities.stream()
            .map(auth -> new SimpleGrantedAuthority(auth.getName()))
            .collect(Collectors.toList()));

        for(Authority _auth: account.getAuthorities()){
            grantedAuthorities.add(new SimpleGrantedAuthority(_auth.getName()));

        }

        return grantedAuthorities;
    }
}
