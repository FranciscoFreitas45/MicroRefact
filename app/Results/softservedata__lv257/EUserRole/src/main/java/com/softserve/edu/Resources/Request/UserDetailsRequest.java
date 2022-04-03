package com.softserve.edu.Resources.Request;
import com.softserve.edu.Resources.DTO.UserDetails;
public interface UserDetailsRequest {

   public User setUserDetails(UserDetails userDetails,Long idO4D4);
   public UserDetails getUserDetails(Long idO4D4);
}