package com.softserve.edu.Resources.Request;
import com.softserve.edu.Resources.DTO.User;
public interface UserRequest {

   public User getUser(Long idO2KS);
   public void setUser(User user,Long idO2KS);
}