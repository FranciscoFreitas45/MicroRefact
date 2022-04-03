package com.cym.Interface;
public interface AdminService {

   public Admin getByToken(String token);
   public Admin getByCreditKey(String creditKey);
}