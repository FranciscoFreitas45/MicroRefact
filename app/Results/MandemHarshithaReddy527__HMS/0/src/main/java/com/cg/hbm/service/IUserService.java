package com.cg.hbm.service;
 import java.util.List;
import com.cg.hbm.entites.User;
import com.cg.hbm.exceptions.UserNotFoundException;
public interface IUserService {


public User removeUser(int user_id)
;

public User addUser(User user)
;

public User showUser(int user_id)
;

public User updateUser(int userId,User user)
;

public List<User> showAllUsers()
;

}