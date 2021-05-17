import cn.com.cnc.fcc.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.Instant;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

 private String USERS_BY_LOGIN_CACHE;

 private String USERS_BY_EMAIL_CACHE;


public Optional<User> findOneByActivationKey(String activationKey)


@EntityGraph(attributePaths = "authorities")
@Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
public Optional<User> findOneWithAuthoritiesByLogin(String login)


public Optional<User> findOneByLogin(String login)


@EntityGraph(attributePaths = "authorities")
public Optional<User> findOneWithAuthoritiesById(Long id)


public List<User> findAllByActivatedIsFalseAndCreatedDateBefore(Instant dateTime)


@EntityGraph(attributePaths = "authorities")
@Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
public Optional<User> findOneWithAuthoritiesByEmail(String email)


public Page<User> findAllByLoginNot(Pageable pageable,String login)


public Optional<User> findOneByEmailIgnoreCase(String email)


public Optional<User> findOneByResetKey(String resetKey)


}