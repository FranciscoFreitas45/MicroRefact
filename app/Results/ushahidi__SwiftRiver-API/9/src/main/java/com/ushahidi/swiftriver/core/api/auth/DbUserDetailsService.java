package com.ushahidi.swiftriver.core.api.auth;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import com.ushahidi.swiftriver.core.api.dao.UserDao;
import com.ushahidi.swiftriver.core.model.Role;
import com.ushahidi.swiftriver.core.model.User;
import com.ushahidi.swiftriver.core.DTO.Role;
@Transactional(readOnly = true)
public class DbUserDetailsService implements UserDetailsService{

 private  UserDao userDao;


@Transactional(readOnly = false)
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    User dbUser = userDao.findByUsernameOrEmail(username);
    if (dbUser == null)
        throw new UsernameNotFoundException("User not found");
    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    for (Role role : dbUser.getRoles()) {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
    }
    if (!authorities.isEmpty()) {
        userDao.updateLastLogin(dbUser);
    }
    return new org.springframework.security.core.userdetails.User(dbUser.getUsername(), dbUser.getPassword(), dbUser.getActive(), !dbUser.getExpired(), !dbUser.getExpired(), !dbUser.getLocked(), authorities);
}


public void setUserDao(UserDao userDao){
    this.userDao = userDao;
}


}