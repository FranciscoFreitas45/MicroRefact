package com.csquard.mregister.Interface;
public interface JwtTokenProvider {

   public String generateToken(Authentication authentication);
}