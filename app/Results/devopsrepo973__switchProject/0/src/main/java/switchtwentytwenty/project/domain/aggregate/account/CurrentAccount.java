package switchtwentytwenty.project.domain.aggregate.account;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;
public class CurrentAccount extends BankAccount{

// Attribute
// Constructor methods
/**
 * Sole constructor
 *
 * @param accountDesignation - designation of the account
 */
public CurrentAccount(AccountID accountID, AccountDesignation accountDesignation) {
    super(accountID, accountDesignation);
}
@Override
public int hashCode(){
    return super.hashCode();
}


@Override
public boolean equals(Object o){
    return super.equals(o);
}


@Override
public String getAccountType(){
    return Constants.CURRENT_ACCOUNT_TYPE;
}


}