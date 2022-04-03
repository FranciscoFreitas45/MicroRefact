package switchtwentytwenty.project.interfaceadaptor.repository;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.datamodel.AccountJPA;
import switchtwentytwenty.project.datamodel.assembler.AccountDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.dto.todomaindto.AccountJpaToDomainDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.AccountJPARepository;
import java.util.Optional;
@Repository
public class AccountRepository implements IAccountRepository{

@Autowired
 private AccountJPARepository repository;

@Autowired
 private AccountDomainDataAssembler assembler;


@Override
public boolean existsByID(AccountID id){
    return this.repository.existsById(id.toString());
}


@Override
public Account findByID(AccountID id){
    Optional<AccountJPA> accountJPA = this.repository.findById(id.toString());
    if (!accountJPA.isPresent()) {
        throw new ElementNotFoundException("Account not found");
    }
    AccountJpaToDomainDTO accountJpaToDomainDTO = assembler.toDomain(accountJPA.get());
    return AccountFactory.createAccount(accountJpaToDomainDTO);
}


@Primary
@Override
public void save(Account account){
    AccountJPA accountJPA = assembler.toData(account);
    this.repository.save(accountJPA);
}


}