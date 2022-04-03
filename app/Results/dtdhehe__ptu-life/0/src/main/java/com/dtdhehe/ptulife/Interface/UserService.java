package com.dtdhehe.ptulife.Interface;
public interface UserService {

   public String getUserNameByUserId(String userId);
   public PtuUser findByUserId(String userId);
}