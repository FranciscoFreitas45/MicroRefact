package com.example.steam.Interface;
public interface UserService {

   public User findByEmail(String email);
   public int updateCommnetNum(String email);
}