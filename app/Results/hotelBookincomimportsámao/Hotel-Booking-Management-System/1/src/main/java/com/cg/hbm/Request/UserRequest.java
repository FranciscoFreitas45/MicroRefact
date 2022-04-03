package com.cg.hbm.Request;
import com.cg.hbm.DTO.User;
public interface UserRequest {

   public User getUser(int user_id);
   public void setUser(User user,int user_id);
}