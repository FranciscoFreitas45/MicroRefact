package switchtwentytwenty.project.domain.aggregate.account;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;
import switchtwentytwenty.project.exception.AccountNotCreatedException;
import switchtwentytwenty.project.exception.DepositNotPossibleException;
import switchtwentytwenty.project.exception.InvalidMovementTypeException;
import switchtwentytwenty.project.exception.WithdrawNotPossibleException;
import java.util.Objects;
import switchtwentytwenty.project.Interface.MoneyValue;
import switchtwentytwenty.project.DTO.MoneyValue;
public class CashAccount implements Account{

 private  RootAccount rootAccount;

 private  MoneyValue cashAmount;

// Constructor methods
/**
 * Sole constructor
 *
 * @param initialAmount - initial amount
 * @param accountDesignation   - designation of the account
 */
protected CashAccount(AccountID cashAccountID, MoneyValue initialAmount, Designation accountDesignation) throws AccountNotCreatedException {
    if (!initialAmount.isPositiveOrZero()) {
        throw new AccountNotCreatedException("Cash Account can't have a negative value.");
    }
    this.cashAmount = initialAmount;
    this.rootAccount = new RootAccount(cashAccountID, accountDesignation);
}
@Override
public boolean isSameDesignation(Designation designation){
    return this.rootAccount.isSameDesignation(designation);
}


@Override
public Designation getDesignation(){
    return this.rootAccount.getDesignation();
}


@Override
public MoneyValue getBalance(){
    return this.cashAmount;
}


@Override
public int hashCode(){
    return Objects.hash(rootAccount);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    CashAccount that = (CashAccount) o;
    return Objects.equals(rootAccount, that.rootAccount);
}


@Override
public String getAccountType(){
    return Constants.CASH_ACCOUNT_TYPE;
}


@Override
public MovementType deposit(MoneyValue amount){
    if (!amount.isPositiveOrZero()) {
        throw new DepositNotPossibleException("Can not deposit negative amount");
    }
    this.cashAmount = this.cashAmount.sum(amount);
    return new MovementType(Constants.CREDIT);
}


@Override
public AccountID getID(){
    return this.rootAccount.getId();
}


@Override
public boolean hasSameID(AccountID accountID){
    return this.rootAccount.hasSameID(accountID);
}


@Override
public boolean isCashAccount(){
    return true;
}


@Override
public boolean sameValueAs(Account other){
    return this.rootAccount.sameValueAs(other);
}


@Override
public MovementType withdraw(MoneyValue amount){
    if (this.cashAmount.compare(amount) < 0) {
        throw new WithdrawNotPossibleException("Not enough money.");
    }
    if (!amount.isPositiveOrZero()) {
        throw new WithdrawNotPossibleException("Invalid money. Amount must be positive.");
    }
    this.cashAmount = this.cashAmount.subtract(amount);
    return new MovementType(Constants.DEBIT);
}


}