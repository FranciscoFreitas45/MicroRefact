package com.gp.cricket.config.security;
 import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.gp.cricket.repository.UserRepository;
import com.gp.cricket.config.security.JwtUserDetails;
import com.gp.cricket.entity.User;
@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService{

@Autowired
 private UserRepository userRepository;

 static  List<JwtUserDetails> inMemoryUserList;

 static  int num;


public int setUserInMemory(){
    inMemoryUserList.clear();
    List<User> userList = new ArrayList<User>();
    userList = userRepository.getActiveUsers();
    for (User user : userList) {
        inMemoryUserList.add(new JwtUserDetails(user.getUserId().longValue(), user.getEmail(), user.getPassword(), user.getRole().toString()));
    }
    num++;
    return 1;
}


public void addNewUserInMemory(User user){
    inMemoryUserList.add(new JwtUserDetails(user.getUserId().longValue(), user.getEmail(), user.getPassword(), user.getRole().toString()));
}


@Override
public UserDetails loadUserByUsername(String username){
    if (num == 0) {
        setUserInMemory();
    }
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    if (!findFirst.isPresent()) {
        throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }
    return findFirst.get();
}


public void updateUserInMemory(User oldUser,User updatedUser){
    JwtUserDetails oldUserData = new JwtUserDetails(oldUser.getUserId().longValue(), oldUser.getEmail(), oldUser.getPassword(), oldUser.getRole().toString());
    for (JwtUserDetails userData : inMemoryUserList) {
        if (userData.equals(oldUserData)) {
            JwtUserDetails updateUserData = new JwtUserDetails(updatedUser.getUserId().longValue(), updatedUser.getEmail(), updatedUser.getPassword(), updatedUser.getRole().toString());
            userData = updateUserData;
            break;
        }
    }
}


}