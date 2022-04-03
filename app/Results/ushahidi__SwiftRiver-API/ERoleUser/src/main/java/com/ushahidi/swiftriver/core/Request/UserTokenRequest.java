package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.UserToken;
public interface UserTokenRequest {

   public Set<UserToken> getTokens(long id);
   public void setTokens(Set<UserToken> tokens,long id);
}