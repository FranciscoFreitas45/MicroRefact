package io.swagger.Request;
import io.swagger.DTO.User;
public interface UserRequest {

   public User getUser(Long id);
   public void setUser(User user,Long id);
}