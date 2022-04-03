package com.csquard.mregister.Interface;
public interface UserRepository {

   public Boolean existsByUsername(String username);
   public Boolean existsByEmail(String email);
   public Object save(Object Object);
}