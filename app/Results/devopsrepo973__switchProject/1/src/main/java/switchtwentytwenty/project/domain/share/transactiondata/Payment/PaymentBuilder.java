package switchtwentytwenty.project.domain.share.transactiondata.Payment;
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
public class PaymentBuilder {

 private  Payment payment;

// Constructor methods
/**
 * Sole Constructor
 */
public PaymentBuilder() {
    payment = new Payment();
}
public Payment build(){
    Objects.requireNonNull(this.payment.getBalanceToThisDate());
    Objects.requireNonNull(this.payment.getBaseTransaction());
    return payment;
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


}