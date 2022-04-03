package Interface;
public interface UserSession {

   public void assertLoggedIn();
   public IUserRolePermissions getUserRolePermissions();
   public String getAD_Language();
}