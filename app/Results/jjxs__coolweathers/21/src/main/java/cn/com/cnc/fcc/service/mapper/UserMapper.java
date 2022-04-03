package cn.com.cnc.fcc.service.mapper;
 import cn.com.cnc.fcc.domain.Authority;
import cn.com.cnc.fcc.domain.User;
import cn.com.cnc.fcc.service.dto.UserDTO;
import org.springframework.stereotype.Service;
import java.util;
import java.util.stream.Collectors;
@Service
public class UserMapper {


public Set<Authority> authoritiesFromStrings(Set<String> strings){
    return strings.stream().map(string -> {
        Authority auth = new Authority();
        auth.setName(string);
        return auth;
    }).collect(Collectors.toSet());
}


public List<User> userDTOsToUsers(List<UserDTO> userDTOs){
    return userDTOs.stream().filter(Objects::nonNull).map(this::userDTOToUser).collect(Collectors.toList());
}


public UserDTO userToUserDTO(User user){
    return new UserDTO(user);
}


public User userFromId(Long id){
    if (id == null) {
        return null;
    }
    User user = new User();
    user.setId(id);
    return user;
}


public List<UserDTO> usersToUserDTOs(List<User> users){
    return users.stream().filter(Objects::nonNull).map(this::userToUserDTO).collect(Collectors.toList());
}


public User userDTOToUser(UserDTO userDTO){
    if (userDTO == null) {
        return null;
    } else {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setImageUrl(userDTO.getImageUrl());
        user.setActivated(userDTO.isActivated());
        user.setLangKey(userDTO.getLangKey());
        Set<Authority> authorities = this.authoritiesFromStrings(userDTO.getAuthorities());
        if (authorities != null) {
            user.setAuthorities(authorities);
        }
        return user;
    }
}


}