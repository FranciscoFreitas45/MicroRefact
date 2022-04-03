package com.ITBlog.Interface;
public interface UserService {

   public int getTypeByUserId(long userId);
   public int saveUser(String name,int gender,int age,String password,long phone);
   public int deleteUser(long userId);
   public int closeUserAccount(long userId);
   public int updatePassword(long userId,String password);
}