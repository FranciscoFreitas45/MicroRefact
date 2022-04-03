package switchtwentytwenty.project.Interface;
public interface AuthorizationService {

   public boolean accessFamilyCashAccountAuthorization(String role);
   public Email getPersonIDOfUser(String username);
}