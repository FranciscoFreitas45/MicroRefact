package com.ushahidi.swiftriver.core.Interface;
public interface AccountDao {

   public Account findByUsernameOrEmail(String username);
   public Object findById(Object Object);
   public Object update(Object Object);
}