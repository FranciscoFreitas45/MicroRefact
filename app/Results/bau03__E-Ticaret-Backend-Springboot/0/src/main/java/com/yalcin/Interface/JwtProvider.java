package com.yalcin.Interface;
public interface JwtProvider {

   public String getSubjectFromJwt(String token,String matter);
}