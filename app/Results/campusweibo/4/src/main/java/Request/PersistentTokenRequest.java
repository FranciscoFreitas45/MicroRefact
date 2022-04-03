package Request;
public interface PersistentTokenRequest {

   public void setPersistentTokens(Set<PersistentToken> persistentTokens,Long id);
   public Set<PersistentToken> getPersistentTokens(Long id);
}