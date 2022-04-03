package com.softserve.edu.Resources.Request;
import com.softserve.edu.Resources.DTO.User;
public interface UserRequest {

   public User getUser(Long idXLHJ);
   public VerificationToken setUser(User user,Long idXLHJ);
}