package app.qienuren.controller;
 import app.qienuren.model.MyPersoonDetails;
import app.qienuren.model.Persoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class MyUserDetailsService implements UserDetailsService{

@Autowired
 private PersoonRepository persoonRepository;


@Override
public MyPersoonDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    Persoon persoon = persoonRepository.findByUserName(username).get();
    if (persoon == null) {
        throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new MyPersoonDetails(persoon);
}


public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles){
    return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
}


}