package switchtwentytwenty.project.Interface;
public interface IAuthorizationService {

   public boolean accessAccountAuthorization(String username,String accountId);
   public Email getPersonIDOfUser(String username);
}