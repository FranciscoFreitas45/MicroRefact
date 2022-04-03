package switchtwentytwenty.project.domain.aggregate.account;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.Designation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.transactiondata.MovementType;
import java.math.BigDecimal;
import java.util.Objects;
import switchtwentytwenty.project.Interface.MoneyValue;
public class BankAccount implements Account{

 private  RootAccount account;

 private  MoneyValue balance;

// Constructor Methods
/**
 * Sole constructor
 *
 * @param accountID          - id number
 * @param accountDesignation - account designation
 */
public BankAccount(AccountID accountID, Designation accountDesignation) {
    this.account = new RootAccount(accountID, accountDesignation);
    this.balance = new MoneyValue(new BigDecimal(0));
}
@Override
public boolean isSameDesignation(Designation designation){
    return this.account.isSameDesignation(designation);
}


@Override
public Designation getDesignation(){
    return this.account.getDesignation();
}


@Override
public MoneyValue getBalance(){
    return this.balance;
}


@Override
public int hashCode(){
    return Objects.hash(account);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    BankAccount that = (BankAccount) o;
    return Objects.equals(account, that.account);
}


@Override
public MovementType deposit(MoneyValue amount){
    throw new UnsupportedOperationException(Constants.UNSUPPORTED_OPERATION);
}


@Override
public AccountID getID(){
    return this.account.getId();
}


@Override
public boolean hasSameID(AccountID accountID){
    return this.account.hasSameID(accountID);
}


@Override
public boolean isCashAccount(){
    return false;
}


@Override
public boolean sameValueAs(Account other){
    return this.account.sameValueAs(other);
}


@Override
public MovementType withdraw(MoneyValue amount){
    throw new UnsupportedOperationException(Constants.UNSUPPORTED_OPERATION);
}


}