package com.cg.oms.Request;
import com.cg.oms.DTO.User;
public interface UserRequest {

   public User getUser(Long userId);
   public void setUser(User user,Long userId);
}