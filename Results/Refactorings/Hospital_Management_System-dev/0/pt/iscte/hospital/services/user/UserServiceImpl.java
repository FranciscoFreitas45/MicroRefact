import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pt.iscte.hospital.entities;
import pt.iscte.hospital.repositories.user.UserRepository;
import pt.iscte.hospital.security.IAuthenticationFacade;
import pt.iscte.hospital.security.Role;
import java.util.List;
@Service
public class UserServiceImpl implements pt.iscte.hospital.services.user.UserService,UserService{

@Autowired
 private  IAuthenticationFacade authenticationFacade;

@Autowired
 private  UserRepository userRepository;


@Override
public User findUser(String username){
    return userRepository.findByUsername(username);
}


@Override
public Role getUserRole(User user){
    Role role = Enum.valueOf(Role.class, user.getAuthorities().get(0).getAuthority());
    return role;
}


@Override
public User currentUser(){
    String username = authenticationFacade.getAuthentication().getName();
    User user = userRepository.findByUsername(username);
    return user;
}


@Override
public boolean validateUser(String username,String password){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    User userLogged = userRepository.findByUsername(username);
    if (userLogged != null) {
        if (encoder.matches(password, userLogged.getPassword())) {
            return true;
        }
    }
    return false;
}


@Override
public String getUserMainPage(User user){
    if (user == null) {
        return "public/main";
    }
    Role role = getUserRole(user);
    return role.getMainPage();
}


@Override
public User findByUsername(String username){
    return userRepository.findByUsername(username);
}


@Override
public void addUser(User user){
    userRepository.save(user);
}


@Override
public User findByUserId(Long userId){
    return userRepository.findByUserId(userId);
}


@Override
public List<GrantedAuthority> getAuthorities(String username){
    User user = userRepository.findByUsername(username);
    return user.getAuthorities();
}


@Override
public boolean validateUserMail(String username,String email){
    User user = userRepository.findByUsername(username);
    if (user != null) {
        if (username.equals(user.getUsername()) && email.equals(user.getEmail())) {
            return true;
        }
    }
    return false;
}


}