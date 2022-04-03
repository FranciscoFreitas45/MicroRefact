package switchtwentytwenty.project.domain.share.transactiondata;
 import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.dddtype.ValueObject;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
public interface Transaction extends ValueObject{


public boolean isTransfer()
;

public CategoryID getCategoryID()
;

public MoneyValue getBalanceToThisDate()
;

public AccountID getOriginAccountID()
;

public TransactionDescription getDescription()
;

public boolean isPayment()
;

public MoneyValue getMyAmount(AccountID accountID)
;

public SystemDateEntry getSystemDateEntry()
;

public AccountID getDestinationAccountID()
;

public AccountID getAccountID()
;

public MovementType getMyMovementType(AccountID accountID)
;

public boolean isBetweenDates(TransactionDate startDate,TransactionDate endDate)
;

public boolean isFromAccount(AccountID accountID)
;

public TransactionDate getDate()
;

public MoneyValue getAmount()
;

}