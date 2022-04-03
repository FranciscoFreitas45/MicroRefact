package edu.xr.campusweibo.service.mapper;
 import edu.xr.campusweibo.domain.Authority;
import edu.xr.campusweibo.domain.User;
import edu.xr.campusweibo.service.dto.UserDTO;
import org.mapstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {


public Set<Authority> authoritiesFromStrings(Set<String> strings){
    return strings.stream().map(string -> {
        Authority auth = new Authority();
        auth.setName(string);
        return auth;
    }).collect(Collectors.toSet());
}
;

public List<User> userDTOsToUsers(List<UserDTO> userDTOs)
;

public UserDTO userToUserDTO(User user)
;

public Set<String> stringsFromAuthorities(Set<Authority> authorities){
    return authorities.stream().map(Authority::getName).collect(Collectors.toSet());
}
;

public User userFromId(Long id){
    if (id == null) {
        return null;
    }
    User user = new User();
    user.setId(id);
    return user;
}
;

public List<UserDTO> usersToUserDTOs(List<User> users)
;

@Mapping(target = "createdBy", ignore = true)
@Mapping(target = "createdDate", ignore = true)
@Mapping(target = "lastModifiedBy", ignore = true)
@Mapping(target = "lastModifiedDate", ignore = true)
@Mapping(target = "persistentTokens", ignore = true)
@Mapping(target = "activationKey", ignore = true)
@Mapping(target = "resetKey", ignore = true)
@Mapping(target = "resetDate", ignore = true)
@Mapping(target = "password", ignore = true)
public User userDTOToUser(UserDTO userDTO)
;

}