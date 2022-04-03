package org.live.Interface;
public interface MobileUserRepository {

   public Object findOne(Object Object);
   public MobileUser findMobileUserByAccount(String account);
}