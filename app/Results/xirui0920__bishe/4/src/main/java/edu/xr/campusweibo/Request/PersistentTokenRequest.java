package edu.xr.campusweibo.Request;
import edu.xr.campusweibo.DTO.PersistentToken;
public interface PersistentTokenRequest {

   public void setPersistentTokens(Set<PersistentToken> persistentTokens,Long id);
   public Set<PersistentToken> getPersistentTokens(Long id);
}