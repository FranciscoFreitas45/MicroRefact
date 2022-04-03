package org.live.Request;
import org.live.DTO.MobileUser;
public interface MobileUserRequest {

   public MobileUser getUser(String idYUDA);
   public void setUser(MobileUser user,String idYUDA);
}