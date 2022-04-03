package com.sprint2.controller;
 import com.sprint2.model.User;
public interface IUserController {


public String logout(User user)
;

public User insertUser(User user)
;

public boolean deleteUser(String userName)
;

public String login(User user)
;

}