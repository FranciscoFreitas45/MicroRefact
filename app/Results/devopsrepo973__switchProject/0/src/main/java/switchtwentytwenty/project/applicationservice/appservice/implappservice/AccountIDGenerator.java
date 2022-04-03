package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountIDGenerator;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.domain.share.id.AccountID;
import java.util.UUID;
@Service
@AllArgsConstructor
public class AccountIDGenerator implements IAccountIDGenerator{

@Autowired
 private IAccountRepository accountRepository;


public AccountID generate(){
    AccountID accountID;
    do {
        accountID = new AccountID(UUID.randomUUID());
    } while (accountRepository.existsByID(accountID));
    return accountID;
}


}