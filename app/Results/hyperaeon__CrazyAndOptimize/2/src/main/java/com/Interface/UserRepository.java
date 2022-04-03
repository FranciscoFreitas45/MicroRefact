package com.Interface;
public interface UserRepository {

   public User getByLoginName(String loginName);
   public User save(User user);
}