package Interface;
public interface UserSession {

   public boolean isLoggedInAs(UserId userId);
   public void assertLoggedIn();
   public UserId getLoggedUserId();
   public String getUserFullname();
   public String getUserEmail();
   public String getAD_Language();
   public ClientId getClientId();
}