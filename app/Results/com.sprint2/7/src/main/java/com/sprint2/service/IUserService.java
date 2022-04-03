package com.sprint2.service;
 import com.sprint2.model.User;
public interface IUserService {


public boolean deleteAdminbyName(String userName)
;

public String logout(User user)
;

public User insertUser(User user)
;

public String login(User user)
;

}