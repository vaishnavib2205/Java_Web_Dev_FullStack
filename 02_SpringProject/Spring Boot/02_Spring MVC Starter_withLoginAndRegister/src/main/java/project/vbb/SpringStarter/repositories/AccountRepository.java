package project.vbb.SpringStarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.vbb.SpringStarter.models.Account;



@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    
}