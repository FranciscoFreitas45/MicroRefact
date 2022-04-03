package com.gp.cricket.Interface;
public interface UserService {

   public User signupUser(User user);
   public Integer updateUser(User updateUser);
   public Integer userAccountDeactivate(Integer userId);
}