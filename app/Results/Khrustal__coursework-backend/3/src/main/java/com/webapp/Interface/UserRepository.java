package com.webapp.Interface;
public interface UserRepository {

   public Optional<User> findByUsername(String username);
}