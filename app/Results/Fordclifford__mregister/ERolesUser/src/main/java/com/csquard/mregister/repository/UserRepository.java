package com.csquard.mregister.repository;
 import com.csquard.mregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{


public List<User> findByIdIn(List<Long> userIds)
;

public Optional<User> findByUsernameOrEmail(String username,String email)
;

public Optional<User> findByUsername(String username)
;

public Boolean existsByUsername(String username)
;

public Boolean existsByEmail(String email)
;

public Optional<User> findByEmail(String email)
;

public void setPassword(Long id,String password);

public void setRoles(Long id,Set<Roles> roles);

}