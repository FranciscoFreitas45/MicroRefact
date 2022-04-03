package com.csquard.mregister.Interface;
public interface UserRepository {

   public Optional<User> findByUsernameOrEmail(String username,String email);
   public Object findById(Object Object);
}