package com.evolvingreality.onleave.repository;
 import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import com.evolvingreality.onleave.model.User;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long>{


public Optional<User> findOneByActivationKey(String activationKey)
;

public List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime)
;

public List<User> findAllByGroupMembers_SecurityGroupGroupNameIn(Collection<String> groupNames)
;

public Optional<User> findOneByEmail(String email)
;

@Override
public void delete(User t)
;

public Optional<User> findOneByResetKey(String resetKey)
;

public User getUser(Long id);

public void setUser(Long id,User user);

public User getUser(Long id);

public void setUser(Long id,User user);

public User getUser(Long id);

public void setUser(Long id,User user);

}