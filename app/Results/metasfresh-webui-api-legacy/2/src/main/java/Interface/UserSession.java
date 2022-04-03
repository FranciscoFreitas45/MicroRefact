package Interface;
public interface UserSession {

   public IUserRolePermissions getUserRolePermissions();
   public void assertLoggedIn();
   public int getHttpCacheMaxAge();
   public String getAD_Language();
   public Evaluatee toEvaluatee();
   public ZoneId getTimeZone();
   public Language getLanguage();
}