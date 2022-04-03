package com.yalcin.Interface;
public interface UserRepository {

   public Object findById(Object Object);
   public Object save(Object Object);
   public Boolean existsByUsername(String username);
}