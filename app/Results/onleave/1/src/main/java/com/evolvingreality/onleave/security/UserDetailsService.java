package com.evolvingreality.onleave.security;
 import com.evolvingreality.onleave.model.SecurityGroupMember;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
@Component("userDetailsService")
public class UserDetailsService {

 private  Logger log;

@Inject
 private  UserRepository userRepository;


@Override
@Transactional
public UserDetails loadUserByUsername(String email){
    log.debug("Authenticating {}", email);
    String lowercaseEmail = email.toLowerCase();
    Optional<User> userFromDatabase = userRepository.findOneByEmail(lowercaseEmail);
    return userFromDatabase.map(user -> {
        if (!user.getActivated()) {
            throw new UserNotActivatedException("User " + lowercaseEmail + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (SecurityGroupMember groupMember : user.getGroupMembers()) {
            grantedAuthorities.addAll(groupMember.getSecurityGroup().getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList()));
        }
        return new org.springframework.security.core.userdetails.User(lowercaseEmail, user.getPassword(), grantedAuthorities);
    }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseEmail + " was not found in the database"));
}


}