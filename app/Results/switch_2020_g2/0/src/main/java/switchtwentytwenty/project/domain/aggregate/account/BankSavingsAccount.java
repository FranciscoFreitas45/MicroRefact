package switchtwentytwenty.project.domain.aggregate.account;
 import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;
public class BankSavingsAccount extends BankAccount{

// Attribute
// Constructor Method
/**
 * Sole constructor
 *
 * @param accountDesignation - designation of the account
 */
public BankSavingsAccount(AccountID accountID, AccountDesignation accountDesignation) {
    super(accountID, accountDesignation);
}
@Override
public String getAccountType(){
    return Constants.BANK_SAVINGS_ACCOUNT_TYPE;
}


}