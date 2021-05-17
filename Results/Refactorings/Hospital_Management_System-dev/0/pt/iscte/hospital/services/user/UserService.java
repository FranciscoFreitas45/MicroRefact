import org.springframework.security.core.GrantedAuthority;
import pt.iscte.hospital.entities.User;
import pt.iscte.hospital.security.Role;
import java.util.List;
public interface UserService {


public User findUser(String username)


public Role getUserRole(User user)


public User currentUser()


public boolean validateUser(String username,String password)


public String getUserMainPage(User user)


public User findByUsername(String username)


public void addUser(User user)


public User findByUserId(Long userId)


public List<GrantedAuthority> getAuthorities(String username)


public boolean validateUserMail(String username,String email)


}