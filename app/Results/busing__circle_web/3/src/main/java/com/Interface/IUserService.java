package com.Interface;
public interface IUserService {

   public boolean addWeixinUser(User user);
   public User getUserByInviteCode(String inviteCode);
}