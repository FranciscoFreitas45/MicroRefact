package sn.Interface;
public interface AccountService {

   public Person findCurrentUser();
   public boolean exists(long personId);
}