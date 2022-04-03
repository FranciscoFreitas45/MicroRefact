package com.evolvingreality.onleave.service;
 import java.util.Optional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.web.rest.dto.UserDTO;
public interface UserService extends EntityService<User>{


public Optional<User> activateRegistration(String key)
;

public void updateUserInformation(String firstName,String lastName,String email,String langKey)
;

public User getUserWithAuthorities()
;

public User create(UserDTO userDto)
;

public void removeNotActivatedUsers()
;

public Optional<User> completePasswordReset(String newPassword,String key)
;

public Optional<User> requestPasswordReset(String mail)
;

public void changePassword(String password)
;

}