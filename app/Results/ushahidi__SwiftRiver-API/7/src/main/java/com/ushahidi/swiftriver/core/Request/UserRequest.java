package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.User;
public interface UserRequest {

   public User getOwner(long idHR3O);
   public void setOwner(User owner,long idHR3O);
}