package com.weflors.service;
 import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.weflors.entity.UserEntity;
import com.weflors.entity.UserRoleMapEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.weflors.repository.RoleRepository;
import com.weflors.repository.UserRepository;
import com.weflors.repository.UserRoleMapRepository;
// сказать  Spring управлять им как  Spring BEAN.
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  RoleRepository roleRepository;

@Autowired
 private  UserRoleMapRepository userRoleMapRepository;

@Autowired
 private  BCryptPasswordEncoder bCryptPasswordEncoder;


public String getRuLocalizedAuthority(String authorityEN){
    String authorityRU = null;
    switch(authorityEN) {
        case "admin":
            authorityRU = "Администратор";
            break;
        case "user":
            authorityRU = "Пользователь";
            break;
    }
    return authorityRU;
}


@Override
public UserDetails loadUserByUsername(String login){
    // List<User> users = userRepository.findAll();
    UserEntity user = userRepository.findByUsername(login);
    List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
    for (Integer roleId : userRoleMapRepository.getUserRoleId(user.getUserId())) {
        grantList.add(new SimpleGrantedAuthority(roleRepository.getRoleNames(roleId)));
    }
    UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUserName() + " " + user.getUserLastname() + " (" + getRuLocalizedAuthority(grantList.get(0).getAuthority()) + ")", user.getPassword(), grantList);
    return userDetails;
}


public void deleteUser(UserEntity userEntity){
    userRepository.deleteByUserId(userEntity.getUserId());
}


public Boolean updateUser(UserEntity userEntity){
    // String password = bCryptPasswordEncoder.encode(userEntity.getPassword());
    userRepository.updateUserInformation(userEntity.geteMail(), userEntity.getUserName(), userEntity.getUserLastname(), userEntity.getLogin(), userEntity.getPhone(), userEntity.getUserId());
    for (UserRoleMapEntity role : userEntity.getUserRoleMapsByUserId()) {
        userRoleMapRepository.deleteUserRoleById(role.getUserId());
    }
    for (UserRoleMapEntity role : userEntity.getUserRoleMapsByUserId()) {
        userRoleMapRepository.saveUserRoleMap(role.getUserId(), role.getRoleId());
    }
    return true;
}


public List<UserEntity> findAllUsers(){
    return userRepository.findAll();
}


public Optional<UserEntity> findUserByLoginAndEmail(UserEntity userEntity){
    return userRepository.findUserByLoginAndEmail(userEntity.getLogin(), userEntity.geteMail());
}


public boolean saveUser(UserEntity user){
    UserEntity userFromDB = userRepository.findByUsername(user.getLogin());
    if (userFromDB != null) {
        return false;
    }
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    if (user.getUserRoleMapsByUserId() == null) {
        userRoleMapRepository.saveUserRoleMap(user.getUserId(), 2);
    } else {
        for (UserRoleMapEntity role : user.getUserRoleMapsByUserId()) {
            userRoleMapRepository.saveUserRoleMap(user.getUserId(), role.getRoleId());
        }
    }
    return true;
}


}