package com.evolvingreality.onleave.Request;
import com.evolvingreality.onleave.DTO.User;
public interface UserRequest {

   public User getUser(Long id);
   public void setUser(User user,Long id);
}