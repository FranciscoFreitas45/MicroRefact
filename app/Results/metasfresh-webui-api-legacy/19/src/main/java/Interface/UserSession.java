package Interface;
public interface UserSession {

   public UserId getLoggedUserId();
   public void assertLoggedIn();
   public OrgId getOrgId();
   public Object getRepoId(Object Object);
   public String getAD_Language();
}