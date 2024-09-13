package project.vbb.SpringStarter.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.vbb.SpringStarter.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    // Define the method to find an Account by email, ignoring case sensitivity
    Optional<Account> findOneByEmailIgnoreCase(String email);
}
