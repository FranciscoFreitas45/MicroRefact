package org.live.Request;
import org.live.DTO.MobileUser;
public interface MobileUserRequest {

   public MobileUser getUser(String idQ2RW);
   public void setUser(MobileUser user,String idQ2RW);
}