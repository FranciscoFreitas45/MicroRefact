package switchtwentytwenty.project.applicationservice.irepository;
 import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.exception.AccountNotCreatedException;
import switchtwentytwenty.project.exception.ElementNotFoundException;
public interface IAccountRepository {


public boolean existsByID(AccountID id)
;

public Account findByID(AccountID id)
;

public void save(Account entity)
;

}