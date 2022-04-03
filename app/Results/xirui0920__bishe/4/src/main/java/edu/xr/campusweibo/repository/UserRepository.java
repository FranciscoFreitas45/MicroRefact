package edu.xr.campusweibo.repository;
 import edu.xr.campusweibo.domain.User;
import java.time.ZonedDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long>{


public Optional<User> findOneByActivationKey(String activationKey)
;

@EntityGraph(attributePaths = "authorities")
public Optional<User> findOneWithAuthoritiesByLogin(String login)
;

public Optional<User> findOneByLogin(String login)
;

@EntityGraph(attributePaths = "authorities")
public User findOneWithAuthoritiesById(Long id)
;

public List<User> findAllByActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime)
;

public Page<User> findAllByLoginNot(Pageable pageable,String login)
;

public Optional<User> findOneByEmail(String email)
;

public Optional<User> findOneByResetKey(String resetKey)
;

public User getUser(Long id);

public void setUser(Long id,User user);

}