package switchtwentytwenty.project.domain.share.transactiondata;
 import switchtwentytwenty.project.dto.todomaindto.TransferInformationDTO;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.dto.todomaindto.TransferAssemblerDTO;
import java.text.ParseException;
public class Transfer implements Transaction{

 private  Movement debit;

 private  Movement credit;

 private  BaseTransaction baseTransaction;

// Constructor Method
/**
 * Constructor for creation of new Transfer.
 *
 * @param dto - data transfer object
 * @param balance - account balance after transaction
 * @throws ParseException - read
 */
public Transfer(TransferInformationDTO dto, MoneyValue balance) throws ParseException {
    this.debit = createMovement(dto.getOriginAccountID(), dto.getDebit(), dto.getAmount());
    this.credit = createMovement(dto.getDestinationAccountID(), dto.getCredit(), dto.getAmount());
    this.baseTransaction = new BaseTransaction(dto.getCategoryID(), dto.getDescription(), dto.getDate(), balance, null);
}/**
 * Constructor for transaction between Data model and Domain model.
 *
 * @param dto - data transfer object
 * @param balance - account balance after transaction
 * @throws ParseException - read
 */
public Transfer(TransferAssemblerDTO dto, MoneyValue balance) throws ParseException {
    this.debit = createMovement(dto.getOriginAccountID(), dto.getDebit(), dto.getAmount());
    this.credit = createMovement(dto.getDestinationAccountID(), dto.getCredit(), dto.getAmount());
    this.baseTransaction = new BaseTransaction(dto.getCategoryID(), dto.getDescription(), dto.getDate(), balance, dto.getSystemDateEntry());
}
public Movement createMovement(AccountID accountID,MovementType type,MoneyValue amount){
    return new Movement(accountID, type, amount);
}


public boolean isTransfer(){
    return true;
}


@Override
public CategoryID getCategoryID(){
    return this.baseTransaction.getCategoryID();
}


@Override
public MoneyValue getBalanceToThisDate(){
    return this.baseTransaction.getBalance();
}


public AccountID getOriginAccountID(){
    return this.debit.getAccountID();
}


@Override
public TransactionDescription getDescription(){
    return this.baseTransaction.getDescription();
}


public boolean isPayment(){
    return false;
}


@Override
public MoneyValue getMyAmount(AccountID accountID){
    if (this.debit.isFromAccount(accountID)) {
        return this.debit.getAmount();
    }
    if (this.credit.isFromAccount(accountID)) {
        return this.credit.getAmount();
    }
    throw new IllegalArgumentException("Movements are not from this account");
}


public AccountID getDestinationAccountID(){
    return this.credit.getAccountID();
}


public SystemDateEntry getSystemDateEntry(){
    return this.baseTransaction.getSystemDateEntry();
}


public MoneyValue getBalance(){
    return this.baseTransaction.getBalance();
}


@Override
public AccountID getAccountID(){
    throw new UnsupportedOperationException();
}


@Override
public MovementType getMyMovementType(AccountID accountID){
    if (this.debit.isFromAccount(accountID)) {
        return this.debit.getType();
    }
    if (this.credit.isFromAccount(accountID)) {
        return this.credit.getType();
    }
    throw new IllegalArgumentException("Movements are not from this account");
}


public boolean isBetweenDates(TransactionDate startDate,TransactionDate endDate){
    return this.baseTransaction.isBetweenDates(startDate, endDate);
}


public boolean isFromAccount(AccountID accountID){
    return this.debit.isFromAccount(accountID) || this.credit.isFromAccount(accountID);
}


@Override
public TransactionDate getDate(){
    return this.baseTransaction.date;
}


public MoneyValue getAmount(){
    return this.credit.getAmount();
}


}