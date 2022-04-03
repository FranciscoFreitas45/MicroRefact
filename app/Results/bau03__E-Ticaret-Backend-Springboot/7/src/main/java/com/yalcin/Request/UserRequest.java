package com.yalcin.Request;
import com.yalcin.DTO.User;
public interface UserRequest {

   public User getUser(Integer id);
   public void setUser(User user,Integer id);
}