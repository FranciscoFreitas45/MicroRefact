package com.softserve.edu.Resources.Request;
import com.softserve.edu.Resources.DTO.User;
public interface UserRequest {

   public ResourceRequest setResourcesAdmin(User resourcesAdmin,Long idSND5);
   public ResourceRequest setRegister(User register,Long idSND5);
   public User getRegister(Long idSND5);
   public User getResourcesAdmin(Long idSND5);
}