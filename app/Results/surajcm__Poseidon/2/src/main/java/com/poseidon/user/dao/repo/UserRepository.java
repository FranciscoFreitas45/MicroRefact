package com.poseidon.user.dao.repo;
 import com.poseidon.user.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User, Long>{


public User findByEmail(String email)
;

}