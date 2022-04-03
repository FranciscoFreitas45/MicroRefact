package com.gp.cricket.Request;
import com.gp.cricket.DTO.User;
public interface UserRequest {

   public User getUserId(Integer userIdv2);
   public void setUserId(User userId,Integer userIdv2);
}