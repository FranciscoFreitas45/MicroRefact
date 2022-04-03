package guru.springframework.services;
 import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import guru.springframework.domain.Role;
import guru.springframework.domain.User;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService{

@Autowired
 private UserRepository userRepository;

@Autowired
 private RoleRepository roleRepository;

@Autowired
 private BCryptPasswordEncoder bCryptPasswordEncoder;


@Override
public User findUserByEmail(String email){
    return userRepository.findByEmail(email);
}


@Override
public void saveUser(User user){
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(1);
    Role userRole = roleRepository.findByRole("ADMIN");
    user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    userRepository.save(user);
}


}