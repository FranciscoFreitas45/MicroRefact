package com.gp.cricket.Interface;
public interface UserRepository {

   public Object existsById(Object Object);
   public User findByUserId(Integer userId);
   public Object save(Object Object);
}