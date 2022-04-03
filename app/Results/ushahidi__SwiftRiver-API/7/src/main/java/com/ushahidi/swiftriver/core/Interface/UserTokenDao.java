package com.ushahidi.swiftriver.core.Interface;
public interface UserTokenDao {

   public UserToken findByToken(String token);
   public Object create(Object Object);
   public void delete(String token);
}