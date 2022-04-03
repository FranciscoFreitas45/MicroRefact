package com.tech.Interface;
public interface IUserService {

   public boolean checkUsername(String username);
   public User getUserByUsername(String username);
   public User getUserById(Long id);
}