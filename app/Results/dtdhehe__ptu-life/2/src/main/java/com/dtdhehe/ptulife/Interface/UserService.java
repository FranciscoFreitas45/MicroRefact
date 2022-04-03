package com.dtdhehe.ptulife.Interface;
public interface UserService {

   public PtuUser findOne(HttpServletRequest request);
   public PtuUser findByUserId(String userId);
}