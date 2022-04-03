package switchtwentytwenty.project.applicationservice.appservice.implappservice;
 import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ITransactionService;
import switchtwentytwenty.project.applicationservice.irepository;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.domaindto.TransferDomainDTO;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id;
import switchtwentytwenty.project.domain.share.transactiondata;
import switchtwentytwenty.project.dto.outdto.PaymentOutDTO;
import switchtwentytwenty.project.dto.outdto.TransferOutDTO;
import switchtwentytwenty.project.dto.todomaindto.TransferInformationDTO;
import switchtwentytwenty.project.dto.toservicedto.PaymentDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTOMapper;
import switchtwentytwenty.project.exception;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.UUID;
import switchtwentytwenty.project.Interface.IPersonRepository;
import switchtwentytwenty.project.Interface.IFamilyRepository;
import switchtwentytwenty.project.Interface.IAccountRepository;
import switchtwentytwenty.project.Interface.ICategoryRepository;
import switchtwentytwenty.project.Interface.IAuthorizationService;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.Family;
import switchtwentytwenty.project.DTO.Person;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.FamilyID;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.Category;
import switchtwentytwenty.project.DTO.Account;
import switchtwentytwenty.project.DTO.AccountID;
@Service
@AllArgsConstructor
public class TransactionService implements ITransactionService{

@Autowired
 private  IPersonRepository personRepository;

@Autowired
 private  IFamilyRepository familyRepository;

@Autowired
 private  IAccountRepository accountRepository;

@Autowired
 private  ILedgerRepository ledgerRepository;

@Autowired
 private  ICategoryRepository categoryRepository;

@Autowired
 private  IAuthorizationService authorizationService;


public Ledger getSenderLedger(String senderIDString){
    Ledger senderLedger;
    try {
        Email senderID = new Email(senderIDString);
        Person sender = personRepository.findByID(senderID);
        senderLedger = ledgerRepository.findByID(sender.getLedgerID());
    } catch (InvalidEmailException | ElementNotFoundException exception) {
        FamilyID senderID = new FamilyID(UUID.fromString(senderIDString));
        Family sender = familyRepository.findByID(senderID);
        senderLedger = ledgerRepository.findByID(sender.getLedgerID());
    }
    return senderLedger;
}


public MovementType debitMovement(Account account,MoneyValue money){
    MovementType debit = new MovementType(Constants.DEBIT);
    if (account.isCashAccount()) {
        debit = account.withdraw(money);
    }
    return debit;
}


@Transactional(rollbackFor = Exception.class)
public TransferOutDTO transferBetweenCashAccounts(TransferDTO transferDTO){
    TransferDomainDTO transferDomainDTO;
    transferDomainDTO = TransferDTOMapper.mapToTransferDomainDTO(transferDTO);
    TransferOutDTO transferOutDTO;
    try {
        // See if the sender id is from a person or a family.
        new Email(transferDomainDTO.getSenderID());
        transferOutDTO = transferBetweenMembers(transferDomainDTO);
    } catch (ElementNotFoundException | InvalidEmailException exception) {
        transferOutDTO = transferBetweenFamilyAndMember(transferDomainDTO);
    }
    return transferOutDTO;
}


public boolean areAccountHolders(TransferDomainDTO transferDomainDTO){
    boolean result;
    try {
        Email senderID = new Email(transferDomainDTO.getSenderID());
        Person sender = personRepository.findByID(senderID);
        Person receiver = personRepository.findByID(transferDomainDTO.getReceiverID());
        AccountID originAccountID = transferDomainDTO.getOriginAccountID();
        AccountID destinationAccountID = transferDomainDTO.getDestinationAccountID();
        result = sender.isMyAccount(originAccountID) && receiver.isMyAccount(destinationAccountID);
    } catch (InvalidEmailException | ElementNotFoundException exception) {
        Family sender = familyRepository.findByID(new FamilyID(UUID.fromString(transferDomainDTO.getSenderID())));
        Person receiver = personRepository.findByID(transferDomainDTO.getReceiverID());
        AccountID originAccountID = transferDomainDTO.getOriginAccountID();
        AccountID destinationAccountID = transferDomainDTO.getDestinationAccountID();
        result = sender.isMyAccount(originAccountID) && receiver.isMyAccount(destinationAccountID);
    }
    return result;
}


public boolean areCashAccounts(AccountID sender,AccountID receiver){
    return accountRepository.findByID(sender).isCashAccount() && accountRepository.findByID(receiver).isCashAccount();
}


public MoneyValue balanceAfterTransaction(Account account,MoneyValue transactionAmount){
    MoneyValue currentAccountBalance = account.getBalance();
    MoneyValue balance;
    balance = currentAccountBalance.subtract(transactionAmount);
    return balance;
}


public MovementType creditMovement(Account account,MoneyValue money){
    MovementType credit = new MovementType(Constants.CREDIT);
    if (account.isCashAccount()) {
        credit = account.deposit(money);
    }
    return credit;
}


public boolean areFromSameFamily(Email senderID,Email receiverID){
    Person sender = personRepository.findByID(senderID);
    Person receiver = personRepository.findByID(receiverID);
    FamilyID senderFamilyID = sender.getFamilyID();
    FamilyID receiverFamilyID = receiver.getFamilyID();
    return senderFamilyID.equals(receiverFamilyID);
}


@NotNull
public TransferOutDTO transferBetweenFamilyAndMember(TransferDomainDTO transferBetweenFamilyAndMemberDTO){
    FamilyID familyID = new FamilyID(UUID.fromString(transferBetweenFamilyAndMemberDTO.getSenderID()));
    Email receiverID = transferBetweenFamilyAndMemberDTO.getReceiverID();
    if (isMemberFromFamily(familyID, receiverID) && areAccountHolders(transferBetweenFamilyAndMemberDTO)) {
        transferBetweenTwoCashAccounts(transferBetweenFamilyAndMemberDTO);
        return new TransferOutDTO(transferBetweenFamilyAndMemberDTO.getOriginAccountID().toString(), transferBetweenFamilyAndMemberDTO.getDestinationAccountID().toString(), transferBetweenFamilyAndMemberDTO.getDate().toString(), transferBetweenFamilyAndMemberDTO.getAmount().toDouble());
    } else {
        throw new IllegalArgumentException("Is not possible to make this transfer");
    }
}


public void transferBetweenTwoCashAccounts(TransferDomainDTO transferDomainDTO){
    Ledger senderLedger = getSenderLedger(transferDomainDTO.getSenderID());
    Person receiver = personRepository.findByID(transferDomainDTO.getReceiverID());
    LedgerID receiverLedgerID = receiver.getLedgerID();
    Ledger receiverLedger = ledgerRepository.findByID(receiverLedgerID);
    Account originAccount = accountRepository.findByID(transferDomainDTO.getOriginAccountID());
    Account destinationAccount = accountRepository.findByID(transferDomainDTO.getDestinationAccountID());
    CategoryID categoryID = transferDomainDTO.getCategoryID();
    if (areCashAccounts(transferDomainDTO.getOriginAccountID(), transferDomainDTO.getDestinationAccountID())) {
        MovementType debit = debitMovement(originAccount, transferDomainDTO.getAmount());
        MovementType credit = creditMovement(destinationAccount, transferDomainDTO.getAmount());
        TransferInformationDTO dto = new TransferInformationDTO.TransferInformationDTOBuilder().withAmount(transferDomainDTO.getAmount()).withCategory(categoryID).withCredit(credit).withDate(transferDomainDTO.getDate()).withDebit(debit).withDescription(transferDomainDTO.getDescription()).withDestinationAccountID(transferDomainDTO.getDestinationAccountID()).withOriginAccountID(transferDomainDTO.getOriginAccountID()).build();
        Transaction transferSenderAccount = new Transfer(dto, originAccount.getBalance());
        senderLedger.addTransaction(transferSenderAccount);
        ledgerRepository.save(senderLedger);
        accountRepository.save(originAccount);
        Transaction transferReceiverAccount = new Transfer(dto, destinationAccount.getBalance());
        receiverLedger.addTransaction(transferReceiverAccount);
        ledgerRepository.save(receiverLedger);
        accountRepository.save(destinationAccount);
    } else {
        throw new UnsupportedOperationException("Not cash account");
    }
}


@NotNull
public TransferOutDTO transferBetweenMembers(TransferDomainDTO transferBetweenMemberAndMemberDTO){
    Email senderID = new Email(transferBetweenMemberAndMemberDTO.getSenderID());
    Email receiverID = transferBetweenMemberAndMemberDTO.getReceiverID();
    if (areFromSameFamily(senderID, receiverID) && areAccountHolders(transferBetweenMemberAndMemberDTO)) {
        transferBetweenTwoCashAccounts(transferBetweenMemberAndMemberDTO);
        return new TransferOutDTO(transferBetweenMemberAndMemberDTO.getOriginAccountID().toString(), transferBetweenMemberAndMemberDTO.getDestinationAccountID().toString(), transferBetweenMemberAndMemberDTO.getDate().toString(), transferBetweenMemberAndMemberDTO.getAmount().toDouble());
    } else {
        throw new IllegalArgumentException("Is not possible to make this transfer");
    }
}


@Transactional(rollbackFor = Exception.class)
public PaymentOutDTO addPaymentTransaction(PaymentDTO dto,String user){
    if (authorizationService.accessAccountAuthorization(user, dto.getAccountID())) {
        Email personID = authorizationService.getPersonIDOfUser(user);
        AccountID accountID = new AccountID(UUID.fromString(dto.getAccountID()));
        Account account = accountRepository.findByID(accountID);
        CategoryID categoryID = new CategoryID(dto.getCategoryID());
        Person person = personRepository.findByID(personID);
        LedgerID ledgerID = person.getLedgerID();
        Ledger personLedger = ledgerRepository.findByID(ledgerID);
        Category category = categoryRepository.findByID(categoryID);
        if (account.isCashAccount() && category.belongsToFamily(person.getFamilyID())) {
            MoneyValue amount = new MoneyValue(BigDecimal.valueOf(dto.getAmount()));
            TransactionDate date = new TransactionDate(dto.getDate());
            TransactionDescription description = new TransactionDescription(dto.getDesignation());
            MoneyValue balance = balanceAfterTransaction(account, amount);
            Transaction payment = new Payment.PaymentBuilder().withBaseTransaction(categoryID, description, date, balance, null).withDebitMovement(accountID, amount).build();
            account.withdraw(amount);
            personLedger.addTransaction(payment);
            accountRepository.save(account);
            ledgerRepository.save(personLedger);
            return new PaymentOutDTO(description.toString(), accountID.toString(), category.toString(), amount.toDouble(), date.toString());
        }
        throw new UnsupportedOperationException("The account isn't a cash account");
    }
    throw new UnsupportedOperationException();
}


public boolean isMemberFromFamily(FamilyID familyID,Email memberID){
    return familyID.equals(personRepository.findByID(memberID).getFamilyID());
}


}