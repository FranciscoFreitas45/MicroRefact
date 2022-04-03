package run.halo.app.repository;
 import java.util.Optional;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.User;
import run.halo.app.repository.base.BaseRepository;
public interface UserRepository extends BaseRepository<User, Integer>{


@NonNull
public Optional<User> findByUsername(String username)
;

@NonNull
public Optional<User> findByEmail(String email)
;

}