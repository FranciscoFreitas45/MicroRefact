package Interface;
public interface UserSession {

   public UserRolePermissionsKey getUserRolePermissionsKey();
   public String getAD_Language();
   public void assertLoggedIn();
   public UserId getLoggedUserId();
}