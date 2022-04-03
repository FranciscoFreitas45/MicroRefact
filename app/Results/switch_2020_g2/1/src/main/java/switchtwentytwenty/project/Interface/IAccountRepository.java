package switchtwentytwenty.project.Interface;
public interface IAccountRepository {

   public Account findByID(AccountID id);
   public void save(Account entity);
}