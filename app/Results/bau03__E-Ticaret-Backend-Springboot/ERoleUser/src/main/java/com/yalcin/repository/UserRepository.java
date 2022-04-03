package com.yalcin.repository;
 import com.yalcin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{


public Optional<User> findByUsername(String username)
;

public Boolean existsByUsername(String username)
;

public Boolean existsByEmail(String email)
;

public Optional<User> findByEmail(String email)
;

public User getUser(Integer id);

public void setUser(Integer id,User user);

public User getUser(Integer id);

public void setUser(Integer id,User user);

public User getUser(Integer id);

public void setUser(Integer id,User user);

public User getUser(Integer id);

public void setUser(Integer id,User user);

public User getUser(Integer id);

public void setUser(Integer id,User user);

public User getUser(Integer id);

public void setUser(Integer id,User user);

public User getUser(Integer id);

public void setUser(Integer id,User user);

public User getUser(Integer id);

public void setUser(Integer id,User user);

public void setName(Integer id,String name);

public void setLastname(Integer id,String lastname);

public void setAge(Integer id,String age);

public void setEmail(Integer id,String email);

public void setEnabled(Integer id,boolean enabled);

public void setRoles(Integer id,Set<Role> roles);

public void setActiveSessions(Integer id,Set<ActiveSessions> activeSessions);

public boolean isEnabled(Integer id);

public void addActiveSession(Integer id,ActiveSessions activeSession);

}