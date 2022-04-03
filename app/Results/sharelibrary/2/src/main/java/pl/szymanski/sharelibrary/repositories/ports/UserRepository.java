package pl.szymanski.sharelibrary.repositories.ports;
 import pl.szymanski.sharelibrary.entity.User;
import java.util.List;
import java.util.Optional;
public interface UserRepository {


public Optional<User> getUserByEmail(String email)
;

public Optional<User> getUserById(Long id)
;

public Optional<User> getUserByUsername(String username)
;

public List<User> getUsers()
;

public Optional<User> getUserByUsernameOrEmail(String user,String email)
;

public User saveUser(User user)
;

}