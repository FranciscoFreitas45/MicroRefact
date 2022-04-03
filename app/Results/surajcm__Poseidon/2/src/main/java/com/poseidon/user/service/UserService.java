package com.poseidon.user.service;
 import com.poseidon.user.dao.UserDAO;
import com.poseidon.user.domain.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
@SuppressWarnings("unused")
public class UserService {

 private  Logger LOG;

 private  String EXCEPTION_TYPE_IN_SERVICE_IMPL;

 private  UserDAO userDAO;

 private  BCryptPasswordEncoder bcryptPasswordEncoder;

public UserService(final UserDAO userDAO, final BCryptPasswordEncoder bcryptPasswordEncoder) {
    this.userDAO = userDAO;
    this.bcryptPasswordEncoder = bcryptPasswordEncoder;
}
public Optional<UserVO> getUserDetailsFromId(Long id){
    return userDAO.getUserDetailsFromId(id);
}


public List<UserVO> getAllUserDetails(){
    return userDAO.getAllUserDetails();
}


public List<UserVO> searchUserDetails(UserVO searchUser,boolean startsWith,boolean includes){
    return userDAO.searchUserDetails(searchUser, startsWith, includes);
}


public void updateWithNewPassword(UserVO userVO,String newPass,String currentLoggedInUser){
    userVO.setPassword(bcryptPasswordEncoder.encode(newPass));
    userDAO.updateUser(userVO, currentLoggedInUser);
}


public void expireUser(Long id){
    userDAO.expireUser(id);
}


public void save(UserVO user,String currentLoggedInUser){
    user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
    user.setExpired(false);
    userDAO.save(user, currentLoggedInUser);
}


public void deleteUser(Long id){
    userDAO.deleteUser(id);
}


public void updateUser(UserVO user,String loggedInUser){
    userDAO.updateUser(user, loggedInUser);
}


public boolean comparePasswords(String passedIn,String currentUserPass){
    return bcryptPasswordEncoder.matches(passedIn, currentUserPass);
}


}