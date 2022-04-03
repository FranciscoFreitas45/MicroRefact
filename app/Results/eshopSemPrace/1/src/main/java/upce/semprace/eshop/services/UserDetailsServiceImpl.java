package upce.semprace.eshop.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upce.semprace.eshop.DTO.Uzivatel;
import upce.semprace.eshop.Interface.UzivatelRepository;
import upce.semprace.eshop.security.services.UserPrinciple;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UzivatelRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Uzivatel user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        return UserPrinciple.build(user);
    }
}