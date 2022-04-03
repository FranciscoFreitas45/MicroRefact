package sn.Interface;
public interface AccountService {

   public Person findCurrentUser();
   public PersonResponse getPersonResponse(Person person);
}