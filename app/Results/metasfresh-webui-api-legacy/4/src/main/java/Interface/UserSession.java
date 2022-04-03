package Interface;
public interface UserSession {

   public IUserRolePermissions getCurrentPermissions();
   public Evaluatee toEvaluatee();
   public void assertLoggedIn();
   public IUserRolePermissions getUserRolePermissions();
   public int getHttpCacheMaxAge();
   public String getAD_Language();
   public UserRolePermissionsKey getUserRolePermissionsKey();
   public Object hasPermission(Object Object);
}