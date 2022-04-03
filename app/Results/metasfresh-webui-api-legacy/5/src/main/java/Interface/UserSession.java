package Interface;
public interface UserSession {

   public void assertLoggedIn();
   public IUserRolePermissions getUserRolePermissions();
   public Object checkWindowPermission(Object Object);
}