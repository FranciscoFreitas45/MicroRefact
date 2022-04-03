package switchtwentytwenty.project.domain.aggregate.account;
 import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;
import switchtwentytwenty.project.domain.share.dddtype.AggregateRoot;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.exception.DepositNotPossibleException;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import switchtwentytwenty.project.exception.WithdrawNotPossibleException;
public interface Account extends AggregateRoot<Account, AccountID>{


public boolean isSameDesignation(Designation designation)
;

public Designation getDesignation()
;

public MoneyValue getBalance()
;

public String getAccountType()
;

public MovementType deposit(MoneyValue amount)
;

public AccountID getID()
;

public boolean hasSameID(AccountID accountID)
;

public boolean isCashAccount()
;

public MovementType withdraw(MoneyValue amount)
;

}