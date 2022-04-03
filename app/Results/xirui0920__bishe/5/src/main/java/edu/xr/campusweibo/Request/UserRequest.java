package edu.xr.campusweibo.Request;
import edu.xr.campusweibo.DTO.User;
public interface UserRequest {

   public User getUser(Long id);
   public void setUser(User user,Long id);
}