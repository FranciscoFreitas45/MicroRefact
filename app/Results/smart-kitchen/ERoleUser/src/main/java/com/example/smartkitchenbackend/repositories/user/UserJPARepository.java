package com.example.smartkitchenbackend.repositories.user;
 import com.example.smartkitchenbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserJPARepository extends JpaRepository<User, Long>{


public Optional<User> findByUsernameOrEmail(String username,String email)
;

public Boolean existsByUsername(String username)
;

public Boolean existsByEmail(String email)
;

}