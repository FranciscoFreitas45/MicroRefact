package com.fzshuai.Request;
import com.fzshuai.DTO.User;
public interface UserRequest {

   public User getUser(Long id);
   public void setUser(User user,Long id);
}