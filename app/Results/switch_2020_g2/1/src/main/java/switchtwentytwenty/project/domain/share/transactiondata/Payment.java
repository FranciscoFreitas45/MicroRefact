package switchtwentytwenty.project.domain.share.transactiondata;
 import lombok.AccessLevel;
import lombok.Getter;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import java.text.ParseException;
import java.util.Objects;
public class Payment implements Transaction{

@Getter(AccessLevel.PROTECTED)
 private  Movement debitMovement;

@Getter(AccessLevel.PROTECTED)
 private  BaseTransaction baseTransaction;

 private  Payment payment;

// Constructor Method
/**
 * Sole Constructor
 */
private Payment() {
}
public boolean isTransfer(){
    return false;
}


@Override
public CategoryID getCategoryID(){
    return this.baseTransaction.getCategoryID();
}


@Override
public AccountID getOriginAccountID(){
    throw new UnsupportedOperationException();
}


@Override
public MoneyValue getBalanceToThisDate(){
    return this.baseTransaction.balance;
}


@Override
public TransactionDescription getDescription(){
    return this.baseTransaction.description;
}


@Override
public MoneyValue getMyAmount(AccountID accountID){
    if (this.debitMovement.isFromAccount(accountID)) {
        return this.debitMovement.getAmount();
    }
    throw new IllegalArgumentException("Movement not from this account");
}


public MovementType getMovementType(){
    return this.debitMovement.getType();
}


public boolean isPayment(){
    return true;
}


@Override
public SystemDateEntry getSystemDateEntry(){
    return this.baseTransaction.getSystemDateEntry();
}


@Override
public AccountID getDestinationAccountID(){
    throw new UnsupportedOperationException();
}


@Override
public AccountID getAccountID(){
    return this.debitMovement.getAccountID();
}


public Payment build(){
    Objects.requireNonNull(this.payment.getBalanceToThisDate());
    Objects.requireNonNull(this.payment.getBaseTransaction());
    return payment;
}


@Override
public MovementType getMyMovementType(AccountID accountID){
    if (this.debitMovement.isFromAccount(accountID)) {
        return this.debitMovement.getType();
    }
    throw new IllegalArgumentException("Movement not from this account");
}


public boolean isBetweenDates(TransactionDate startDate,TransactionDate endDate){
    return this.baseTransaction.isBetweenDates(startDate, endDate);
}


public boolean isFromAccount(AccountID accountID){
    return this.debitMovement.isFromAccount(accountID);
}


@Override
public TransactionDate getDate(){
    return this.baseTransaction.getDate();
}


public PaymentBuilder withDebitMovement(AccountID accountID,MoneyValue amount){
    MovementType type = new MovementType(Constants.DEBIT);
    this.payment.debitMovement = new Movement(accountID, type, amount);
    return this;
}


public PaymentBuilder withBaseTransaction(CategoryID categoryID,TransactionDescription description,TransactionDate date,MoneyValue balance,SystemDateEntry systemDateEntry){
    this.payment.baseTransaction = new BaseTransaction(categoryID, description, date, balance, systemDateEntry);
    return this;
}


@Override
public MoneyValue getAmount(){
    return this.debitMovement.getAmount();
}


}