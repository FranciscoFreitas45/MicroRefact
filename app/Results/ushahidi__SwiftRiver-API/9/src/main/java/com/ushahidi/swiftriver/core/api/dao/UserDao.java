package com.ushahidi.swiftriver.core.api.dao;
 import com.ushahidi.swiftriver.core.model.User;
public interface UserDao extends GenericDao<User>{


public User findByUsernameOrEmail(String search)
;

public User findByUsername(String username)
;

public void updateLastLogin(User user)
;

}