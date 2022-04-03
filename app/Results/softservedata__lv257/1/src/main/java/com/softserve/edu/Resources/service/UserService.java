package com.softserve.edu.Resources.service;
 import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.VerificationToken;
import com.softserve.edu.Resources.exception.UserAlreadyExistException;
import java.util.List;
public interface UserService {


public List<User> getAllUsers()
;

public User getUserById(Long id)
;

public VerificationToken getVerificationToken(String verificationToken)
;

public User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException
;

public User getUser(String username)
;

public VerificationToken createVerificationTokenForUser(User user,String token)
;

public User getUserForSpring(String email)
;

public void delete(User user)
;

public void deleteVerificationToken(VerificationToken verificationToken)
;

public boolean emailExist(String email)
;

public List<User> getUsersWithRoles(String roleNames)
;

public User findByEmail(String email)
;

public void saveRegisteredUser(User user)
;

}