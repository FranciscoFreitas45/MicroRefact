package com.yalcin.Interface;
public interface UserRepository {

   public Optional<User> findByUsername(String username);
   public Optional<User> findByEmail(String email);
}