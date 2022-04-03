package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerService;
import switchtwentytwenty.project.applicationservice.irepository;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;
import switchtwentytwenty.project.domain.share.transactiondata.TransactionDate;
import switchtwentytwenty.project.dto.outdto.LedgerMovementOutDTO;
import switchtwentytwenty.project.dto.outdto.LedgerMovementOutMapper;
import switchtwentytwenty.project.dto.outdto.MovementOutDTO;
import switchtwentytwenty.project.dto.outdto.MovementOutDTOMapper;
import switchtwentytwenty.project.exception;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import switchtwentytwenty.project.Interface.IPersonRepository;
import switchtwentytwenty.project.Interface.ILedgerRepository;
import switchtwentytwenty.project.Interface.IAccountRepository;
import switchtwentytwenty.project.Interface.ICategoryRepository;
import switchtwentytwenty.project.Interface.IFamilyRepository;
import switchtwentytwenty.project.Interface.IAuthorizationService;
import switchtwentytwenty.project.DTO.Ledger;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Category;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Family;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
import switchtwentytwenty.project.DTO.Transaction;
@Service
@AllArgsConstructor
public class LedgerService implements ILedgerService{

@Autowired
 private  IPersonRepository personRepository;

@Autowired
 private  ILedgerRepository ledgerRepository;

@Autowired
 private  IAccountRepository accountRepository;

@Autowired
 private  ICategoryRepository categoryRepository;

@Autowired
 private  IFamilyRepository familyRepository;

@Autowired
 private  IAuthorizationService authorizationService;


public List<LedgerMovementOutDTO> getListOfAllMovements(Ledger ledger,List<AccountID> holderAccounts){
    List<Transaction> list = ledger.getTransactionList();
    List<LedgerMovementOutDTO> result = new ArrayList<>();
    for (Transaction transaction : list) {
        String origin = getSenderAccount(transaction);
        String destination = getReceiverAccount(transaction);
        String familyMember = getFamilyMember(holderAccounts, transaction);
        String type = getTransactionType(holderAccounts, transaction);
        String category = getTransactionCategory(transaction);
        LedgerMovementOutDTO dto = LedgerMovementOutMapper.toDTO(transaction, familyMember, origin, destination, type, category);
        result.add(dto);
    }
    return result;
}


public String getTransactionCategory(Transaction transaction){
    Category category = categoryRepository.findByID(transaction.getCategoryID());
    Designation categoryDesignation = category.getDesignation();
    return categoryDesignation.toString();
}


public String getFamilyMember(List<AccountID> holderAccounts,Transaction transaction){
    String familyMember = null;
    if (transaction.isTransfer()) {
        AccountID originAccountID = transaction.getOriginAccountID();
        AccountID destinationAccountID = transaction.getDestinationAccountID();
        if (holderAccounts.contains(originAccountID)) {
            try {
                familyMember = personRepository.findByAccountID(destinationAccountID).getName().toString();
            } catch (ElementNotFoundException exception) {
                return null;
            }
        } else {
            try {
                familyMember = personRepository.findByAccountID(originAccountID).getName().toString();
            } catch (ElementNotFoundException exception) {
                return null;
            }
        }
    }
    return familyMember;
}


public String getTransactionType(List<AccountID> holderAccounts,Transaction transaction){
    String type;
    if (transaction.isTransfer()) {
        AccountID originAccountID = transaction.getOriginAccountID();
        if (holderAccounts.contains(originAccountID)) {
            type = "DEBIT_TRANSFER";
        } else {
            type = "CREDIT_TRANSFER";
        }
    } else {
        type = "PAYMENT";
    }
    return type;
}


public List<LedgerMovementOutDTO> getListOfFamilyLedgerMovements(String familyID){
    FamilyID parseFamilyID = new FamilyID(UUID.fromString(familyID));
    Family family = familyRepository.findByID(parseFamilyID);
    Ledger ledger = ledgerRepository.findByID(family.getLedgerID());
    List<AccountID> holderAccounts = new ArrayList<>();
    Optional<AccountID> familyCashAccount = family.getCashAccountID();
    if (!familyCashAccount.isPresent()) {
        throw new InvalidEmailException("Family without cash account");
    }
    holderAccounts.add(familyCashAccount.get());
    return getListOfAllMovements(ledger, holderAccounts);
}


public String getSenderAccount(Transaction transaction){
    String origin;
    if (transaction.isTransfer()) {
        AccountID originAccountID = transaction.getOriginAccountID();
        origin = accountRepository.findByID(originAccountID).getDesignation().toString();
    } else {
        origin = accountRepository.findByID(transaction.getAccountID()).getDesignation().toString();
    }
    return origin;
}


public String getReceiverAccount(Transaction transaction){
    String destination = null;
    if (transaction.isTransfer()) {
        AccountID destinationAccountID = transaction.getDestinationAccountID();
        destination = accountRepository.findByID(destinationAccountID).getDesignation().toString();
    }
    return destination;
}


@Override
public List<MovementOutDTO> getListOfMovementsBetweenDates(String username,String accountIDString,String startDateString,String endDateString){
    if (authorizationService.accessAccountAuthorization(username, accountIDString)) {
        // Authorization
        // initialize value objects
        Email personID = authorizationService.getPersonIDOfUser(username);
        Person accountHolder = personRepository.findByID(personID);
        AccountID accountID = new AccountID(UUID.fromString(accountIDString));
        TransactionDate startDate = new TransactionDate(startDateString);
        TransactionDate endDate = new TransactionDate(endDateString);
        LedgerID myLedgerID = accountHolder.getLedgerID();
        Ledger myLedger = ledgerRepository.findByID(myLedgerID);
        List<Transaction> transactionBetweenDates = myLedger.getListOfMovementsBetweenDates(accountID, startDate, endDate);
        return MovementOutDTOMapper.toDTOList(transactionBetweenDates, accountID);
    } else {
        throw new IllegalArgumentException("Invalid account ID.");
    }
}


public List<LedgerMovementOutDTO> getListOfPersonLedgerMovements(String personID){
    Email parsePersonID = new Email(personID);
    Person holder = personRepository.findByID(parsePersonID);
    Ledger ledger = ledgerRepository.findByID(holder.getLedgerID());
    List<AccountID> holderAccounts = holder.getAccountIDList().getList();
    return getListOfAllMovements(ledger, holderAccounts);
}


}