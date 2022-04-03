package com.tech.Interface;
public interface IUserService {

   public boolean checkUsername(String username);
   public User getUserByUsername(String username);
   public User getUserById(Long id);
   public void updateUserRoom(boolean hasRoom,Long userid);
}