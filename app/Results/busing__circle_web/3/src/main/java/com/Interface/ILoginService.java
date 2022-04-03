package com.Interface;
public interface ILoginService {

   public User login(String openid);
   public boolean updateUserLastLoginTimerByWeixin(String openid);
}