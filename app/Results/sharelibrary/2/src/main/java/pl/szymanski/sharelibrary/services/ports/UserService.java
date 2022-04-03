package pl.szymanski.sharelibrary.services.ports;
 import pl.szymanski.sharelibrary.entity.User;
import pl.szymanski.sharelibrary.requests.EditUserRequest;
import pl.szymanski.sharelibrary.requests.LoginRequest;
import pl.szymanski.sharelibrary.security.JwtAuthenticationResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;
public interface UserService {


public Optional<User> getUserByEmail(String email)
;

public User getUserByEmailOrUserName(String user)
;

public User changeUserDetails(Long id,EditUserRequest editUserRequest)
;

public User getUserById(Long id)
;

public List<User> getUsersWithBooksWhereAtUserIs(Long userId)
;

public Set<User> getUsers()
;

public User withdrawBookFromUser(Long userId,Long bookId)
;

public JwtAuthenticationResponse getJwt(LoginRequest loginRequest)
;

public User assignBookToUser(Long userId,Long bookId)
;

public User saveUser(User user)
;

}