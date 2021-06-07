import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.pz.restaurantServer.entity.User;
import pl.edu.wat.wcy.pz.restaurantServer.repository.UserRepository;
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

 private  UserRepository userRepository;


@Override
public UserDetails loadUserByUsername(String s){
    User user = userRepository.findByMail(s).orElseThrow(() -> new UsernameNotFoundException("User with mail " + s + " not found."));
    return UserPrinciple.build(user);
}


}