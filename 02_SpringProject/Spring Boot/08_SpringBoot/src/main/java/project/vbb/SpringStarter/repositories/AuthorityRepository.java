package project.vbb.SpringStarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.vbb.SpringStarter.models.Authority;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
    
}
