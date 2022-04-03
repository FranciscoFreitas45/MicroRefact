package com.yalcin.service;
 import com.yalcin.entity.User;
import com.yalcin.security.services.UserDetailImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
public interface UserService {


public UserDetailImpl getUserDetails(Authentication authentication)
;

public User getUserByEmail(String email)
;

public User setNewPassword(User user,String password)
;

public User editUser(User user,String name,String lastName,String age,String phoneNumber)
;

public User getUserByToken(String token,String matter)
;

public User getUserWithAuthentication(Authentication authentication)
;

public boolean existsByEmail(String email)
;

public User changeEmail(User user,String email)
;

}