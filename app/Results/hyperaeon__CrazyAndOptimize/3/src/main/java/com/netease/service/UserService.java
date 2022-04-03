package com.netease.service;
 import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.netease.domain.User;
import com.netease.repositories.UserRepository;
@Service
public class UserService {

@Autowired
 private  UserRepository userRepository;


public User findUserByMobileNumber(BigInteger mobileNumber){
    return userRepository.findByMobileNumber(mobileNumber);
}


public List<User> findByCreateUserAndLoginName(String createUser,String loginName){
    List<User> users = findByCreateUser(createUser);
    User user1 = getByLoginName(loginName);
    if (user1 != null) {
        if (CollectionUtils.isEmpty(users)) {
            users = new ArrayList<User>();
        }
        boolean flag = false;
        for (User u : users) {
            if (u.getLoginName().equals(user1.getLoginName())) {
                flag = true;
            }
        }
        if (!flag) {
            users.add(user1);
        }
    }
    return users;
}


public User findById(Integer id){
    return userRepository.findById(id);
}


public User save(User user){
    return userRepository.save(user);
}


public User findByName(String name){
    return userRepository.findByName(name);
}


public User getByLoginName(String loginName){
    return userRepository.getByLoginName(loginName);
}


public List<User> findByCreateUser(String createUser){
    return userRepository.findByCreateUser(createUser);
}


public List<User> findAll(){
    return userRepository.findAll();
}


public Integer updateByLoginName(Integer reportStatus,String loginName){
    return userRepository.updateByLoginName(reportStatus, loginName);
}


}