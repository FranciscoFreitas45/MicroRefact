package Interface;
public interface TokenManager {

   public String createToken(TSUser user);
   public void deleteToken(String username);
}