package com.softserve.edu.Resources.Interface;
public interface UserService {

   public VerificationToken createVerificationTokenForUser(User user,String token);
}