package com.yalcin.Interface;
public interface JwtProvider {

   public boolean validateJwtToken(String authToken,String matter,HttpServletRequest request);
}