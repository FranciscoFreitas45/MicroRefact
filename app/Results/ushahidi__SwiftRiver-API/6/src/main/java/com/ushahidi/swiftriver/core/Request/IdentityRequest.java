package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Identity;
public interface IdentityRequest {

   public void setIdentity(Identity identity,long idYKYP);
   public Identity getIdentity(long idYKYP);
}