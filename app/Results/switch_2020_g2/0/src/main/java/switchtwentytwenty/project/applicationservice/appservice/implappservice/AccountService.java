package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountIDGenerator;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.account.CashAccount;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.AccountIDList;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.dto.outdto.AccountOutDTO;
import switchtwentytwenty.project.dto.outdto.FamilyCashAccountOutDTO;
import switchtwentytwenty.project.dto.outdto.PersonalCashAccountOutDTO;
import switchtwentytwenty.project.exception;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import switchtwentytwenty.project.Interface.IPersonRepository;
import switchtwentytwenty.project.Interface.IFamilyRepository;
import switchtwentytwenty.project.Interface.AuthorizationService;
import switchtwentytwenty.project.DTO.Family;
import switchtwentytwenty.project.DTO.IPersonRepository;
import switchtwentytwenty.project.DTO.Person;
import switchtwentytwenty.project.DTO.IPersonRepository;
import switchtwentytwenty.project.DTO.IPersonRepository;
import switchtwentytwenty.project.DTO.IPersonRepository;
import switchtwentytwenty.project.DTO.PersonList;
import switchtwentytwenty.project.DTO.IPersonRepository;
import switchtwentytwenty.project.DTO.AuthorizationService;
import switchtwentytwenty.project.DTO.IPersonRepository;
import switchtwentytwenty.project.DTO.IFamilyRepository;
import switchtwentytwenty.project.DTO.IFamilyRepository;
@Service
@AllArgsConstructor
public class AccountService implements IAccountService{

@Autowired
 private  IAccountRepository accountRepository;

@Autowired
 private  IPersonRepository personRepository;

@Autowired
 private  IFamilyRepository familyRepository;

@Autowired
 private  IAccountIDGenerator accountIDGenerator;

@Autowired
 private  AuthorizationService authorizationService;


public MoneyValue checkChildCashAccountBalance(String parentID,String childID,String accountID){
    UUID id = UUID.fromString(accountID);
    AccountID parseAccountID = new AccountID(id);
    Account account = accountRepository.findByID(parseAccountID);
    Email parentID1 = new Email(parentID);
    Email childID1 = new Email(childID);
    Person parent = personRepository.findByID(parentID1);
    FamilyID familyID = parent.getFamilyID();
    Family family = familyRepository.findByID(familyID);
    if (!family.isMyChild(parentID1, childID1)) {
        throw new IllegalArgumentException("Relation not founded");
    }
    return account.getBalance();
}


public MoneyValue checkAccountBalance(String accountID,String holderID){
    Email parseHolderID = new Email(holderID);
    Person holder = this.personRepository.findByID(parseHolderID);
    AccountID parseAccountID = new AccountID(UUID.fromString(accountID));
    if (!holder.isMyAccount(parseAccountID)) {
        throw new InvalidAccountOwner("Account does not belong to this holder");
    }
    Account account = this.accountRepository.findByID(parseAccountID);
    return account.getBalance();
}


public CashAccount createCashAccount(double cashAmount,String designation){
    MoneyValue amountValue = new MoneyValue(BigDecimal.valueOf(cashAmount));
    AccountDesignation accountCategoryDesignation = new AccountDesignation(designation);
    AccountID accountID = accountIDGenerator.generate();
    return (CashAccount) AccountFactory.createCashAccount(accountID, accountCategoryDesignation, amountValue);
}


public boolean checkUniqueDesignation(Designation designation,Person holder){
    AccountIDList accountIDs = holder.getAccountIDList();
    for (int index = 0; index < accountIDs.size(); index++) {
        Account account = accountRepository.findByID(accountIDs.getID(index));
        if (account.isSameDesignation(designation)) {
            return false;
        }
    }
    return true;
}


@Transactional(rollbackFor = Exception.class)
public AccountOutDTO addBankAccount(String designation,String personIDString,String accountType){
    Email parseHolderID = new Email(personIDString);
    AccountDesignation parseDesignation = new AccountDesignation(designation);
    Person person = this.personRepository.findByID(parseHolderID);
    if (!checkUniqueDesignation(parseDesignation, person)) {
        throw new AccountNotCreatedException("The account designation is already used");
    }
    AccountID bankAccountID = accountIDGenerator.generate();
    Account account = AccountFactory.createBankAccount(bankAccountID, parseDesignation, accountType);
    this.accountRepository.save(account);
    person.addAccountID(bankAccountID);
    this.personRepository.save(person);
    return new AccountOutDTO(bankAccountID.toString(), designation);
}


@Transactional(rollbackFor = Exception.class)
public PersonalCashAccountOutDTO createPersonalCashAccount(String personIDString,double cashAmount,String designation){
    Email personID = new Email(personIDString);
    Person person = personRepository.findByID(personID);
    CashAccount cashAccount = createCashAccount(cashAmount, designation);
    AccountID accountID = cashAccount.getID();
    person.addAccountID(accountID);
    this.accountRepository.save(cashAccount);
    this.personRepository.save(person);
    return new PersonalCashAccountOutDTO(cashAccount.getID().toString(), cashAccount.getDesignation().toString(), cashAccount.getBalance().toString());
}


public boolean hasAdminPermissionsOnTheAccount(Email adminID,AccountID accountID){
    Person admin = personRepository.findByID(adminID);
    FamilyID familyID = admin.getFamilyID();
    Family family = familyRepository.findByID(familyID);
    // Chek if I am the administrator
    if (!(family.isAdministrator(adminID))) {
        return false;
    }
    // Check if families cash account
    if (family.ownsCashAccount(accountID)) {
        return true;
    }
    // Check if relatives cash account
    PersonList relatives = personRepository.findByFamilyID(familyID);
    return relatives.ownsAccount(accountID);
}


public List<PersonalCashAccountOutDTO> getListOfAccounts(String personID){
    Email email = new Email(personID);
    Person person = this.personRepository.findByID(email);
    List<PersonalCashAccountOutDTO> list = new ArrayList<>();
    List<String> accountIDList = person.getAccountIDList().idToString();
    for (String accountId : accountIDList) {
        AccountID accountID = new AccountID(UUID.fromString(accountId));
        Account account = this.accountRepository.findByID(accountID);
        PersonalCashAccountOutDTO dto = new PersonalCashAccountOutDTO(account.getID().toString(), account.getDesignation().toString(), account.getBalance().toString());
        list.add(dto);
    }
    return list;
}


public FamilyCashAccountOutDTO getFamilyCashAccount(String username,String role){
    boolean hasAccess = this.authorizationService.accessFamilyCashAccountAuthorization(role);
    if (hasAccess) {
        // get family cash account ID trough person's familyID and family's cashAccountID
        Email adminID = this.authorizationService.getPersonIDOfUser(username);
        Person admin = this.personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();
        Family family = this.familyRepository.findByID(familyID);
        Optional<AccountID> familyCashAccountID = family.getCashAccountID();
        // see if the is already a cash account created
        if (familyCashAccountID.isPresent()) {
            Account account = this.accountRepository.findByID(familyCashAccountID.get());
            String balance = account.getBalance().toString();
            String designation = account.getDesignation().toString();
            return new FamilyCashAccountOutDTO(designation, balance);
        }
        throw new ElementNotFoundException("Cash account wasn't created yet.");
    }
    throw new AuthorizationServiceException("User is not the family administrator.");
}


@Transactional(rollbackFor = Exception.class)
public FamilyCashAccountOutDTO createFamilyCashAccount(String familyID,String adminID,double cashAmount,String designation){
    Email administratorID = new Email(adminID);
    FamilyID parseFamilyID = new FamilyID(UUID.fromString(familyID));
    Family family = familyRepository.findByID(parseFamilyID);
    if (!(family.isAdministrator(administratorID))) {
        throw new IllegalArgumentException("You don't have admin permission to create a Family Cash Account");
    }
    if (family.hasCashAccount()) {
        throw new IllegalArgumentException("This family already has an account");
    }
    CashAccount cashAccount = createCashAccount(cashAmount, designation);
    AccountID accountID = cashAccount.getID();
    family.addAccountID(accountID);
    this.accountRepository.save(cashAccount);
    this.familyRepository.save(family);
    return new FamilyCashAccountOutDTO(cashAccount.getDesignation().toString(), cashAccount.getBalance().toString());
}


public MoneyValue getCashAccountBalance(String adminId,String accountId){
    Email administratorID = new Email(adminId);
    AccountID cashAccountID = new AccountID(UUID.fromString(accountId));
    Account account = accountRepository.findByID(cashAccountID);
    if (!account.getAccountType().equals(Constants.CASH_ACCOUNT_TYPE)) {
        throw new IllegalArgumentException("Not a cash account");
    }
    if (hasAdminPermissionsOnTheAccount(administratorID, cashAccountID)) {
        return account.getBalance();
    }
    throw new IllegalArgumentException("Balance can not be checked");
}


}