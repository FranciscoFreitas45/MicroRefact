package switchtwentytwenty.project.Interface;
public interface IPersonRepository {

   public Person findByID(Email id);
   public Person findByAccountID(AccountID accountID);
}