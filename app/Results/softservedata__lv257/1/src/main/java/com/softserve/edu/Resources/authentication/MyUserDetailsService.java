package com.softserve.edu.Resources.authentication;
 import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.entity.Role;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util;
import com.softserve.edu.Resources.DTO.Privilege;
@Service
public class MyUserDetailsService implements UserDetailsService{

@Autowired
 private UserService userService;

@Autowired
 private  PasswordEncoder passwordEncoder;


public List<GrantedAuthority> getGrantedAuthorities(Set<String> privileges){
    final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (final String privilege : privileges) {
        authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
}


@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
    User user = userService.getUserForSpring(email);
    // User user = getUserForSpring(email);
    if (user == null) {
        throw new UsernameNotFoundException("User " + email + " was not found in the database");
    }
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));
}


public Set<String> getPrivileges(Collection<Role> roles){
    final Set<String> privileges = new HashSet<>();
    for (Role x : roles) {
        // add all role names to privileges collection
        privileges.add(x.getName());
        // add all privileges names to privileges collection
        for (Privilege item : x.getPrivileges()) {
            privileges.add(item.getName());
        }
    }
    return privileges;
}


public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles){
    return getGrantedAuthorities(getPrivileges(roles));
}


}