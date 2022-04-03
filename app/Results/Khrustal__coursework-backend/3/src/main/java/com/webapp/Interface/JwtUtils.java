package com.webapp.Interface;
public interface JwtUtils {

   public boolean validateJwtToken(String authToken);
   public String getUserNameFromJwtToken(String token);
}